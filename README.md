## Overview
Main aim of the project was to create successful connection with a database to enable data input and output. Project focuses mostly on creating connection with database, proper usage of SQL syntax, deployment of DAO and Page Object Model patterns to enable fetching and sending data into database.

Running the project is dependent on deployment of MySQL database from dump.sql.

This Project was part of A1QA automation testing internship.

## Test Cases

### 1. Registration and Profile Setup Test (`RegistrationAndProfileSetupTest`)

#### Purpose:
This test case validates the registration process and profile setup functionality of the web application.

#### Test Steps:
1. Navigate to the home page.
2. Click on the link to navigate to the next page.
3. Input random valid password and email, accept the terms of use, and click the 'next' button.
4. Choose two random interests, upload an image, and click the 'Next' button.

### 2. Accept Cookies Test (`AcceptCookiesTest`)

#### Purpose:
This test case verifies the functionality of the cookie acceptance feature on the web application.

#### Test Steps:
1. Open the home page.
2. Navigate to the next page.
3. Validate the display of the cookies form.
4. Accept cookies and verify that the cookies form is no longer displayed.

### 3. Hide Help Form Test (`HideHelpFormTest`)

#### Purpose:
This test case ensures the correct behavior of hiding the help form in the web application.

#### Test Steps:
1. Open the home page.
2. Navigate to the next page.
3. Validate the display of the help form.
4. Hide the help form and verify that it is no longer displayed.

### 4. Start Time of Timer Test (`StartTimeOfTimerTest`)

#### Purpose:
This test case checks if the timer on the game page starts from 00:00:00.

#### Test Steps:
1. Open the home page.
2. Navigate to the game page.
3. Validate that the timer starts from 00:00:00.

## Project Structure

- **`tests.TC1` Package:** Contains individual test classes for each test case.
- **`Database` Package:** Contains database connection, entities and dao pattern.
- **`PageObjects.pages` Package:** Page object classes representing different pages in the web application.
- **`Utilities` Package:** Utility classes for data generation and file reading.

## Technologies
The project is created with the following technologies:

- Java 17
- TestNG 7.6.1
- Aquality Selenium
- MySQL 8.0.31
