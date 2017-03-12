package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Iryna_Rozhneva
 */

public class InboxPage {
	@FindBy(xpath = "//span[contains(@class,'mail-Toolbar-Item-Text') and text()='Написать']")
	private WebElement buttonNewEmail;
	
	@FindBy(xpath = "//span[text()='Iryna Test']")
	private WebElement tapOnEmail;
	
	@FindBy(xpath = "//span[contains(@class,'mail-NestedList-Item-Name') and text()='Спам']")
	private WebElement tapOnSpam;

	private final WebDriver driver;

	public InboxPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public NewEmailPage invokeNewMessage() {
		buttonNewEmail.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new NewEmailPage(driver);
	}
	
	public EmailPage openEmail() {
		tapOnEmail.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new EmailPage(driver);
	}
	
	public SpamPage openSpam() {
		tapOnSpam.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SpamPage(driver);
	}
}