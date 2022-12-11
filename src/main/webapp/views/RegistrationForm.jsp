<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8"/>
    <link href="./style/styleRegistration/FormRegistration12.css" rel="stylesheet"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
</head>
<body>
<div>
    <c:choose>
        <c:when test="${requestScope.UserAddError}">
          <script>
            sweetAlert("Реєстрування", "Даний логін або пошта вже існує", "error");
          </script>
        </c:when>
        <c:when test="${requestScope.UserAddTrue}">
            <script>
                sweetAlert("Реєстрування", "Ви успішно зареєструвались", "success");
                setTimeout(() => document.location.href = "/exhibition/", 8000);
            </script>
        </c:when>
        <c:when test="${not requestScope.UserAdd}">
            <script>
                swal("Реєстрація", "Заповніть форму");
            </script>
        </c:when>
    </c:choose>
</div>
<form action="reg"  id="regForm" method="POST">

    <div class="container" >
        <h1 class="hcheck">Реєстрація</h1>
        <p class="pcheck">Будь ласка заповніть форму для створення акаунту.</p>
        <hr>

        <label for="firstname"><b>Прізвище</b></label>
        <input type="text" placeholder="Введіть прізвище" name="firstName" required>

        <label for="lastname"><b>Ім`я</b></label>
        <input type="text" placeholder="Введіть ім`я" name="lastName" required>

        <label for="email"><b>Поштова скринька</b></label>
        <input type="email" placeholder="Введіть поштову скриньку" name="email" required>

        <label for="login"><b>Логін</b></label>
        <input type="text" placeholder="Введіть логін" name="login" required>

        <label for="psw"><b>Пароль</b></label>
        <input type="password" id="psw" placeholder="Введіть пароль" name="password" id="passwordcheck" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>

        <input type="checkbox" onclick="checkPassword()">Переглянути пароль
        <div class="clearfix">
            <button class="backmainbtn" name="autorizedButton">До авторизації</button>
            <button class="signupbtn" name="registrationButton">Зареєструватися</button>
        </div>
        <div id="message">
                <h3 class="h3Tx">Пароль повинен складатися з:</h3>
                <p id="letter" class="invalid">Літера <b>нижнього</b> регістру</p>
                <p id="capital" class="invalid">Літера <b>верхнього</b> регістру</p>
                <p id="number" class="invalid"><b>Цифра</b></p>
                <p id="length" class="invalid">Мінімум <b>8 символів</b></p>
            </div>
    </div>


</form>
<script src="./JS/Autorizedvalidate.js"></script>
<script>
function checkPassword() {
    var x = document.getElementById("psw");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

</script>
</body>
</html>