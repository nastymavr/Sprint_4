package com.samokat.test;

import com.samokat.pages.OrderPage;
import org.junit.Assert;
import org.junit.Test;

public class SamokatTest extends BaseTest {

    private OrderPage orderPage;

    @Override
    public void setUp() {
        super.setUp();  // Вызов метода родительского класса для инициализации драйвера
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderViaTopButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Закрываем баннер куки, если он есть
        orderPage.closeCookies();

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
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Закрываем баннер куки, если он есть
        orderPage.closeCookies();

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
