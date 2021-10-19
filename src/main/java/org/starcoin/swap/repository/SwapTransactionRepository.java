package org.starcoin.swap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.starcoin.swap.entity.SwapTransaction;

import java.util.List;

public interface SwapTransactionRepository extends JpaRepository<SwapTransaction, String> {
    List<SwapTransaction> findAllByTransactionHash(String transactionHash);

    @Query(value = "select sum(total_value) as volume, sum(amount_a) as volumeAmount  from {h-domain}swap_transaction where token_a = :token "
            + " and swap_type > 0 and swap_type < 3 and (ts between :start_time and :end_time)", nativeQuery = true)
    TokenVolumeDTO getVolumeByTokenA(@Param("token") String tokenA, @Param("start_time") long startTime, @Param("end_time") long endTime);

    @Query(value = "select sum(total_value) as volume, sum(amount_b) as volumeAmount  from {h-domain}swap_transaction where token_b = :token "
            + "and swap_type > 0 and swap_type < 3 and (ts between :start_time and :end_time)", nativeQuery = true)
    TokenVolumeDTO getVolumeByTokenB(@Param("token") String tokenB, @Param("start_time") long startTime, @Param("end_time") long endTime);

    @Query(value = "select sum(total_value) as volume, sum(amount_a) as volumeAmount  from {h-domain}swap_transaction where token_a = :tokenA "
            + "and token_b = :tokenB "
            + " and swap_type > 0 and swap_type < 3 and (ts between :start_time and :end_time)", nativeQuery = true)
    TokenVolumeDTO getPoolVolumeA(@Param("tokenA") String tokenA, @Param("tokenB") String tokenB,
                                  @Param("start_time") long startTime, @Param("end_time") long endTime);

    @Query(value = "select sum(total_value) as volume, sum(amount_b) as volumeAmount  from {h-domain}swap_transaction where token_b = :tokenA "
            + "and token_a = :tokenB "
            + " and swap_type > 0 and swap_type < 3 and (ts between :start_time and :end_time)", nativeQuery = true)
    TokenVolumeDTO getPoolVolumeB(@Param("tokenA") String tokenA, @Param("tokenB") String tokenB,
                                  @Param("start_time") long startTime, @Param("end_time") long endTime);

    @Query(value = "select * from swap_transaction  where swap_type=:swap_type and swap_seq>:start_seq limit :count", nativeQuery = true)
    public List<SwapTransaction> findByType(@Param("swap_type") int swapType,
                                            @Param("start_seq") int startSeq,
                                            @Param("count") int count);

    @Query(value = "select * from swap_transaction  where (swap_type=:swap_type1 or swap_type=:swap_type2) and swap_seq>:start_seq limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTypes(@Param("swap_type1") int swapType1,
                                             @Param("swap_type2") int swapType2,
                                             @Param("start_seq") int startSeq,
                                             @Param("count") int count);

    @Query(value = "select * from swap_transaction  where swap_seq>:start_seq limit :count", nativeQuery = true)
    public List<SwapTransaction> find(@Param("start_seq") int startSeq, @Param("count") int count);

    @Query(value = "select * from swap_transaction  where swap_seq>:start_seq and (token_a=:token_name or token_b=:token_name) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTokenName(@Param("token_name") String tokenName,
                                                 @Param("start_seq") int startSeq, @Param("count") int count);

    @Query(value = "select * from swap_transaction  where swap_type=:swap_type and swap_seq>:start_seq and (token_a=:token_name or token_b=:token_name) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTypeAndTokenName(@Param("token_name") String tokenName,
                                                        @Param("swap_type") int swapType,
                                                        @Param("start_seq") int startSeq,
                                                        @Param("count") int count);

    @Query(value = "select * from swap_transaction  where (swap_type=:swap_type1 or swap_type=:swap_type2)  and swap_seq>:start_seq and (token_a=:token_name or token_b=:token_name) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTypesAndTokenName(@Param("token_name") String tokenName,
                                                         @Param("swap_type1") int swapType1,
                                                         @Param("swap_type2") int swapType2,
                                                         @Param("start_seq") int startSeq,
                                                         @Param("count") int count);

    @Query(value = "select * from swap_transaction  where swap_seq>:start_seq and ((token_a=:token_x_name and token_b=:token_y_name) or (token_a=:token_y_name and token_b=:token_x_name)) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTokenPair(@Param("token_x_name") String tokenXName,
                                                 @Param("token_y_name") String tokenYName,
                                                 @Param("start_seq") int startSeq, @Param("count") int count);

    @Query(value = "select * from swap_transaction  where swap_type=:swap_type and swap_seq>:start_seq and ((token_a=:token_x_name and token_b=:token_y_name) or (token_a=:token_y_name and token_b=:token_x_name)) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTypeAndTokenPair(@Param("token_x_name") String tokenXName,
                                                        @Param("token_y_name") String tokenYName,
                                                        @Param("swap_type") int swapType,
                                                        @Param("start_seq") int startSeq,
                                                        @Param("count") int count);

    @Query(value = "select * from swap_transaction  where (swap_type=:swap_type1 or swap_type=:swap_type2) and swap_seq>:start_seq and ((token_a=:token_x_name and token_b=:token_y_name) or (token_a=:token_y_name and token_b=:token_x_name)) limit :count", nativeQuery = true)
    public List<SwapTransaction> findByTypesAndTokenPair(@Param("token_x_name") String tokenXName,
                                                         @Param("token_y_name") String tokenYName,
                                                         @Param("swap_type1") int swapType1,
                                                         @Param("swap_type2") int swapType2,
                                                         @Param("start_seq") int startSeq,
                                                         @Param("count") int count);

}
