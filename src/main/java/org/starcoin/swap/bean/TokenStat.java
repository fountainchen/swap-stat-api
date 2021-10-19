package org.starcoin.swap.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class TokenStat {

    private String token;

    @JSONField(name = "volume_amount")
    private BigDecimal volumeAmount;

    private BigDecimal volume;

    private BigDecimal tvl;

    public TokenStat(String token, BigDecimal volume, BigDecimal volumeAmount, BigDecimal tvl) {
        this.token = token;
        this.volume = volume;
        this.volumeAmount = volumeAmount;
        this.tvl = tvl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getTvl() {
        return tvl;
    }

    public void setTvl(BigDecimal tvl) {
        this.tvl = tvl;
    }

    public BigDecimal getVolumeAmount() {
        return volumeAmount;
    }

    public void setVolumeAmount(BigDecimal volumeAmount) {
        this.volumeAmount = volumeAmount;
    }

}
