# Student Management System

A comprehensive Java console application for managing student records with full CRUD operations, advanced search capabilities, and data persistence.

## 🎓 Features

### Core Functionality
- **Complete CRUD Operations**: Create, Read, Update, and Delete student records
- **Advanced Search**: Search by ID, name, email, or major
- **Data Filtering**: Filter students by GPA range, age range, and major
- **Sorting Options**: Sort by name, ID, or GPA
- **Honor Roll Tracking**: Automatic identification of honor students (GPA ≥ 3.5)

### Data Management
- **CSV Import/Export**: Import and export student data in CSV format
- **Automatic Backup**: Automatic backup creation before data modifications
- **Data Validation**: Comprehensive input validation with regex patterns
- **Statistics**: System statistics and reporting
- **Persistent Storage**: Automatic data saving and loading

### User Experience
- **Menu-Driven Interface**: Intuitive console-based navigation
- **Input Validation**: Robust error handling and user input validation
- **Color-Coded Output**: Enhanced visual feedback with console colors
- **Professional UI**: Clean, formatted display with Unicode box drawing

## 🏗️ Architecture

### Design Patterns
- **Model-View-Controller (MVC)**: Clear separation of concerns
- **Data Access Object (DAO)**: Abstracted data operations
- **Singleton Pattern**: Single instance management
- **Strategy Pattern**: Multiple sorting and filtering strategies

### Class Structure
```
Student.java              # Student data model with validation
StudentManager.java       # Business logic and data operations
InputValidator.java       # Input validation utilities
StudentManagementApp.java # Main application and UI controller
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Console/Terminal with Unicode support (for best visual experience)

### Installation
1. Download all Java files to a directory
2. Ensure all files are in the same folder:
   - `Student.java`
   - `StudentManager.java`
   - `InputValidator.java`
   - `StudentManagementApp.java`

### Running the Application

#### Windows
```batch
# Compile and run using the batch script
run.bat

# Or manually:
javac *.java
java StudentManagementApp
```

#### Linux/Mac
```bash
# Make the script executable and run
chmod +x run.sh
./run.sh

# Or manually:
javac *.java
java StudentManagementApp
```

## 📋 Usage Guide

### Main Menu Options

1. **Add New Student**: Create a new student record with validation
2. **View All Students**: Display all students in a formatted table
3. **Search Students**: Search by ID, name, email, or major
4. **Update Student**: Modify existing student information
5. **Delete Student**: Remove a student from the system
6. **Filter & Sort**: Advanced filtering and sorting options
7. **Import/Export**: Data import/export functionality
8. **View Statistics**: System statistics and analysis
9. **View Honor Students**: Display students with GPA ≥ 3.5
10. **Data Management**: Backup, restore, and data operations
11. **About System**: Information about the application
12. **Exit**: Save data and exit the application

### Sample Operations

#### Adding a Student
```
First Name: John
Last Name: Smith
Email: john.smith@email.com
Age: 20
Phone Number: (555) 123-4567
Address: 123 Main St, City, State
GPA: 3.85
Major: Computer Science
```

#### Searching Students
- **By ID**: Enter student ID number
- **By Name**: Enter partial or full name
- **By Email**: Enter exact email address
- **By Major**: Enter partial or full major name

#### Filtering Options
- **GPA Range**: Filter students within specific GPA range (0.0-4.0)
- **Age Range**: Filter students within specific age range
- **Honor Students**: View students with GPA ≥ 3.5

## 📊 Data Format

### Student Record Fields
- **Student ID**: Unique identifier (auto-generated)
- **First Name**: Student's first name (letters and spaces, 2-30 chars)
- **Last Name**: Student's last name (letters and spaces, 2-30 chars)
- **Email**: Valid email address (unique across system)
- **Age**: Student age (16-100 years)
- **Phone Number**: Phone number (10-15 digits, spaces/dashes allowed)
- **Address**: Student's address (free text)
- **GPA**: Grade Point Average (0.0-4.0)
- **Major**: Academic major (free text)

### CSV Format
```csv
StudentID,FirstName,LastName,Email,Age,PhoneNumber,Address,GPA,Major
1001,"John","Smith","john.smith@email.com",20,"(555) 123-4567","123 Main St",3.85,"Computer Science"
```

## 🔧 Technical Details

### Input Validation
- **Email Validation**: RFC-compliant email format checking
- **Phone Validation**: Flexible phone number format support
- **Name Validation**: Alphabetic characters and spaces only
- **Range Validation**: Age (16-100), GPA (0.0-4.0)
- **Uniqueness**: Email uniqueness enforcement

### Error Handling
- **Try-Catch Blocks**: Comprehensive exception handling
- **Input Validation**: Prevention of invalid data entry
- **File Operations**: Graceful handling of I/O errors
- **User Feedback**: Clear error messages and recovery options

### Data Persistence
- **Automatic Saving**: Data automatically saved after modifications
- **Backup System**: Automatic backup creation before changes
- **CSV Format**: Human-readable and Excel-compatible format
- **Data Recovery**: Restore from backup functionality

## 📁 File Structure

```
student-management-system/
├── Student.java              # Student model class
├── StudentManager.java       # Data management class
├── InputValidator.java       # Input validation utilities
├── StudentManagementApp.java # Main application class
├── run.bat                   # Windows run script
├── run.sh                    # Linux/Mac run script
├── sample_students.csv       # Sample data for testing
├── README.md                 # This documentation
├── students.csv              # Main data file (created at runtime)
└── students_backup.csv       # Backup file (created automatically)
```

## 🧪 Testing

### Sample Data
Load the included `sample_students.csv` file to test the system with 10 sample student records.

1. Start the application
2. Go to "Import/Export Data" menu
3. Select "Import students from file"
4. Enter filename: `sample_students.csv`
5. Choose whether to replace existing data

### Test Scenarios
- **CRUD Operations**: Add, view, update, and delete students
- **Search Functionality**: Test all search methods
- **Data Validation**: Try entering invalid data
- **Import/Export**: Test with sample CSV file
- **Statistics**: View system statistics with sample data
- **Error Handling**: Test invalid inputs and edge cases

## 🔍 Troubleshooting

### Common Issues

#### Compilation Errors
- Ensure all Java files are in the same directory
- Check Java version (requires JDK 8+)
- Verify no syntax errors in code

#### Runtime Errors
- Check file permissions for data files
- Ensure sufficient disk space for data files
- Verify console supports Unicode characters

#### Display Issues
- Use a console with Unicode support for best appearance
- Colors may not appear in all terminals
- Adjust console width for optimal table display

### Error Messages
- **"Student ID already exists"**: Use a different ID or update existing student
- **"Email already exists"**: Each student must have a unique email
- **"Invalid input format"**: Follow the specified input format requirements
- **"File not found"**: Check filename and file location for import operations

## 🤝 Contributing

This is an educational project demonstrating Java programming concepts:
- Object-Oriented Programming (OOP)
- Data validation and error handling
- File I/O operations
- Console application development
- Design patterns implementation

## 📄 License

This project is created for educational purposes. Feel free to use, modify, and distribute as needed for learning and teaching Java programming concepts.

## 🎯 Learning Objectives

This project demonstrates:
- **Java Fundamentals**: Classes, objects, inheritance, encapsulation
- **Data Structures**: ArrayList, HashMap, Collections framework
- **I/O Operations**: File reading/writing, CSV parsing
- **Exception Handling**: Try-catch blocks, custom exceptions
- **Input Validation**: Regex patterns, user input handling
- **Design Patterns**: MVC, DAO, Strategy patterns
- **Console Applications**: Menu systems, formatted output

## 📞 Support

For questions or issues:
1. Check the troubleshooting section
2. Verify your Java installation
3. Review the sample data and test scenarios
4. Ensure all files are present and properly named

---

**Happy coding! 🎓**
