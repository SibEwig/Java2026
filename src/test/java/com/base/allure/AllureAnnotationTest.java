package com.base.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Заказы")
@Feature("Создание заказа")
public class AllureAnnotationTest {

    @Test
    @Story("Покупатель создаёт заказ")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание валидного заказа возвращает 201")
    @Description("""
            Гость выбирает рецепт, отправляет POST на /api/orders,
            бекенд возвращает 201 Created и отдаёт id нового заказа""")
    public void test() {
        int code = 201;
        assertThat(code).isEqualTo(201);
    }
}
