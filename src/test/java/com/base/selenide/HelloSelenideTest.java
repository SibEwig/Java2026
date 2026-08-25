package com.base.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HelloSelenideTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://shawarma.threadqa.ru/";
        Configuration.timeout = 10000;
        Configuration.headless = false;

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void test1() {
        Selenide.open("/");
        $x("//input[@data-testid='login-username']").setValue("owner");
        $x("//input[@data-testid='login-password']").setValue("owner123");
        $x("//button[@data-testid='login-submit']").click();
        $x("//button[@data-testid='logout-btn']").shouldBe(Condition.visible);
        $$x("//div[@data-testid='recipe-card']").should(CollectionCondition.sizeGreaterThanOrEqual(1));

    }
}
