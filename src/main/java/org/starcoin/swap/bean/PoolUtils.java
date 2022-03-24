package org.starcoin.swap.bean;

import java.util.ArrayList;
import java.util.List;

public class PoolUtils {

    public static String toShort(String longPoolName) {
        String[] tokens = longPoolName.split("/");
        if (tokens == null || tokens.length != 2) {
            return null;
        }
        return TokenUtils.toShort(tokens[0]) + "/" + TokenUtils.toShort(tokens[1]);
    }

    public static String[] splitShortToken(String poolName) {
        String[] tokens = poolName.split("/");
        if (tokens == null || tokens.length != 2) {
            return null;
        }
        return tokens;
    }

    public static String[] splitAndToLongToken(String network, String poolName) {
        String[] tokens = poolName.split("/");
        if (tokens == null || tokens.length != 2) {
            return null;
        }
        if (poolName.contains("::")) {
            return tokens;
        }
        List<String> longTokens = new ArrayList<>();
        longTokens.add(TokenUtils.toLong(network, tokens[0]));
        longTokens.add(TokenUtils.toLong(network, tokens[1]));
        return longTokens.toArray(new String[0]);
    }
}
