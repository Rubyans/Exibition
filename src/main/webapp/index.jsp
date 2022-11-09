<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<head>
    <title></title>
    <meta charset="UTF-8"/>
    <link href="AutorizedForm.css" rel="stylesheet"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
</head>

<div>
    <%
    if(request.getAttribute("UserRoleCheck")=="false") {
    %>
        <script>
            sweetAlert("Авторизація", "Пароль або логін невірний. Спробуйте знову!", "error");
        </script>
    <%}
    %>
</div>
<form action="auto" class="autoForm" method="POST">
    <div class="container">
        <h1>Авторизація</h1>
        <p>Будь ласка заповніть форму.</p>
        <hr>
        <label for="login"><b>Логін</b></label>
        <input type="text" placeholder="Введіть логін" name="loginUser" required>
        <label for="psw"><b>Пароль</b></label>
        <input type="password" placeholder="Введіть пароль" name="passwordUser" id="passwordcheck" required>
        <input type="checkbox" onclick="checkPassword()">Переглянути пароль

        <a href="/exibition/reg" class="forgot">Не зареєструвались?</a>

        <div class="clearfix">
            <button onclick="location.href=/exibition/auto" type="submit" class="loginbutton">Увійти</button>
        </div>
    </div>
</form>

<script>
function checkPassword() {
    var x = document.getElementById("passwordcheck");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}


</script>
</body>
</html>