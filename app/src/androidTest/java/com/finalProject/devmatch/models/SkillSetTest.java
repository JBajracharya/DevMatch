package com.finalProject.devmatch.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillSetTest {

    SkillSet skills;

    @Before
    public void makeSkillset(){
        skills = new SkillSet();
        skills.setJavascript(true);
    }

    @Test
    public void testJavascriptTrue(){
        assertTrue(skills.isJavascript());
    }
}