package org.starcoin.swap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "token_price")
public class TokenPrice {
    @EmbeddedId
    TokenPriceId tokenPriceId;

    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "rate")
    private BigDecimal rate;

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

    public String getToken() {
        return tokenPriceId.getToken();
    }

    public Date getTimestamp() {
        return tokenPriceId.getTimestamp();
    }
}

@Embeddable
class TokenPriceId implements Serializable {
    @Column(name = "token_name")
    private String token;
    @Column(name = "ts")
    private Date timestamp;

    public TokenPriceId(String token, Date timestamp) {
        this.token = token;
        this.timestamp = timestamp;
    }

    public TokenPriceId() {
    }

    public String getToken() {
        return token;
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
}