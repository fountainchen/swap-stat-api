package org.starcoin.swap.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.starcoin.swap.entity.SwapPoolStat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class SwapPoolStatView {
    @JSONField(name = "pool_name")
    private String poolName;
    @JSONField(name = "timestamp")
    private Date timestamp;
    @JSONField(name = "volume")
    private BigDecimal volume;
    @JSONField(name = "volume_amount")
    private BigDecimal volumeAmount;
    @JSONField(name = "tvl_a")
    private BigDecimal tvlA;
    @JSONField(name = "tvl_a_amount")
    private BigInteger tvlAAmount;
    @JSONField(name = "tvl_b")
    private BigDecimal tvlB;
    @JSONField(name = "tvl_b_amount")
    private BigInteger tvlBAmount;

    public SwapPoolStatView(String poolName, Date timestamp, BigDecimal volume, BigDecimal volumeAmount,
                            BigDecimal tvlA, BigInteger tvlAAmount, BigDecimal tvlB, BigInteger tvlBAmount) {
        this.poolName = poolName;
        this.timestamp = timestamp;
        this.volume = volume;
        this.volumeAmount = volumeAmount;
        this.tvlA = tvlA;
        this.tvlAAmount = tvlAAmount;
        this.tvlB = tvlB;
        this.tvlBAmount = tvlBAmount;
    }

    public static SwapPoolStatView fromEntity(SwapPoolStat poolStat) {
        String poolName = PoolUtils.toShort(poolStat.getPoolName());
        return new SwapPoolStatView(poolName, poolStat.getTimestamp(), poolStat.getVolume(), poolStat.getVolumeAmount(),
                poolStat.getTvlA(), poolStat.getTvlAAmount(), poolStat.getTvlB(), poolStat.getTvlBAmount());
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
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

    public BigDecimal getTvlA() {
        return tvlA;
    }

    public void setTvlA(BigDecimal tvlA) {
        this.tvlA = tvlA;
    }

    public BigInteger getTvlAAmount() {
        return tvlAAmount;
    }

    public void setTvlAAmount(BigInteger tvlAAmount) {
        this.tvlAAmount = tvlAAmount;
    }

    public BigDecimal getTvlB() {
        return tvlB;
    }

    public void setTvlB(BigDecimal tvlB) {
        this.tvlB = tvlB;
    }

    public BigInteger getTvlBAmount() {
        return tvlBAmount;
    }

    public void setTvlBAmount(BigInteger tvlBAmount) {
        this.tvlBAmount = tvlBAmount;
    }
}
