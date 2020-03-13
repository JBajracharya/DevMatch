package com.finalProject.devmatch.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkillSetTest {

    @Test
    public void getId() {
        SkillSet testSkillSet = new SkillSet();

        testSkillSet.setId("testId");

        assertTrue(testSkillSet.getId() == "testId");
    }
}