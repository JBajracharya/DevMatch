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
        skills.setReact(true);
        skills.setiOS(true);
    }

    @Test
    public void testJavascriptTrue(){
        assertTrue(skills.isJavascript());
        assertTrue(skills.isReact());
        assertTrue(skills.isiOS());
    }
}