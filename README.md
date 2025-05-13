# QA Automation Framework

## Overview
This test automation framework is built using Selenium WebDriver with Java and TestNG, designed for automated testing of web applications. It implements the Page Object Model (POM) design pattern and includes features like automatic test retry, explicit waits, and comprehensive reporting.

## Tech Stack
- Java 21
- Selenium WebDriver
- TestNG
- Maven

## Prerequisites
- Java Development Kit (JDK) 21
- Maven
- Chrome/Firefox browser
- IDE (IntelliJ IDEA recommended)

## Setup
1. Clone the repository: git clone [repository-url]

2. Install dependencies: mvn clean install

3. Run test via maven which will run default on Chrome browser: mvn clean test
   Optionally browser can be send as a parameter, supporting Chrome and Firefox. Example: mvn clean test -Dbrowser=firefox
   
## Key Features

### Page Object Model
- Each web page has its corresponding Page Object class
- Maintains separation between test code and page-specific code
- Reduces code duplication and improves maintenance

### Retry Mechanism
The framework includes an automatic retry mechanism for failed tests:
- Configurable retry count
- Automatic retry listener
- Detailed retry logging

### Explicit Waits
Implements smart waiting mechanisms:
- No hardcoded sleeps
- Custom wait conditions
- Efficient element synchronization

### Helper Methods
Common utility methods available in `HelperMethods.java`:
- Click when clickable
- Element presence checks
- Custom wait implementations


