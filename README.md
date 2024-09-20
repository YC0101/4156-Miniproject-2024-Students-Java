# Welcome Students of 4156

Please follow the assignment specifications on Courseworks when completing this project.

The Code is written using IntelliJ IDEA. And mvn/jaCoco are run in IntelliJ IDEA as well.

HowToTestNote:
1. Tests can be run with "mvn clean test"
2. deSerializeObjectFromFileTest() in MyFileDatabasedTests will give System.out.print in console for one to check the read file status.
3. saveContentsToFileTest() will create a testdata.txt for user to check save file output.

I2 implementation:

1. retrieveCourses takes a courseCode(int) and return the String representation of all the courses with the specified course code or displays the proper error message in response to the request.
2. enrollStudentInCourse endpoint attempt to enroll a student in a course specified by the department id as well as the course code.It takes department id as well as the course code and return ResponseEntity<> OK if enroll is successful; BadRequest if student can't be enrolled; Not Found if course is not found.