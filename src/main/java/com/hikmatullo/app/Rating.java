package com.hikmatullo.app;

public enum Rating {
    FIVE_STARS("*****", "5"),
    FOUR_STARS("****", "4"),
    THREE_STARS( "***",  "3"),
    TWO_STARS( "**",  "2"),
    ONE_STAR( "*", "1");

    private final String star;
    private final String rate;

    Rating(String star, String rate) {
        this.star = star;
        this.rate = rate;
    }
}
