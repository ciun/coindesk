package com.coindesk.demo.dto;

import com.coindesk.demo.Serialzation.CoindeskSerialization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Embeddable
@JsonSerialize(using = CoindeskSerialization.class)
public class Coindesk {

    @Id
    private String ccyCode;

    @OneToOne
    @JoinColumn(name = "ccy_code", referencedColumnName = "code")
    @Embedded
    private CoindeskDetail coindeskDetail;

    @JsonIgnore
    private String name;

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoindeskDetail getCoindesk() {
        return coindeskDetail;
    }

    public void setCoindesk(CoindeskDetail coindeskDetail) {
        this.coindeskDetail = coindeskDetail;
    }
}
