package com.master.nst.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.nst.NstApplication;
import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.EmployeeTitle;
import com.master.nst.domain.EmployeeVocation;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.repository.EmployeeRepository;
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
import java.util.Calendar;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NstApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeTest {

    private static final String GENERAL_PATH = "/application/employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    private static ObjectMapper objectMapper;
    private static String employeeEdit;
    private static String employeeAdd;
    private static String employeeGet;
    private static String employeeAll;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public static void prepare() throws Exception {
        objectMapper = new ObjectMapper();
        employeeEdit = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/employee/employee_edit.json").toURI()), "UTF-8");
        employeeAdd = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/employee/employee_add.json").toURI()), "UTF-8");
        employeeGet = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/employee/employee_get.json").toURI()), "UTF-8");
        employeeAll = FileUtils.readFileToString(
            new File(FileUtils.class.getResource("/employee/employee_all.json").toURI()), "UTF-8");
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void testGetEmployee() throws Exception {
        EmployeeEntity employeeEntity = employeeRepository.findByPersonalIdentificationNumber("1234567891234").get();
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/{employeeId}", employeeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(employeeGet));
    }

    @Test
    @Transactional
    @Rollback
    public void testAddEmployee() throws Exception {
        final EmployeeCmd employeeCmd = new EmployeeCmd();
        employeeCmd.setName("Pera");
        employeeCmd.setSurname("Peric");
        employeeCmd.setAddress("Adresa");
        employeeCmd.setPersonalIdentificationNumber("9874563214568");
        Calendar c = Calendar.getInstance();
        c.set(1990 , 10, 10);
        employeeCmd.setDateOfBirth(c.getTime());
        employeeCmd.setDateOfEmployment(c.getTime());
        employeeCmd.setTitle(EmployeeTitle.DR);
        employeeCmd.setVocation(EmployeeVocation.REDOVNI_PROFESOR);
        this.mockMvc
            .perform(post(GENERAL_PATH)
                         .contentType(MediaType.APPLICATION_JSON_UTF8)
                         .content(objectMapper.writeValueAsString(employeeCmd)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(employeeAdd));

    }

    @Test
    @Transactional
    @Rollback
    public void testEditEmployee() throws Exception {
        EmployeeEntity employeeEntity = employeeRepository.findByPersonalIdentificationNumber("1234567891234").get();
        MvcResult mvcResult = this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/{employeeId}", employeeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        JsonNode jsonNodeRoot = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        Employee employee = objectMapper.readValue(jsonNodeRoot.get("data").toString(), Employee.class);

        employee.setName("Update name");

        this.mockMvc
            .perform(MockMvcRequestBuilders.put(GENERAL_PATH + "/{employeeId}", employeeEntity.getId())
                         .contentType(MediaType.APPLICATION_JSON_UTF8)
                         .content(objectMapper.writeValueAsString(employee)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(employeeEdit));
    }

    @Test
    @Transactional
    public void testGetAll() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(GENERAL_PATH + "/findAllEmployees"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(employeeAll));
    }

}
