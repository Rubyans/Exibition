<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Адміністратор</title>
    <meta charset="UTF-8"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style> <%@include file="/style/styleAdmin/seventhPage/FormUserAutorized.css"%> </style>
</head>
    <body>
    <c:if test="${requestScope.TrueAdd}">
        <script>
                sweetAlert("Addition", "You have successfully added data. Save the changes!", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.TrueChange}">
        <script>
            sweetAlert("Access", "You have successfully added data. Save the changes!", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.TrueDel}">
        <script>
                sweetAlert("Removal", "You have successfully deleted the data. Save the changes!", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.AddAmount}">
        <script>
                sweetAlert("Change", "You have successfully changed the amount of money. Save the changes", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.AddAmountError}">
        <script>
                sweetAlert("Change", "No data found!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.AddError}">
        <script>
                sweetAlert("Addition", "This user already exists, enter another email or login!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
                sweetAlert("Removal", "Data for this mail does not exist!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.ChangeError}">
        <script>
            sweetAlert("Access", "There is no data for this email!", "error");
        </script>
    </c:if>

    <form action="userautorized" id="UserAutorizedForm" class="UserAutorizedForm" method="POST">
        <div class="languageDiv">
            <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
            <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
        </div>
        <div class="nameExit">
            <h3 class="h3Add" id="authorizedHello">Список користувачів</p></h3>
            <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="buttonAuto">
            <button class="updateButton" id="updateButton" name="updateButton">Оновити</button>
            <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button">Видалення</button>
            <button class="accessButton" name="accessButton" id="accessButton" onclick="accessFunc()" type="Button">Доступ</button>
            <button class="saveButton" id="roleBackButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" id="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display:none;">
            <div class="wrapper">
                <h6 class="h6Add" id="addH6">Оберіть спосіб додавання</h6>
                <div class="buttonAdd">
                    <button type="Button" id="addButtonUser" class="addButtonUser" onclick="addUser()" name="addButtonUser">Додати користувача</button>
                    <button type="Button" id="addButtonMoney" class="addButtonMoney" onclick="addMoney()" name="addButtonMoney">Змінити ціну</button>
                </div>
            </div>
        </div>
        <div id="addDivUser" style="display: none;">
            <div class="wrapper">
                <div class="SelectDiv">
                    <h6 id="roleChange" class="h6Add">Оберіть роль</h6>
                    <select class="SelectRole" name="role" id="SelectRole">
                        <option value="1">Користувач</option>
                        <option value="2">Адміністратор</option>
                    </select>
                </div>
                <div class="left_block">
                    <input type="text" id="InputFirstNameAuto" placeholder="Введіть прізвище користувача" name="firstName" >
                    <input type="text" id="InputLastNameAuto" placeholder="Введіть ім`я користувача" name="lastName" >
                    <input type="text" id="InputLoginAuto" placeholder="Введіть логін користувача" name="login" >
                </div>
                <div class="right_block">
                    <input type="text" id="InputPasswordAuto" placeholder="Введіть пароль користувача" name="password" >
                    <input type="email" id="InputEmailAuto" placeholder="Введіть електронну адресу" name="email" >
                    <input type="number" id="InputAmountAuto" placeholder="Введіть кількість грошей" step="any" name="amount" >
                </div>
                <button class="addButtonServlet" id="addButtonAuto" name="addButtonAuto">Додати</button>
            </div>
        </div>
        <div id="addDivMoney" style="display:none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="email" id="InputEmailUserAuto" placeholder="Введіть електронну адресу" name="emailMoney" >
                </div>
                <div class="right_block">
                    <input type="number" id="InputAmountUserAuto" placeholder="Введіть кількість грошей" step="any" name="money" >
                </div>
                <button class="addButtonServlet" id="ButtonMoney" name="ButtonMoney">Змінити</button>
            </div>
        </div>
        <div class="delDiv" id="delDiv" style="display: none;">
            <div class="InputDelDiv">
                <input class="inputDel" type="email" id="InputDelAuto" placeholder="Введіть пошту користувача" name="autoDel">
            </div>
            <button class="delButtonServlet" id="delButtonAuto" name="delButtonAuto">Видалити</button>
        </div>
        <div class="accessDiv" id="accessDiv" style="display: none;">
            <div class="checkAccess">
                <h6 class="h6Acc" id="userChange">Оберіть доступ</h6>
                <select class="SelectAccess" name="access" id="SelectAccess">
                    <option value="1">Дозволити</option>
                    <option value="2">Заборонити</option>
                </select>
            </div>
            <div class="InputAccessDiv">
                <input class="inputAccess" type="email" id="InputAccessEmail" placeholder="Введіть поштову адресу" name="emailExhibitionAccess">
            </div>
            <button class="accessButtonServlet" id="accessButtonServlet" name="accessButtonServlet">Змінити</button>
        </div>
        <table id="myTable">
            <tr>
                <th id="firstName">Прізвище користувача</th>
                <th id="lastName">Ім`я користувача</th>
                <th id="login">Логін користувача</th>
                <th id="password">Пароль користувача</th>
                <th id="email">Електрона адреса</th>
                <th id="amount">Кількість грошей</th>
                <th id="role">Роль</th>
                <th id="access">Доступ</th>
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
                        <td>${autorized.getRole()}</td>
                        <c:if test="${autorized.getAccess().equals('Заборонено')}">
                            <td style="background: #EB1D36;">${autorized.getAccess()}</td>
                        </c:if>
                        <c:if test="${autorized.getAccess().equals('Дозволено')}">
                            <td>${autorized.getAccess()}</td>
                        </c:if>
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