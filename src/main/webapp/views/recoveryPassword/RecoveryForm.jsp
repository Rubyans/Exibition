<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleRecovery/FormRecovery.css"%> </style>
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${requestScope.Error}">
                  <script>
                    sweetAlert("Помилка", "Ви ввели невірно електронну адресу", "error");
                  </script>
                </c:when>
                <c:when test="${requestScope.RecoveryChange}">
                    <script>
                        document.location.href = "exhibition?command=recoverypassword";
                    </script>
                </c:when>
            </c:choose>
        </div>
        <form action="exhibition?command=recovery" class="recoveryForm" method="POST">
            <div class="container">
                <div class="languageDiv">
                    <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                    <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                </div>
                <h1 id="recoveryh" name="recoveryh">${languageChange.get(1)}</h1>
                <p id="writeForm">${languageChange.get(2)}</p>
                <hr>
                <label for="recoveryLabel" id="recoveryLabel" class="labelChange">${languageChange.get(3)}</label>
                <input type="email" placeholder="${languageChange.get(4)}" id="writeRecovery" name="recoveryEmail" required>
                <div class="clearfix">
                    <button type="submit" class="recoveryButton" name="recoveryButton" id="recoveryButton">${languageChange.get(5)}</button>
                    <button type="submit" onclick="requiredFalse()" class="autoButton" name="autoButton" id="autoButton">${languageChange.get(6)}</button>
                </div>
            </div>
        </form>
        <script src="./JS/fubctionsRecovery.js"></script>
    </body>
</html>