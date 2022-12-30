<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tagfile" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>${languageChange.get(0)}</title>
    <meta charset="UTF-8"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style> <%@include file="/style/styleAdmin/seventhPage/FormUserAutorized.css"%> </style>
</head>
    <body>
    <c:if test="${requestScope.TrueAdd}">
        <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(5)}", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.TrueChange}">
        <script>
            sweetAlert("${languageChange.get(2)}", "${languageChange.get(6)}", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.TrueDel}">
        <script>
                sweetAlert("${languageChange.get(3)}", "${languageChange.get(7)}", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.AddAmount}">
        <script>
                sweetAlert("${languageChange.get(4)}", "${languageChange.get(8)}", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.AddAmountError}">
        <script>
                sweetAlert("${languageChange.get(4)}", "${languageChange.get(9)}", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.AddError}">
        <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(10)}", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
                sweetAlert("${languageChange.get(3)}", "${languageChange.get(11)}", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.ChangeError}">
        <script>
            sweetAlert("${languageChange.get(2)}", "${languageChange.get(12)}", "error");
        </script>
    </c:if>

    <form action="exhibition?command=userautorized" id="UserAutorizedForm" class="UserAutorizedForm" method="POST">
        <div class="languageDiv">
            <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
            <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
        </div>
        <div class="nameExit">
            <h3 class="h3Add" id="authorizedHello">${languageChange.get(13)}</p></h3>
            <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(14)}</button>
        </div>
        <div class="buttonAuto">
            <button class="updateButton" id="updateButton" name="updateButton">${languageChange.get(15)}</button>
            <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">${languageChange.get(16)}</button>
            <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button">${languageChange.get(17)}</button>
            <button class="accessButton" name="accessButton" id="accessButton" onclick="accessFunc()" type="Button">${languageChange.get(18)}</button>
            <button class="saveButton" id="roleBackButton" name="roleBackButton">${languageChange.get(19)}</button>
            <button class="saveButton" id="saveButton" name="saveButton">${languageChange.get(20)}</button>
        </div>

        <div id="addDiv" style="display:none;">
            <div class="wrapper">
                <h6 class="h6Add" id="addH6">${languageChange.get(21)}</h6>
                <div class="buttonAdd">
                    <button type="Button" id="addButtonUser" class="addButtonUser" onclick="addUser()" name="addButtonUser">${languageChange.get(22)}</button>
                    <button type="Button" id="addButtonMoney" class="addButtonMoney" onclick="addMoney()" name="addButtonMoney">${languageChange.get(23)}</button>
                </div>
            </div>
        </div>
        <div id="addDivUser" style="display: none;">
            <div class="wrapper">
                <div class="SelectDiv">
                    <h6 id="roleChange" class="h6Add">${languageChange.get(24)}</h6>
                    <select class="SelectRole" name="role" id="SelectRole">
                        <option value="1">${languageChange.get(25)}</option>
                        <option value="2">${languageChange.get(26)}</option>
                    </select>
                </div>
                <div class="left_block">
                    <input type="text" id="InputFirstNameAuto" placeholder="${languageChange.get(27)}" name="firstName" >
                    <input type="text" id="InputLastNameAuto" placeholder="${languageChange.get(28)}" name="lastName" >
                    <input type="text" id="InputLoginAuto" placeholder="${languageChange.get(29)}" name="login" >
                </div>
                <div class="right_block">
                    <input type="text" id="InputPasswordAuto" placeholder="${languageChange.get(30)}" name="password" >
                    <input type="email" id="InputEmailAuto" placeholder="${languageChange.get(31)}" name="email" >
                    <input type="number" id="InputAmountAuto" placeholder="${languageChange.get(32)}" step="any" name="amount" >
                </div>
                <button class="addButtonServlet" id="addButtonAuto" name="addButtonAuto">${languageChange.get(33)}</button>
            </div>
        </div>
        <div id="addDivMoney" style="display:none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="email" id="InputEmailUserAuto" placeholder="${languageChange.get(34)}" name="emailMoney" >
                </div>
                <div class="right_block">
                    <input type="number" id="InputAmountUserAuto" placeholder="${languageChange.get(35)}" step="any" name="money" >
                </div>
                <button class="addButtonServlet" id="ButtonMoney" name="ButtonMoney">${languageChange.get(36)}</button>
            </div>
        </div>
        <div class="delDiv" id="delDiv" style="display: none;">
            <div class="InputDelDiv">
                <input class="inputDel" type="email" id="InputDelAuto" placeholder="${languageChange.get(37)}" name="autoDel">
            </div>
            <button class="delButtonServlet" id="delButtonAuto" name="delButtonAuto">${languageChange.get(38)}</button>
        </div>
        <div class="accessDiv" id="accessDiv" style="display: none;">
            <div class="checkAccess">
                <h6 class="h6Acc" id="userChange">${languageChange.get(39)}</h6>
                <select class="SelectAccess" name="access" id="SelectAccess">
                    <option value="1">${languageChange.get(40)}</option>
                    <option value="2">${languageChange.get(41)}</option>
                </select>
            </div>
            <div class="InputAccessDiv">
                <input class="inputAccess" type="email" id="InputAccessEmail" placeholder="${languageChange.get(42)}" name="emailExhibitionAccess">
            </div>
            <button class="accessButtonServlet" id="accessButtonServlet" name="accessButtonServlet">${languageChange.get(43)}</button>
        </div>
        <table id="myTable">
            <tr>
                <th id="firstName">${languageChange.get(44)}</th>
                <th id="lastName">${languageChange.get(45)}</th>
                <th id="login">${languageChange.get(46)}</th>
                <th id="password">${languageChange.get(47)}</th>
                <th id="email">${languageChange.get(48)}</th>
                <th id="amount">${languageChange.get(49)}</th>
                <th id="role">${languageChange.get(50)}</th>
                <th id="access">${languageChange.get(51)}</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="autorized" items="${SeventhPageShow}">
                    <tr>
                        <td>${autorized.getFirstName()}</td>
                        <td>${autorized.getLastName()}</td>
                        <td>${autorized.getLogin()}</td>
                        <td>${autorized.getPassword()}</td>
                        <td>${autorized.getEmail()}</td>
                        <td>${autorized.getAmount()}</td>
                        <tagfile:changeLanguageRole language="${languageChange.get(52)}" role="${autorized.getRole()}"></tagfile:changeLanguageRole>
                        <tagfile:tableColor name="${autorized.getAccess()}"></tagfile:tableColor>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.Error}">
                <script>
                        sweetAlert("${languageChange.get(53)}", "${languageChange.get(54)}", "error");
                </script>
            </c:if>
        </table>
        <div class="pagination">
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="«"></a>
            <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
            <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="»"></a>
        </div>
    </form>
    <script src="./JS/adminJS/seventhPage/AdminSeventhMenu.js" type="text/javascript"></script>
    </body>
</html>