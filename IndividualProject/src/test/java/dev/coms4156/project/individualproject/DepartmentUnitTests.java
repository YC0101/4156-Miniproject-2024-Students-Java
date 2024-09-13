package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains tests for the Department class.
 */
public class DepartmentUnitTests {

  @BeforeAll
  public static void setupDepartmentForTesting() {
    testCourse = new Course("Kenneth Shepard", "1205 MUDD", "4:10-6:40", 45);
    testCourse2 = new Course("David G Vallancourt", "301 PUP", "4:10-5:25", 33);
    testCourse3 = new Course("Keren Bergman", "829 MUDD", "2:40-3:55", 45);

    testCourseMap = new HashMap<>();
    testCourseMaptoString = new HashMap<>();

    testCourseMaptoString.put("1001",testCourse);
    testCourseMap.put("1001",testCourse);
    testCourseMap.put("1002",testCourse2);
    testDept = new Department("3082",testCourseMap,"Luca Carloni",4);
    testDept2 = new Department("3082",testCourseMap,"Luca Carloni",4);
    testDept3 = new Department("3082",testCourseMap,"Luca Carloni",4);
    testDept4 = new Department("3082",testCourseMap,"Luca Carloni",4);
    testDeptToString = new Department("3082",testCourseMaptoString,"Luca Carloni",4);
  }

  @Test
  public void getNumberOfMajorsTest(){
    assertEquals(4, testDept.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest(){
    assertEquals("Luca Carloni", testDept.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest(){
    assertEquals(testCourseMap, testDept.getCourseSelection());
  }

  @Test
  public void addPersonToMajorTest(){
    assertEquals(4, testDept2.getNumberOfMajors());
    testDept2.addPersonToMajor();
    assertEquals(5, testDept2.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorTest(){
    testDept3.dropPersonFromMajor();
    assertEquals(3, testDept3.getNumberOfMajors());
    testDept3.dropPersonFromMajor();
    testDept3.dropPersonFromMajor();
    testDept3.dropPersonFromMajor();
    assertEquals(0, testDept3.getNumberOfMajors());
    testDept3.dropPersonFromMajor();
    assertEquals(0, testDept3.getNumberOfMajors());
  }

  @Test
  public void addCourseTest(){
    testDept3.addCourse("1003",testCourse3);
    HashMap<String, Course> expectedResult = new HashMap<>();
    expectedResult.put("1001",testCourse);
    expectedResult.put("1002",testCourse2);
    expectedResult.put("1003",testCourse3);
    assertEquals(expectedResult, testDept3.getCourseSelection());
  }

  @Test
  public void createCourseTest(){
    testDept4.createCourse("1003","Keren Bergman", "829 MUDD", "2:40-3:55", 45);
    HashMap<String, Course> expectedResult = new HashMap<>();
    expectedResult.put("1001",testCourse);
    expectedResult.put("1002",testCourse2);
    expectedResult.put("1003",testCourse3);
    assertEquals(expectedResult.toString(), testDept2.getCourseSelection().toString());
  }

  @Test
  public void toStringTest(){
    //System.out.println("PRINT:" + testDeptToString.toString());
    String expectedResult = "3082 1001: \n" +
            "Instructor: Kenneth Shepard; Location: 1205 MUDD; Time: 4:10-6:40\n";
    assertEquals(expectedResult, testDeptToString.toString());
  }

  /** The test department instance and course hashmap used for testing. */
  public static Department testDept,testDept2,testDept3,testDept4, testDeptToString;
  /** The test course instance used for testing. */
  public static Course testCourse, testCourse2,testCourse3;
  public static HashMap<String, Course> testCourseMap,testCourseMaptoString;
}
