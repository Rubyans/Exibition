<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Адміністратор</title>
    <meta charset="UTF-8"/>
    <link href="./style/styleAdmin/seventhPage/FormUserAutorized1.css" rel="stylesheet"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
</head>
<body>

<c:if test="${requestScope.TrueAdd}">
    <script>
            sweetAlert("Додавання", "Ви успішно додали дані. Збережіть зміни!", "success");
    </script>
</c:if>

<c:if test="${requestScope.TrueDel}">
    <script>
            sweetAlert("Видалення", "Ви успішно видалили дані. Збережіть зміни!", "success");
    </script>
</c:if>

<c:if test="${requestScope.AddAmount}">
    <script>
            sweetAlert("Змінення", "Ви успішно змінили кількість грошей. Збережіть зміни", "success");
    </script>
</c:if>

<c:if test="${requestScope.AddAmountError}">
    <script>
            sweetAlert("Помилка", "Дані не знайдено!", "error");
    </script>
</c:if>

<c:if test="${requestScope.AddError}">
    <script>
            sweetAlert("Помилка", "Даний користувач вже існує, введіть іншу пошту або логін!", "error");
    </script>
</c:if>

<c:if test="${requestScope.DelError}">
    <script>
            sweetAlert("Помилка", "Даних за даною поштою не існує!", "error");
    </script>
</c:if>

<form action="userautorized" id="UserAutorizedForm" class="UserAutorizedForm" method="POST">
    <div class="nameExit">
        <h3 class="h3Add">Список авторизованих користувачів</p></h3>
        <button class="exitButton" name="exitButton">Вийти</button>
    </div>
    <div class="buttonAuto">
        <button class="updateButton" name="updateButton">Оновити</button>
        <button class="addButton" id="one" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
        <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button">Видалення</button>
        <button class="saveButton" name="roleBackButton">Відхилити</button>
        <button class="saveButton" name="saveButton">Зберегти</button>
    </div>

    <div id="addDiv" style="display:none;">
        <div class="wrapper">
            <h6 class="h6Add">Оберіть спосіб додавання</h6>
            <div class="buttonAdd">
                <button type="Button" class="addButtonUser" onclick="addUser()" name="addButtonUser">Додати
                    користувача
                </button>
                <button type="Button" class="addButtonMoney" onclick="addMoney()" name="addButtonMoney">Змінити ціну</button>
            </div>
        </div>
    </div>
    <div id="addDivUser" style="display: none;">
        <div class="wrapper">
            <div class="SelectDiv">
                <h6 class="h6Add">Оберіть роль</h6>
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
            <button class="addButtonServlet" name="addButtonAuto">Додати</button>
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
            <button class="addButtonServlet" name="ButtonMoney">Змінити</button>
        </div>
    </div>
    <div class="delDiv" id="delDiv" style="display: none;">
        <div class="InputDelDiv">
            <input class="inputDel" type="email" id="InputDelAuto" placeholder="Введіть пошту користувача"
                   name="autoDel">
        </div>
        <button class="delButtonServlet" name="delButtonAuto">Видалити</button>
    </div>

    <table id="myTable">
        <tr>
            <th>Прізвище користувача</th>
            <th>Ім`я користувача</th>
            <th>Логін користувача</th>
            <th>Пароль користувача</th>
            <th>Електрона адреса</th>
            <th>Кількість грошей</th>
            <th>Роль</th>
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
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.Error}">
            <script>
                    sweetAlert("Помилка", "Проблеми з базою даних, спробуйте пізніше!", "error");
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
        <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="»"></a>
    </div>
</form>
<script src="./JS/adminJS/seventhPage/AdminSeventhMenu.js" type="text/javascript"></script>
</body>
</html>