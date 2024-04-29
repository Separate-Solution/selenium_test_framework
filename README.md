## Overview

This framework has been created in order for me to understand how a team creates an automation framework alongwith all the concepts of java, selenium, testng. This framework will work as a base for my future automation projects in order to ease the flow of development of
test methods, design test suites and test cases, report generation etc. Basically this will help save time while also
making everything structured.

---

## Pre-requisites

Please note that below things are already present in your system before moving to next step:

1. JDK v17 or more
2. Maven v3.0 or above

---

## Setup

Follow the below steps in order to have the automation framework inside your IDE of choice:

1. Once you've cloned/forked the repo and the project is in your local system, check your **pom.xml** file and load all maven dependencies.
2. [OPTIONAL] If the above step shows some errors, then try to generate sources and update folders of the projects.
3. **Build** the project.
4. Confirm the project structure from your IDE project settings - it should show one module named "automation_framework". Use this step to mark the project directories as _Sources, Test Sources, Resources_ and _Test Resources_ (if not done already).
5. Once all the above steps are executed successfully, then you are ready with the basic setup required to start automating your test cases. Please refer the **project structure and development guidelines** for more info on how to get started with contributing to the framework.

---

## TestNG Configurations setup

Follow the below steps to set up the TestNG run configuration in your IDE(the below steps might differ depending upon
your IDE as this is created with IDEA in mind):

1. Once the project is built successfully, click on the **Run** option in the toolbar.
2. Select **Edit Configurations**.
3. Click on the **+** button present in the top left corner and select **TestNG** under **Add new configurations**.
4. A popup for setting up your TestNG run configs will open.
5. Add the name of your config and select **Suite** in *Test Kind* dropdown.
6. Now a new field for test suite will appear under the dropdown. Enter the path of the TestNG.xml file inside it.
7. Select the **In whole project** radio button.
8. Enter the path of the **results** folder in the *Output Directory*.
9. Click **Apply** and then **OK**. Now you can execute your test cases from anywhere!

---

## Project Structure and Development Guidelines

This section is only for those who want to know deeply about how all this works  and want to gain a deeper understanding of different features of the framework and the flow of development.

### Design Pattern

This framework follows Page Object Model as its design pattern, and we request the future contributors to follow the same whenever developing new functionalities. The basic project structure goes like this : 

```
├── pom.xml
├── README.md
├── src
│   ├── config
│   │   └── properties
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── automation_framework
│   │   │           ├── base
│   │   │           ├── page_objects
│   │   │           │   ├── exceltojson
│   │   │           │   ├── faststart
│   │   │           │   ├── home
│   │   │           │   ├── login
│   │   │           │   └── teammanagement
│   │   │           └── utils
│   │   │               ├── common_utils
│   │   │               ├── load_strategies
│   │   │               ├── logging
│   │   │               └── read_files
│   │   └── test
│   │       └── com
│   │           └── automation_framework
│   │               └── test
│   │                   ├── base
│   │                   ├── listeners
│   │                   ├── report
│   │                   └── rerun_test
│   └── test
│       ├── java
│       │   └── com
│       │       └── automation_framework
│       │           └── test_cases
│       └── resources
└── test-logs
    └── automation_framework_local.log

```

### main/java

All the core logic, utils classes and page objects are created here.

`BasePage` class contains the helper methods related to webdriver actions. All the future methods that will be created for supporting additional webdriver actions will be created in here. All the `page_objects` inherits `BasePage` in order to effectively pass the instance of the webdriver and utilize helper methods. 

Each page object has been created under a directory related to the webpage in AUT. The locators have been defined using `@FindBy` and their access modifier has been set as `private`. WebDriver actions have been done using `BasePage` helper methods. Sufficient waits have been added before performing actions on the webelements to avoid exceptions such as `NoSuchElementException`, `ElementClickInterceptedException`, etc. 

### main/resources~~~~

All the resources required for execution of methods related to `main/java`'s classes are added in here. Properties file added for different environments are also present here.

### main/test

All the core logic for webdriver initialization, termination, listeners and extent-report management are present here. All the test cases inherits `BaseTest`.

### test/java

All the TestNG test cases are created here. Test cases are created under the directory with the name same as the page objects. 

### pom.xml

All the project dependencies are present here and this framework follows single pom structure. The dependencies won't be updated on regular basis so long as the flow does not break.

### Naming conventions

- The project directories' names follow snake case (for e.g. : `test_cases`).
- Classes follow regular Java naming convention (for e.g. : `BasePage.class`).
- Methods' and variables' names follow camel case (for e.g. : `testUserHasLoggedInSuccessfully`).