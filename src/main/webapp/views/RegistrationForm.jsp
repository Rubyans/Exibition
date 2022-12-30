<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8">
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link href="./style/styleRegistration/FormRegistration.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${requestScope.UserAddError}">
                  <script>
                    sweetAlert("${languageChange.get(0)}", "${languageChange.get(1)}", "error");
                  </script>
                </c:when>
                <c:when test="${requestScope.UserAddTrue}">
                    <script>
                        sweetAlert("${languageChange.get(0)}", "${languageChange.get(2)}", "success");
                        setTimeout(() => document.location.href = "exhibition?command=auto", 8000);
                    </script>
                </c:when>
                <c:when test="${not requestScope.UserAdd}">
                    <script>
                        swal("${languageChange.get(0)}", "${languageChange.get(3)}");
                    </script>
                </c:when>
            </c:choose>
        </div>
            <form action="exhibition?command=reg"  id="regForm" method="POST">
                <div class="container" >
                    <div class="languageDiv">
                        <button class="languageButton" onclick="requiredFalse()" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                        <button class="languageButton" onclick="requiredFalse()" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
                    </div>
                    <h1 class="hcheck" id="hReg">${languageChange.get(0)}</h1>
                    <p class="pCheck" id="pReg" >${languageChange.get(4)}</p>
                    <hr>

                    <label for="firstname" class="labelChange" id="firstName">${languageChange.get(5)}</label>
                    <input type="text" placeholder="${languageChange.get(6)}" name="firstName" id="firstCheck" required>

                    <label for="lastname" class="labelChange" id="lastName">${languageChange.get(7)}</label>
                    <input type="text" placeholder="${languageChange.get(8)}" name="lastName" id="lastCheck" required>

                    <label for="email" class="labelChange" id="email">${languageChange.get(9)}</label>
                    <input type="email" placeholder="${languageChange.get(10)}" name="email" id="emailCheck" required>

                    <label for="login" class="labelChange" id="login">${languageChange.get(11)}</label>
                    <input type="text" placeholder="${languageChange.get(12)}" name="login" id="loginCheck" required>

                    <label for="psw" class="labelChange" id="password">${languageChange.get(13)}</label>
                    <input type="password" placeholder="${languageChange.get(14)}" name="password" id="passwordCheck" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>

                    <input type="checkbox" onclick="checkPassword()"><spam id="checkPass">${languageChange.get(15)}</spam>
                    <div class="clearfix">
                        <button class="backmainbtn" onclick="requiredFalse()" name="autorizedButton" id="autorizedButton">${languageChange.get(16)}</button>
                        <button class="signupbtn" name="registrationButton" id="registrationButton">${languageChange.get(17)}</button>
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
        <script src="./JS/functionsRegistration.js"></script>
    </body>
</html>