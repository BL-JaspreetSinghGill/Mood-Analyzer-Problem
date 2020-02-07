package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ConstructorType;
import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectReflector {

    public static MoodAnalyser createObject(String className, Class<?> constructorParamType, ConstructorType constructorType, String attributeName, String message) {
        Class<?> classObject = getClassObject(className);
        Constructor<?> constructorObject = getConstructorObject(classObject, constructorParamType);
        Object instanceObject = getInstance(constructorObject, constructorType, message);
        MoodAnalyser moodAnalyser = (MoodAnalyser) instanceObject;
        if (constructorType.toString().equals(ConstructorType.DEFAULT.toString())) {
            setField(classObject, attributeName, message, moodAnalyser);
        }
        return moodAnalyser;
    }

    public static Method getMethod(String className, String methodName) {
        Method method = null;
        Class<?> classObject = getClassObject(className);
        try {
            method  = classObject.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("No Such Method Error");
        }
        return method;
    }

    public static Object invokeMethod(Object object, Method method) {
        Object obj = null;
        MoodAnalyser moodAnalyser = (MoodAnalyser) object;
        try {
            obj= method.invoke(moodAnalyser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static Class<?> getClassObject(String className) {
        Class<?> classObject = null;
        try {
            classObject = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalysisException("No Such Class Error");
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

    private static Object getInstance(Constructor<?> constructorObject, ConstructorType constructorType, String message) {
        Object instanceObject = null;
        try {
            if (constructorType.toString().equals(ConstructorType.DEFAULT.toString())) {
                instanceObject = constructorObject.newInstance();
            } else {
                instanceObject = constructorObject.newInstance(message);
            }
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return instanceObject;
    }

    private static void setField(Class<?> classObject, String attributeName, String value, Object object) {
        try {
            if (value == null) {
                throw new MoodAnalysisException("Please enter valid message");
            }
            Field fieldName = classObject.getDeclaredField(attributeName);
            fieldName.setAccessible(true);
            MoodAnalyser modMoodAnalyser = (MoodAnalyser) object;
            fieldName.set(modMoodAnalyser, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new MoodAnalysisException("No Such Field error");
        }
    }
}
