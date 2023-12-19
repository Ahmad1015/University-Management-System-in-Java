import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Department department = new Department();
    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {
      System.out.println("Please enter a command:");
      System.out.println("1: Add teacher");
      System.out.println("2: Add student");
      System.out.println("3: Exit");
      input = scanner.nextLine();

      switch (input) {
        case "1":
          // Add teacher
          System.out.println("Enter teacher's name:");
          String teacherName = scanner.nextLine();
          System.out.println("Enter teacher's password:");
          String teacherPassword = scanner.nextLine();
          Teacher teacher = new Teacher(teacherName, teacherPassword);
          department.addTeacher(teacher, department.getTeacherIndex());
          System.out.println("Teacher added successfully.");
          break;
        case "2":
          // Add student
          System.out.println("Enter student's name:");
          String studentName = scanner.nextLine();
          System.out.println("Enter student's password:");
          String studentPassword = scanner.nextLine();
          Student student = new Student(studentName, studentPassword);
          department.addStudent(student, department.getStudentIndex());
          System.out.println("Student added successfully.");
          break;
        case "3":
          System.out.println("Exiting...");
          System.exit(0);
        default:
          System.out.println("Invalid command. Please try again.");
      }
    }
  }
}

// Composition //

// Base class for users
class User {
  // Name of the user
  String name;
  // Password for authentication
  String password;

  // Constructor to initialize user details
  User(String name, String password) {
    this.name = name;
    this.password = password;
  }
}

// Class representing a course offered in the department
class Course {
  // Name of the course
  String courseName;
  // Array to store registered students
  Student[] registeredStudents;
  // Array to store marks for each student
  int[] marks;
  // Array to store attendance for each student
  int[] attendance;

  // Constructor to initialize course details
  Course(String courseName) {
    this.courseName = courseName;
    this.registeredStudents = new Student[40]; // Maximum students allowed
    this.marks = new int[40];
    this.attendance = new int[40];
  }

  // Register a student to the course
  void registerStudent(Student student, int index) {
    this.registeredStudents[index] = student;
  }

  // Upload marks for a specific student
  void uploadMarks(int index, int mark) {
    this.marks[index] = mark;
  }

  // Modify marks for a specific student
  void modifyMarks(int index, int mark) {
    this.marks[index] = mark;
  }

  // View marks for a specific student
  int viewMarks(int index) {
    return this.marks[index];
  }

  // Record attendance for a specific student
  void recordAttendance(int index, int attendance) {
    this.attendance[index] = attendance;
  }

  // View attendance for a specific student
  int viewAttendance(int index) {
    return this.attendance[index];
  }
}

// Class representing a teacher in the department
class Teacher extends User {
  // Courses assigned to the teacher
  Course[] courses;

  // Constructor to initialize teacher details
  Teacher(String name, String password) {
    super(name, password);
    this.courses = new Course[5]; // Maximum courses assigned
  }

  // Assign a course to the teacher
  void assignCourse(Course course, int index) {
    this.courses[index] = course;
  }
}

// Class representing a student in the department
class Student extends User {
  // Courses registered by the student
  Course[] registeredCourses;

  // Constructor to initialize student details
  Student(String name, String password) {
    super(name, password);
    this.registeredCourses = new Course[5]; // Maximum courses registered
  }

  // Register a course for the student
  void registerCourse(Course course, int index) {
    this.registeredCourses[index] = course;
    course.registerStudent(this, index); // Add student to the course
  }

  // View student's results for all registered courses
  void viewResults() {
    for (int i = 0; i < this.registeredCourses.length; i++) {
        System.out.println("Course: " + this.registeredCourses[i].courseName);
        System.out.println("Marks: " + this.registeredCourses[i].viewMarks(i));
        System.out.println("Attendance: " + this.registeredCourses[i].viewAttendance(i));
    }
  }
}

// Class representing the department
class Department {
  // Array to store teachers in the department
  Teacher[] teachers;
  // Array to store students in the department
  Student[] students;
  // Array to store courses offered in the department
  Course[] courses;
  // Variables to keep track of the current index for adding teachers and students
  private int teacherIndex;
  private int studentIndex;

  // Constructor to initialize department data structures
  Department() {
    this.teachers = new Teacher[50]; // Maximum teachers
    this.students = new Student[500]; // Maximum students
    this.courses = new Course[100]; // Maximum courses
    this.teacherIndex = 0;
    this.studentIndex = 0;
  }

  // Add a teacher to the department
  void addTeacher(Teacher teacher, int index) {
    this.teachers[index] = teacher;
    teacherIndex++;
  }

  // Add a student to the department
  void addStudent(Student student, int index) {
    this.students[index] = student;
    studentIndex++;
  }

  // Get the current index for adding a teacher
  int getTeacherIndex() {
    return teacherIndex;
  }

  // Get the current index for adding a student
  int getStudentIndex() {
    return studentIndex;
  }
}

