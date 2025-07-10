package com.samokat.test;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Before;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        // Настройка драйвера
        System.setProperty("webdriver.chrome.driver", "D:\\Nasti\\drive\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Запуск браузера в полноэкранном режиме
        driver.manage().window().maximize();
    }

    // Метод для закрытия браузера после теста
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
