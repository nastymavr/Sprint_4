package com.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // URL главной страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
     //Кнопка закрытия куки
     private final By cookieButton = By.id("rcc-confirm-button");
    // Локаторы для вопросов
    private final By howMuchCostQuestion = By.id("accordion__heading-0");
    private final By multipleScootersQuestion = By.id("accordion__heading-1");
    private final By rentalTimeQuestion = By.id("accordion__heading-2");
    private final By orderTodayQuestion = By.id("accordion__heading-3");
    private final By extendOrderQuestion = By.id("accordion__heading-4");
    private final By bringChargerQuestion = By.id("accordion__heading-5");
    private final By cancelOrderQuestion = By.id("accordion__heading-6");
    private final By deliveryOutsideMkadQuestion = By.id("accordion__heading-7");

    // Локаторы для ответов
    private final By howMuchCostAnswer = By.id("accordion__panel-0");
    private final By multipleScootersAnswer = By.id("accordion__panel-1");
    private final By rentalTimeAnswer = By.id("accordion__panel-2");
    private final By orderTodayAnswer = By.id("accordion__panel-3");
    private final By extendOrderAnswer = By.id("accordion__panel-4");
    private final By bringChargerAnswer = By.id("accordion__panel-5");
    private final By cancelOrderAnswer = By.id("accordion__panel-6");
    private final By deliveryOutsideMkadAnswer = By.id("accordion__panel-7");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
    public void clickQuestion(String questionKey) {
        By questionLocator = getQuestionLocator(questionKey);
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        question.click();
    }

    // Проверка: ответ виден
    public boolean isAnswerDisplayed(String questionKey) {
        By answerLocator = getAnswerLocator(questionKey);
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.isDisplayed();
    }

    // Получение текста ответа
    public String getAnswerTextByKey(String questionKey) {
        By answerLocator = getAnswerLocator(questionKey);
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText().trim();
    }


    // Получаем локатор ответа по имени вопроса
    public By getAnswerLocator(String questionName) {
        switch (questionName) {
            case "howMuchCost": return howMuchCostAnswer;
            case "multipleScooters": return multipleScootersAnswer;
            case "rentalTime": return rentalTimeAnswer;
            case "orderToday": return orderTodayAnswer;
            case "extendOrder": return extendOrderAnswer;
            case "bringCharger": return bringChargerAnswer;
            case "cancelOrder": return cancelOrderAnswer;
            case "deliveryOutsideMkad": return deliveryOutsideMkadAnswer;
            default: throw new IllegalArgumentException("Invalid question name: " + questionName);
        }
    }

    // Получаем локатор вопроса по имени
    public By getQuestionLocator(String questionName) {
        switch (questionName) {
            case "howMuchCost": return howMuchCostQuestion;
            case "multipleScooters": return multipleScootersQuestion;
            case "rentalTime": return rentalTimeQuestion;
            case "orderToday": return orderTodayQuestion;
            case "extendOrder": return extendOrderQuestion;
            case "bringCharger": return bringChargerQuestion;
            case "cancelOrder": return cancelOrderQuestion;
            case "deliveryOutsideMkad": return deliveryOutsideMkadQuestion;
            default: throw new IllegalArgumentException("Invalid question name: " + questionName);
        }
    }
}
