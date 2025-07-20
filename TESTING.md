# Testing Guide for Student Management System

## 🧪 Comprehensive Testing Scenarios

### 1. Setup and Demo Data

#### Quick Setup with Demo Script
```bash
# Compile and run demo script to populate with test data
javac DemoScript.java StudentManager.java Student.java
java DemoScript

# Then run the main application
javac StudentManagementApp.java InputValidator.java
java StudentManagementApp
```

### 2. Core CRUD Operations Testing

#### Test Case 1: Add New Student
**Steps:**
1. Select "Add New Student" from main menu
2. Enter valid student information
3. Verify student is added with auto-generated ID

**Test Data:**
```
First Name: Alice
Last Name: Cooper
Email: alice.cooper@test.com
Age: 21
Phone: (555) 999-8888
Address: 123 Test Street, Test City
GPA: 3.75
Major: Computer Science
```

**Expected Result:** Student added successfully with confirmation message

#### Test Case 2: View All Students
**Steps:**
1. Select "View All Students"
2. Verify all students are displayed in table format

**Expected Result:** Formatted table with all student records

#### Test Case 3: Update Student
**Steps:**
1. Select "Update Student"
2. Enter existing student ID
3. Update specific fields (leave others empty)
4. Confirm changes

**Expected Result:** Student information updated successfully

#### Test Case 4: Delete Student
**Steps:**
1. Select "Delete Student"
2. Enter existing student ID
3. Confirm deletion

**Expected Result:** Student removed from system with confirmation

### 3. Search and Filter Testing

#### Test Case 5: Search by Name
**Test Data:** Search for "John"
**Expected Result:** All students with "John" in first or last name

#### Test Case 6: Search by Email
**Test Data:** Search for "emma.johnson@email.com"
**Expected Result:** Exact match for Emma Johnson

#### Test Case 7: Filter by GPA Range
**Test Data:** GPA range 3.5 to 4.0
**Expected Result:** Only honor students displayed

#### Test Case 8: Filter by Major
**Test Data:** Search for "Computer Science"
**Expected Result:** All CS students displayed

### 4. Data Validation Testing

#### Test Case 9: Invalid Email Format
**Test Data:** Enter "invalid-email" as email
**Expected Result:** Error message, prompt to re-enter

#### Test Case 10: Age Out of Range
**Test Data:** Enter age 15 or 101
**Expected Result:** Error message, valid range displayed

#### Test Case 11: Invalid GPA
**Test Data:** Enter GPA 5.0 or -1.0
**Expected Result:** Error message, valid range (0.0-4.0) displayed

#### Test Case 12: Duplicate Email
**Test Data:** Enter email that already exists
**Expected Result:** Error message about duplicate email

### 5. Import/Export Testing

#### Test Case 13: Export Data
**Steps:**
1. Select "Import/Export Data"
2. Choose "Export students to file"
3. Enter filename "test_export.csv"

**Expected Result:** CSV file created with all student data

#### Test Case 14: Import Data
**Steps:**
1. Use sample_students.csv file
2. Select "Import students from file"
3. Enter filename "sample_students.csv"

**Expected Result:** Sample students loaded into system

### 6. Statistics and Reporting

#### Test Case 15: View Statistics
**Steps:**
1. Select "View Statistics"

**Expected Result:** 
- Total student count
- Honor student count and percentage
- Average, highest, lowest GPA
- Major distribution

#### Test Case 16: Honor Students
**Steps:**
1. Select "View Honor Students"

**Expected Result:** Only students with GPA ≥ 3.5 displayed

### 7. Data Management Testing

#### Test Case 17: Backup and Restore
**Steps:**
1. Create backup through data management menu
2. Make changes to data
3. Restore from backup

**Expected Result:** Data restored to previous state

#### Test Case 18: Clear All Data
**Steps:**
1. Select "Data Management"
2. Choose "Clear all data"
3. Confirm action

**Expected Result:** All students removed, empty system

### 8. Error Handling Testing

#### Test Case 19: Invalid Menu Choice
**Test Data:** Enter invalid menu option (e.g., 99)
**Expected Result:** Error message, prompt to re-enter

#### Test Case 20: Empty Input Fields
**Test Data:** Leave required fields empty
**Expected Result:** Error message, prompt to re-enter

#### Test Case 21: Non-Existent Student ID
**Test Data:** Search for ID 99999
**Expected Result:** "Student not found" message

### 9. Edge Cases Testing

#### Test Case 22: Maximum Values
**Test Data:**
- Age: 100
- GPA: 4.0
- Long names and addresses

**Expected Result:** All values accepted within limits

#### Test Case 23: Minimum Values
**Test Data:**
- Age: 16
- GPA: 0.0
- Minimum length names

**Expected Result:** All values accepted within limits

#### Test Case 24: Special Characters
**Test Data:**
- Names with spaces and hyphens
- Addresses with special characters
- Phone numbers with various formats

**Expected Result:** Valid formats accepted, invalid rejected

### 10. Performance Testing

#### Test Case 25: Large Dataset
**Steps:**
1. Import large CSV file (100+ students)
2. Test search and filter operations
3. Verify response times

**Expected Result:** System handles large datasets efficiently

#### Test Case 26: Rapid Operations
**Steps:**
1. Perform multiple CRUD operations quickly
2. Verify data integrity

**Expected Result:** All operations complete successfully

## 📊 Test Results Template

| Test Case | Description | Status | Notes |
|-----------|-------------|--------|-------|
| TC01 | Add New Student | ✅ Pass | |
| TC02 | View All Students | ✅ Pass | |
| TC03 | Update Student | ✅ Pass | |
| ... | ... | ... | ... |

## 🐛 Bug Report Template

**Bug ID:** BUG-001
**Test Case:** TC03
**Description:** Brief description of the issue
**Steps to Reproduce:**
1. Step 1
2. Step 2
3. Step 3

**Expected Result:** What should happen
**Actual Result:** What actually happened
**Severity:** High/Medium/Low
**Priority:** High/Medium/Low

## ✅ Testing Checklist

- [ ] All CRUD operations work correctly
- [ ] Input validation prevents invalid data
- [ ] Search and filter functions return correct results
- [ ] Import/export maintains data integrity
- [ ] Statistics calculations are accurate
- [ ] Error messages are clear and helpful
- [ ] File operations handle errors gracefully
- [ ] Menu navigation works smoothly
- [ ] Data persistence works correctly
- [ ] System handles edge cases properly

## 🎯 Acceptance Criteria

**System is ready for deployment when:**
1. ✅ All test cases pass
2. ✅ No critical bugs remain
3. ✅ Performance meets requirements
4. ✅ Documentation is complete
5. ✅ User interface is intuitive
6. ✅ Data validation is comprehensive
7. ✅ Error handling is robust
8. ✅ File operations are reliable

---

**Note:** Run tests in sequence and document any issues found. Each test case should be repeatable and verifiable.
