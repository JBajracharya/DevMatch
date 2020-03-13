package com.finalProject.devmatch.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectsTest {

    Projects project;

    @Before
    public void startProject(){

        project = new Projects("Test Project","Test Description","Fake Date","LINK");

    }
    @Test
    public void testGetters(){
        assertEquals("Test Project",project.getName());
        assertEquals("Test Description",project.getDescription());
        assertEquals("Fake Date",project.getDate());
        assertEquals("LINK",project.getLink());
    }
    @Test
    public void testSetters(){
        project.setName("New Name");
        project.setDescription("New Description");
        project.setDate("New Date");
        project.setLink("NEW LINK");
        assertEquals("New Name",project.getName());
        assertEquals("New Description",project.getDescription());
        assertEquals("New Date",project.getDate());
        assertEquals("NEW LINK",project.getLink());
    }

}