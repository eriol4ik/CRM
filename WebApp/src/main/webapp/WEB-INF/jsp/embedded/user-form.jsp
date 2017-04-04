<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a class="dropdown-toggle" href="#">Hello, <span style="text-decoration: underline">${customer.name}</span></a>
<div class="dropdown-menu" style="min-width: 200px" >
    <fieldset class="user-form" style="text-align: center">
        <div style="margin: 10px">
            Go to <a class="login-a" href="/backoffice">BackOffice</a>
        </div>
        <div>
            <button class="btn btn-danger" id="logOut" onclick="logOut()">Log out</button>
        </div>
    </fieldset>
</div>