<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Корзина | iShop</title>
      <%@include file="embedded/load-footer-libraries.jsp"%>
  </head>
  <body>
    <%@include file="embedded/menu.jsp"%>
    <div class="product-big-title-area">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-big-title text-center">
                        <h2>Корзина</h2>
                    </div>
                </div>
            </div>
    </div> <!-- End Page title area -->

    <div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <c:choose>
        <c:when test="${sessionScope['scopedTarget.cart'].amount > 0}">
            <div class="container" id="cart-items">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-content-right">
                            <fieldset>
                                <table cellspacing="0" class="shop_table cart">
                                    <thead>
                                        <tr>
                                            <th class="product-remove">&nbsp;</th>
                                            <th class="product-thumbnail">&nbsp;</th>
                                            <th class="product-name">Товар</th>
                                            <th class="product-price">Цена</th>
                                            <th class="product-quantity">Количество</th>
                                            <th class="product-subtotal">Итого</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%-- Start cart item --%>
                                        <c:forEach items="${sessionScope['scopedTarget.cart'].items}" var="entry">
                                            <tr class="cart_item" id="cart-item${entry.key.id}">
                                                <td class="product-remove">
                                                    <button title="Remove this item" class="remove" onclick="removeItem(${entry.key.id})">×</button>
                                                </td>

                                                <td class="product-thumbnail">
                                                    <a href="single-product"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="../..${entry.key.pictureList[0].imageLink}"></a>
                                                </td>

                                                <td class="product-name">
                                                    <a href="single-product">${entry.key.name} ${entry.key.capacity.string} ${entry.key.color.string}</a>
                                                </td>

                                                <td class="product-price">
                                                    <span class="amount">${entry.key.priceVATFormat} грн.</span>
                                                </td>

                                                <td class="product-quantity">
                                                    <div class="quantity buttons_added">
                                                        <input id="minus" type="button" class="minus" value="-" onclick="subtractOne(${entry.key.id})">
                                                        <input id="amount${entry.key.id}" type="text" class="number" size="2" title="amount" value="${entry.value}" onchange="setItemAmount(${entry.key.id}, this.value)" style="text-align: center">
                                                        <input id="plus" type="button" class="plus" value="+" onclick="addOne(${entry.key.id})">
                                                    </div>
                                                </td>

                                                <td class="product-subtotal">
                                                    <span class="amount" id="item-sum${entry.key.id}">${sessionScope['scopedTarget.cart'].getItemSum(entry.key)} грн.</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <%-- End cart item --%>
                                        <tr>
                                            <td colspan="4" style="text-align: right">
                                                Всего:
                                            </td>
                                            <td>
                                                <b id="amount-total">${sessionScope['scopedTarget.cart'].amount}</b>
                                            </td>
                                            <td>
                                                <b id="sum-total">${sessionScope['scopedTarget.cart'].sumFormat} грн.</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6">
                                                <button type="submit" onclick="checkout()">Оформить</button>
                                                <button type="button" class="simple_button" onclick="clearCart()">Очистить корзину</button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <%@include file="embedded/elements/cartNoItems.jsp"%>
        </c:otherwise>
    </c:choose>
    </div>

    <%@include file="embedded/footer.jsp"%>
    <%@include file="embedded/load-libraries.jsp"%>
  </body>
</html>