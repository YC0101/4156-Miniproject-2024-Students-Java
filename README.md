# Trader Project

This is a Trading website project.
The Code is written using IntelliJ IDEA & VScode.

# Building and Running a Local Instance

Before using this service, please follow the steps below to set up environment for MacOS.

1. Maven 3.9.5: https://maven.apache.org/download.cgi Download and follow the installation instructions.
2. JDK 17: This project used JDK 17 for development https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
3. IntelliJ IDE: https://www.jetbrains.com/idea/
4. After you clone this project, go to /TraderProject and run `mvn spring-boot:run -Dspring-boot.run.arguments="setup"` for MacOS. Leave it running until you see “System Setup” in the terminal then terminate the program.
5. After initializes the database in step 4, you may run the project using `mvn spring-boot:run`
6. If you wish to check the style, run `mvn checkstyle:check`; to see coverage report, run `mvn jacoco:report` and open the coverage report in the target/site/jacoco.

For detailed info about endpoints, chechk the "Endpoints" section.

# Running Tests

All the test file are under the directory 'src/test'.

1. Tests can be run with `mvn clean test` in terminal, you may also use the buttons in IntelliJ IDEA and see outputs in IntelliJ console.
2. deSerializeObjectFromFileTest() in MyFileDatabasedTests will give System.out.print in console for one to check the read file status.
3. saveContentsToFileTest() will create a testdata.txt for user to check save file output.

# Endpoints

**GET/NAME**

- Parameters:
- Return:
- Upon Success:
- Upon Failure:

**Patch/NAME**

- Parameters:
- Return:
- Upon Success:
- Upon Failure:

# implementation(sample):

1. retrieveCourses takes a courseCode(int) and return the String representation of all the courses with the specified course code or displays the proper error message in response to the request. It returns OK if the course(s) is(are) found, NOTFOUND if not found.
2. enrollStudentInCourse endpoint attempt to enroll a student in a course specified by the department id as well as the course code.It takes department id as well as the course code and return ResponseEntity<> OK if enroll is successful; BadRequest if student can't be enrolled; Not Found if course is not found.
3. Added .idea/ and .DS_Store to gitignore

# implementation(sample):

1. Follow this sample to record progress
2. Google GCP target url:
3. Rearrange this README file you are reading ;D
4. Instance screenshots:
   ![Screenshots of instance.](/screenshots/instance2.png)
   ![Screenshots of instance.](/screenshots/instance1.png)
5. Add tests for exceptions testHandleRetrieveCourseException(), testHandleFindCourseTimeException() in RoutControllerTests..
   ![Screenshots of coverage.](/screenshots/coverage1.png)
6. Checkstyle screen shot:
   ![Screenshots of coverage.](/screenshots/checkstyle1.png)
