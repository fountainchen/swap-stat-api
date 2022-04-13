package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.TokenStat;

import java.util.List;

public interface TokenStatRepository extends JpaRepository<TokenStat, String> {
    @Query(value = "select * from token_swap_day_stat limit :count offset :offset", nativeQuery = true)
    List<TokenStat> findAll(@Param("offset") int offset, @Param("count") int count);

    @Query(value = "select * from token_swap_day_stat where token_name=:token_name order by ts desc limit :count offset :offset", nativeQuery = true)
    List<TokenStat> findByTokenName(@Param("token_name") String tokenName, @Param("offset") int offset, @Param("count") int count);

    @Query(value = "select * from token_swap_day_stat where token_name=:token_name order by ts limit 1", nativeQuery = true)
    TokenStat find(@Param("token_name") String tokenName);

    @Query(value = "select * from token_swap_day_stat where ts = current_date - 1", nativeQuery = true)
    List<TokenStat> lastDayStat();

    @Query(value = "select * from token_swap_day_stat where ts = (current_date - 1) and token_name=:token_name", nativeQuery = true)
    TokenStat lastStatByToken(@Param("token_name") String tokenName);

    @Query(value = "select distinct token_name from token_swap_day_stat", nativeQuery = true)
    List<String> getAllToken();
}
