package com.finalProject.devmatch.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeveloperTest {




    @Test
    public void getName() {
        Developer devTest = new Developer();

        devTest.setName("name");
        assertTrue(devTest.getName() == "name");

    }

    @Test
    public void getGithub() {
        Developer devTest = new Developer();

        devTest.setGithub("myGithub.com");
        assertTrue(devTest.getGithub() == "myGithub.com");
    }

    @Test
    public void getEmail() {
        Developer devTest = new Developer();

        devTest.setEmail("myEmail.com");
        assertTrue(devTest.getEmail() == "myEmail.com");
    }

    @Test
    public void getSkills() {
        Developer devTest = new Developer();

        SkillSet awesomeSkills = new SkillSet();

        devTest.setSkills(awesomeSkills);
        assertTrue(devTest.getSkills() == awesomeSkills);
    }


    @Test
    public void getUsername() {
        Developer devTest = new Developer();

        devTest.setUsername("testUser");
        assertTrue(devTest.getUsername() == "testUser");
    }

    @Test
    public void getId() {
        Developer devTest = new Developer();

        devTest.setId("First");
        assertTrue(devTest.getId() == "First");
    }


}