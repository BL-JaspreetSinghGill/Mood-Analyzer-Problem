package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ConstructorType;
import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ExceptionType;
import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class MoodAnalyserTest {

    @Test
    public void givenMessage_WhenContainsSad_ShouldReturnSad() {
        MoodAnalyser moodAnalyser = new MoodAnalyser();
        String mood = moodAnalyser.analyseMood("I am in Sad mood");
        Assert.assertEquals("SAD", mood);
    }

    @Test
    public void givenMessage_WhenContainsAnyMood_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser();
        String mood = moodAnalyser.analyseMood("I am in any mood");
        Assert.assertEquals("HAPPY", mood);
    }

    @Test
    public void givenMessageInConstructor_WhenContainsSad_ShouldReturnSad() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in Sad mood");
        String mood = moodAnalyser.analyseMood();
        Assert.assertEquals("SAD", mood);
    }

    @Test
    public void givenMessageInConstructor_WhenContainsHappy_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in Happy mood");
        String mood = moodAnalyser.analyseMood();
        Assert.assertEquals("HAPPY", mood);
    }

    @Test
    public void givenMessageInConstructor_WhenNull_ShouldThrowMoodAnalysisException() {
        try {
            MoodAnalyser moodAnalyser = new MoodAnalyser(null);
            moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(ExceptionType.ENTERED_NULL, e.exceptionType);
        }
    }

    @Test
    public void givenMessageInConstructor_WhenEmpty_ShouldThrowMoodAnalysisException() {
        try {
            MoodAnalyser moodAnalyser = new MoodAnalyser("");
            moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(ExceptionType.ENTERED_EMPTY, e.exceptionType);
        }
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenMoodAnalyserClass_WhenProperWithUseOfDefaultConstr_ShouldReturnObject() {
        MoodAnalyser moodAnalyser = ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", null, ConstructorType.DEFAULT, "message", "I am in Happy mood");
        Assert.assertEquals(new MoodAnalyser("I am in Happy mood"), moodAnalyser);
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalysisException() {
        try {
            ObjectReflector.createObject("", null, ConstructorType.DEFAULT, null,null);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("No Such Class Error", e.getMessage());
        }
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenClassName_WhenProperWithImproperConstr_ShouldThrowMoodAnalysisException() {
        try {
            ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", Integer.class, ConstructorType.DEFAULT, null,null);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("No Such Method Error", e.getMessage());
        }
    }

    // PARAMETERIZED CONSTRUCTOR
    @Test
    public void givenMoodAnalyserClass_WhenProperWithUseOfParaConstr_ShouldReturnObject() {
        MoodAnalyser moodAnalyser = ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", String.class, ConstructorType.PARAMETERIZED, null, "I am in Happy mood");
        Assert.assertEquals(new MoodAnalyser("I am in Happy mood"), moodAnalyser);
    }

    // PARAMETERIZED CONSTRUCTOR
    @Test
    public void givenHappyMessage_WhenProper_ShouldReturnHappyMood() {
        MoodAnalyser moodAnalyser = ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", String.class, ConstructorType.PARAMETERIZED, null, "I am in Happy mood");
        Method method = ObjectReflector.getMethod("com.bridgelabz.moodanalyser.MoodAnalyser", "analyseMood");
        Object object = ObjectReflector.invokeMethod(moodAnalyser, method);
        Assert.assertEquals(object.toString(), "HAPPY");
    }

    // PARAMETERIZED CONSTRUCTOR
    @Test
    public void givenMethodName_WhenImproper_ShouldThrowMoodAnalysisException() {
        try {
            ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", String.class, ConstructorType.PARAMETERIZED, null, "I am in Happy mood");
            ObjectReflector.getMethod("com.bridgelabz.moodanalyser.MoodAnalyser", "analyseMoody");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("No Such Method Error", e.getMessage());
        }
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenHappyMessageInField_WhenProper_ShouldReturnHappyMood() {
        MoodAnalyser moodAnalyser = ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", null, ConstructorType.DEFAULT, "message", "I am in Happy mood");
        Method method = ObjectReflector.getMethod("com.bridgelabz.moodanalyser.MoodAnalyser", "analyseMood");
        Object object = ObjectReflector.invokeMethod(moodAnalyser, method);
        Assert.assertEquals(object.toString(), "HAPPY");
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenHappyMessageInField_WhenImproper_ShouldThrowMoodAnalysisException() {
        try {
            ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", null, ConstructorType.DEFAULT, "messages", "I am in Happy mood");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("No Such Field error", e.getMessage());
        }
    }

    // DEFAULT CONSTRUCTOR
    @Test
    public void givenMessageInField_WhenNull_ShouldThrowMoodAnalysisException() {
        try {
            ObjectReflector.createObject("com.bridgelabz.moodanalyser.MoodAnalyser", null, ConstructorType.DEFAULT, "message", null);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(e.getMessage(), "Please enter valid message");
        }
    }
}
