package io.telepnev.Jenkins_Selenoid_video_Allure;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NewWindowTest {

    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    public void newTabWindowTest() {
        open("https://demoqa.com/browser-windows");
        $("#windowButton").click();
        switchTo().window(1);
        $("h1").shouldHave(text("This is a sample page"));
        switchTo().window(0);
        $(".main-header").shouldHave(text("Browser Windows"));

    }
}
