# Amazon Automation Test

This is a Selenium WebDriver automation project using **Java, TestNG, and Maven**.  
It automates searching for a product on Amazon and validates that the product details page loads correctly.

## Prerequisites

- Java JDK installed  
- Maven installed  
- Google Chrome browser  
- ChromeDriver matching your Chrome version (added to PATH)

## How to Run

1. Open terminal or command prompt.
2. Navigate to the project root directory.
3. Run the following command:

```bash
mvn test 
     ```

## Test Flow
- Launch browser
- Search product
- Open product details page
- Validate page load
- Capture screenshot
