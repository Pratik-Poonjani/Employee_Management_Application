# Employee_Management_Application

##   Overview
The Employee Management Application is a Java-based desktop application developed to facilitate efficient management of employee records within an organization. It provides a user-friendly interface for adding new employees, viewing existing employee details, updating employee information, and deleting employee records. By leveraging Java's robust features along with JDBC for database connectivity and Swing/AWT for the graphical user interface, this application offers a seamless and intuitive solution for HR departments or managers to manage their workforce effectively.


## Features
#### 1. Employee CRUD Operations
 - __Add Employee:__ Allows users to add new employees to the database by entering their details such as name, age, position, and salary.
 - __View Employees:__ Displays a list of all employees currently stored in the database, along with their basic information.
 - __Update Employee:__ Enables users to update the details of existing employees, such as their position or salary.
 - __Delete Employee:__ Allows users to delete employee records from the database.

#### 2. User Authentication and Authorization
 - __Login System:__ Requires users to authenticate themselves using a username and password before accessing the application.
 - __Role-based Access Control:__ Implements role-based access control to restrict certain functionalities based on the user's role (e.g., admin, manager, regular employee).

#### 3. Database Integration
 - __JDBC Connectivity:__ Utilizes JDBC (Java Database Connectivity) to connect the application to a backend database, enabling seamless interaction with employee data stored in a relational database management system (RDBMS) such as MySQL, PostgreSQL, or SQLite.
 - __Database Schema:__ Maintains a well-defined database schema to organize employee data efficiently, ensuring data integrity and consistency.

#### 4. Graphical User Interface (GUI)
 - __Swing and AWT Components:__ Employs Swing and AWT components to create an intuitive and visually appealing user interface for interacting with the application.
 - __Form Validation:__ Implements form validation to ensure that users provide valid input when adding or updating employee records, preventing data inconsistencies and errors.

#### 5. Error Handling and Logging
 - __Error Handling:__ Includes robust error handling mechanisms to gracefully handle exceptions and unexpected errors, providing informative error messages to users.
 - __Logging:__ Utilizes logging frameworks such as Log4j to log important events, errors, and system activities, aiding in troubleshooting and debugging.


## Technologies Used

 - __Java SE (Standard Edition)__
 - __JDBC (Java Database Connectivity)__
 - __Swing (Graphical User Interface Toolkit)__
 - __AWT (Abstract Window Toolkit)__


## Setup and Installation

#### Clone the Repository:
 - Clone this repository to your local machine using Git:
 - git clone 
```https://github.com/your-username/employee-management.git```

#### Import Project: 
 - Import the project into your preferred Java IDE (Integrated Development Environment), IntelliJ IDEA.

#### Database Configuration: 
 - Configure the database connection parameters in the DatabaseManager.java file. Update the JDBC URL, username, and password according to your database setup.

#### Database Setup: 
 - Create a database named employee_management in your preferred RDBMS (Relational Database Management System) such as MySQL, PostgreSQL, or SQLite. Use the provided SQL script database_script.sql to create the necessary tables and schema.

#### Run Application: 
 - Compile and run the Main.java file to start the application.


## Usage

 1. Upon launching the application, the user interface will display options to add, view, update, and delete employee records.
 2. Choose the desired operation by clicking on the corresponding button.
 3. Follow the on-screen instructions to complete the chosen operation.
 4. All changes made to employee records will be reflected in the database.


## DATABASE
~~~
CREATE database employee_table;

use employee_table;

CREATE table login(username varchar(20), password varchar(20));
select * from login;

insert into login values ('rajdeep','1234567');
insert into login values ('vivek','abcdefg');
select * from login;

CREATE table employeedata(
  name varchar(40), 
  fname varchar(40), 
  dob varchar(40), 
  aadhar varchar(40), 
  email varchar(40), 
  phone varchar(40), 
  address varchar(40), 
  education varchar(40), 
  designation varchar(40), 
  salary varchar(40), 
  empID varchar(40)
);

insert into employeedata values (
  'raj', 
  'deep', 
  '05/11/2020', 
  '32344556', 
  'rajdeep@gmail.com', 
  '9898989898', 
  '45-ascvhjik', 
  'B.COM', 
  'Manager', 
  '989898', 
  '32456'
);
select * from employeedata;
~~~