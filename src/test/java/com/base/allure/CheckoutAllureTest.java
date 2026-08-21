//package com.base.allure;
//
//import io.qameta.allure.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//@Epic("Заказы")
//@Feature("Оформление заказа")
//class CheckoutAllureTest {
//
//    @Test
//    @Story("Покупатель создаёт и оплачивает заказ")
//    @Severity(SeverityLevel.CRITICAL)
//    @DisplayName("Успешный флоу создания и оплаты заказа")
//    @Description("""
//            Пользователь логинится под своей учётной записью, выбирает блюдо и количество, оформляет
//            заказ. API возвращает номер заказа. После этого заказ оплачивается картой. Бекенд возвращает статус заказа
//            ACTIVE.
//            """)
//    void successfulCheckoutFlow() {
//        String email = "test@email.com";
//        String password = "password";
//        int recipeId = 5;
//        int qty = 20;
//        long cardNumber = 2546248598754258L;
//        loginAs(email, password);
//        int orderId = createOrder(recipeId, qty);
//        payByCard(orderId, cardNumber);
//        String status = fetchStatus(orderId);
//        assertStatus(status, "ACTIVE");
//    }
//
//    @Step("Логинимся как {0}")
//    private void loginAs(String email, String password) {
//        // TODO (заглушка)
//    }
//
//    @Step("Создаём заказ с рецептом {0} и количеством {1}")
//    private int createOrder(int recipeId, int qty) {
//        // TODO (заглушка)
//    }
//
//    @Step("Производим оплату картой")
//    private void payByCard(int orderId, long cardNumber) {
//        // TODO (заглушка)
//    }
//
//    @Step("Получаем статус заказа с номером {0}")
//    private String fetchStatus(int orderID) {
//        // TODO (заглушка)
//    }
//
//    @Step("Сверяем фактический и ожидаемый статус")
//    private void assertStatus(String actual, String expected) {
//        // TODO (заглушка)
//    }
//}
