package org.starcoin.swap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.starcoin.swap.entity.SwapPoolStat;
import org.starcoin.swap.entity.SwapStat;
import org.starcoin.swap.entity.SwapTransaction;
import org.starcoin.swap.entity.TokenStat;
import org.starcoin.swap.service.SwapService;

import java.io.IOException;
import java.util.List;

@Api(tags = "swap")
@RestController
@RequestMapping("swap")
public class SwapController {

    @Autowired
    private SwapService swapService;

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/{network}")
    public List<SwapTransaction> swapTransactionsList(@PathVariable("network") String network,
                                                      @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                      @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                      @RequestParam("filter") String filter) {
        return swapService.swapTransactionsList(network, count, startId, filter);
    }

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/token/{network}")
    public List<SwapTransaction> swapTransactionsListByToken(@PathVariable("network") String network,
                                                             @RequestParam(value = "token") String token,
                                                             @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                             @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                             @RequestParam("filter") String filter) {
        return swapService.swapTransactionsListByTokenName(network, token, count, startId, filter);
    }

    @ApiOperation("get swap transaction list")
    @GetMapping("/transactions/pool/{network}")
    public List<SwapTransaction> swapTransactionsListByPool(@PathVariable("network") String network,
                                                            @RequestParam(value = "pool_name") String poolName,
                                                            @RequestParam(value = "count", required = false, defaultValue = "20") int count,
                                                            @RequestParam(value = "start_id", required = false, defaultValue = "0") int startId,
                                                            @RequestParam("filter") String filter) throws IOException {
        return swapService.swapTransactionsListByPoolName(network, poolName, count, startId, filter);
    }

    @ApiOperation("get token stat list")
    @GetMapping("/token/{network}/page/{page}")
    public List<TokenStat> getTokenStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                            @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getTokenStatList(network, page, count);
    }

    @ApiOperation("get token stat by token name")
    @GetMapping("/token/{network}/{token}")
    public TokenStat getTokenStat(@PathVariable("network") String network, @PathVariable("token") String token) {
        return swapService.getTokenStat(network, token);
    }


    @ApiOperation("get token pool stat list")
    @GetMapping("/pool/{network}/page/{page}")
    public List<SwapPoolStat> getTokenPoolStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                                   @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getTokenPoolStatList(network, page, count);
    }

    @ApiOperation("get token pool stat list by token name")
    @GetMapping("/token_pool_stats/{network}/{token_name}/page/{page}")
    public List<SwapPoolStat> getTokenPoolStatListByTokenName(@PathVariable("network") String network, @PathVariable("token_name") String tokenName,
                                                              @PathVariable("page") int page,
                                                              @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getTokenPoolStatListByTokenName(network, tokenName, page, count);
    }

    @ApiOperation("get token pool stat by name")
    @GetMapping("/token_pool_stats/{network}/{pool_name}")
    public SwapPoolStat getTokenPoolStat(@PathVariable("network") String network,
                                         @PathVariable("pool_name") String poolName) {
        return swapService.getTokenPoolStat(network, poolName);
    }

    @ApiOperation("get swap stat list")
    @GetMapping("/swap_stats/{network}/page/{page}")
    public List<SwapStat> getSwapStatList(@PathVariable("network") String network, @PathVariable("page") int page,
                                          @RequestParam(value = "count", required = false, defaultValue = "20") int count) {
        return swapService.getSwapStatList(network, page, count);
    }
}

