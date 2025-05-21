package com.example.endofyearassignment2025;

public class GameResult {
    private String username;
    private int score;
    private String motivationSentence;

    public GameResult(String username, int score, String motivationSentence) {
        this.username = username;
        this.score = score;
        this.motivationSentence = motivationSentence;
    }
    public String getUsername() {

        return username;
    }
    public int getScore() {

        return score;
    }
    public String getMotivationSentence() {

        return motivationSentence;
    }
}
