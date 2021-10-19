package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.SwapStat;

import java.util.Date;
import java.util.List;

public interface SwapStatRepository extends JpaRepository<SwapStat, Date> {
    @Query(value = "select * from swap_day_stat limit :count offset :offset", nativeQuery = true)
    List<SwapStat> findAll(@Param("offset") int offset, @Param("count") int count);
}
