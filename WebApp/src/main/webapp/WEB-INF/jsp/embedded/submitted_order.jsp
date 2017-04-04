<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${containsAddress}">
    <h3 class="text-center">
        Заказ оформлен! Информация о заказе будет отправлена на ваш номер.
    </h3>
</c:if>
<c:if test="${containsAddress == null}">
    <h3 class="text-center">
        Заказ оформлен! С Вами свяжутся по телефону для деталей доставки.
    </h3>
</c:if>
<c:if test="${containsRegData}">
    <h3 class="text-center">
        На Вашу электронную почту отправлено письмо для активации аккаунта.
    </h3>
</c:if>