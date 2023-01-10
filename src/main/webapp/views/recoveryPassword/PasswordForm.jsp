<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link href="./style/stylePassword/FormPassword.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${requestScope.Error}">
                  <script>
                    sweetAlert("${languageChange.get(1)}", "${languageChange.get(3)}", "error");
                  </script>
                </c:when>
                <c:when test="${requestScope.RecoveryChange}">
                    <script>
                        sweetAlert("${languageChange.get(2)}", "${languageChange.get(4)}", "success");
                        setTimeout(() => document.location.href = "exhibition?command=auto", 8000);
                    </script>
                </c:when>
                <c:when test="${not requestScope.RecoveryChange}">
                    <c:if test="${not requestScope.Error}">
                        <script>
                            swal("${languageChange.get(2)}", "${languageChange.get(5)}");
                         </script>
                    </c:if>
                </c:when>
            </c:choose>
        </div>
        <form action="exhibition?command=recoverypassword" class="recoveryForm" method="POST">
            <div class="container">
                <div class="languageDiv">
                    <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                    <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                </div>
                <h1 id="recoveryh" name="recoveryh">${languageChange.get(6)}</h1>
                <p id="writeForm">${languageChange.get(7)}</p>
                <hr>
                <label for="recoveryLabel" id="recoveryLabel" class="labelChange">${languageChange.get(8)}</label>
                <input type="text" placeholder="${languageChange.get(9)}" id="writeCode" name="recoveryCode" required>

                <label for="recoveryLabel" id="recoveryLabel" class="labelChange">${languageChange.get(10)}</label>
                <input type="password" placeholder="${languageChange.get(11)}" id="writePassword" name="recoveryPassword" required>

                <label for="recoveryLabel" id="recoveryLabel" class="labelChange">${languageChange.get(12)}</label>
                <input class="inputDiv" type="password" placeholder="${languageChange.get(13)}" id="writeConfirmPassword" name="confirmPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>

                <div id="messagePassword">
                     <p id="confirmPassword" class="invalidPassword">${languageChange.get(14)}</p>
                </div>

                <input class="checkPassword" type="checkbox" onclick="checkPassword()"><spam id="checkPass">${languageChange.get(15)}</spam>

                <div class="clearfix">
                    <button type="submit" onclick="requiredSubmit()" class="confirmButton" name="confirmButton" id="confirmButton">${languageChange.get(16)}</button>
                    <button type="submit" onclick="requiredFalse()" class="autoButton" name="autoButton" id="autoButton">${languageChange.get(17)}</button>
                </div>
                <div id="message">
                    <h3 class="h3Tx" id="passwordMessage">${languageChange.get(18)}</h3>
                    <p id="letter" class="invalid">${languageChange.get(19)}</p>
                    <p id="capital" class="invalid">${languageChange.get(20)}</p>
                    <p id="number" class="invalid">${languageChange.get(21)}</p>
                    <p id="length" class="invalid">${languageChange.get(22)}</p>
                </div>
            </div>
        </form>
        <script src="./JS/functionsPassword.js"></script>
    </body>
</html>