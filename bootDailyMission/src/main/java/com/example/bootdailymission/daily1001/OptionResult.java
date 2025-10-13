package com.example.bootdailymission.daily1001;

public class OptionResult {
    private final String optionName;
    private final int votes;
    private final double percentage;
    private final boolean isWinning;

    public OptionResult(String optionName, int votes, double percentage, boolean isWinning) {
        this.optionName = optionName;
        this.votes = votes;
        this.percentage = percentage;
        this.isWinning = isWinning;
    }

    public String getOptionName() {
        return optionName;
    }

    public int getVotes() {
        return votes;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isWinning() {
        return isWinning;
    }
}
