package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserFactory {

    public static MoodAnalyser createObject(String className, Class<?> constructorParamType) {
        boolean isValidClassName = checkValidClassName(className);
        if (!isValidClassName) {
            throw new MoodAnalysisException("No Such Class Error");
        }

        Class<?> classObject = getClassObject(className);
        Constructor<?> constructorObject = getConstructorObject(classObject, constructorParamType);
        Object instanceObject = getInstance(constructorObject);
        MoodAnalyser moodAnalyser = (MoodAnalyser) instanceObject;
        return moodAnalyser;
    }

    public static boolean checkValidClassName(String className) {
        if (className.equals("com.bridgelabz.moodanalyser.MoodAnalyser")) {
            return true;
        }
        return false;
    }

    public static Class<?> getClassObject(String className) {
        Class<?> classObject = null;
        try {
            classObject = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classObject;
    }

    public static Constructor<?> getConstructorObject(Class<?> classObject, Class<?> constructorParamType) {
        Constructor<?> constructorObject = null;
        try {
            constructorObject = classObject.getConstructor(constructorParamType);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("No Such Method Error");
        }
        return constructorObject;
    }

    public static Object getInstance(Constructor<?> constructorObject) {
        Object instanceObject = null;
        try {
            instanceObject = constructorObject.newInstance("I am in Happy mood");
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return instanceObject;
    }
}