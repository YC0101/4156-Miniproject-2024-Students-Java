# Indicidual Project for 4156

This is an individual project for COMS4156 Advanced Software Engineering.
The Code is written using IntelliJ IDEA.

# Viewing original assignment repo 
This project is forked from https://github.com/Programming-Systems-Lab/4156-Miniproject-2024-Students-Java 

# Building and Running a Local Instance
Before using this service, please follow the steps below to set up environment for MacOS.

1. Maven 3.9.5: https://maven.apache.org/download.cgi Download and follow the installation instructions.
2. JDK 17: This project used JDK 17 for development https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html 
3. IntelliJ IDE: https://www.jetbrains.com/idea/
4. After you clone this project, go to /IndividualProject and run ```mvn spring-boot:run -Dspring-boot.run.arguments="setup"``` for MacOS. Leave it running until you see “System Setup” in the terminal then terminate the program.
5. After initializes the database in step 4, you may run the project using ```mvn spring-boot:run```
6. If you wish to check the style, run ```mvn checkstyle:check```; to see coverage report, run ```mvn jacoco:report``` and open the coverage report in the target/site/jacoco.

For detailed info about endpoints, chechk the "Endpoints" section.

# Running Tests
All the test file are under the directory 'src/test'.

1. Tests can be run with ```mvn clean test``` in terminal, you may also use the buttons in IntelliJ IDEA and see outputs in IntelliJ console.
2. deSerializeObjectFromFileTest() in MyFileDatabasedTests will give System.out.print in console for one to check the read file status.
3. saveContentsToFileTest() will create a testdata.txt for user to check save file output.

# Endpoints

**GET/retrieveDept**
  - Parameters: deptCode (String)
  - Return: object containing either the details of the Department with provided info.
  - Upon Success: HTTP 200 Status Code is returned along with the details of the department if it exist.
  - Upon Failure: HTTP 404 Status Code with "Department Not Found" if it doesn't exist.

**GET/retrieveCourse**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object containing the details of the course with provided info.
  - Upon Success: HTTP 200 Status Code is returned along with the details of the course if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/retrieveCourses**
  - Parameters: courseCode (int)
  - Return: object with the String representation of all the courses with the specified course code.
  - Upon Success: HTTP 200 Status Code is returned along with all the courses with the specified course code if they exist.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/isCourseFull**
  - Parameters: deptCode (String), courseCode (int)
  - Return: whether the course has at minimum reached its enrollmentCapacity.
  - Upon Success: HTTP 200 Status Code is returned along with whether the course has at minimum reached its enrollmentCapacity if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/getMajorCountFromDept**
  - Parameters: deptCode (String)
  - Return: object with string displaying the number of majors in the specified department.
  - Upon Success: HTTP 200 Status Code is returned along with the number of majors in the specified department if it exists.
  - Upon Failure: HTTP 404 Status Code with "Department Not Found" if it doesn't exist.

**GET/idDeptChair**
  - Parameters: deptCode (String)
  - Return: object with string displaying the department chair for the specified department.
  - Upon Success: HTTP 200 Status Code is returned along with the department chair for the specified department if it exists.
  - Upon Failure: HTTP 404 Status Code with "Department Not Found" if it doesn't exist.

**GET/findCourseLocation**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying the location for the specified course.
  - Upon Success: HTTP 200 Status Code is returned along with the location for the specified course if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/findCourseInstructor**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying the instructor for the specified course.
  - Upon Success: HTTP 200 Status Code is returned along with the instructor for the specified course if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/findCourseTime**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying the time for the specified course.
  - Upon Success: HTTP 200 Status Code is returned along with the time for the specified course if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**Patch/addMajorToDept**
  - Parameters: deptCode (String)
  - Return: object with string displaying whether add is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attribute was updated successfully" if it exists.
  - Upon Failure: HTTP 404 Status Code with "Department Not Found" if it doesn't exist.

**Patch/removeMajorFromDept**
  - Parameters: deptCode (String)
  - Return: object with string displaying whether remove is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attribute was updated successfully is at minimum" if it exists.
  - Upon Failure: HTTP 404 Status Code with "Department Not Found" if it doesn't exist.

**Patch/dropStudentFromCourse**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying whether dropping is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Student has been dropped." if it exists and student is dropped successfully.
  - Upon Failure: 2 cases, HTTP 400 Status Code with "Student has not been dropped." if course exists but the student can't be dropped (reach minimum); HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**Patch/enrollStudentInCourse**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object displaying whether enrolling is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Student has been enrolled." if it exists and student is dropped successfully.
  - Upon Failure: 2 cases, HTTP 400 Status Code with "Student has not been enrolled." if course exists but the student can't be enrolled (reach maximum); HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**Patch/setEnrollmentCount**
  - Parameters: deptCode (String), courseCode (int), count (int)
  - Return: object with string displaying whether setting count is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attributed was updated successfully." if it exists and student is dropped successfully.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**Patch/changeCourseTime**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying whether changing time is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attributed was updated successfully." if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**Patch/changeCourseTeacher**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying whether changing teacher is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attributed was updated successfully." if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.
  
**Patch/changeCourseLocation**
  - Parameters: deptCode (String), courseCode (int)
  - Return: object with string displaying whether changing location is successful or not.
  - Upon Success: HTTP 200 Status Code is returned along with "Attributed was updated successfully." if it exists.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.


# I2 implementation:
1. retrieveCourses takes a courseCode(int) and return the String representation of all the courses with the specified course code or displays the proper error message in response to the request. It returns OK if the course(s) is(are) found, NOTFOUND if not found.
2. enrollStudentInCourse endpoint attempt to enroll a student in a course specified by the department id as well as the course code.It takes department id as well as the course code and return ResponseEntity<> OK if enroll is successful; BadRequest if student can't be enrolled; Not Found if course is not found.
3. Added .idea/ and .DS_Store to gitignore 

# I3 implementation:
1. Demo video link can be found in DeployDemoVideoLink.txt or https://youtu.be/nyrna03pv9I 
2. Google GCP target url: https://individual-project-436720.ue.r.appspot.com 
3. Rearrange this README file you are reading ;D
4. Instance screenshots: 
    ![Screenshots of instance.](/screenshots/instance2.png)
    ![Screenshots of instance.](/screenshots/instance1.png)
5. Add tests for exceptions testHandleRetrieveCourseException(), testHandleFindCourseTimeException() in RoutControllerTests..
    ![Screenshots of coverage.](/screenshots/coverage1.png)
6. Checkstyle screen shot:
    ![Screenshots of coverage.](/screenshots/checkstyle1.png)