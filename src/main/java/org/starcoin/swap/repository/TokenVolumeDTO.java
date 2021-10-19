package org.starcoin.swap.repository;

import java.math.BigDecimal;

public interface TokenVolumeDTO {
    public BigDecimal getVolume();

    public BigDecimal getVolumeAmount();
}
