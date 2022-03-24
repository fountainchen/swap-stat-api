package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.TokenStat;

import java.util.List;

public interface TokenStatRepository extends JpaRepository<TokenStat, String> {
    @Query(value = "select * from token_swap_day_stat limit :count offset :offset", nativeQuery = true)
    List<TokenStat> findAll(@Param("offset") int offset, @Param("count") int count);

    @Query(value = "select * from token_swap_day_stat where token_name=:token_name order by ts limit 1", nativeQuery = true)
    TokenStat find(@Param("token_name") String tokenName);

    @Query(value = "select token_name,now() as ts, sum(tvl) as tvl, sum(tvl_amount) as tvl_amount,sum(volume) as volume, sum(volume_amount) as volume_amount  from main.token_swap_day_stat group by token_name", nativeQuery = true)
    List<TokenStat> sum();

    @Query(value = "select token_name,now() as ts, sum(tvl) as tvl, sum(tvl_amount) as tvl_amount,sum(volume) as volume, sum(volume_amount) as volume_amount  from main.token_swap_day_stat where token_name=:token_name group by token_name", nativeQuery = true)
    TokenStat sumByToken(@Param("token_name") String tokenName);

    @Query(value = "select distinct token_name from main.token_swap_day_stat", nativeQuery = true)
    List<String> getAllToken();
}
