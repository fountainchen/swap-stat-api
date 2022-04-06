package org.starcoin.swap.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.starcoin.swap.entity.TokenPriceStat;

import java.math.BigDecimal;
import java.util.Date;

public class TokenPriceView {
    @JSONField(name = "token")
    private String token;
    @JSONField(name = "timestamp")
    private Date timestamp;
    @JSONField(name = "price")
    private BigDecimal price;
    @JSONField(name = "max_price")
    private BigDecimal maxPrice;
    @JSONField(name = "min_price")
    private BigDecimal minPrice;
    @JSONField(name = "rate")
    private BigDecimal rate;

    public TokenPriceView(String token, Date timestamp, BigDecimal price, BigDecimal maxPrice,BigDecimal minPrice, BigDecimal rate) {
        this.token = token;
        this.timestamp = timestamp;
        this.price = price;
        this.rate = rate;
        this.maxPrice = maxPrice;
        this.minPrice =minPrice;
    }

    public static TokenPriceView fromEntity(TokenPriceStat tokenPrice) {
        return new TokenPriceView(tokenPrice.getToken(), tokenPrice.getTimestamp(), tokenPrice.getPrice(),tokenPrice.getMaxPrice(), tokenPrice.getMinPrice(), tokenPrice.getRate());
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
