package com.samokat.test;
import com.samokat.pages.MainPage;
import com.samokat.pages.OrderPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SamokatTest extends BaseTest {

    private OrderPage orderPage;
    private MainPage mainPage;

    @Before
    public void initPages() {
        orderPage = new OrderPage(driver);
        mainPage = new MainPage(driver);
        mainPage.closeCookies();
    }

    @Test
    public void testOrderViaTopButton() {
        // Кликаем на верхнюю кнопку "Заказать"
        orderPage.clickTopOrderButton();

        // Заполняем первый шаг: имя, фамилия, адрес, метро, телефон
        orderPage.fillFirstStep(
                "Настя",
                "Мавричева",
                "ул. Примерная, 1",
                "Черкизовская",
                "89999999999"
        );

        // Переходим к следующему шагу
        orderPage.clickNextButton();

        // Заполняем второй шаг — дату и срок аренды
        orderPage.fillSecondStep();

        // Нажимаем кнопку "Заказать"
        orderPage.clickSubmitOrderButton();

        // Подтверждаем заказ
        orderPage.clickYesButton();

        // Проверяем, что появилось окно подтверждения заказа
        Assert.assertTrue("Окно подтверждения заказа не появилось!", orderPage.isOrderConfirmed());
    }

    @Test
    public void testOrderViaBottomButton() {
        // Кликаем на нижнюю кнопку "Заказать"
        orderPage.clickBottomOrderButton();

        // Заполняем первый шаг: имя, фамилия, адрес, метро, телефон
        orderPage.fillFirstStep(
                "Евгений",
                "Печорин",
                "ул. Приморская, 1",
                "Черкизовская",
                "89999999955"
        );

        // Переходим к следующему шагу
        orderPage.clickNextButton();

        // Заполняем второй шаг — дату и срок аренды
        orderPage.fillSecondStep();

        // Нажимаем кнопку "Заказать"
        orderPage.clickSubmitOrderButton();

        // Подтверждаем заказ
        orderPage.clickYesButton();

        // Проверяем, что появилось окно подтверждения заказа
        Assert.assertTrue("Окно подтверждения заказа не появилось!", orderPage.isOrderConfirmed());
    }
}
