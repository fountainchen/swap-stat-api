package org.starcoin.swap.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class TokenPoolStat {

    @JSONField(name = "token_pair")
    private TokenPair tokenPair;

    private BigDecimal volume;

    @JSONField(name = "volume_amount")
    private BigDecimal volumeAmount;

    private BigDecimal tvl;

    public TokenPoolStat(TokenPair tokenPair, BigDecimal volume, BigDecimal volumeAmount, BigDecimal tvl) {
        this.tokenPair = tokenPair;
        this.volume = volume;
        this.volumeAmount = volumeAmount;
        this.tvl = tvl;
    }

    public TokenPair getTokenPair() {
        return tokenPair;
    }

    public void setTokenPair(TokenPair tokenPair) {
        this.tokenPair = tokenPair;
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
