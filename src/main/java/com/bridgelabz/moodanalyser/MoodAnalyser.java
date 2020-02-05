package com.bridgelabz.moodanalyser;

public class MoodAnalyser {

    private String message;

    public MoodAnalyser() {
    }

    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood() {
        return analyseMood(message);
    }

    public String analyseMood(String message) {
        if (message.contains("Sad")) {
            return "SAD";
        } else {
            return "HAPPY";
        }
    }
}
