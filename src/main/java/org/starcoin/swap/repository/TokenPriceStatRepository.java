package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.TokenPriceStat;

import java.util.List;


public interface TokenPriceStatRepository extends JpaRepository<TokenPriceStat, String> {
    @Query(value = "select * from token_price_stat where token_name=:token_name order by ts desc limit :count offset :offset", nativeQuery = true)
    List<TokenPriceStat> findAllByTokenName(@Param("token_name") String tokenName, @Param("offset") int offset, @Param("count") int count);
}
