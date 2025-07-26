# Todo Automation Testing Framework 🧪

[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/OmarElsheikh1/selenium-todo-testing-framework/main.yml?label=Build&logo=githubactions)](https://github.com/OmarElsheikh1/selenium-todo-testing-framework/actions)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/OmarElsheikh1/selenium-todo-testing-framework)


[![Java](https://img.shields.io/badge/Java-21-blue?logo=java)](https://docs.oracle.com/en/java/javase/21/)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green?logo=selenium)](https://www.selenium.dev/documentation/)
[![RestAssured](https://img.shields.io/badge/REST--Assured-4.5.1-lightgrey?logo=restassured)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8-red?logo=testng)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-brightgreen?logo=apachemaven)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.20.1-magenta?logo=allure)](https://docs.qameta.io/allure/)


A hybrid UI & API test automation framework for the [QACart Todo App](https://todo.qacart.com) built with Java, Selenium WebDriver, REST Assured, Cucumber, and TestNG.
It follows the Page Object Model (POM) design pattern, supports cross-browser execution, integrates API testing, and is structured for maintainability and CI/CD pipelines.

---

## Key Features ✨
🚦 Cross-Browser Testing (Chrome, Firefox, Edge)

🔄 API + UI Integration Testing

🧩 Page Object Model Implementation

🔐 Cookie Authentication Handling

📊 Allure Test Reporting

🧪 Data-Driven Configuration

🖼️ Automatic Screenshots on Failure

⚙️ Environment-Specific Configuration

---

## Technology Stack 💻
Core Testing: Selenium WebDriver 4.x

API Testing: REST Assured

Language: Java 21

Build Tool: Maven

Test Runner: TestNG

Reporting: Allure Framework

Utilities: JavaFaker, WebDriverManager

---

## Prerequisites 📦
Java JDK 21+

Maven 3.8+

Chrome/Firefox/Edge browsers

---

## 🚀 Clone & Run

```bash
# 1. Clone the repository
git clone https://github.com/OmarElsheikh1/selenium-todo-testing-framework.git

@ 2. Navigate into the project directory
cd selenium-todo-testing-framework

# 3. Run tests (default environment: PRODUCTION)
mvn clean test

# To run with local config
mvn clean test -Denv=local

# Optional: specify browser
mvn clean test -Dbrowser=firefox
```
> 💡 Note: Ensure the required browsers are installed and your Java and Maven versions match the prerequisites.

---

## Project Structure 🗂️
```text
src/
├── main/
│   ├── java/
│   │   ├── api/
│   │   │   ├── config/          # API endpoint configurations
│   │   │   ├── models/          # API request/response models
│   │   │   ├── requests/        # API request builders
│   │   │   └── utils/           # API helper utilities
│   │   ├── drivers/             # Browser driver management
│   │   ├── pages/               # Page Object classes
│   │   └── utils/               # Configuration utilities
│   └── resources/
│       └── config/              # Environment configuration files
│           ├── production.properties
│           └── local.properties
├── test/
│   ├── java/
│   │   ├── base/                # Test base classes
│   │   └── ui/                  # UI test cases
│   └── resources/
```

---

## Core Components 🔧
1. Page Objects

   
`LoginPage.java`
```java
public class LoginPage extends BasePage {
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("submit");
    
    public TodoPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickSubmit();
    }
}
```

`TodoPage.java`
```java
public class TodoPage extends BasePage {
    private final By addTodoButton = By.cssSelector("[data-testid=\"add\"]");
    private final By deleteTodo = By.cssSelector("[data-testid=\"delete\"]");
    
    public NewTodoPage clickAddTodoButton() {
        driver.findElement(addTodoButton).click();
        return new NewTodoPage(driver);
    }
}
```

2. API Integration


`RegisterApi.java`
```java
public class RegisterApi {
    public void register() {
        User user = UserUtils.generateRandomUser();
        Response response = given()
            .baseUri("https://todo.qacart.com")
            .header("Content-Type", "application/json")
            .body(user)
            .post(Endpoints.API_REGISTER_END_POINT);
        
        accessToken = response.path("access_token");
        userId = response.path("userID");
    }
}
```

`TasksApi.java`
```java
public class TasksApi {
    public void addTask(String token) throws IOException {
        Task task = new Task("Building Selenium", false);
        given()
            .baseUri(ConfigUtil.getInstance().getBaseUrl())
            .header("Content-Type", "application/json")
            .body(task)
            .auth().oauth2(token)
            .post(Endpoints.API_TASK_END_POINT);
    }
}
```

3. Configuration Management


`ConfigUtil.java`
```java
public class ConfigUtil {
    private static ConfigUtil configUtil;
    private final Properties properties;
    
    private ConfigUtil() throws IOException {
        String env = System.getProperty("env", "PRODUCTION").toUpperCase();
        switch(env) {
            case "PRODUCTION":
                properties = loadProperties("production.properties");
                break;
            case "LOCAL":
                properties = loadProperties("local.properties");
                break;
        }
    }
    
    public String getBaseUrl() { /* ... */ }
    public String getEmail() { /* ... */ }
    public String getPassword() { /* ... */ }
}
```

4. Test Base


`BaseTest.java`
```java
public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    @BeforeMethod
    public void setUp() {
        WebDriver driver = new DriverFactory().initDriver();
        setDriver(driver);
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        takeScreenshot(destFile); // Captures screenshot on failure
        getDriver().quit();
    }
    
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookies) {
        // Converts API cookies to browser cookies
    }
}

```

---

## Configuration ⚙️
Create environment-specific properties in `src/main/resources/config/`:

`production.properties`

```properties
baseUrl=https://todo.qacart.com
email=omar1@example.com
password=Test1234
```

`local.properties`

```properties
baseUrl=http://localhost:3000
email=omar1@example.com
password=Test1234
```
Select environment at runtime:

```bash
mvn test -Denv=local
```

### Running Tests ▶️
Run all tests:
```bash
mvn test
```
Run specific test class:
```bash
mvn test -Dtest=LoginTest
```
Run with specific environment:
```bash
mvn test -Denv=local
```
Run with specific browser:
```bash
mvn test -Dbrowser=firefox
```

### Test Cases 🧪
UI Test Example


`LoginTest.java`
```java
@Feature("Auth Feature")
public class LoginTest extends BaseTest {
    @Test
    public void shouldBeAbleToLoginWithEmailAndPassword() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        boolean isWelcomeDisplayed = loginPage
            .load()
            .login(ConfigUtil.getInstance().getEmail(), 
                   ConfigUtil.getInstance().getPassword())
            .isWelcomeMessageDisplayed();
        
        Assert.assertTrue(isWelcomeDisplayed);
    }
}
```

API-UI Integration Test


`TodoTest.java`
```java
@Feature("Todo Feature")
public class TodoTest extends BaseTest {
    @Test
    public void shouldBeAbleToAddNewTodo() throws IOException {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();  // API call
        
        NewTodoPage newTodoPage = new NewTodoPage(getDriver());
        newTodoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());  // Cookie injection
        
        String actualResult = newTodoPage
            .addNewTodo("Learn Selenium")
            .getTodoText();
        
        Assert.assertEquals(actualResult, "Learn Selenium");
    }
}
```

---

## CI/CD Integration 🤖
GitHub Actions configuration (maven.yml):

```yaml
name: Run Selenium Test
on: [push]
jobs:
  test:
    runs-on: windows-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
          cache: maven
          distribution: 'temurin'
      - uses: browser-actions/setup-chrome@latest
      - run: mvn clean test
```

### Best Practices ✅
1. **API‑UI Hybrid Testing**
   
   - Use API calls for setup and teardown  
   - Use UI flows for verification  
   - Inject cookies to share authentication between API and UI  



2. **Environment Management:**
```java
String env = System.getProperty("env", "PRODUCTION");
```

3. **Thread-Safe Drivers:**
```java
protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
```

4. **Automatic Screenshots:**
```java
@AfterMethod
public void takeScreenshot() { /* ... */ }
```

5. **Centralized Configuration:**
```java
ConfigUtil.getInstance().getBaseUrl();
```

---

## Troubleshooting 🛠️
Common Issues:

- Browser not launching: Ensure WebDriverManager dependencies are updated
- Element not found: Verify locators match current UI
- API failures: Check network connectivity and endpoint URLs
- Environment issues: Validate properties files exist in src/main/resources/config/

---

## Support & Contact 📬
For issues or contributions, please contact: [Omar Elsheikh GitHub Issues](https://github.com/OmarElsheikh1/selenium-todo-testing-framework/issues)
