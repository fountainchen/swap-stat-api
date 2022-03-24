package org.starcoin.swap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return swapService.swapTransactionsListByTokenName(network, token, count, startId, filter);
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
        for (TokenStat tokenStat: tokenStats) {
            tokenStatViews.add(TokenStatView.fromEntity(tokenStat));
        }
        return tokenStatViews;
    }

    @ApiOperation("get token stat by token name")
    @GetMapping("/token/{network}/{token}")
    public TokenStatView getTokenStat(@PathVariable("network") String network, @PathVariable("token") String token) {
        String longToken = TokenUtils.toLong(network, token);
        if(longToken == null) {
            return null;
        }
        TokenStat tokenStat = swapService.getTokenStat(network, longToken);
        if(tokenStat != null) {
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
        if(longToken != null) {
            List<TokenPrice> tokenPriceList = swapService.getTokenPriceList(network, token, page, count);
            List<TokenPriceView> tokenPriceViews = new ArrayList<>();
            for (TokenPrice tokenPrice: tokenPriceList) {
                tokenPriceViews.add(TokenPriceView.fromEntity(tokenPrice));
            }
            return tokenPriceViews;
        }
        return null;
    }

    @ApiOperation("get token pool stat list")
    @GetMapping("/pool/{network}/page/{page}")
    public List<SwapPoolStat> getTokenPoolStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                                   @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getTokenPoolStatList(network, page, count);
    }

    @ApiOperation("get token pool stat list by token name")
    @GetMapping("/pool/stats/{network}/{token_name}/page/{page}")
    public List<SwapPoolStat> getTokenPoolStatListByTokenName(@PathVariable("network") String network, @PathVariable("token_name") String tokenName,
                                                              @PathVariable("page") int page,
                                                              @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getTokenPoolStatListByTokenName(network, tokenName, page, count);
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/stats/{network}")
    public SwapPoolStat getTokenPoolStat(@PathVariable("network") String network,
                                         @RequestParam("pool_name") String poolName) {
        return swapService.getTokenPoolStat(network, poolName);
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/fees/{network}/page/{page}")
    public SwapPoolStat getPoolFees(@PathVariable("network") String network,
                                    @RequestParam("pool_name") String poolName, @PathVariable String page) {
        return null;
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/pool/liquidity/{network}/page/{page}")
    public SwapPoolStat getPoolLiquidity(@PathVariable("network") String network,
                                         @RequestParam("pool_name") String poolName, @PathVariable String page) {
        return null;
    }

}

