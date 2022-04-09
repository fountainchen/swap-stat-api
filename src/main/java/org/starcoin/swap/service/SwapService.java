package org.starcoin.swap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starcoin.swap.bean.FilterType;
import org.starcoin.swap.bean.PoolUtils;
import org.starcoin.swap.bean.SwapType;
import org.starcoin.swap.bean.TokenUtils;
import org.starcoin.swap.entity.*;
import org.starcoin.swap.repository.*;
import org.starcoin.swap.utils.CommonUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SwapService {
    private static final Logger logger = LoggerFactory.getLogger(SwapService.class);
    @Autowired
    private BaseService baseService;

    @PostConstruct
    public void initTokenCache() {
        logger.info("init main token");
        TokenUtils.putCache("main", getAllToken("main"));
        String stc = TokenUtils.getLongTokenFromCache("main", "STC");
        logger.info("stc token :" + stc);
        logger.info("init barnard token");
        TokenUtils.putCache("barnard", getAllToken("barnard"));
    }

    public List<SwapTransaction> swapTransactionsList(String network, int count, int startId, String filterType) {
        if(startId == 0) startId = Integer.MAX_VALUE;
        FilterType filterType1 = FilterType.fromValue(filterType);
        SwapTransactionRepository swapTransactionRepository = baseService.getSwapTransactionRepository(network);
        if (swapTransactionRepository != null) {
            if (filterType1 == FilterType.All) {
                return swapTransactionRepository.find(startId, count);
            } else if (filterType1 == FilterType.Swap) {
                return swapTransactionRepository.findByTypes(SwapType.SwapExactTokenForToken.ordinal(), SwapType.SwapTokenForExactToken.ordinal(), startId, count);
            } else if (filterType1 == FilterType.Add ){
                return swapTransactionRepository.findByType(SwapType.AddLiquidity.ordinal(), startId, count);
            } else if (filterType1 == FilterType.Remove) {
                return swapTransactionRepository.findByType(SwapType.RemoveLiquidity.ordinal(), startId, count);
            }
        }
        return null;
    }

    public List<SwapTransaction> swapTransactionsListByTokenName(String network, String tokenName, int count, int startId, String filter) {
        if(startId == 0) startId = Integer.MAX_VALUE;
        FilterType filterType = FilterType.fromValue(filter);
        SwapTransactionRepository swapTransactionRepository = baseService.getSwapTransactionRepository(network);
        if (swapTransactionRepository != null) {
            if (filterType == FilterType.All) {
                return swapTransactionRepository.findByTokenName(tokenName, startId, count);
            } else if (filterType == FilterType.Swap) {
                return swapTransactionRepository.findByTypesAndTokenName(tokenName, SwapType.SwapExactTokenForToken.ordinal(), SwapType.SwapTokenForExactToken.ordinal(), startId, count);
            } else if (filterType == FilterType.Add) {
                return swapTransactionRepository.findByTypeAndTokenName(tokenName, SwapType.AddLiquidity.ordinal(), startId, count);
            } else if(filterType == FilterType.Remove) {
                return swapTransactionRepository.findByTypeAndTokenName(tokenName, SwapType.RemoveLiquidity.ordinal(), startId, count);
            }
        }
        return null;
    }

    public List<SwapTransaction> swapTransactionsListByPoolName(String network, String poolName, int count, int startId, String filter) {
        if(startId == 0) startId = Integer.MAX_VALUE;
        String[] tokens = PoolUtils.splitAndToLongToken(network, poolName);
        String tokenX = tokens[0].trim();
        String tokenY = tokens[1].trim();
        FilterType filterType = FilterType.fromValue(filter);
        SwapTransactionRepository swapTransactionRepository = baseService.getSwapTransactionRepository(network);
        if (swapTransactionRepository != null) {
            if (filterType == FilterType.All) {
                return swapTransactionRepository.findByTokenPair(tokenX, tokenY, startId, count);
            } else if (filterType == FilterType.Swap) {
                return swapTransactionRepository.findByTypesAndTokenPair(tokenX, tokenY, SwapType.SwapExactTokenForToken.ordinal(), SwapType.SwapTokenForExactToken.ordinal(), startId, count);
            } else if (filterType == FilterType.Add ){
                return swapTransactionRepository.findByTypeAndTokenPair(tokenX, tokenY, SwapType.AddLiquidity.ordinal(), startId, count);
            } else if (filterType == FilterType.Remove) {
                return swapTransactionRepository.findByTypeAndTokenPair(tokenX, tokenY, SwapType.RemoveLiquidity.ordinal(), startId, count);
            }
        }
        return null;
    }

    public List<String> getAllToken(String network) {
        TokenStatRepository tokenStatRepository = baseService.getTokenStatRepository(network);
        if (tokenStatRepository != null) {
            return tokenStatRepository.getAllToken();
        }
        return null;
    }

    public List<TokenStat> getTokenStatList(String network,String tokenName, int page, int count) {
        TokenStatRepository tokenStatRepository = baseService.getTokenStatRepository(network);
        if (tokenStatRepository != null) {
            if(tokenName == null || tokenName.length() < 1) {
                return tokenStatRepository.sum();
            }
            String longToken = TokenUtils.toLong(network, tokenName);
            if (longToken != null) {
                return tokenStatRepository.findByTokenName(longToken, CommonUtils.getOffset(page, count), count);
            }
            logger.warn("token long name not exist: {}, {}", network, tokenName);
        }
        return null;
    }

    public TokenStat getTokenStat(String network, String tokenName) {
        TokenStatRepository tokenStatRepository = baseService.getTokenStatRepository(network);
        if (tokenStatRepository != null) {
            return tokenStatRepository.sumByToken(tokenName);
        }
        return null;
    }

    public List<TokenPriceStat> getTokenPriceList(String network, String tokenName, int page, int count) {
        TokenPriceStatRepository tokenPriceStatRepository = baseService.getTokenPriceStatRepository(network);
        if (tokenPriceStatRepository != null) {
            return tokenPriceStatRepository.findAllByTokenName(tokenName, CommonUtils.getOffset(page, count), count);
        }
        logger.warn("tokenPriceRepository is null: " + network + " token: " + tokenName);
        return null;
    }

    public List<SwapPoolStat> getTokenPoolStatList(String network, int page, int count) {
        SwapPoolStatRepository swapPoolStatRepository = baseService.getSwapPoolStatRepository(network);
        if (swapPoolStatRepository != null) {
            //todo sum result by page and count parameter for more token in future
            return swapPoolStatRepository.sum();
        }
        return null;
    }

    public SwapPoolStat getTokenPoolStat(String network, String poolName) {
        String[] tokens = PoolUtils.splitAndToLongToken(network, poolName);
        SwapPoolStatRepository swapPoolStatRepository = baseService.getSwapPoolStatRepository(network);
        if (swapPoolStatRepository != null) {
            return swapPoolStatRepository.sumByToken(tokens[0], tokens[1]);
        }
        return null;
    }

    public List<SwapPoolStat> getTokenPoolStatListByTokenName(String network, String tokenName, int page, int count) {
        SwapPoolStatRepository swapPoolStatRepository = baseService.getSwapPoolStatRepository(network);
        if (swapPoolStatRepository != null) {
            return swapPoolStatRepository.sumByOneToken(tokenName);
        }
        return null;
    }

    public List<SwapPoolStat> getTokenPoolStatListByPoolName(String network, String poolName, int page, int count) {
        String[] tokens = PoolUtils.splitAndToLongToken(network, poolName);
        SwapPoolStatRepository swapPoolStatRepository = baseService.getSwapPoolStatRepository(network);
        if (swapPoolStatRepository != null) {
            return swapPoolStatRepository.find(tokens[0], tokens[1], CommonUtils.getOffset(page, count), count);
        }
        return null;
    }

    public List<PoolFeeStat> getPoolFeeStatList(String network, String poolName, int page, int count) {
        PoolFeeStatRepository poolFeeStatRepository = baseService.getPoolFeeStatRepository(network);
        if (poolFeeStatRepository != null) {
            String[] tokens = PoolUtils.splitAndToLongToken(network, poolName);
            String tokenX = tokens[0].trim();
            String tokenY = tokens[1].trim();
            return poolFeeStatRepository.findAll(tokenX, tokenY, CommonUtils.getOffset(page, count), count);
        }
        return null;
    }

    public List<SwapStat> getSwapStatList(String network, int page, int count) {
        SwapStatRepository swapStatRepository = baseService.getSwapStatRepository(network);
        if (swapStatRepository != null) {
            return swapStatRepository.findAll(CommonUtils.getOffset(page, count), count);
        }
        return null;
    }

}
