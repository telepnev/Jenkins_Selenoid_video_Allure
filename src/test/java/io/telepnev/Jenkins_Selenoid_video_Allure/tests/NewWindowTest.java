package io.telepnev.Jenkins_Selenoid_video_Allure.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;
import static io.telepnev.Jenkins_Selenoid_video_Allure.helpers.AttachmentsHelper.*;


public class NewWindowTest {

    @BeforeAll
    public static void setUp() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        //gradle test -Dremote.browser.url=selenoid.autotests.cloud

        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
        Configuration.startMaximized = true;

    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        closeWebDriver();
    }

    @Test
    @DisplayName("Successful switch to new window and test ...")
    public void positiveNewTabWindowTest() {
        step("Открываем 'Tools QA'",
                ()-> open("https://demoqa.com/browser-windows"));
        step("Кликаем кнопку 'New Window'",
                ()-> $("#windowButton").click());
        step("Переключаемся на новое окно и проверяем текст 'This is a sample page'",
                ()-> {switchTo().window(1);
                    $("h1").shouldHave(text("This is a sample page"));});
        step("Переключаемся обратно на главную страницу 'Tools QA'",
                ()-> {switchTo().window(0);
                    $(".main-header").shouldHave(text("Browser Windows"));});

    }

    @Test
    @DisplayName("Unsuccessful switch to new window and test ...")
    public void negativeNewTabWindowTest() {
        step("Открываем 'Tools QA'",
                ()-> open("https://demoqa.com/browser-windows"));
        step("Кликаем кнопку 'New Window'",
                ()-> $("#windowButton").click());
        step("Переключаемся на новое окно и проверяем текст 'This is a sample page'",
                ()-> {switchTo().window(1);
            $("h1").shouldHave(text("!!!!!!!!  This is a sample page  !!!!!!"));});
        step("Переключаемся обратно на главную страницу 'Tools QA'",
                ()-> {switchTo().window(0);
            $(".main-header").shouldHave(text("Browser Windows"));});


    }
}
