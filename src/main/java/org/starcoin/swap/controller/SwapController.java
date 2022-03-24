package org.starcoin.swap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.starcoin.swap.bean.SwapPoolStatView;
import org.starcoin.swap.bean.TokenPriceView;
import org.starcoin.swap.bean.TokenStatView;
import org.starcoin.swap.bean.TokenUtils;
import org.starcoin.swap.entity.*;
import org.starcoin.swap.service.SwapService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "swap")
@RestController
@RequestMapping("swap")
public class SwapController {

    @Autowired
    private SwapService swapService;

    @ApiOperation("get swap stat list")
    @GetMapping("/stats/{network}/page/{page}")
    public List<SwapStat> getSwapStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                          @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getSwapStatList(network, page, count);
    }

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/{network}")
    public List<SwapTransaction> swapTransactionsList(@PathVariable("network") String network,
                                                      @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                      @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                      @RequestParam(value = "filter", required = false, defaultValue = "all") String filter) {
        return swapService.swapTransactionsList(network, count, startId, filter);
    }

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/token/{network}")
    public List<SwapTransaction> swapTransactionsListByToken(@PathVariable("network") String network,
                                                             @RequestParam(value = "token") String token,
                                                             @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                             @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                             @RequestParam(value = "filter", required = false, defaultValue = "all") String filter) {
        String longToken = TokenUtils.toLong(network, token);
        if (longToken == null) {
            return null;
        }
        return swapService.swapTransactionsListByTokenName(network, longToken, count, startId, filter);
    }

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/pool/{network}")
    public List<SwapTransaction> swapTransactionsListByPool(@PathVariable("network") String network,
                                                            @RequestParam(value = "pool_name") String poolName,
                                                            @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                            @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                            @RequestParam(value = "filter", required = false, defaultValue = "all") String filter) throws IOException {
        return swapService.swapTransactionsListByPoolName(network, poolName, count, startId, filter);
    }

    @ApiOperation("get token stat list")
    @GetMapping("/token/{network}/page/{page}")
    public List<TokenStatView> getTokenStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                                @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        List<TokenStat> tokenStats = swapService.getTokenStatList(network, page, count);
        List<TokenStatView> tokenStatViews = new ArrayList<>();
        for (TokenStat tokenStat : tokenStats) {
            tokenStatViews.add(TokenStatView.fromEntity(tokenStat));
        }
        return tokenStatViews;
    }

    @ApiOperation("get token stat by token name")
    @GetMapping("/token/{network}/{token}")
    public TokenStatView getTokenStat(@PathVariable("network") String network, @PathVariable("token") String token) {
        String longToken = TokenUtils.toLong(network, token);
        if (longToken == null) {
            return null;
        }
        TokenStat tokenStat = swapService.getTokenStat(network, longToken);
        if (tokenStat != null) {
            return TokenStatView.fromEntity(tokenStat);
        }
        return null;
    }

    @ApiOperation("get token price by token name")
    @GetMapping("/token/price/{network}/{token}/page/{page}")
    public List<TokenPriceView> getTokenPrice(@PathVariable("network") String network, @PathVariable("token") String token,
                                              @RequestParam(value = "count", required = false, defaultValue = "7") int count,
                                              @PathVariable int page) {
        String longToken = TokenUtils.toLong(network, token);
        if (longToken != null) {
            List<TokenPrice> tokenPriceList = swapService.getTokenPriceList(network, token, page, count);
            List<TokenPriceView> tokenPriceViews = new ArrayList<>();
            for (TokenPrice tokenPrice : tokenPriceList) {
                tokenPriceViews.add(TokenPriceView.fromEntity(tokenPrice));
            }
            return tokenPriceViews;
        }
        return null;
    }

    @ApiOperation("get token pool stat list")
    @GetMapping("/pool/{network}/page/{page}")
    public List<SwapPoolStatView> getTokenPoolStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                                       @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        List<SwapPoolStat> swapPoolStatList = swapService.getTokenPoolStatList(network, page, count);
        if (swapPoolStatList == null) {
            return null;
        }
        List<SwapPoolStatView> views = new ArrayList<>();
        for (SwapPoolStat stat : swapPoolStatList) {
            views.add(SwapPoolStatView.fromEntity(stat));
        }
        return views;
    }

    @ApiOperation("get token pool stat list by token name")
    @GetMapping("/pool/stats/{network}/{token_name}/page/{page}")
    public List<SwapPoolStatView> getTokenPoolStatListByTokenName(@PathVariable("network") String network, @PathVariable("token_name") String tokenName,
                                                                  @PathVariable("page") int page,
                                                                  @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        String longToken = TokenUtils.toLong(network, tokenName);
        if (longToken != null) {
            List<SwapPoolStat> swapPoolStatList = swapService.getTokenPoolStatListByTokenName(network, longToken, page, count);
            List<SwapPoolStatView> views = new ArrayList<>();
            for (SwapPoolStat stat : swapPoolStatList) {
                views.add(SwapPoolStatView.fromEntity(stat));
            }
            return views;
        }
        return null;
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/stats/{network}")
    public SwapPoolStatView getTokenPoolStat(@PathVariable("network") String network,
                                             @RequestParam("pool_name") String poolName) {
        SwapPoolStat swapPoolStat = swapService.getTokenPoolStat(network, poolName);
        if (swapPoolStat != null) {
            return SwapPoolStatView.fromEntity(swapPoolStat);
        }
        return null;
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/fees/{network}/page/{page}")
    public List<PoolFeeStat> getPoolFees(@PathVariable("network") String network,
                                         @RequestParam("pool_name") String poolName, @PathVariable int page,
                                         @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getPoolFeeStatList(network, poolName, page, count);
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/liquidity/{network}/page/{page}")
    public SwapPoolStat getPoolLiquidity(@PathVariable("network") String network,
                                         @RequestParam("pool_name") String poolName, @PathVariable String page) {
        return null;
    }

}

