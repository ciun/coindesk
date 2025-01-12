package com.coindesk.demo.controller;

import com.coindesk.demo.dto.Coindesk;
import com.coindesk.demo.dto.CoindeskDetail;
import com.coindesk.demo.jpa.GenericSpecification;
import com.coindesk.demo.searchParam.CoindeskSearchParam;
import com.coindesk.demo.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;


    @GetMapping("/searchDetail")
    public List<CoindeskDetail> search(@RequestBody CoindeskSearchParam param) {
        GenericSpecification<CoindeskDetail> specification = new GenericSpecification<>();
        return  coindeskService.searchDetail(param);

    }

    /*
     * 儲存,修改
     */
    @PutMapping("/save")
    public CoindeskDetail save(@RequestBody CoindeskDetail coindeskDetail) {
        return coindeskService.save(coindeskDetail);
    }

    @GetMapping("/findAll")
    public List<CoindeskDetail> findAll() {
        return coindeskService.findAll();
    }

    /*
     {
      "GBP": {
            "code": "GBP",
            "symbol": "&pound;",
            "rate": "76,489.731",
            "description": "",
            "rate_float": 76489.734
        }
     },格式
     */
    @GetMapping("/search")
    public List<Coindesk> searchCcy(@RequestBody CoindeskSearchParam param) {
        return coindeskService.search(param);
    }

    /*
     * 修改匯率
     */
    @PutMapping("/update")
    public Integer update (@RequestBody CoindeskSearchParam param) {
        return coindeskService.updateCoinDeskRate(param.getCode(), param.getUpdateRate());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody CoindeskSearchParam param) {
        coindeskService.deleteByCode(param);
        return ResponseEntity.noContent().build();
    }

}
