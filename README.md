**Project Description**

This project is a UI test automation framework developed using Selenium + TestNG + Java.
It automates a product search, filtering, selection, and add-to-cart scenario on the target e-commerce website.

**Framework follows:**

- Page Object Model (POM)
- Thread-safe Driver structure
- Explicit Wait strategy
- Configurable environment structure

**Instructions to Run the Project**
Requirements:
Make sure these are installed:
- Java JDK 11+
- Maven
- Chrome browser

Check installations:
- java -version
- mvn -version

**Run via IntelliJ IDEA**
- Open project in IntelliJ
- Wait until Maven dependencies load
- Navigate to:
src/test/java/tests/ProductPurchaseTest

Right click -> Run

**Run via Terminal (Any IDE / CI / CLI)**
From project root directory:

mvn clean test

**Configurable URL**
Base URL is defined in:

src/main/resources/config.properties


You can change environment target by editing:
url=https://www.hepsiburada.com

**Technologies Used**
- Java
- Selenium 4
- TestNG
- Maven
- WebDriverManager

**Page Object Model Design Pattern**
Framework Architecture:
src
├── main
│   ├── java
│   │   ├── pages        → Page classes
│   │   └── utilities    → Driver, Wait, Config, BaseTest
│   └── resources        → config.properties
│
└── test
└── java/tests       → Test classes

**Assumptions Made During Implementation**

- Test scenario assumes site is reachable and not under maintenance.
- Chrome is used as default browser.
- First product listed is assumed selectable.
- HP brand filter is assumed present in results.
- Cookie popup may appear and is handled.
- Dynamic elements may reload -> handled via explicit waits.
- If click interception occurs -> JS click fallback is used.
- Multiple tab opening is possible -> last tab is assumed product page.
- Due to the StaleElementReferenceException error, some WaitUtils functions were used while other operations used different actions.

**Test Scenario Covered**
Test: Product Search → Filter → Select → Add to Cart → Verify

Steps automated:
1. Open homepage
2. Search product
3. Verify results
4. Apply brand filter
5. Select first product
6. Add to cart
7. Go to cart
8. Verify product exists
