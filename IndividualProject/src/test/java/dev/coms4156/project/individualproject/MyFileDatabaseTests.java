package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains tests for the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseTests {

  /**
   * Create variable for testing.
   */
  @BeforeAll
  public static void setupDepartmentForTesting() {
    testDb = new MyFileDatabase(1, "/Users/ireneisabug/Desktop/4156/4156-Miniproject-2024-Students-Java/IndividualProject/data.txt");
    testDb2 = new MyFileDatabase(1, "data.java");
    testDb3 = new MyFileDatabase(3, "testdata.txt");

    testCourse = new Course("Kenneth Shepard", "1205 MUDD", "4:10-6:40", 45);
    testCourse2 = new Course("David G Vallancourt", "301 PUP", "4:10-5:25", 33);
    testCourse3 = new Course("Keren Bergman", "829 MUDD", "2:40-3:55", 45);

    testCourseMap = new HashMap<>();
    testCourseMap2 = new HashMap<>();

    testCourseMap.put("1001", testCourse);
    testCourseMap2.put("1002", testCourse2);
    testDept = new Department("3082", testCourseMap, "Luca Carloni", 4);
    testDept2 = new Department("3083", testCourseMap2, "Mohamed Kamaludeen", 2);

    testDeptMap = new HashMap<>();
    testDeptMap.put("3082", testDept);
    testDeptMap.put("3083", testDept2);
  }

  @Test
  public void setMappingTest() {
    testDb.setMapping(testDeptMap);
    assertEquals(testDeptMap, testDb.getDepartmentMapping());
  }

  @Test
  public void toStringTest() {
    testDb.setMapping(testDeptMap);
    String expect = "For the 3082 department: \n"
            + "3082 1001: \n"
            + "Instructor: Kenneth Shepard; Location: 1205 MUDD; Time: 4:10-6:40\n"
            + "For the 3083 department: \n"
            + "3083 1002: \n"
            + "Instructor: David G Vallancourt; Location: 301 PUP; Time: 4:10-5:25\n";
    assertEquals(expect, testDb.toString());
  }

  @Test
  public void deSerializeObjectFromFileTest() {
    assertEquals(null, testDb2.deSerializeObjectFromFile());
    System.out.println("Print:"+testDb.deSerializeObjectFromFile());
  }

  /** The testdata.txt output can be find in IndividualProject/testdata.txt. */
  @Test
  public void saveContentsToFileTest() {
    testDb3.setMapping(testDeptMap);
    testDb3.saveContentsToFile();
  }

  /** The test database instance for testing. */
  public static MyFileDatabase testDb;
  public static MyFileDatabase testDb2;
  public static MyFileDatabase testDb3;
  public static MyFileDatabase testDb4;
  public static Department testDept;
  public static Department testDept2;
  /** The test course instance used for testing. */
  public static Course testCourse;
  public static Course testCourse2;
  public static Course testCourse3;
  public static HashMap<String, Course> testCourseMap;
  public static HashMap<String, Course> testCourseMap2;
  public static HashMap<String, Department> testDeptMap;
}
