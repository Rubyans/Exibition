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
            <button class="updateButton" id="updateButton" name="updateButton" onclick="updateFunc()" type="Button">${languageChange.get(15)}</button>
            <button class="addButton" id="addButton" name="addButton" onclick="addMoney()" type="Button">${languageChange.get(16)}</button>
            <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button">${languageChange.get(17)}</button>
            <button class="accessButton" name="accessButton" id="accessButton" onclick="accessFunc()" type="Button">${languageChange.get(18)}</button>
            <button class="saveButton" id="roleBackButton" name="roleBackButton">${languageChange.get(19)}</button>
            <button class="saveButton" id="saveButton" name="saveButton">${languageChange.get(20)}</button>
        </div>

        <div class="updateDiv" id="updateDiv" style="display: none;">
            <div class="wrapper">
                <h6 class="h6Upd" id="updateTxt">${languageChange.get(21)}</h6>
                <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                    <option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="all">${languageChange.get(22)}</option>
                </select>
                <div class="buttonAdd">
                    <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(23)}</button>
                </div>
            </div>
        </div>

        <div id="addDivMoney" style="display:none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="email" id="InputEmailUserAuto" placeholder="${languageChange.get(37)}" name="emailMoney" >
                </div>
                <div class="right_block">
                    <input type="number" id="InputAmountUserAuto" placeholder="${languageChange.get(38)}" step="any" name="money" >
                </div>
                <button class="addButtonServlet" id="ButtonMoney" name="ButtonMoney">${languageChange.get(39)}</button>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
            <div class="InputDelDiv">
                <input class="inputDel" type="email" id="InputDelAuto" placeholder="${languageChange.get(40)}" name="autoDel">
            </div>
            <button class="delButtonServlet" id="delButtonAuto" name="delButtonAuto">${languageChange.get(41)}</button>
        </div>
        <div class="accessDiv" id="accessDiv" style="display: none;">
            <div class="checkAccess">
                <h6 class="h6Acc" id="userChange">${languageChange.get(42)}</h6>
                <select class="SelectAccess" name="access" id="SelectAccess">
                    <option value="1">${languageChange.get(43)}</option>
                    <option value="2">${languageChange.get(44)}</option>
                </select>
            </div>
            <div class="InputAccessDiv">
                <input class="inputAccess" type="email" id="InputAccessEmail" placeholder="${languageChange.get(45)}" name="emailExhibitionAccess">
            </div>
            <button class="accessButtonServlet" id="accessButtonServlet" name="accessButtonServlet">${languageChange.get(46)}</button>
        </div>
        <table id="myTable">
            <tr>
                <th id="firstName">${languageChange.get(47)}</th>
                <th id="lastName">${languageChange.get(48)}</th>
                <th id="email">${languageChange.get(51)}</th>
                <th id="amount">${languageChange.get(52)}</th>
                <th id="role">${languageChange.get(53)}</th>
                <th id="access">${languageChange.get(54)}</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="autorized" items="${SeventhPageShow}">
                    <tr>
                        <td>${autorized.getFirstName()}</td>
                        <td>${autorized.getLastName()}</td>
                        <td>${autorized.getEmail()}</td>
                        <td>${autorized.getAmount()}</td>
                        <tagfile:changeLanguageRole language="${languageChange.get(55)}" role="${autorized.getRole()}"></tagfile:changeLanguageRole>
                        <tagfile:tableColor name="${autorized.getAccess()}"></tagfile:tableColor>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.Error}">
                <script>
                        sweetAlert("${languageChange.get(56)}", "${languageChange.get(57)}", "error");
                </script>
            </c:if>
        </table>
        <div class="pagination">
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="??"></a>
            <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
            <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsPagination" value="8"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsPagination" value="??"></a>
        </div>
    </form>
    <script src="./JS/adminJS/seventhPage/AdminSeventhMenu.js" type="text/javascript"></script>
    </body>
</html>