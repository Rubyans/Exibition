<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Реєстрація</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleRegistration/FormRegistration.css"%> </style>
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${requestScope.UserAddError}">
                  <script>
                    sweetAlert("Registration", "This login or mail already exists!", "error");
                  </script>
                </c:when>
                <c:when test="${requestScope.UserAddTrue}">
                    <script>
                        sweetAlert("Registration", "You have successfully registered!", "success");
                        setTimeout(() => document.location.href = "/exhibition/", 8000);
                    </script>
                </c:when>
                <c:when test="${not requestScope.UserAdd}">
                    <script>
                        swal("Registration", "Fill out the form");
                    </script>
                </c:when>
            </c:choose>
        </div>
            <form action="reg"  id="regForm" method="POST">
                <div class="container" >
                    <div class="languageDiv">
                        <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                        <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                    </div>
                    <h1 class="hcheck" id="hReg">Реєстрація</h1>
                    <p class="pCheck" id="pReg" >Будь ласка заповніть форму для створення акаунту.</p>
                    <hr>

                    <label for="firstname" class="labelChange" id="firstName">Прізвище</label>
                    <input type="text" placeholder="Введіть прізвище" name="firstName" id="firstCheck" required>

                    <label for="lastname" class="labelChange" id="lastName">Ім`я</label>
                    <input type="text" placeholder="Введіть ім`я" name="lastName" id="lastCheck" required>

                    <label for="email" class="labelChange" id="email">Поштова скринька</label>
                    <input type="email" placeholder="Введіть поштову скриньку" name="email" id="emailCheck" required>

                    <label for="login" class="labelChange" id="login">Логін</label>
                    <input type="text" placeholder="Введіть логін" name="login" id="loginCheck" required>

                    <label for="psw" class="labelChange" id="password">Пароль</label>
                    <input type="password" placeholder="Введіть пароль" name="password" id="passwordCheck" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>

                    <input type="checkbox" onclick="checkPassword()"><spam id="checkPass">Переглянути пароль</spam>
                    <div class="clearfix">
                        <button class="backmainbtn" onclick="requiredFalse()" name="autorizedButton" id="autorizedButton">До авторизації</button>
                        <button class="signupbtn" name="registrationButton" id="registrationButton">Зареєструватися</button>
                    </div>
                    <div id="message">
                            <h3 class="h3Tx" id="passwordMessage">Пароль повинен складатися з:</h3>
                            <p id="letter" class="invalid">Літера нижнього регістру</p>
                            <p id="capital" class="invalid">Літера верхнього регістру</p>
                            <p id="number" class="invalid">Цифра</p>
                            <p id="length" class="invalid">Мінімум 8 символів</p>
                        </div>
                </div>
            </form>
            <script src="./JS/functionsRegistration.js"></script>
            <c:if test="${requestScope.languageEnglish}">
                <script>
                    changeLanguage('en');
                </script>
            </c:if>
            <c:if test="${requestScope.languageUkraine}">
                 <script>
                    changeLanguage('ua');
                 </script>
            </c:if>
    </body>
</html>