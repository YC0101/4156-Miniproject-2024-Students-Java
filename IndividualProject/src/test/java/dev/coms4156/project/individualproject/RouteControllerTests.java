package dev.coms4156.project.individualproject;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class contains tests for the RouteController class.
 * Reference ed: https://edstem.org/us/courses/58089/discussion/5251978
 * Reference Youtube tutorial: https://www.youtube.com/watch?v=BZBFw6fBeIU&list=PL82C6-O4XrHcg8sNwpoDDhcxUCbFy855E&index=8
 */
@WebMvcTest(controllers = RouteController.class)
public class RouteControllerTests {

  /**
   * This class contains tests for the RouteController class.
   */
  @BeforeEach
  public void setUp() {
    depMap = new HashMap<>();
    myFileDatabase = new MyFileDatabase(0, "./data.txt");
    IndividualProjectApplication.overrideDatabase(myFileDatabase);
  }

  @Test
  public void indexTest() throws Exception {
    mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content()
                    .string("Welcome, in order to make an API "
                            + "call direct your browser or Postman to an endpoint "
                    + "\n\n This can be done using the following format: \n\n "
                            + "http:127.0.0.1:8080/endpoint?arg=value"));
  }

  @Test
  public void retrieveDepartmentNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveDept")
                    .param("deptCode", "1000"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void retrieveDepartmentFoundTest() throws Exception {
    depMap = IndividualProjectApplication
            .myFileDatabase.getDepartmentMapping();
    mockMvc.perform(get("/retrieveDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string(depMap.get("COMS").toString()));
  }

  @Test
  public void retrieveCourseNotFoundTest() throws Exception {
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
  public void retrieveCourseFoundTest() throws Exception {
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
  public void retrieveCoursesNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveCourses")
                    .param("courseCode", "100411"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void retrieveCoursesFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveCourses")
                    .param("courseCode", "1001"))
            .andExpect(status().isOk())
            .andExpect(content().string(org.hamcrest.Matchers.containsString("PSYC 1001")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("PHYS 1001")));
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

  @Test
  public void removeMajorFromDeptTest() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attribute was updated or is at minimum"));
    mockMvc.perform(get("/getMajorCountFromDept")
                    .param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 2699 majors in the department"));
  }

  @Test
  public void removeMajorFromDeptNotFoundTest() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
                    .param("deptCode", "1111"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void dropStudentTest() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Student has been dropped."));
  }

  @Test
  public void enrollStudentTest() throws Exception {
    mockMvc.perform(patch("/enrollStudentInCourse")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Student has been enrolled."));
  }

  @Test
  public void enrollStudentFailTest() throws Exception {
    mockMvc.perform(patch("/enrollStudentInCourse")
                    .param("deptCode", "IEOR")
                    .param("courseCode", "2500"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Student has not been enrolled."));
  }

  @Test
  public void dropStudentNotFoundTest() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void enrollStudentNotFoundTest() throws Exception {
    mockMvc.perform(patch("/enrollStudentInCourse")
                    .param("deptCode", "11111")
                    .param("courseCode", "250011"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void setEnrollmentCountTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("count", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(patch("/dropStudentFromCourse")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Student has not been dropped."));
  }

  @Test
  public void setEnrollmentCountNotFoundTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004")
                    .param("count", "0"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseTimeTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("time", "4:10-5:25"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseTime")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("The course meets at: 4:10-5:25 some time "));
  }

  @Test
  public void changeCourseTimeNotFoundTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004")
                    .param("time", "0"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseTeacherTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("teacher", "Brian Borowski"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseInstructor")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Brian Borowski is the instructor for the course."));
  }

  @Test
  public void changeCourseTeacherNotFoundTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004")
                    .param("teacher", "Brian Borowski"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseLocationTest() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("location", "309 HAV"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseLocation")
                    .param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("309 HAV is where the course is located."));
  }

  @Test
  public void changeCourseLocationNotFoundTest() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
                    .param("deptCode", "1111")
                    .param("courseCode", "1004")
                    .param("location", "309 HAV"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Course Not Found"));
  }

  //  @Test
  //  public void testHandleException() throws Exception {
  //    when(myFileDatabase.getDepartmentMapping())
  //          .thenThrow(new RuntimeException("Simulated exception"));
  //
  //    mockMvc.perform(get("/retrieveCourses")
  //                    .param("courseCode", "1004"))
  //            .andExpect(status().isInternalServerError())
  //            .andExpect(content().string("An error occurred."));
  //  }

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper deptMapper;
  @MockBean
  private MyFileDatabase myFileDatabase;
  private HashMap<String, Department> depMap;
}
