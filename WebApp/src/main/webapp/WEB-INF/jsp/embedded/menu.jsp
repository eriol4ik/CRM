<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="user-menu">
                <ul>
                    <li id="uMenu" class="dropdown">
                        <c:if test="${customer != null}">
                            <%@include file="user-form.jsp"%>
                        </c:if>
                        <c:if test="${customer == null}">
                            <%@include file="login-form.jsp"%>
                        </c:if>
                    </li>
                    <li><a href="cart"><i class="fa fa-shopping-cart"></i>Корзина</a></li>
                    <li style="float: right; position: relative">
                        <span class="contacts"><b>Наши контакты</b>: (099) 66-77-888, (066) 22-33-444</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="/">i<span>Shop</span></a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart">Корзина - <span id="summary" class="cart-amount">${sessionScope['scopedTarget.cart'].sumFormat} грн.</span> <i class="fa fa-shopping-cart"></i> <span class="product-count" id="amount">${sessionScope['scopedTarget.cart'].amount}</span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Главная</a></li>
                    <li><a href="shop">iPhone</a></li>
                    <li><a href="#">Аксессуары</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->