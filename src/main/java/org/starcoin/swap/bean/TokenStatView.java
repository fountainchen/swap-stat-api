package org.starcoin.swap.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.starcoin.swap.entity.TokenStat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TokenStatView {
    @JSONField(name = "token")
    private String token;
    @JSONField(name = "timestamp")
    private Date timestamp;
    @JSONField(name = "volume")
    private BigDecimal volume;
    @JSONField(name = "volume_amount")
    private BigDecimal volumeAmount;
    @JSONField(name = "tvl")
    private BigDecimal tvl;
    @JSONField(name = "tvl_amount")
    private BigInteger tvlAmount;

    public TokenStatView(String token, Date timestamp, BigDecimal volume, BigDecimal volumeAmount, BigDecimal tvl, BigInteger tvlAmount) {
        this.token = token;
        this.timestamp = timestamp;
        this.volume = volume;
        this.volumeAmount = volumeAmount;
        this.tvl = tvl;
        this.tvlAmount = tvlAmount;
    }

    public static TokenStatView fromEntity(TokenStat tokenStat) {
        return new TokenStatView(tokenStat.getToken(), tokenStat.getTimestamp(), tokenStat.getVolume(), tokenStat.getVolumeAmount(), tokenStat.getTvl(), tokenStat.getTvlAmount());
    }

    public String getToken() {
        return TokenUtils.toShort(token);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getVolumeAmount() {
        return volumeAmount;
    }

    public void setVolumeAmount(BigDecimal volumeAmount) {
        this.volumeAmount = volumeAmount;
    }

    public BigDecimal getTvl() {
        return tvl;
    }

    public void setTvl(BigDecimal tvl) {
        this.tvl = tvl;
    }

    public BigInteger getTvlAmount() {
        return tvlAmount;
    }

    public void setTvlAmount(BigInteger tvlAmount) {
        this.tvlAmount = tvlAmount;
    }
}
