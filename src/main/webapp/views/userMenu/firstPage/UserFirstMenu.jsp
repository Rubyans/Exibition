<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="v" uri="AmountTag" %>

<html>
    <head>
        <title>Квиток</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleUser/firstPage/FormUser.css"%> </style>
    </head>
<body>
    <c:if test="${requestScope.TrueAdd}">
        <script>
                sweetAlert("Purchase", "You have successfully purchased a ticket, confirm the ticket purchase!", "success");
        </script>
    </c:if>
    <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("Error", "Insufficient money!", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitError}">
            <script>
                sweetAlert("Error", "Database problems!", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitTrue}">
            <script>
                sweetAlert("Purchase", "You have successfully confirmed your ticket purchase!", "success");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitError}">
            <script>
                sweetAlert("Error", "Database problems!", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitTrue}">
            <script>
                sweetAlert("Purchase", "You have successfully declined your ticket purchase", "success");
            </script>
    </c:if>
    <form action="user"  id="userForm" class="userForm" method="POST">
        <div class="languageDiv">
            <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
            <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
        </div>
        <div class="nameExit">
             <h3 class="h3Add" id="ticketHello">Купівля квитка</p></h3>
            <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="moneyUser">
            <h4 id="amount" class="h4Text">Ваш бюджет</h4>
            <h4 class="h4Money">
                <v:virguleTag
                    amount="${Money.get(0).getAmount()} ₴"
                />
            </h4>
        </div>
        <div class="buttonUser">
            <button class="updateButton" name="updateButton" id="updateButton">Оновити</button>
            <button class="addButton" type="Button" name="addButton" onclick="addFunc()" id="addButton">Квиток</button>
            <button class="saveButton" name="saveButton" id="saveButton">Підтвердити</button>
            <button class="roleBackButton" name="roleBackButton" id="roleBackButton">Відхилити</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                 <h6 class="h6Add" id="nameEx">Оберіть виставку</h6>
                 <select class="SelectExhibition" name="nameExhibition" id="SelectExhibition" multiple>
                    <c:forEach var="name" items="${AddShow}">
                        <option value="${name.getNameExhibition()}">${name.getNameExhibition()}</option>
                    </c:forEach>
                 </select>
                <div class="buttonAdd">
                    <button class="addButtonServlet" id="addButtonTicket" name="addButtonTicket">Придбати квиток</button>
                </div>
            </div>
        </div>
        <table id="myTable">
            <tr>
                <th id="nameExhibition">Назва виставки</th>
                <th id="ticketPrice">Ціна білету</th>
                <th id="dateStart">Дата початку</th>
                <th id="dateEnd">Дата кінця</th>
                <th id="boughtTicket">Придбані квитки</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="user" items="${FirstPageUser}">
                    <tr>
                        <td>${user.getNameExhibition()}</td>
                        <td>${user.getPrice()}</td>
                        <td>${user.getDateStart()}</td>
                        <td>${user.getDateEnd()}</td>
                        <td>${user.getTicket()}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.Error}">
                <script>
                    sweetAlert("Error", "Database problems, try again later!", "error");
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
    <script src="./JS/userJS/firstPage/UserAutorized.js" type="text/javascript"></script>
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

