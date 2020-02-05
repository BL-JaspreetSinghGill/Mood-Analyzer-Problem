package com.bridgelabz.moodanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoodAnalyserTest {

    private MoodAnalyser moodAnalyser;

    @Before
    public void initializer() {
        moodAnalyser = new MoodAnalyser();
    }

    @Test
    public void givenMessage_WhenContainsSad_ShouldReturnSad() {
        String mood = moodAnalyser.analyseMood("I am in Sad mood");
        Assert.assertEquals("SAD", mood);
    }

    @Test
    public void givenMessage_WhenContainsAnyMood_ShouldReturnHappy() {
        String mood = moodAnalyser.analyseMood("I am in any mood");
        Assert.assertEquals("HAPPY", mood);
    }
}
