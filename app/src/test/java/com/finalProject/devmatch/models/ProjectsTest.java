package com.finalProject.devmatch.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectsTest {


    Projects testProject = new Projects();


    @Test
    public void getName() {
        testProject.setName("testname");

        assertTrue(testProject.getName() == "testname");
    }

    @Test
    public void getDescription() {
        testProject.setDescription("testDescription");

        assertTrue(testProject.getDescription() == "testDescription");
    }

    @Test
    public void getDate() {
        testProject.setDate("testDate");

        assertTrue(testProject.getDate() == "testDate");
    }

    @Test
    public void getLink() {
        testProject.setLink("testLink");

        assertTrue(testProject.getLink() == "testLink");
    }



    @Test
    public void getId() {
        testProject.setId("testId");

        assertTrue(testProject.getId() == "testId");
    }

    @Test
    public void getOwner() {
        testProject.setOwner("testOwner");

        assertTrue(testProject.getOwner() == "testOwner");
    }


}