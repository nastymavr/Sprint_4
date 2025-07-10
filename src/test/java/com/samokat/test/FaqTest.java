package com.samokat.test;

import com.samokat.pages.MainPage;
import com.samokat.pages.OrderPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    private final String questionKey;
    private final String expectedAnswer;

    private MainPage mainPage;
    private OrderPage orderPage;

    public FaqTest(String questionKey, String expectedAnswer) {
        this.questionKey = questionKey;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "FAQ: {0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"howMuchCost", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"multipleScooters", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"rentalTime", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"orderToday", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"extendOrder", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"bringCharger", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"cancelOrder", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"deliveryOutsideMkad", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUpPages() {
        driver.get(MainPage.URL);
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        orderPage.closeCookies();
    }

    @Test
    public void checkFaqAnswerText() {
        By questionLocator = mainPage.getQuestionLocator(questionKey);
        By answerLocator = mainPage.getAnswerLocator(questionKey);

        mainPage.clickFaqQuestion(questionLocator);

        Assert.assertTrue("Ответ не отображается для вопроса: " + questionKey,
                mainPage.isAnswerVisible(answerLocator));

        String actualAnswer = mainPage.getAnswerText(answerLocator);
        Assert.assertEquals("Текст ответа не совпадает для вопроса: " + questionKey, expectedAnswer, actualAnswer);
    }
}
