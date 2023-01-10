<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="v" uri="virguleTag" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleUser/firstPage/FormUser.css"%> </style>
    </head>
<body>
    <c:if test="${requestScope.TrueAdd}">
        <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(3)}", "success");
        </script>
    </c:if>
    <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(4)}", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(5)}", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitTrue}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(6)}", "success");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(5)}", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitTrue}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(7)}", "success");
            </script>
    </c:if>
    <form action="exhibition?command=user"  id="userForm" class="userForm" method="POST">
        <div class="languageDiv">
            <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
            <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
        </div>
        <div class="nameExit">
             <h3 class="h3Add" id="ticketHello">${languageChange.get(8)}</p></h3>
            <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(9)}</button>
        </div>
        <div class="moneyUser">
            <h4 id="amount" class="h4Text">${languageChange.get(10)}</h4>
            <h4 class="h4Money">
                <v:virguleTag
                    amount="${Money.get(0).getAmount()} ₴"
                />
            </h4>
        </div>
        <div class="buttonUser">
            <button class="updateButton" name="updateButton" id="updateButton" onclick="updateFunc()" type="Button">${languageChange.get(11)}</button>
            <button class="addButton" type="Button" name="addButton" onclick="addFunc()" id="addButton">${languageChange.get(12)}</button>
            <button class="saveButton" name="saveButton" id="saveButton">${languageChange.get(13)}</button>
            <button class="roleBackButton" name="roleBackButton" id="roleBackButton">${languageChange.get(14)}</button>
        </div>

        <div id="updateDiv" style="display: none;">
            <div class="wrapper">
                <h6 class="h6Add" id="updateTxt">${languageChange.get(15)}</h6>
                <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                    <option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="all">${languageChange.get(16)}</option>
                </select>
                <div class="buttonAdd">
                    <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(17)}</button>
                </div>
            </div>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                 <h6 class="h6Add" id="nameEx">${languageChange.get(18)}</h6>
                 <select class="SelectExhibition" name="nameExhibition" id="SelectExhibition" multiple>
                    <c:forEach var="name" items="${AddShow}">
                        <option value="${name.getNameExhibition()}">${name.getNameExhibition()}</option>
                    </c:forEach>
                 </select>
                <div class="buttonAdd">
                    <button class="addButtonServlet" id="addButtonTicket" name="addButtonTicket">${languageChange.get(19)}</button>
                </div>
            </div>
        </div>
        <table id="myTable">
            <tr>
                <th id="nameExhibition">${languageChange.get(20)}</th>
                <th id="ticketPrice">${languageChange.get(21)}</th>
                <th id="dateStart">${languageChange.get(22)}</th>
                <th id="dateEnd">${languageChange.get(23)}</th>
                <th id="Hour">${languageChange.get(24)}</th>
                <th id="boughtTicket">${languageChange.get(25)}</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="user" items="${FirstPageUser}">
                    <tr>
                        <td>${user.getNameExhibition()}</td>
                        <td>${user.getPrice()}</td>
                        <td>${user.getDateStart()}</td>
                        <td>${user.getDateEnd()}</td>
                        <td>${user.getHours()}</td>
                        <td>${user.getTicket()}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.Error}">
                <script>
                    sweetAlert("${languageChange.get(2)}", "${languageChange.get(5)}", "error");
                </script>
            </c:if>
        </table>
        <div class="pagination">
            <a><input class="buttonPagination" type="submit" name="UserPagination" value="«"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="UserPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="»"></a>
        </div>
    </form>
    <script src="./JS/userJS/firstPage/UserAutorized1.js" type="text/javascript"></script>
</body>
</html>

