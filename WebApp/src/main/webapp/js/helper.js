function logOut() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://" + window.location.host + "/logout");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("uMenu").innerHTML = this.responseText;
        }
    }
}

var emailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var $inputEmail = $("#inputEmail");
var $inputPassword = $("#inputPassword");

function logIn() {
    if (emailRegEx.test($inputEmail.val()) &&
        $inputPassword.val().length > 5) {

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://" + window.location.host + "/login");
        xhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');

        var obj = {"email":$inputEmail.val(), "password":$inputPassword.val()};
        xhttp.send(JSON.stringify(obj));
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("uMenu").innerHTML = this.responseText;
            }
        };
    }
}

function addToCart(id) {
    var data = {"id":id, "amount":1};
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "http://" + window.location.host + "/addtocart",
        data: JSON.stringify(data),
        timeout: 100000,
        success: function(data) {
            document.getElementById("addToCart" + id).innerHTML = data;
            updateCartData();
        },
        error: function() {
            console.log("error during adding product to cart");
        }
    });
}

function updateCartData() {
    $.ajax({
        type: "POST",
        url: "http://" + window.location.host + "/updatecartdata",
        timeout: 100000,
        success: function(data) {
            var json = JSON.parse(data);
            document.getElementById("summary").innerHTML = json.summary + " грн.";
            document.getElementById("amount").innerHTML = json.amount;
        },
        error: function() {
            console.log("error during receiving summary");
        }
    });
}

$('li.dropdown a').on('click', function (event) {
    $(this).parent().toggleClass('open');
});

$('body').on('click', function (e) {
    if (!$('li.dropdown').is(e.target)
        && $('li.dropdown').has(e.target).length === 0
        && $('.open').has(e.target).length === 0
    ) {
        $('li.dropdown').removeClass('open');
    }
});

$(document).ready(function() {
    $(".number").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl/cmd+A
            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: Ctrl/cmd+C
            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: Ctrl/cmd+X
            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});

function checkout() {
    document.location.href = "checkout";
}

function submitOrder() {
    var $name = $("#deliveryName");
    var $phone = $("#deliveryPhone");
    var $city = $("#deliveryCity");
    var $street = $("#deliveryStreet");
    var $houseNumber = $("#deliveryHouseNumber");
    var $email = $("#regEmail");
    var $password = $("#regPassword");
    var $confirmPassword = $("#regConfirmPassword");

    if ($name.val() != "" && $phone.val() != "") {
        var data = {
            "name":$name.val(),
            "phone":$phone.val(),
            "city":$city.val(),
            "street":$street.val(),
            "houseNumber":$houseNumber.val(),
            "email":$email.val(),
            "password":$password.val(),
            "confirmPassword":$confirmPassword.val()
        };
        $.ajax({
            type: "POST",
            url: "http://" + window.location.host + "/submitorder",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            timeout: 100000,
            success: function(data) {
                document.getElementById("order-form").innerHTML = data;
                $('html,body').animate({
                        scrollTop: $("body").offset().top},
                    'slow');
                document.getElementById("summary").innerHTML = "0,00 грн.";
                document.getElementById("amount").innerHTML = "0";
            },
            error: function() {
                alert("error")
            }
        });
    }
}

function submitOrderForCustomer() {
    var $name = $("#deliveryName");
    var $phone = $("#deliveryPhone");
    var $city = $("#deliveryCity");
    var $street = $("#deliveryStreet");
    var $houseNumber = $("#deliveryHouseNumber");

    if ($name.val() != "" && $phone.val() != "") {
        var data = {
            "name":$name.val(),
            "phone":$phone.val(),
            "city":$city.val(),
            "street":$street.val(),
            "houseNumber":$houseNumber.val(),
        };
        $.ajax({
            type: "POST",
            url: "http://" + window.location.host + "/submitorderforcustomer",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            timeout: 100000,
            success: function(data) {
                document.getElementById("order-form").innerHTML = data;
                $('html,body').animate({
                        scrollTop: $("body").offset().top},
                    'slow');
                document.getElementById("summary").innerHTML = "0,00 грн.";
                document.getElementById("amount").innerHTML = "0";
            },
            error: function() {
                alert("error")
            }
        });
    }
}

$("button").click(function() {
    $('html,body').animate({
            scrollTop: $(".second").offset().top},
        'slow');
});

function setItemAmount(id, amount) {
    var data = {"id":id, "amount":amount};
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "http://" + window.location.host + "/setitemamount",
        data: JSON.stringify(data),
        timeout: 100000,
        success: function(data) {
            var json = JSON.parse(data);
            document.getElementById("summary").innerHTML = json.summary + " грн.";
            document.getElementById("amount").innerHTML = json.amount;

            document.getElementById("sum-total").innerHTML = json.summary + " грн.";
            document.getElementById("amount-total").innerHTML = json.amount;

            if (json.amount == "0") {
                document.getElementById("cart-items").innerHTML = "<div class='cart-no-items'>" +
                    "В корзине нет товаров." + "</div>";
            }
            if (amount != "0") {
                document.getElementById("item-sum" + id).innerHTML = json.itemSum + " грн.";
            }

        },
        error: function() {
            alert("error");
            console.log("error during adding product to cart");
        }
    });
}

function addOne(id) {
    var newAmount = parseInt(document.getElementById("amount" + id).value) + 1;
    document.getElementById("amount" + id).value = newAmount;

    setItemAmount(id, newAmount);
}

function subtractOne(id) {
    var newAmount = parseInt(document.getElementById("amount" + id).value) - 1;
    if (newAmount === 0) return;
    document.getElementById("amount" + id).value = newAmount;

    setItemAmount(id, newAmount);
}

function removeItem(id) {
    $("#cart-item" + id).remove();

    setItemAmount(id, 0);
}

function clearCart() {
    $.ajax({
        type: "POST",
        url: "http://" + window.location.host + "/clearcart",
        timeout: 100000,
        success: function(data) {
            document.getElementById("summary").innerHTML = "0,00 грн.";
            document.getElementById("amount").innerHTML = "0";
            document.getElementById("cart-items").innerHTML = data;
        },
        error: function() {
            alert("error");
            console.log("error during adding product to cart");
        }
    });
}