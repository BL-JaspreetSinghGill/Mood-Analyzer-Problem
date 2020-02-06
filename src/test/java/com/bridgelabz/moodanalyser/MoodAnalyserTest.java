package com.bridgelabz.moodanalyser;

import com.bridgelabz.moodanalyser.com.bridgelabz.moodanalyser.enums.ExceptionType;
import com.bridgelabz.moodanalyser.exceptions.MoodAnalysisException;
import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void givenMoodAnalyserClass_WhenProper_ShouldReturnObject() {
        MoodAnalyser moodAnalyser = MoodAnalyserFactory.createObject("com.bridgelabz.moodanalyser.MoodAnalyser");
        Assert.assertEquals(new MoodAnalyser("I am in Happy mood"), moodAnalyser);
    }

    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalysisException() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserFactory.createObject("");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("No Such Class Error", e.getMessage());
        }
    }
}
