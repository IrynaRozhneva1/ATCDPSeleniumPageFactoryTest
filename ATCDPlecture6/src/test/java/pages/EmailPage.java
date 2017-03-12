package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Iryna_Rozhneva
 */

public class EmailPage {
	@FindBy(xpath = "//div[contains(@class,'ns-view-toolbar-button-spam')]//span[contains(text(),'Это спам!')]")
	private WebElement buttonSpam;

	private final WebDriver driver;

	public EmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public InboxPage markAsSpam() {
		buttonSpam.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new InboxPage(driver);
	}
}