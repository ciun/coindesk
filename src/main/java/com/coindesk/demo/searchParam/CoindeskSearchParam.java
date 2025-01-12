package com.coindesk.demo.searchParam;


public class CoindeskSearchParam {
    private String code;
    private Float rateFloatGt;
    private Float rateFloatLt;
    private Float updateRate;


    public String getCode() {
        return code;
    }

    public Float getRateFloatGt() {
        return rateFloatGt;
    }

    public Float getRateFloatLt() {
        return rateFloatLt;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRateFloatGt(Float rateFloatGt) {
        this.rateFloatGt = rateFloatGt;
    }

    public void setRateFloatLt(Float rateFloatLt) {
        this.rateFloatLt = rateFloatLt;
    }

    public Float getUpdateRate() {
        return updateRate;
    }

    public void setUpdateRate(Float updateRate) {
        this.updateRate = updateRate;
    }
}
