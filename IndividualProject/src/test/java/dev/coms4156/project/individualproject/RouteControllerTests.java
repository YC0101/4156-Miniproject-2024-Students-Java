package dev.coms4156.project.individualproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class contains tests for the RouteController class.
 * Reference ed: https://edstem.org/us/courses/58089/discussion/5251978
 * Reference Youtube tutorial: https://www.youtube.com/watch?v=BZBFw6fBeIU&list=PL82C6-O4XrHcg8sNwpoDDhcxUCbFy855E&index=8
 */
@WebMvcTest(controllers = RouteController.class)
public class RouteControllerTests {

  @BeforeEach
  public void setUp(){
    testDepartment = new Department("ELEN", new HashMap<>(), "Ioannis Kymissis", 250);
    depMap = new HashMap<>();
    myFileDatabase = new MyFileDatabase(0, "./data.txt");
    IndividualProjectApplication.overrideDatabase(myFileDatabase);
  }

  @Test
  public void indexTest() throws Exception {
    mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string("Welcome, in order to make an API call direct your browser or Postman to an endpoint "
                    + "\n\n This can be done using the following format: \n\n http:127.0.0.1:8080/endpoint?arg=value"));
  }
  @Test
  public void RetrieveDepartmentNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveDept")
                    .param("deptCode", "1000"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void RetrieveDepartmentFoundTest() throws Exception {
    depMap =IndividualProjectApplication
            .myFileDatabase.getDepartmentMapping();
    mockMvc.perform(get("/retrieveDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string(depMap.get("COMS").toString()));
  }

  @Test
  public void RetrieveCourseNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
                    .param("deptCode", "1000")
                    .param("courseCode", "1004"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
    mockMvc.perform(get("/retrieveCourse")
                    .param("deptCode", "COMS")
                    .param("courseCode", "10041"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void RetrieveCourseFoundTest() throws Exception {
    HashMap<String, Course> coursesMap;
    coursesMap = IndividualProjectApplication.myFileDatabase
            .getDepartmentMapping().get("COMS").getCourseSelection();
    mockMvc.perform(get("/retrieveCourse")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string(coursesMap.get("1004").toString()));
  }

  @Test
  public void isCourseFullTest() throws Exception {
    mockMvc.perform(get("/isCourseFull")
                    .param("deptCode", "IEOR")
                    .param("courseCode", "4106"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    mockMvc.perform(get("/isCourseFull")
                    .param("deptCode", "IEOR")
                    .param("courseCode", "4540"))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
  }

  @Test
  public void isCourseFullNotFoundTest() throws Exception {
    mockMvc.perform(get("/isCourseFull")
                    .param("deptCode", "1111")
                    .param("courseCode", "4106"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
    mockMvc.perform(get("/isCourseFull")
                    .param("deptCode", "IEOR")
                    .param("courseCode", "454011"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void getMajorCtFromDeptTest() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 2700 majors in the department"));
  }

  @Test
  public void getMajorCtFromDeptNotFoundTest() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
                    .param("deptCode", "1111"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void identifyDeptChairTest() throws Exception {
    mockMvc.perform(get("/idDeptChair")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Luca Carloni is the department chair."));
  }

  @Test
  public void identifyDeptChairNotFoundTest() throws Exception {
    mockMvc.perform(get("/idDeptChair")
                    .param("deptCode", "1111"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void findCourseLocationTest() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("417 IAB is where the course is located."));
  }

  @Test
  public void findCourseLocationNotFoundTest() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void findCourseInstructorTest() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Adam Cannon is the instructor for the course."));
  }

  @Test
  public void findCourseInstructorNotFoundTest() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void findCourseTimeTest() throws Exception {
    mockMvc.perform(get("/findCourseTime")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("The course meets at: 11:40-12:55 some time "));
  }

  @Test
  public void findCourseTimeNotFoundTest() throws Exception {
    mockMvc.perform(get("/findCourseTime")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void addMajorToDeptTest() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attribute was updated successfully"));
    mockMvc.perform(get("/getMajorCountFromDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 2701 majors in the department"));
  }

  @Test
  public void addMajorToDeptNotFoundTest() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
                    .param("deptCode", "1111"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper deptMapper;
  @MockBean
  private MyFileDatabase myFileDatabase;
  private Department testDepartment;
  private HashMap<String, Department> depMap;
}
