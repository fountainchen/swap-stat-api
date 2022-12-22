package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.SwapPoolStat;

import java.util.List;

public interface SwapPoolStatRepository extends JpaRepository<SwapPoolStat, String> {
    @Query(value = "select * from pool_swap_day_stat where ts = current_date - 1", nativeQuery = true)
    List<SwapPoolStat> lastDayStat();

    @Query(value = "select * from pool_swap_day_stat where ts = (current_date - 1) and first_token_name=:token_x_name and second_token_name=:token_y_name", nativeQuery = true)
    SwapPoolStat lastStatByToken(@Param("token_x_name") String tokenXName, @Param("token_y_name") String tokenYName);

    @Query(value = "select * from pool_swap_day_stat where (first_token_name=:token_x_name and second_token_name=:token_y_name) or (first_token_name=:token_y_name and second_token_name=:token_x_name) order by ts desc limit :count offset :offset", nativeQuery = true)
    List<SwapPoolStat> find(@Param("token_x_name") String tokenXName, @Param("token_y_name") String tokenYName,
                            @Param("offset") int offset,
                            @Param("count") int count);

    @Query(value = "select * from pool_swap_day_stat where ts = (current_date - 1) and (first_token_name=:token_name or second_token_name=:token_name)", nativeQuery = true)
    List<SwapPoolStat> lastStatByOneToken(@Param("token_name") String tokenName);

    @Query(value = "select * from pool_swap_day_stat where (first_token_name=:token_name or second_token_name=:token_name) order by ts desc limit :count offset :offset", nativeQuery = true)
    List<SwapPoolStat> findAll(@Param("offset") int offset,
                               @Param("count") int count,
                               @Param("token_name") String tokenName);
}
