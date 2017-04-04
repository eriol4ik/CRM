<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Оформление заказа | iShop</title>
      <%@include file="embedded/load-footer-libraries.jsp"%>
  </head>
  <body>

    <%@include file="embedded/menu.jsp"%>

    <div class="product-big-title-area">
        <div class="row">
            <div class="col-md-12">
                <div class="product-big-title text-center">
                    <h2>Оформление заказа</h2>
                </div>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${sessionScope['scopedTarget.cart'].amount > 0}">
            <div class="single-product-area">
                <div class="zigzag-bottom"></div>
                <div class="container" id="order-form">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="product-content-right">
                                <c:if test="${customer == null}">
                                    <div class="acc-info">
                                        Уже покупали у нас?
                                        <a data-toggle="collapse" href="#login-form-wrap">
                                            Войти в аккаунт
                                        </a>
                                        <fieldset id="login-form-wrap" class="collapse">
                                            <div class="inner-form">
                                                <p>
                                                    <input type="text" placeholder="Электронная почта">
                                                </p>
                                                <p>
                                                    <input type="password" placeholder="Пароль">
                                                </p>
                                                <p class="lost-pass">
                                                    <a href="/recover">Забыли пароль?</a>
                                                </p>
                                                <p class="btn-login">
                                                    <button class="btn-block" type="submit" onclick="logIn()">Войти</button>
                                                </p>
                                            </div>
                                        </fieldset>
                                    </div>

                                </c:if>
                                <fieldset class="checkout" name="checkout">
                                    <div id="customer_details" class="col2-set">
                                        <div class="col-md-8">
                                            <div>
                                                <h3>Контактные данные</h3>
                                                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                    <input id="deliveryName" type="text" placeholder="Имя" value="${customer.name}">
                                                </p>
                                                <p>
                                                    <input id="deliveryPhone" type="text" placeholder="Телефон" value="${customer.mobile}">
                                                </p>
                                            </div>
                                            <div>
                                                <h3>Детали доставки</h3>
                                                <c:if test="${customer == null}">
                                                    <p>Оставьте эти поля пустыми, если хотите, чтобы наш менеджер уточнил детали доставки по телефону.</p>
                                                </c:if>

                                                <p id="billing_city_field" class="form-row form-row-wide address-field validate-required" data-o_class="form-row form-row-wide address-field validate-required">
                                                    <input id="deliveryCity" type="text" placeholder="Город" value="${customer.city}">
                                                </p>
                                                <p>
                                                    <input id="deliveryStreet" type="text" placeholder="Улица" value="${customer.street}">
                                                </p>
                                                <p>
                                                    <input id="deliveryHouseNumber" type="text" placeholder="Номер дома, квартиры (по-желанию)" value="${customer.houseNumber}">
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${customer == null}">
                                        <div class="acc-info">
                                            Сохранить данные для будущих заказов?
                                            <a data-toggle="collapse" href="#signup-form-wrap">
                                                Зарегистрироваться
                                            </a>
                                            <fieldset id="signup-form-wrap" class="collapse">
                                                <div class="inner-form">
                                                    <p>
                                                        <input id="regEmail" type="text" placeholder="Электронная почта">
                                                    </p>
                                                    <p>
                                                        <input id="regPassword" type="password" value="" placeholder="Пароль">
                                                    </p>
                                                    <p>
                                                        <input id="regConfirmPassword" type="password" value="" placeholder="Повторите пароль">
                                                    </p>
                                                    <p>
                                                        Вы автоматически будете зарегистрированы при подтверждении заказа.
                                                    </p>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </c:if>
                                    <h3 id="payment_details">Ваш заказ</h3>
                                    <div id="order_review" style="position: relative;">
                                        <table cellspacing="0" class="shop_table cart">
                                            <thead>
                                            <tr>
                                                <th class="product-name">Товар</th>
                                                <th class="product-price">Цена</th>
                                                <th class="product-quantity">Количество</th>
                                                <th class="product-subtotal">Итого</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <%-- Start cart item --%>
                                            <c:forEach items="${sessionScope['scopedTarget.cart'].items}" var="entry">
                                                <tr class="cart_item">

                                                    <td class="product-name">
                                                        <a href="single-product">${entry.key.name} ${entry.key.capacity.string} ${entry.key.color.string}</a>
                                                    </td>

                                                    <td class="product-price">
                                                        <span class="amount">${entry.key.priceVATFormat} грн.</span>
                                                    </td>

                                                    <td class="product-quantity">
                                                        <span class="amount">${entry.value}</span>

                                                    </td>

                                                    <td class="product-subtotal">
                                                        <span class="amount" id="item-sum${entry.key.id}">${sessionScope['scopedTarget.cart'].getItemSum(entry.key)} грн.</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                                <%-- End cart item --%>
                                            <tr>
                                                <td colspan="2" style="text-align: right">
                                                    Всего:
                                                </td>
                                                <td>
                                                    <b id="amount-total">${sessionScope['scopedTarget.cart'].amount}</b>
                                                </td>
                                                <td>
                                                    <b id="sum-total">${sessionScope['scopedTarget.cart'].sumFormat} грн.</b>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div id="payment">
                                            <h3 id="order_review_heading">Оплата</h3>
                                            <p>
                                                Список доступных способов оплаты:
                                            </p>
                                            <div class="radio">
                                                <label><input type="radio" name="payment" checked>Оплата наличными</label>
                                            </div>
                                            <div class="radio disabled">
                                                <label><input type="radio" name="payment" disabled>Оплата картой ПриватБанка</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row place-order">
                                        <c:choose>
                                            <c:when test="${customer == null}">
                                                <button type="submit" style="margin-top: 15px" onclick="submitOrder()">
                                                    Оформить заказ
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" onclick="submitOrderForCustomer()">
                                                    Оформить заказ
                                                </button>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <%@include file="embedded/elements/cartNoItems.jsp"%>
        </c:otherwise>
    </c:choose>


    <%@include file="embedded/footer.jsp"%>
    <%@include file="embedded/load-libraries.jsp"%>
  </body>
</html>