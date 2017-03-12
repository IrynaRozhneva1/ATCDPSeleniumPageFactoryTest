package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Iryna_Rozhneva
 */

public class SpamPage {
	@FindBy(xpath = "//span[text()='Iryna Test']/../../..//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span")
	private WebElement spamSubject;

	private final WebDriver driver;

	public SpamPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getSpamSubject() {
		return spamSubject.getText();
	}
}
