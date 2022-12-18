<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>

    <head>
        <title>Авторизація</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAutorized/FormAutorized.css"%> </style>
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${requestScope.Error}">
                    <script>
                        sweetAlert("Error", "Database problems, try again later!", "error");
                    </script>
                </c:when>
                <c:when test="${requestScope.UserBlock}">
                    <script>
                        sweetAlert("Error", "Your account is blocked!", "error");
                    </script>
                </c:when>
                <c:when test="${requestScope.UserRoleCheck eq false}">
                    <script>
                        sweetAlert("Authorization", "The login or password is incorrect!", "error");
                    </script>
                </c:when>
            </c:choose>
        </div>

        <form action="auto" class="autoForm" method="POST">
            <div class="container">
                <div class="languageDiv">
                    <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                    <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                </div>
                <h1 id="authorization" name="authorization">Авторизація</h1>
                <a href="/exhibition/guest" class="hyperlink" id="hyper">Увійти як гість</a>
                <p id="writeForm">Будь ласка заповніть форму.</p>
                <hr>
                <label for="login" id="login" class="labelChange">Логін</label>
                <input type="text" placeholder="Введіть логін" id="writeLogin" name="loginUser" required>
                <label for="psw" id="password" class="labelChange">Пароль</label>
                <input type="password" placeholder="Введіть пароль" name="passwordUser" id="passwordCheck" required>

                <input type="checkbox" onclick="checkPassword()"><spam id="checkPass">Переглянути пароль</spam>
                <a href="/exhibition/reg" class="hyperlink" id="hyperReg">Не зареєструвались?</a>
                <div class="clearfix">
                    <button type="submit" class="loginButton" name="loginButton" id="loginButton">Увійти</button>
                </div>
            </div>
        </form>
        <script src="./JS/functionsAutorized1.js"></script>
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