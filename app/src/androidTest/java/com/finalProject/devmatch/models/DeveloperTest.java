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
        skills.getLanguage().setJavascript(true);
        dev = new Developer("Devin","Github.com/test","Devinthetestdev@gmail.com");
        dev.setSkills(skills);
        dev.setType("Front End");
    }

    @Test
    public void testDeveloperJavascriptTrue(){
        assertTrue(dev.getSkills().getLanguage().isJavascript());
    }
    @Test
    public void testEnumsAreWorking(){
        System.out.println(dev.getType());
    }
}