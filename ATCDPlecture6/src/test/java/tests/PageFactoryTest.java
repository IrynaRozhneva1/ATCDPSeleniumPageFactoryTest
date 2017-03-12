package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.EmailPage;
import pages.InboxPage;
import pages.NewEmailPage;
import pages.SignInPage;
import pages.SpamPage;
import pages.StartPage;

public class PageFactoryTest {

	private final String USERNAME1 = "IrynaATCDP";
	private final String PASSWORD1 = "123456qw";
	private final String USERNAME2 = "IrynaATCDP1";
	private final String PASSWORD2 = "123456zx";
	private final String recipientEmail = "IrynaATCDP1@yandex.ru";
	private final String subject1 = "Subj1 test";
	private final String subject2 = "Subj2 test";
	private final String messageText = "Hello my friend!";

	@Test
	public void Test() {
		WebDriver driver = new FirefoxDriver();
		StartPage startPage = new StartPage(driver);
		startPage.open();
		
		// log in as user1
		SignInPage signInPage = startPage.invokeSignIn();
		InboxPage inboxPage = signInPage.signIn(USERNAME1, PASSWORD1);
		
		// write a new email
		NewEmailPage newEmail = inboxPage.invokeNewMessage();
		newEmail.sendEmail(recipientEmail, subject1, messageText);
		newEmail.exitFromAccount();

		// log in as user2
		startPage.invokeSignIn();
		signInPage.signIn(USERNAME2, PASSWORD2);
		
		// find email and mark as spam
		EmailPage emailPage = inboxPage.openEmail();
		emailPage.markAsSpam();
		newEmail.exitFromAccount();
		
		// log in as user1
		startPage.invokeSignIn();
		signInPage.signIn(USERNAME1, PASSWORD1);
		
		// write a new email
		inboxPage.invokeNewMessage();
		newEmail.sendEmail(recipientEmail, subject2, messageText);
		newEmail.exitFromAccount();
		
		// log in as user2
		startPage.invokeSignIn();
		signInPage.signIn(USERNAME2, PASSWORD2);
		
		//go to spam and compare user name
		SpamPage spamPage = inboxPage.openSpam();
		String userSubjFromEmail = spamPage.getSpamSubject();
		Assert.assertEquals(subject2, userSubjFromEmail);
		driver.quit();
	}
}