package com.bridgelabz.moodanalyser.exceptions;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ExceptionType;

public class MoodAnalysisException extends RuntimeException {

    public ExceptionType exceptionType;

    public MoodAnalysisException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
