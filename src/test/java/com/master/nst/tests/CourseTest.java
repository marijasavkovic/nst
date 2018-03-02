package com.master.nst.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.nst.NstApplication;
import com.master.nst.repository.CourseRepository;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NstApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseTest {

    private static final String GENERAL_PATH = "/application/employee";

    @Autowired
    private CourseRepository courseRepository;

    private static ObjectMapper objectMapper;
    private static String courseEdit;
    private static String courseAdd;
    private static String courseGet;
    private static String courseAll;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public static void prepare() throws Exception {
        objectMapper = new ObjectMapper();
        courseEdit = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/course/course_edit.json").toURI()), "UTF-8");
        courseAdd = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/course/course_add.json").toURI()), "UTF-8");
        courseGet = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/course/course_get.json").toURI()), "UTF-8");
        courseAll = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/course/course_all.json").toURI()), "UTF-8");
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void testGetEmployee() throws Exception {
//        CourseEntity courseEntity = courseRepository.findByPersonalIdentificationNumber("1234567891234").get();
//        this.mockMvc
//            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/{employeeId}", employeeEntity.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//            .andExpect(content().json(employeeGet));
    }

}
