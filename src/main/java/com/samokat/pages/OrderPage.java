    package com.samokat.pages;

    import org.openqa.selenium.*;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;

    public class OrderPage {

        private final WebDriver driver;

        // Локаторы

        // Кнопка "Заказать" в шапке сайта
        private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");

        // Кнопка логотипа Яндекс Самокат (для скролла)
        private final By buttonLogoSamokat = By.className("Header_LogoScooter__3lsAR");

        // Нижняя кнопка "Заказать"
        private final By bottomOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

        // Поля первого шага
        private final By nameInput = By.xpath("//input[@placeholder='* Имя']"); // Локатор для поля "Имя"
        private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']"); // Локатор для поля "Фамилия"
        private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); // Локатор для поля "Адрес"
        private final By metroInput = By.className("select-search__input"); // Локатор для поля "Метро"
        private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); // Локатор для поля "Телефон"
        private final By nextButton = By.xpath("//button[text()='Далее']"); // Локатор для кнопки "Далее"

        // Поля второго шага
        private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']"); // Локатор для поля "Когда привезти самокат"
        private final By dateButton = By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='5']"); // Локатор для кнопки выбора дня в календаре (например, 5)
        private final By rentalPeriodDropdown = By.className("Dropdown-control"); // Локатор для выпадающего списка выбора срока аренды
        private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']"); // Локатор для выбора опции "двое суток" в списке срока аренды

        // Подтверждение заказа
        private final By submitOrderButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");
        private final By yesButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Да']");

        // Модальное окно подтверждения
        private final By orderConfirmationModal = By.xpath("//button[text()='Посмотреть статус']");

        public OrderPage(WebDriver driver) {
            this.driver = driver;
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



        // Заполнение полей первого шага заказа
        public void fillFirstStep(String name, String surname, String address, String metro, String phone) {
            driver.findElement(nameInput).sendKeys(name);
            driver.findElement(surnameInput).sendKeys(surname);
            driver.findElement(addressInput).sendKeys(address);

            WebElement metroField = driver.findElement(metroInput);
            metroField.click();
            metroField.sendKeys(metro);
            metroField.sendKeys(Keys.ARROW_DOWN);
            metroField.sendKeys(Keys.ENTER);

            driver.findElement(phoneInput).sendKeys(phone);
        }


     // Нажать кнопку "Далее" после заполнения первого шага
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


         // Заполнение второго шага — дата и срок аренды

        public void fillSecondStep() {
            driver.findElement(dateInput).click();
            driver.findElement(dateButton).click();
            driver.findElement(rentalPeriodDropdown).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();
        }


         // Нажать кнопку "Заказать" на втором шаге
        public void clickSubmitOrderButton() {
            driver.findElement(submitOrderButton).click();
        }


         // Подтвердить заказ нажатием "Да"

        public void clickYesButton() {
            driver.findElement(yesButton).click();
        }

         // Проверить, что появилось модальное окно с подтверждением заказа

        public boolean isOrderConfirmed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationModal));
                return driver.findElement(orderConfirmationModal).isDisplayed();
            } catch (TimeoutException e) {
                return false;
            }
        }
    }