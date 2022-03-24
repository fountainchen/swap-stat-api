package org.starcoin.swap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starcoin.swap.constant.StarcoinNetwork;
import org.starcoin.swap.repository.*;
import org.starcoin.swap.repository.barnard.BarnardSwapPoolStatRepository;
import org.starcoin.swap.repository.barnard.BarnardSwapStatRepository;
import org.starcoin.swap.repository.barnard.BarnardSwapTransactionRepository;
import org.starcoin.swap.repository.barnard.BarnardTokenStatRepository;
import org.starcoin.swap.repository.main.MainSwapPoolStatRepository;
import org.starcoin.swap.repository.main.MainSwapStatRepository;
import org.starcoin.swap.repository.main.MainSwapTransactionRepository;
import org.starcoin.swap.repository.main.MainTokenStatRepository;

@Service
public class BaseService {
    @Autowired
    private BarnardSwapTransactionRepository barnardSwapTransactionRepository;
    @Autowired
    private MainSwapTransactionRepository mainSwapTransactionRepository;

    @Autowired
    private BarnardSwapPoolStatRepository barnardSwapPoolStatRepository;
    @Autowired
    private MainSwapPoolStatRepository mainSwapPoolStatRepository;

    @Autowired
    private BarnardSwapStatRepository barnardSwapStatRepository;
    @Autowired
    private MainSwapStatRepository mainSwapStatRepository;

    @Autowired
    private BarnardTokenStatRepository barnardTokenStatRepository;
    @Autowired
    private MainTokenStatRepository mainTokenStatRepository;
    @Autowired
    private TokenPriceRepository barnardTokenPriceRepository;
    @Autowired
    private TokenPriceRepository mainTokenPriceRepository;

    SwapPoolStatRepository getSwapPoolStatRepository(String network) {
        StarcoinNetwork starcoinNetwork = StarcoinNetwork.fromValue(network);
        if (starcoinNetwork == StarcoinNetwork.barnard) {
            return barnardSwapPoolStatRepository;
        }
        else if (starcoinNetwork == StarcoinNetwork.main) {
            return mainSwapPoolStatRepository;
        }
        return null;
    }


    SwapTransactionRepository getSwapTransactionRepository(String network) {
        StarcoinNetwork starcoinNetwork = StarcoinNetwork.fromValue(network);
        if (starcoinNetwork == StarcoinNetwork.barnard) {
            return barnardSwapTransactionRepository;
        }
        else if (starcoinNetwork == StarcoinNetwork.main) {
            return mainSwapTransactionRepository;
        }
        return null;
    }

    SwapStatRepository getSwapStatRepository(String network) {
        StarcoinNetwork starcoinNetwork = StarcoinNetwork.fromValue(network);
        if (starcoinNetwork == StarcoinNetwork.barnard) {
            return barnardSwapStatRepository;
        }
        else if (starcoinNetwork == StarcoinNetwork.main) {
            return mainSwapStatRepository;
        }
        return null;
    }

    TokenStatRepository getTokenStatRepository(String network) {
        StarcoinNetwork starcoinNetwork = StarcoinNetwork.fromValue(network);
        if (starcoinNetwork == StarcoinNetwork.barnard) {
            return barnardTokenStatRepository;
        }
        else if (starcoinNetwork == StarcoinNetwork.main) {
            return mainTokenStatRepository;
        }
        return null;
    }

    TokenPriceRepository getTokenPriceRepository(String network) {
        StarcoinNetwork starcoinNetwork = StarcoinNetwork.fromValue(network);
        if (starcoinNetwork == StarcoinNetwork.barnard) {
            return barnardTokenPriceRepository;
        }
        else if (starcoinNetwork == StarcoinNetwork.main) {
            return mainTokenPriceRepository;
        }
        return null;
    }
}
