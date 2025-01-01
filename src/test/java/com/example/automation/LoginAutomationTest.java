package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;

public class LoginAutomationTest {
    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:3000/");

            // Wait until the elements are visible and interactable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));

            // Perform the login actions
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Wait for the dashboard to load (check for an element that's only present on the dashboard)
            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardElementId"))); // Replace with an actual element on the dashboard

            // Assert that the element is present (confirm successful login)
            assertTrue(dashboardElement.isDisplayed(), "Dashboard not displayed after login");
        } finally {
            driver.quit();
        }
    }
}
