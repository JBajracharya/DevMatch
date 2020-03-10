package com.finalProject.devmatch.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeveloperTest {

    SkillSet skills;
    Developer dev;

    @Before
    public void makeDeveloper(){
        skills = new SkillSet();
        skills.language.setJavascript(true);
        dev = new Developer("Devin","Github.com/test","Devinthetestdev@gmail.com");
        dev.setSkills(skills);
    }

    @Test
    public void testDeveloperJavascriptTrue(){
        assertTrue(dev.getSkills().language.isJavascript());
    }
}