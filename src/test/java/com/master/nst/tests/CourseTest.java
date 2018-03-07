package com.master.nst.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.nst.NstApplication;
import com.master.nst.domain.CourseEntity;
import com.master.nst.mapper.CourseMapper;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.model.thematicunit.ThematicUnitCmd;
import com.master.nst.repository.CourseRepository;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NstApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseTest {

    private static final String GENERAL_PATH = "/application/course";

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

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
        CourseEntity courseEntity = courseRepository.findByName("Napredne softverske tehnologije").get();
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/{courseId}", courseEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(courseGet));
    }

    @Test
    @Transactional
    @Rollback
    public void testAddCourse() throws Exception {
        final CourseCmd courseCmd = new CourseCmd();
        courseCmd.setName("Novi predmet");
        courseCmd.setLevelOfStudiesId(1l);
        courseCmd.setDepartmentId(1l);
        courseCmd.setEspb(6);
        courseCmd.setGoal("Cilj predmeta");
        courseCmd.setMethodOfEvaluation("Nacin ocenjivanja");
        courseCmd.setLecturerList(setLecturerList());
        courseCmd.setThematicUnitsList(setThematicUnitList());

        this.mockMvc
            .perform(post(GENERAL_PATH)
                         .contentType(MediaType.APPLICATION_JSON_UTF8)
                         .content(objectMapper.writeValueAsString(courseCmd)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(courseAdd));

    }

    private List<ThematicUnitCmd> setThematicUnitList() {
        ThematicUnitCmd thematicUnitCmd = new ThematicUnitCmd();
        thematicUnitCmd.setName("Tematska jedinica");
        thematicUnitCmd.setDescription("Opis tematske jedinice");
        thematicUnitCmd.setSerialNumber("1.");
        List<ThematicUnitCmd>  list = new ArrayList<>();
        list.add(thematicUnitCmd);
        return list;
    }

    private List<LecturerCmd> setLecturerList(){
        LecturerCmd lecturerCmd = new LecturerCmd();
        lecturerCmd.setEmployeeId(1l);
        lecturerCmd.setTeachingTypeId(1l);
        List<LecturerCmd> list = new ArrayList<>();
        list.add(lecturerCmd);
        return list;
    }

    @Test
    @Transactional
    @Rollback
    public void testEditCourse() throws Exception {
        CourseEntity courseEntity = courseRepository.findByName("Napredne softverske tehnologije").get();
        MvcResult mvcResult = this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/{courseId}", courseEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        JsonNode jsonNodeRoot = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        Course course = objectMapper.readValue(jsonNodeRoot.get("data").toString(), Course.class);

        course.setName("Update name");

        CourseCmd courseCmd = courseMapper.courseToCourseCmd(course);

        this.mockMvc
            .perform(MockMvcRequestBuilders.put(GENERAL_PATH + "/{courseId}", courseEntity.getId())
                         .contentType(MediaType.APPLICATION_JSON_UTF8)
                         .content(objectMapper.writeValueAsString(courseCmd)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(courseEdit));
    }

    @Test
    @Transactional
    public void testGetAll() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/findAllCourses"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(courseAll));
    }

}
