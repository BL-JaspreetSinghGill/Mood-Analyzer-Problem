package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserFactory {

    public static MoodAnalyser createObject(String className) {
        boolean isValidClassName = checkValidClassName(className);
        if (!isValidClassName) {
            throw new MoodAnalysisException("No Such Class Error");
        }
        MoodAnalyser moodAnalyser = null;
        try {
            Class<?> moodAnalyserClass = Class.forName(className);
            Constructor<?> moodConstructor = moodAnalyserClass.getConstructor(String.class);
            Object instanceObject = moodConstructor.newInstance("I am in Happy mood");
            moodAnalyser = (MoodAnalyser) instanceObject;
            return moodAnalyser;
        } catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return moodAnalyser;
    }

    public static boolean checkValidClassName(String className) {
        if (className.equals("com.bridgelabz.moodanalyser.MoodAnalyser")) {
            return true;
        }
        return false;
    }
}
