# 🏦 ParaBank Enterprise Test Automation Framework

An enterprise-grade, scalable, and maintainable **UI & End-to-End Test Automation Framework** built for the **ParaBank** banking web application using **Java (JDK 21), Selenium WebDriver, TestNG, Maven, Allure, ExtentReports, and Jenkins CI/CD**.

---

## 🚀 Key Features & Architecture

- **Page Object Model (POM):** Clean separation of test scripts and page locators/actions (`com.parabank.qa.pages`) for high maintainability and zero code duplication.
- **Data-Driven Architecture:** Externalized test data management powered by **Apache POI** (`ParabankTestData.xlsx`) and **TestNG DataProviders** (`com.parabank.qa.dataprovider`).
- **Parallel & Cross-Browser Execution:** Thread-safe WebDriver management supporting parallel execution via Maven profiles (`-Pparallel`) and dedicated TestNG runner suites (`testrunners`).
- **Centralized Configuration:** Environment URLs, browser selection, and timeout configurations managed dynamically through `config.properties`.
- **Dual Reporting & Logging:**
  - **ExtentReports** & **Allure Reports** for rich, interactive HTML execution dashboards with failure screenshots.
  - **Log4j2** (`log4j2.xml`) for structured execution logging across all framework layers.
- **CI/CD Ready:** Integrated with **Jenkins** using Maven build targets for automated regression and continuous testing.

---

## 🛠️ Tech Stack

| Category | Technology / Tool |
| :--- | :--- |
| **Programming Language** | Java (JavaSE-21) |
| **Web Automation** | Selenium WebDriver |
| **Test Framework** | TestNG |
| **Build & Dependency Tool** | Apache Maven (3.9.16) |
| **Data-Driven Engine** | Apache POI (Excel) & TestNG DataProvider |
| **Reporting** | ExtentReports & Allure Reports |
| **Logging** | Apache Log4j2 |
| **Version Control** | Git & GitHub |
| **CI/CD Pipeline** | Jenkins |

---

## 📂 Project Directory Structure

```text
ParaBankEnterpriseFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.parabank.qa.base        # BaseTest & ThreadLocal WebDriver setup
│   │   │   ├── com.parabank.qa.pages       # Page Object classes
│   │   │   ├── com.parabank.qa.reports     # ExtentReport & Allure reporting managers
│   │   │   └── com.parabank.qa.utils       # Core utilities (Waits, Screenshots, ConfigReader)
│   │   └── resources/
│   │       └── config.properties           # Environment & browser configurations
│   └── test/
│       ├── java/
│       │   ├── com.parabank.qa.dataprovider # TestNG DataProvider implementations
│       │   ├── com.parabank.qa.tests        # Automated test suites
│       │   └── com.parabank.qa.utils        # Test-level helper utilities (ExcelReader)
│       └── resources/
│           ├── testdata/
│           │   └── ParabankTestData.xlsx    # Externalized Excel test data
│           ├── testrunners/                 # Custom TestNG XML suite runners
│           └── log4j2.xml                   # Log4j2 logging configuration
├── allure-results/                          # Generated Allure raw results (ignored in Git)
├── logs/                                    # Execution log files (ignored in Git)
├── reports/                                 # Generated ExtentReports HTML (ignored in Git)
├── target/                                  # Maven build artifacts (ignored in Git)
├── test-output/                             # Default TestNG reports (ignored in Git)
├── pom.xml                                  # Maven dependencies & execution profiles
├── testng.xml                               # Master TestNG suite file
└── README.md                                # Framework documentation
⚙️ Prerequisites
Java Development Kit (JDK 21) – JAVA_HOME configured.

Apache Maven (3.9+) – MAVEN_HOME configured.

Google Chrome / Microsoft Edge browser installed.

Allure Commandline (optional, for serving Allure reports locally).

▶️ How to Run Tests
1. Clone the Repository
Bash
git clone [https://github.com/sahusudhir023/ParaBankEnterpriseFramework.git](https://github.com/sahusudhir023/ParaBankEnterpriseFramework.git)
cd ParaBankEnterpriseFramework
2. Run Default Test Suite
Bash
mvn clean test
3. Run Parallel Execution Profile
Bash
mvn clean test -Pparallel
📊 Test Reports & Logs
ExtentReport: Open reports/ExtentReport.html in any web browser after execution.

Allure Report: Run the following command from the project root:

Bash
allure serve allure-results
Execution Logs: Available inside the logs/ directory.

🔄 CI/CD Integration (Jenkins)
Configured as a Maven/Freestyle Jenkins job:

Build Goal: clean test -Pparallel

Automated Execution: Pulls source code from GitHub and executes parallel test suites automatically.

👨‍💻 Author
Sudhir Sahu

QA Automation Engineer / SDET