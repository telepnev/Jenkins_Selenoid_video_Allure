package io.telepnev.Jenkins_Selenoid_video_Allure;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class NewWindowTest {

    @BeforeAll
    public static void setUp() {

        Configuration.startMaximized = true;
    }

    @Test
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
    public void negativeNewTabWindowTest() {
        step("Открываем 'Tools QA'",
                ()-> open("https://demoqa.com/browser-windows"));
        step("Кликаем кнопку 'New Window'",
                ()-> $("#windowButton").click());
        step("Переключаемся на новое окно и проверяем текст 'This is a sample page'",
                ()-> {switchTo().window(1);
            $("h1").shouldHave(text("This is a sample page 666"));});
        step("Переключаемся обратно на главную страницу 'Tools QA'",
                ()-> {switchTo().window(0);
            $(".main-header").shouldHave(text("Browser Windows"));});


    }
}
