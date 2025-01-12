package com.coindesk.demo.service;

import com.coindesk.demo.dto.Coindesk;
import com.coindesk.demo.dto.CoindeskDetail;
import com.coindesk.demo.repostiory.CoindeskRepository;
import com.coindesk.demo.repostiory.CoindeskDetailRepository;
import com.coindesk.demo.jpa.GenericSpecification;
import com.coindesk.demo.jpa.SearchCriteria;
import com.coindesk.demo.searchParam.CoindeskSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CoindeskService {

    @Autowired
    private CoindeskDetailRepository coindeskDetailRepository;

    @Autowired
    private CoindeskRepository coindeskRepository;

    public CoindeskDetail save(CoindeskDetail coindeskDetail) {
        List<Coindesk> coindesk = coindeskRepository.findByCode(coindeskDetail.getCode());
        coindeskDetail.setRate(String.format("%,.2f", null == coindeskDetail.getRateFloat() ? 0 : coindeskDetail.getRateFloat()));
        coindeskDetail.setUpdateDate(new Date());

        return coindeskDetailRepository.save(coindeskDetail);
    }

    public List<CoindeskDetail> findAll() {
        return coindeskDetailRepository.findAll();
    }

    public void remove(String code) {
        coindeskDetailRepository.deleteByCode(code);
    }

    public List<CoindeskDetail> searchDetail(CoindeskSearchParam param) {
        try {
            GenericSpecification<CoindeskDetail> specification = new GenericSpecification<>();
            if (param.getCode() != null) {
                specification.add(new SearchCriteria("code", ":", param.getCode()));
            }
            if (param.getRateFloatGt() != null) {
                specification.add(new SearchCriteria("rate_float", ">", param.getRateFloatGt()));
            }
            if (param.getRateFloatLt() != null) {
                specification.add(new SearchCriteria("rate_float", "<", param.getRateFloatLt()));
            }

            return coindeskDetailRepository.findAll(Specification.where(specification));
        } catch (Exception e) {
            throw new RuntimeException("Invalid searchParam format", e);
        }
    }

    @Transactional
    public Integer updateCoinDeskRate(String code, Float newRate) {
        Integer result = 0;
        GenericSpecification<CoindeskDetail> specification = new GenericSpecification<>();
        specification.add(new SearchCriteria("code", ":", code));
        List<CoindeskDetail> coindeskList = coindeskDetailRepository.findAll(Specification.where(specification));
        // 更新實體
        for (CoindeskDetail coindeskDetail : coindeskList) {
            coindeskDetail.setRate(String.format("%,.2f", newRate));
            coindeskDetail.setRateFloat(newRate);
            coindeskDetailRepository.save(coindeskDetail);
            result++;
        }
        return result;
    }

    public List<Coindesk> search(CoindeskSearchParam param) {
        String code = param.getCode();
        if (code == null) {
            return coindeskRepository.findAll();
        }
        return coindeskRepository.findByCode(param.getCode());
    }

    public void deleteByCode(CoindeskSearchParam param) {
        coindeskDetailRepository.deleteByCode(param.getCode());
    }


}
