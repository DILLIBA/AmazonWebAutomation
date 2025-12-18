package JavaSelenium.AmazonWebAutomation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Amazon {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void searchAndValidateProduct() throws Exception {
		navigateToHomePage();
		searchForProduct("Asus laptop");
		clickFirstSearchResult();
		validateProductPage();
		takeScreenshot();
	}

	public void navigateToHomePage() {
		driver.get("https://www.amazon.com/");
		List<WebElement> continueBtn = driver.findElements(
			    By.xpath("//button[normalize-space()='Continue shopping']")
			);

			if (!continueBtn.isEmpty()) {
			    continueBtn.get(0).click();
			}

	}

	public void searchForProduct(String product) {
		WebElement searchInput = wait.until(
				ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox"))
				);

		searchInput.clear();
		searchInput.click();
		searchInput.sendKeys(product);
	}

	public void clickFirstSearchResult() {
		By firstSuggestionAsus =
				By.xpath("//div[contains(text(), 'asus laptop') and @role='button']");

		WebElement suggestion = wait.until(
				ExpectedConditions.elementToBeClickable(firstSuggestionAsus)
				);

		suggestion.click();
	}

	public void validateProductPage() {
		WebElement addToCartButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//button[@aria-label='Add to cart']")
						)
				);

		Assert.assertTrue(addToCartButton.isDisplayed(),
				"Add to Cart button should be visible");
		Assert.assertTrue(driver.getTitle().toLowerCase().contains("asus"),
		        "Page title should contain product name");

	}


	public void takeScreenshot() throws Exception {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(
				src.toPath(),
				new File("product_page.png").toPath(),
				StandardCopyOption.REPLACE_EXISTING
				);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
