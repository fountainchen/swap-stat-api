package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.SwapPoolStat;

import java.util.List;

public interface SwapPoolStatRepository extends JpaRepository<SwapPoolStat, String> {
    @Query(value = "select first_token_name, second_token_name, now() as ts, sum(volume_amount) as volume_amount, sum(volume) as volume, sum(tvl_a_amount) as tvl_a_amount, sum(tvl_a) as tvl_a, sum(tvl_b_amount) as tvl_b_amount, sum(tvl_b) as tvl_b  from main.pool_swap_day_stat group by first_token_name, second_token_name", nativeQuery = true)
    List<SwapPoolStat> sum();

    @Query(value = "select first_token_name, second_token_name, now() as ts, sum(volume_amount) as volume_amount, sum(volume) as volume, sum(tvl_a_amount) as tvl_a_amount, sum(tvl_a) as tvl_a, sum(tvl_b_amount) as tvl_b_amount, sum(tvl_b) as tvl_b  from main.pool_swap_day_stat where (first_token_name=:token_x_name and second_token_name=:token_y_name) group by first_token_name, second_token_name", nativeQuery = true)
    SwapPoolStat sumByToken(@Param("token_x_name") String tokenXName, @Param("token_y_name") String tokenYName);

    @Query(value = "select * from pool_swap_day_stat where (first_token_name=:token_x_name and second_token_name=:token_y_name) or (first_token_name=:token_y_name and second_token_name=:token_x_name) order by volume limit :count offset :offset", nativeQuery = true)
    List<SwapPoolStat> find(@Param("token_x_name") String tokenXName, @Param("token_y_name") String tokenYName,
                            @Param("offset") int offset,
                            @Param("count") int count);

    @Query(value = "select first_token_name, second_token_name, now() as ts, sum(volume_amount) as volume_amount, sum(volume) as volume, sum(tvl_a_amount) as tvl_a_amount, sum(tvl_a) as tvl_a, sum(tvl_b_amount) as tvl_b_amount, sum(tvl_b) as tvl_b  from main.pool_swap_day_stat where (first_token_name=:token_name or second_token_name=:token_name) group by first_token_name, second_token_name", nativeQuery = true)
    List<SwapPoolStat> sumByOneToken(@Param("token_name") String tokenName);

    @Query(value = "select * from pool_swap_day_stat where (first_token_name=:token_name or second_token_name=:token_name) order by volume limit :count offset :offset", nativeQuery = true)
    List<SwapPoolStat> findAll(@Param("offset") int offset,
                               @Param("count") int count,
                               @Param("token_name") String tokenName);
}
