package com.coindesk.demo;

import com.coindesk.demo.controller.CoindeskController;
import com.coindesk.demo.dto.Coindesk;
import com.coindesk.demo.dto.CoindeskDetail;
import com.coindesk.demo.jpa.GenericSpecification;
import com.coindesk.demo.jpa.SearchCriteria;
import com.coindesk.demo.repostiory.CoindeskDetailRepository;
import com.coindesk.demo.repostiory.CoindeskRepository;
import com.coindesk.demo.searchParam.CoindeskSearchParam;
import com.coindesk.demo.service.CoindeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CoindeskDetailApplicationTests {

//    @InjectMocks
    @Mock
    private CoindeskService coindeskService;
    @Mock
    private CoindeskDetailRepository coindeskDetailRepository;
    @Mock
    private CoindeskRepository coindeskRepository;

    @Autowired
    CoindeskController controller;

    private MockMvc mockMvc;

    private List<CoindeskDetail> coindeskDetails;
    private List<Coindesk> coindesks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.controller).build();
        coindeskDetails = new ArrayList<>();
        CoindeskDetail testDetail = new CoindeskDetail();
        testDetail.setCode("USD");
        testDetail.setSymbol("&#36;");
        testDetail.setRate("94,783.51");
        testDetail.setDescription("United States Dollar");
        testDetail.setUpdateDate(new Date());
        testDetail.setRateFloat(94783.51f);
        coindesks = new ArrayList<>();
        Coindesk coindesk = new Coindesk();
        coindesk.setCcyCode("USD");
        coindesk.setName("美元");
        coindesk.setCoindesk(testDetail);
//        when(coindeskDetailRepository.save(any(CoindeskDetail.class))).thenReturn(testDetail);
        coindeskDetails.add(testDetail);
    }
//    @Test
    public void testSave() {

        Coindesk coindesk = new Coindesk();
        coindesk.setCcyCode("USD");
        coindesk.setName("美元");

        CoindeskDetail coindeskDetail = new CoindeskDetail();
        coindeskDetail.setCode("USD");
        coindeskDetail.setSymbol("&#36;");
        coindeskDetail.setRate("94,783.51");
        coindeskDetail.setDescription("United States Dollar");
        coindeskDetail.setUpdateDate(new Date());
        coindeskDetail.setRateFloat(94783.51f);
        coindesk.setCoindesk(coindeskDetail);

        when(coindeskDetailRepository.save(any(CoindeskDetail.class))).thenAnswer(invocation -> invocation.getArgument(0));
        CoindeskDetail createdCoindeskDetail = coindeskService.save(coindeskDetail);
        assertNotNull(createdCoindeskDetail);
    }

    @Test
    public void testSaveApi() throws Exception {
        CoindeskSearchParam param = new CoindeskSearchParam();
        param.setCode("USD");
        when(coindeskRepository.findByCode(param.getCode())).thenReturn(coindesks);
        when(this.coindeskService.searchDetail(param)).thenReturn(coindeskDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String searchParam = objectMapper.writeValueAsString(param);
        mockMvc.perform(get("/coindesk/searchDetail").contentType(MediaType.APPLICATION_JSON).content(searchParam))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].code", is("USD")));
    }

//    @Test
    public void testUpdateApi() throws Exception {
        CoindeskSearchParam param = new CoindeskSearchParam();
        param.setCode("USD");
        param.setUpdateRate(23333.33f);
        String code = "USD";
        Float rateFloat = 23333.33f;
        Integer updateCount = 1;
        GenericSpecification<CoindeskDetail> specification = new GenericSpecification<>();
        specification.add(new SearchCriteria("code", ":", code));
        when(coindeskDetailRepository.findAll(any(Specification.class))).thenReturn(coindeskDetails);
        when(coindeskService.updateCoinDeskRate(code,rateFloat)).thenReturn(updateCount);
        ObjectMapper objectMapper = new ObjectMapper();
        String searchParam = objectMapper.writeValueAsString(param);
        mockMvc.perform(put("/coindesk/update").contentType(MediaType.APPLICATION_JSON).content(searchParam))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(updateCount))); // 驗證返回的更新記錄數量
    }

//    @Test
    public void DeleteApi() throws Exception{
        CoindeskSearchParam param = new CoindeskSearchParam();
        param.setCode("USD");
        ObjectMapper objectMapper = new ObjectMapper();
        String searchParam = objectMapper.writeValueAsString(param);
        doNothing().when(coindeskService).deleteByCode(param);
        mockMvc.perform(delete("/coindesk/delete").contentType(MediaType.APPLICATION_JSON).content(searchParam))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}

