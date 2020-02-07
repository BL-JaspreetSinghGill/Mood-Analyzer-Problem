package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ConstructorType;
import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserFactory {

    public static MoodAnalyser createObject(String className, Class<?> constructorParamType, ConstructorType constructorType) {
        boolean isValidClassName = checkValidClassName(className);
        if (!isValidClassName) {
            throw new MoodAnalysisException("No Such Class Error");
        }

        Class<?> classObject = getClassObject(className);
        Constructor<?> constructorObject = getConstructorObject(classObject, constructorParamType);
        Object instanceObject = getInstance(constructorObject, constructorType);
        MoodAnalyser moodAnalyser = (MoodAnalyser) instanceObject;
        if (constructorType.toString().equals(ConstructorType.DEFAULT.toString())) {
            getField(classObject, "message", "I am in Happy mood", moodAnalyser);
        }
        return moodAnalyser;
    }

    private static boolean checkValidClassName(String className) {
        if (className.equals("com.bridgelabz.moodanalyser.MoodAnalyser")) {
            return true;
        }
        return false;
    }

    private static Class<?> getClassObject(String className) {
        Class<?> classObject = null;
        try {
            classObject = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classObject;
    }

    private static Constructor<?> getConstructorObject(Class<?> classObject, Class<?> constructorParamType) {
        Constructor<?> constructorObject = null;
        try {
            if (constructorParamType == null) {
                constructorObject = classObject.getConstructor();
            } else {
                constructorObject = classObject.getConstructor(constructorParamType);
            }
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("No Such Method Error");
        }
        return constructorObject;
    }

    private static Object getInstance(Constructor<?> constructorObject, ConstructorType constructorType) {
        Object instanceObject = null;
        try {
            if (constructorType.toString().equals(ConstructorType.DEFAULT.toString())) {
                instanceObject = constructorObject.newInstance();
            } else {
                instanceObject = constructorObject.newInstance("I am in Happy mood");
            }
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return instanceObject;
    }

    private static void getField(Class<?> classObject, String name, String value, Object object) {
        try {
            Field fieldName = classObject.getDeclaredField(name);
            fieldName.setAccessible(true);
            MoodAnalyser modMoodAnalyser = (MoodAnalyser) object;
            fieldName.set(modMoodAnalyser, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
