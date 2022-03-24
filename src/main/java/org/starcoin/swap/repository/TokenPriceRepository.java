package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.TokenPrice;

import java.util.List;

public interface TokenPriceRepository extends JpaRepository<TokenPrice, String> {
    @Query(value = "select * from token_price limit :count offset :offset", nativeQuery = true)
    List<TokenPrice> findAll(@Param("offset") int offset, @Param("count") int count);
}
