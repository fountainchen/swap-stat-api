package org.starcoin.swap.bean;

public enum FilterType {
    Add("add"),
    Swap("swap"),
    Remove("remove"),
    All("all");
    String value;

    FilterType(String value) {
        this.value = value;
    }

    public static FilterType fromValue(String value) {
        for (FilterType type : FilterType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return FilterType.All;
    }

    public static FilterType fromSwapType(SwapType swapType) {
        if(swapType == SwapType.SwapExactTokenForToken || swapType == SwapType.SwapTokenForExactToken) {
            return FilterType.Swap;
        } else if(swapType == SwapType.AddLiquidity) {
            return FilterType.Add;
        } else if(swapType == SwapType.RemoveLiquidity) {
            return FilterType.Remove;
        }
        return FilterType.All;
    }

    public static int getIndex(String value) {
        return fromValue(value).ordinal();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
