package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ExceptionType;
import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;

public class MoodAnalyser {

    private String message;

    public MoodAnalyser() {
    }

    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood() {
        try {
            if (message.length() == 0) {
                throw new MoodAnalysisException(ExceptionType.ENTERED_EMPTY, "Please enter valid message");
            }
            if (message.contains("Sad")) {
                return "SAD";
            }
            return "HAPPY";
        } catch (NullPointerException e) {
            throw new MoodAnalysisException(ExceptionType.ENTERED_NULL, "Please enter valid message");
        }
    }

    public String analyseMood(String message) {
        this.message = message;
        return analyseMood();
    }

    @Override
    public boolean equals(Object object) {
        MoodAnalyser moodAnalyser = (MoodAnalyser) object;
        if (this.message.equals(moodAnalyser.message)) {
            return true;
        }
        return false;
    }

}
