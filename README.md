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
  - Upon Success: HTTP 200 Status Code is returned along with the details of the course if it exist.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/retrieveCourses**
  - Parameters: courseCode (int)
  - Return: the String representation of all the courses with the specified course code.
  - Upon Success: HTTP 200 Status Code is returned along with all the courses with the specified course code if they exist.
  - Upon Failure: HTTP 404 Status Code with "Course Not Found" if it doesn't exist.

**GET/isCourseFull**
**GET/getMajorCountFromDept**
**GET/idDeptChair**
**GET/findCourseLocation**
**GET/findCourseInstructor**
**GET/findCourseTime**
**Patch/addMajorToDept**
**Patch/removeMajorFromDept**
**Patch/dropStudentFromCourse**
**Patch/enrollStudentInCourse**
**Patch/setEnrollmentCount**
**Patch/changeCourseTime**
**Patch/changeCourseTeacher**
**Patch/changeCourseLocation**



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