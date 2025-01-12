package com.coindesk.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CoindeskDetail {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @Column(nullable = false)
    @Id
    @Column(name = "code", columnDefinition = "varchar(3)", nullable = false)
    private String code;
    private String symbol;
    private String rate;
    private String description;

    @Column(name = "rate_float")
    @JsonProperty("rate_float")
    private Float rateFloat;

    @Column(name = "update_date")
    private Date updateDate;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(Float rateFloat) {
        this.rateFloat = rateFloat;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
