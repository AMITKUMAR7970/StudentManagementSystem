import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class Student {
    private int id;
    private String name;
    private String email;
    private int age;
    private String course;
    private double gpa;

    public Student(int id, String name, String email, int age, String course, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public double getGpa() { return gpa; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Age: %d | Course: %s | GPA: %.2f",
            id, name, email, age, course, gpa
        );
    }

    public String toCSV() {
        return id + "," +
            name.replace(",", " ") + "," +
            email + "," +
            age + "," +
            course.replace(",", " ") + "," +
            gpa;
    }

    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", 6);
        if (parts.length != 6) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String email = parts[2];
            int age = Integer.parseInt(parts[3]);
            String course = parts[4];
            double gpa = Double.parseDouble(parts[5]);
            return new Student(id, name, email, age, course, gpa);
        } catch (Exception e) {
            return null;
        }
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static int nextId = 1;
    private static final String FILE_NAME = "students.csv";

    public static void main(String[] args) {
        loadStudents();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = getIntInput(sc, "Choose an option: ");
            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(sc); break;
                case 4: deleteStudent(sc); break;
                case 5: searchStudent(sc); break;
                case 6: statsMenu(); break;
                case 7: sortMenu(sc); break;
                case 8: filterMenu(sc); break;
                case 0: saveStudents(); System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid option!");
            }
            System.out.println();
        } while (choice != 0);
        sc.close();
    }

    private static void displayMenu() {
        System.out.println("----- Student Management System -----");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("6. GPA & Statistics");
        System.out.println("7. Sort Students");
        System.out.println("8. Filter Students");
        System.out.println("0. Save & Exit");
    }

    // -------- CRUD FUNCTIONS --------

    private static void addStudent(Scanner sc) {
        System.out.println("--- Add Student ---");
        sc.nextLine(); // flush
        String name;
        do {
            System.out.print("Enter name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name cannot be empty.");
        } while (name.isEmpty());

        String email;
        do {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
            if (!isEmailValid(email))
                System.out.println("Invalid email format.");
            else if (isEmailExists(email))
                System.out.println("Email already exists.");
        } while (!isEmailValid(email) || isEmailExists(email));

        int age;
        do {
            age = getIntInput(sc, "Enter age: ");
            if (age < 10 || age > 100) System.out.println("Age must be between 10 and 100.");
        } while (age < 10 || age > 100);

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        double gpa;
        do {
            gpa = getDoubleInput(sc, "Enter GPA (0.0 - 4.0): ");
            if (gpa < 0.0 || gpa > 4.0) System.out.println("GPA must be 0.0 to 4.0.");
        } while (gpa < 0.0 || gpa > 4.0);

        Student s = new Student(nextId++, name, email, age, course, gpa);
        students.add(s);
        saveStudents();
        System.out.println("Student added. ID: " + s.getId());
    }

    private static void viewStudents() {
        System.out.println("--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.sort(Comparator.comparing(Student::getId));
            for (Student s : students) System.out.println(s);
        }
    }

    private static void updateStudent(Scanner sc) {
        System.out.println("--- Update Student ---");
        int id = getIntInput(sc, "Enter student ID: ");
        Student s = getStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        sc.nextLine(); // flush
        System.out.print("Enter new name (Enter for current: " + s.getName() + "): ");
        String newName = sc.nextLine();
        if (!newName.trim().isEmpty()) s.setName(newName);

        String email;
        do {
            System.out.print("Enter new email (Enter for current: " + s.getEmail() + "): ");
            email = sc.nextLine().trim();
            if (email.isEmpty()) break;
            if (!isEmailValid(email))
                System.out.println("Invalid email format.");
            else if (isEmailExists(email, s.getId()))
                System.out.println("Email already exists.");
            else { s.setEmail(email); break; }
        } while (!email.isEmpty());

        System.out.print("Enter new age (0 for current: " + s.getAge() + "): ");
        int age = getIntInput(sc, "");
        if (age >= 10 && age <= 100) s.setAge(age);

        System.out.print("Enter new course (Enter for current: " + s.getCourse() + "): ");
        String newCourse = sc.nextLine();
        if (!newCourse.trim().isEmpty()) s.setCourse(newCourse);

        System.out.print("Enter new GPA (-1 for current: " + s.getGpa() + "): ");
        double newGpa = getDoubleInput(sc, "");
        if (newGpa >= 0.0 && newGpa <= 4.0) s.setGpa(newGpa);

        saveStudents();
        System.out.println("Student updated.");
    }

    private static void deleteStudent(Scanner sc) {
        System.out.println("--- Delete Student ---");
        int id = getIntInput(sc, "Enter student ID: ");
        Student s = getStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        students.remove(s);
        saveStudents();
        System.out.println("Student deleted.");
    }

    private static void searchStudent(Scanner sc) {
        System.out.println("--- Search Student ---");
        System.out.println("1. By ID");
        System.out.println("2. By Name");
        System.out.println("3. By Email");
        int opt = getIntInput(sc, "Choose search type: ");
        sc.nextLine(); // flush
        switch (opt) {
            case 1: {
                int id = getIntInput(sc, "Enter ID: ");
                Student s = getStudentById(id);
                if (s != null) System.out.println(s);
                else System.out.println("Not found.");
                break;
            }
            case 2: {
                System.out.print("Enter name: ");
                String name = sc.nextLine().trim().toLowerCase();
                boolean found = false;
                for (Student st : students)
                    if (st.getName().toLowerCase().contains(name)) {
                        System.out.println(st);
                        found = true;
                    }
                if (!found) System.out.println("Not found.");
                break;
            }
            case 3: {
                System.out.print("Enter email: ");
                String email = sc.nextLine().trim().toLowerCase();
                boolean found = false;
                for (Student st : students)
                    if (st.getEmail().equalsIgnoreCase(email)) {
                        System.out.println(st);
                        found = true;
                        break;
                    }
                if (!found) System.out.println("Not found.");
                break;
            }
            default: System.out.println("Invalid option.");
        }
    }

    // -------- ADVANCED FEATURES --------

    private static void statsMenu() {
        System.out.println("--- GPA & Statistics ---");
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }
        double avg = students.stream().mapToDouble(Student::getGpa).average().orElse(0.0);
        double min = students.stream().mapToDouble(Student::getGpa).min().orElse(0.0);
        double max = students.stream().mapToDouble(Student::getGpa).max().orElse(0.0);
        long honor = students.stream().filter(s -> s.getGpa() >= 3.5).count();

        System.out.println("Total students: " + students.size());
        System.out.printf("Average GPA: %.2f | Min GPA: %.2f | Max GPA: %.2f\n", avg, min, max);
        System.out.println("Honor Roll (GPA ≥ 3.5): " + honor);
    }

    private static void sortMenu(Scanner sc) {
        System.out.println("--- Sort Students ---");
        System.out.println("1. By Name");
        System.out.println("2. By GPA");
        System.out.println("3. By Age");
        System.out.println("4. By Course");
        int opt = getIntInput(sc, "Choose: ");
        switch (opt) {
            case 1: students.sort(Comparator.comparing(Student::getName)); break;
            case 2: students.sort(Comparator.comparing(Student::getGpa).reversed()); break;
            case 3: students.sort(Comparator.comparing(Student::getAge)); break;
            case 4: students.sort(Comparator.comparing(Student::getCourse)); break;
            default: System.out.println("Invalid choice."); return;
        }
        viewStudents();
    }

    private static void filterMenu(Scanner sc) {
        System.out.println("--- Filter Students ---");
        System.out.println("1. By Course");
        System.out.println("2. By GPA threshold");
        int opt = getIntInput(sc, "Choose: ");
        sc.nextLine(); // flush
        switch (opt) {
            case 1:
                System.out.print("Enter course name: ");
                String course = sc.nextLine().trim().toLowerCase();
                boolean found = false;
                for (Student s : students) {
                    if (s.getCourse().toLowerCase().contains(course)) {
                        System.out.println(s);
                        found = true;
                    }
                }
                if (!found) System.out.println("No students found for this course.");
                break;
            case 2:
                double gpat = getDoubleInput(sc, "Enter minimum GPA: ");
                found = false;
                for (Student s : students) {
                    if (s.getGpa() >= gpat) {
                        System.out.println(s);
                        found = true;
                    }
                }
                if (!found) System.out.println("No students found with GPA >= " + gpat);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // -------- UTILITIES --------

    private static Student getStudentById(int id) {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }

    private static boolean isEmailValid(String email) {
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, email);
    }

    private static boolean isEmailExists(String email) {
        for (Student s : students)
            if (s.getEmail().equalsIgnoreCase(email))
                return true;
        return false;
    }

    private static boolean isEmailExists(String email, int exceptId) {
        for (Student s : students)
            if (s.getId() != exceptId && s.getEmail().equalsIgnoreCase(email))
                return true;
        return false;
    }

    private static int getIntInput(Scanner sc, String prompt) {
        int num;
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            if (sc.hasNextInt()) {
                num = sc.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
        return num;
    }

    private static double getDoubleInput(Scanner sc, String prompt) {
        double num;
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            if (sc.hasNextDouble()) {
                num = sc.nextDouble();
                break;
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
        return num;
    }

    private static void loadStudents() {
        File file = new File(FILE_NAME);
        students.clear();
        int maxId = 0;
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Student s = Student.fromCSV(line.trim());
                    if (s != null) {
                        students.add(s);
                        if (s.getId() > maxId) maxId = s.getId();
                    }
                }
            } catch (IOException e) {
                System.out.println("Failed to load data: " + e.getMessage());
            }
        }
        nextId = maxId + 1;
    }

    private static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }
}
