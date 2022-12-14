<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <c:if test="${not empty requestScope.languageChange}">
        <head>
            <title>${languageChange.get(0)}</title>
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
                            sweetAlert("${languageChange.get(1)}", "${languageChange.get(2)}", "error");
                        </script>
                    </c:when>
                    <c:when test="${requestScope.UserBlock}">
                        <script>
                            sweetAlert("${languageChange.get(1)}", "${languageChange.get(3)}", "error");
                        </script>
                    </c:when>
                    <c:when test="${requestScope.UserRoleCheck eq false}">
                        <script>
                            sweetAlert("${languageChange.get(0)}", "${languageChange.get(4)}", "error");
                        </script>
                    </c:when>
                </c:choose>
            </div>
            <form action="exhibition?command=auto" class="autoForm" method="POST">
                <div class="container">
                    <div class="languageDiv">
                        <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                        <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                    </div>
                        <h1 id="authorization" name="authorization">${languageChange.get(0)}</h1>
                    <a href="exhibition?command=guest" class="hyperlink" id="hyper">${languageChange.get(5)}</a>
                    <p id="writeForm">${languageChange.get(6)}</p>
                    <hr>
                    <label for="login" id="login" class="labelChange">${languageChange.get(7)}</label>
                    <input type="text" placeholder="${languageChange.get(8)}" id="writeLogin" name="loginUser" required>
                    <label for="psw" id="password" class="labelChange">${languageChange.get(9)}</label>
                    <input type="password" placeholder="${languageChange.get(10)}" name="passwordUser" id="passwordCheck" required>
                    <div class="hypercheckDiv">
                        <div>
                            <input type="checkbox" onclick="checkPassword()"><spam id="checkPass">${languageChange.get(11)}</spam>
                        </div>
                        <div class="hyperDiv">
                            <a href="exhibition?command=reg" class="hyperlink" id="hyperReg">${languageChange.get(12)}</a>
                            <br><a href="exhibition?command=recovery" class="hyperlink hyperforget" id="hyperReg">${languageChange.get(13)}</a>
                        </div>
                    </div>
                    <div class="clearfix">
                        <button type="submit" class="loginButton" name="loginButton" id="loginButton">${languageChange.get(14)}</button>
                    </div>
                </div>
            </form>
            <script src="./JS/functionsAutorized.js"></script>
        </body>
    </c:if>
    <c:if test="${empty requestScope.languageChange}">
        <head>
            <title>??????????????????????</title>
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
                            sweetAlert("??????????????", "???????????????? ?? ????, ?????????????????? ??????????????!", "error");
                        </script>
                    </c:when>
                    <c:when test="${requestScope.UserBlock}">
                        <script>
                            sweetAlert("??????????????", "?????? ???????????? ????????????????????????!", "error");
                        </script>
                    </c:when>
                    <c:when test="${requestScope.UserRoleCheck eq false}">
                        <script>
                            sweetAlert("??????????????????????", "?????????? ?????? ???????????? ?????????????? ??????????????!", "error");
                        </script>
                    </c:when>
                </c:choose>
            </div>
            <form action="exhibition?command=auto" class="autoForm" method="POST">
                <div class="container">
                    <div class="languageDiv">
                        <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                        <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                    </div>
                        <h1 id="authorization" name="authorization">??????????????????????</h1>
                    <a href="exhibition?command=guest" class="hyperlink" id="hyper">???????????? ???? ??????????</a>
                    <p id="writeForm">???????? ?????????? ?????????????????? ??????????.</p>
                    <hr>
                    <label for="login" id="login" class="labelChange">??????????</label>
                    <input type="text" placeholder="?????????????? ??????????" id="writeLogin" name="loginUser" required>
                    <label for="psw" id="password" class="labelChange">????????????</label>
                    <input type="password" placeholder="?????????????? ????????????" name="passwordUser" id="passwordCheck" required>
                    <div class="hypercheckDiv">
                        <div>
                            <input type="checkbox" onclick="checkPassword()"><spam id="checkPass">?????????????????????? ????????????</spam>
                        </div>
                        <div class="hyperDiv">
                            <a href="exhibition?command=reg" class="hyperlink" id="hyperReg">???? ???????????????????????????????</a>
                            <br><a href="exhibition?command=recovery" class="hyperlink hyperforget" id="hyperReg">???????????? ?????????????</a>
                        </div>
                    </div>
                    <div class="clearfix">
                        <button type="submit" class="loginButton" name="loginButton" id="loginButton">????????????</button>
                    </div>
                </div>
            </form>
            <script src="./JS/functionsAutorized.js"></script>
        </body>
    </c:if>
</html>