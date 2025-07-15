package com.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка "Заказать" в шапке сайта
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");

    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    // Кнопка логотипа Яндекс Самокат (для скролла)
    private final By buttonLogoSamokat = By.className("Header_LogoScooter__3lsAR");
    // URL главной страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
     //Кнопка закрытия куки
     private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Клик на верхнюю кнопку "Заказать"
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }


    // Клик на нижнюю кнопку "Заказать"
    public void clickBottomOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bottomButton = wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomButton);
        bottomButton.click();
    }

    // Метод для закрытия cookie-блоков
    public void closeCookies() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            cookieBtn.click();
        } catch (Exception ignored) {
        }
    }


    // Клик по вопросу по ключу
    public void clickQuestionByIndex(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        question.click();
    }

    // Проверка: ответ виден
    public boolean isAnswerDisplayedByIndex(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.isDisplayed();
    }

    // Получение текста ответа
    public String getAnswerTextByIndex(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText().trim();
    }

}
