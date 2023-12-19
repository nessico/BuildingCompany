-- Create Database
CREATE DATABASE IF NOT EXISTS BuildingCompany;
USE BuildingCompany;

-- Create Tables

-- Clients
CREATE TABLE Clients (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

-- Departments
CREATE TABLE Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL
);

-- Employees
CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

-- Projects
CREATE TABLE Projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    budget DECIMAL(10,2),
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Clients(client_id)
);

-- Inspections
CREATE TABLE Inspections (
    inspection_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    date DATE NOT NULL,
    report TEXT,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id)
);

-- Suppliers
CREATE TABLE Suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

-- Materials
CREATE TABLE Materials (
    material_id INT AUTO_INCREMENT PRIMARY KEY,
    material_name VARCHAR(255) NOT NULL,
    supplier_id INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id)
);

-- Equipment
CREATE TABLE Equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_name VARCHAR(255) NOT NULL,
    maintenance_date DATE
);

-- Tasks
CREATE TABLE Tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_description VARCHAR(255) NOT NULL,
    estimated_time INT COMMENT 'Estimated time in hours'
);

-- Project_Tasks (Associative Table)
CREATE TABLE Project_Tasks (
    project_id INT,
    task_id INT,
    PRIMARY KEY (project_id, task_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    FOREIGN KEY (task_id) REFERENCES Tasks(task_id)
);

-- Employee_Tasks (Associative Table)
CREATE TABLE Employee_Tasks (
    employee_id INT,
    task_id INT,
    PRIMARY KEY (employee_id, task_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
    FOREIGN KEY (task_id) REFERENCES Tasks(task_id)
);

-- Project_Employees (Associative Table)
CREATE TABLE Project_Employees (
    project_id INT,
    employee_id INT,
    PRIMARY KEY (project_id, employee_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

-- Invoices
CREATE TABLE Invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    issue_date DATE,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id)
);

-- Contracts
CREATE TABLE Contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    client_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    terms TEXT,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    FOREIGN KEY (client_id) REFERENCES Clients(client_id)
);

-- Time_Sheets
CREATE TABLE Time_Sheets (
    sheet_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    task_id INT NOT NULL,
    hours INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
    FOREIGN KEY (task_id) REFERENCES Tasks(task_id)
);

-- Insert
INSERT INTO Clients (client_name, contact_info) VALUES ('Ace Builders', 'contact@acebuilders.com');
INSERT INTO Clients (client_name, contact_info) VALUES ('Beta Constructions', 'info@betaconstruct.com');

INSERT INTO Departments (department_name) VALUES ('Engineering');
INSERT INTO Departments (department_name) VALUES ('Sales');
INSERT INTO Employees (name, department_id) VALUES ('John Doe', 1);
INSERT INTO Employees (name, department_id) VALUES ('Jane Smith', 2);
INSERT INTO Projects (project_name, start_date, end_date, budget, client_id) VALUES ('Project Alpha', '2023-01-01', '2023-12-31', 500000, 1);
INSERT INTO Projects (project_name, start_date, end_date, budget, client_id) VALUES ('Project Beta', '2023-02-01', '2023-11-30', 300000, 2);

INSERT INTO Inspections (project_id, date, report) VALUES (1, '2023-03-15', 'Initial inspection completed. No issues.');
INSERT INTO Equipment (equipment_name, maintenance_date) VALUES ('Excavator', '2023-06-01');
INSERT INTO Suppliers (supplier_name, contact_info) VALUES ('Quality Materials Ltd.', 'sales@qualitymaterials.com');
INSERT INTO Suppliers (supplier_name, contact_info) VALUES ('Standard Supplies', 'contact@standardsupplies.com');
INSERT INTO Materials (material_name, supplier_id) VALUES ('Concrete', 1);
INSERT INTO Tasks (task_description) VALUES ('Foundation Setup');

-- Update

UPDATE Clients
SET contact_info = 'newcontact@acebuilders.com'
WHERE client_name = 'Ace Builders';

UPDATE Departments
SET department_name = 'Design'
WHERE department_name = 'Engineering';

UPDATE Employees
SET name = 'John Smith'
WHERE name = 'John Doe';

UPDATE Projects
SET budget = 550000
WHERE project_name = 'Project Alpha';

UPDATE Inspections
SET report = 'Initial inspection completed. Minor issues found.'
WHERE project_id = 1;

UPDATE Suppliers
SET contact_info = 'contact@qualitymaterials.com'
WHERE supplier_name = 'Quality Materials Ltd.';

UPDATE Materials
SET material_name = 'High-Quality Cement'
WHERE material_name = 'Cement';

UPDATE Equipment
SET maintenance_date = '2023-05-20'
WHERE equipment_name = 'Crane';

UPDATE Tasks
SET task_description = 'Finalizing construction'
WHERE task_description = 'Initial construction phase';

UPDATE Invoices
SET amount = 120000
WHERE project_id = 1;

-- Deletion

-- Select project where name is Beta Construction
SELECT * FROM Projects WHERE client_id = (SELECT client_id FROM Clients WHERE client_name = 'Beta Constructions');

-- Update projects associated with 'Beta Constructions' to a valid client_id (e.g., 1)
UPDATE Projects
SET client_id = 1
WHERE client_id = (SELECT client_id FROM Clients WHERE client_name = 'Beta Constructions');

-- Set beta constructions to null / deleting it
UPDATE Projects
SET client_id = NULL
WHERE client_id = (SELECT client_id FROM Clients WHERE client_name = 'Beta Constructions');
DELETE FROM Clients WHERE client_name = 'Beta Constructions';


-- Reassign employees to delete later
UPDATE Employees
SET department_id = 1
WHERE department_id = (SELECT department_id FROM Departments WHERE department_name = 'Sales');

-- Delete Sales department
DELETE FROM Departments
WHERE department_name = 'Sales';

DELETE FROM Employees
WHERE name = 'Jane Smith';

DELETE FROM Projects
WHERE project_name = 'Project Beta';

DELETE FROM Inspections
WHERE project_id = 2;

-- Select supplier id to delete
SELECT supplier_id FROM Suppliers WHERE supplier_name = 'Quality Materials Ltd.';

-- Reassign materials to another supplier (e.g., supplier_id = 2)
UPDATE Materials
SET supplier_id = 2
WHERE supplier_id = (SELECT supplier_id FROM Suppliers WHERE supplier_name = 'Quality Materials Ltd.');

-- delete 'Quality Materials Ltd.' supplier
DELETE FROM Suppliers
WHERE supplier_name = 'Quality Materials Ltd.';

DELETE FROM Materials
WHERE material_name = 'Cement';

DELETE FROM Equipment
WHERE equipment_name = 'Crane';

DELETE FROM Tasks
WHERE task_description = 'Initial construction phase';

DELETE FROM Invoices
WHERE project_id = 1;

-- Alter

ALTER TABLE Employees
ADD COLUMN salary DECIMAL(10,2);

ALTER TABLE Projects
MODIFY COLUMN budget DECIMAL(15,2);

ALTER TABLE Suppliers
CHANGE COLUMN contact_info email VARCHAR(255);

ALTER TABLE Tasks
DROP COLUMN estimated_time;

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoice_Client
FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE;

ALTER TABLE Equipment MODIFY COLUMN maintenance_date DATETIME;


-- Join
SELECT *
FROM Clients
JOIN Projects ON Clients.client_id = Projects.client_id
JOIN Contracts ON Projects.project_id = Contracts.project_id AND Clients.client_id = Contracts.client_id
JOIN Inspections ON Projects.project_id = Inspections.project_id
JOIN Invoices ON Projects.project_id = Invoices.project_id
JOIN Project_Employees ON Projects.project_id = Project_Employees.project_id
JOIN Employees ON Project_Employees.employee_id = Employees.employee_id
JOIN Departments ON Employees.department_id = Departments.department_id
JOIN Employee_Tasks ON Employees.employee_id = Employee_Tasks.employee_id
JOIN Tasks ON Employee_Tasks.task_id = Tasks.task_id
JOIN Project_Tasks ON Projects.project_id = Project_Tasks.project_id AND Tasks.task_id = Project_Tasks.task_id
JOIN Time_Sheets ON Employees.employee_id = Time_Sheets.employee_id AND Tasks.task_id = Time_Sheets.task_id
LEFT JOIN Materials ON Projects.project_id = Materials.material_id
LEFT JOIN Suppliers ON Materials.supplier_id = Suppliers.supplier_id
LEFT JOIN Equipment ON Departments.department_id = Equipment.equipment_id;

-- left join

SELECT Projects.project_name, Clients.client_name
FROM Projects
LEFT JOIN Clients ON Projects.client_id = Clients.client_id;

-- right join

SELECT Employees.name, Departments.department_name
FROM Employees
RIGHT JOIN Departments ON Employees.department_id = Departments.department_id;

-- inner join

SELECT Invoices.invoice_id, Projects.project_name, Invoices.amount
FROM Invoices
INNER JOIN Projects ON Invoices.project_id = Projects.project_id;

-- full outer join
SELECT Suppliers.supplier_name, Materials.material_name
FROM Suppliers
LEFT JOIN Materials ON Suppliers.supplier_id = Materials.supplier_id
UNION
SELECT Suppliers.supplier_name, Materials.material_name
FROM Suppliers
RIGHT JOIN Materials ON Suppliers.supplier_id = Materials.supplier_id;

-- cross join
SELECT Tasks.task_description, Equipment.equipment_name
FROM Tasks
CROSS JOIN Equipment;

-- statements with aggregate functions and group by and without
SELECT Clients.client_name, SUM(Projects.budget) AS TotalBudget
FROM Clients
JOIN Projects ON Clients.client_id = Projects.client_id
GROUP BY Clients.client_name;


SELECT Employees.name, AVG(Time_Sheets.hours) AS AvgHours
FROM Employees
JOIN Time_Sheets ON Employees.employee_id = Time_Sheets.employee_id
GROUP BY Employees.name;


SELECT Departments.department_name, COUNT(DISTINCT Projects.project_id) AS ProjectCount
FROM Departments
JOIN Employees ON Departments.department_id = Employees.department_id
JOIN Project_Employees ON Employees.employee_id = Project_Employees.employee_id
JOIN Projects ON Project_Employees.project_id = Projects.project_id
GROUP BY Departments.department_name;


SELECT Projects.project_name, COUNT(Inspections.inspection_id) AS InspectionCount
FROM Projects
JOIN Inspections ON Projects.project_id = Inspections.project_id
GROUP BY Projects.project_name;


SELECT Projects.project_name, MAX(Invoices.amount) AS MaxInvoiceAmount
FROM Projects
JOIN Invoices ON Projects.project_id = Invoices.project_id
GROUP BY Projects.project_name;

SELECT Projects.project_name, COUNT(Project_Tasks.task_id) AS TaskCount
FROM Projects
JOIN Project_Tasks ON Projects.project_id = Project_Tasks.project_id
GROUP BY Projects.project_name;

SELECT Tasks.task_description, SUM(Time_Sheets.hours) AS TotalHours
FROM Tasks
JOIN Employee_Tasks ON Tasks.task_id = Employee_Tasks.task_id
JOIN Time_Sheets ON Employee_Tasks.task_id = Time_Sheets.task_id
GROUP BY Tasks.task_description;

-- statements with aggregate functions and group by and with having.

SELECT Clients.client_name, SUM(Projects.budget) AS TotalBudget
FROM Clients
JOIN Projects ON Clients.client_id = Projects.client_id
GROUP BY Clients.client_name
HAVING SUM(Projects.budget) > 500000;

SELECT Departments.department_name, COUNT(DISTINCT Projects.project_id) AS ProjectCount
FROM Departments
JOIN Employees ON Departments.department_id = Employees.department_id
JOIN Project_Employees ON Employees.employee_id = Project_Employees.employee_id
JOIN Projects ON Project_Employees.project_id = Projects.project_id
GROUP BY Departments.department_name
HAVING COUNT(DISTINCT Projects.project_id) > 3;

SELECT Projects.project_name, COUNT(Inspections.inspection_id) AS InspectionCount
FROM Projects
JOIN Inspections ON Projects.project_id = Inspections.project_id
GROUP BY Projects.project_name
HAVING COUNT(Inspections.inspection_id) > 2;

SELECT Employees.name, AVG(Time_Sheets.hours) AS AvgHours
FROM Employees
JOIN Time_Sheets ON Employees.employee_id = Time_Sheets.employee_id
GROUP BY Employees.name
HAVING AVG(Time_Sheets.hours) > 40;

SELECT Projects.project_name, MAX(Invoices.amount) AS MaxInvoiceAmount
FROM Projects
JOIN Invoices ON Projects.project_id = Invoices.project_id
GROUP BY Projects.project_name
HAVING MAX(Invoices.amount) > 100000;

SELECT Projects.project_name, COUNT(Project_Tasks.task_id) AS TaskCount
FROM Projects
JOIN Project_Tasks ON Projects.project_id = Project_Tasks.project_id
GROUP BY Projects.project_name
HAVING COUNT(Project_Tasks.task_id) > 5;

SELECT Suppliers.supplier_name, COUNT(DISTINCT Materials.material_id) AS MaterialCount
FROM Suppliers
JOIN Materials ON Suppliers.supplier_id = Materials.supplier_id
GROUP BY Suppliers.supplier_name
HAVING COUNT(DISTINCT Materials.material_id) > 2;









