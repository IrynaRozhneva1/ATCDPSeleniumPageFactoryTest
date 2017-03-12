package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Iryna_Rozhneva
 */
public class SignInPage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement inputPassword;

    @FindBy(xpath = "//span[contains(@class,'new-auth-submit')]//button[@type='submit']")
    private WebElement buttonSignIn;

    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public InboxPage signIn(String username, String password){
    	inputLogin.clear();
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new InboxPage(driver);
    }
}
