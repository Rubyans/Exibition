<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Адміністратор</title>
    <meta charset="UTF-8"/>
    <link href="./style/styleAdmin/seventhPage/FormUserAutorized123.css" rel="stylesheet"/>
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
                <input type="email" id="InputEmailAuto" placeholder="Введіть електронну адресу користувача" name="email" >
                <input type="number" id="InputAmountAuto" placeholder="Введіть кількість грошей" step="any" name="amount" >
            </div>
            <button class="addButtonServlet" name="addButtonAuto">Додати</button>
        </div>
    </div>
    <div id="addDivMoney" style="display:none;">
        <div class="wrapper">
            <div class="left_block">
                <input type="email" id="InputEmailUserAuto" placeholder="Введіть електронну адресу користувача" name="emailMoney" >
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
        <a href="/exhibition/adminview">«</a>
        <a href="/exhibition/adminmain">1</a>
        <a href="/exhibition/adminhall">2</a>
        <a href="/exhibition/adminaddress">3</a>
        <a href="/exhibition/adminauthor">4</a>
        <a href="/exhibition/adminart">5</a>
        <a href="/exhibition/adminview">6</a>
        <a class="active" href="/exhibition/userautorized">7</a>
        <a href="/exhibition/userautorized">»</a>
    </div>
</form>

<script>
            function addUser()
            {
                document.getElementById("addDiv").style.display='none';
                display = document.getElementById("addDivUser").style.display;

                if(display=='none'){
                    document.getElementById("addDivUser").style.display='block';
                }
                else{
                    document.getElementById("addDivUser").style.display='none';
                }
            };
            function addMoney()
            {
                document.getElementById("addDiv").style.display='none';
                display = document.getElementById("addDivMoney").style.display;
                if(display=='none'){
                    document.getElementById("addDivMoney").style.display='block';
                    document.getElementById("InputAmountUserAuto").required = true;
                    document.getElementById("InputEmailUserAuto").required = true;
                }
                else{
                    document.getElementById("addDivMoney").style.display='none';
                    document.getElementById("InputAmountUserAuto").required = false;
                    document.getElementById("InputEmailUserAuto").required = false;
                }
            };


           function addFunc()
           {
                displayMoney=document.getElementById("addDivMoney").style.display;
                displayAddUser=document.getElementById("addDivUser").style.display;
                displayDel=document.getElementById("delDiv").style.display;

                if(displayMoney=='block'){
                    addMoney();
                }

                if(displayAddUser=='block'){
                    addUser();
                }

                if(displayDel=='none'){
                    display = document.getElementById("addDiv").style.display;
                    if(display=='none'){
                        document.getElementById("addDiv").style.display='block';
                    }
                    else{
                        document.getElementById("addDiv").style.display='none';
                    }
                }
                else{
                    document.getElementById("delDiv").style.display='block';
                    delFunc();
                    addFunc();
                }
           };
           function delFunc(){

                displayMoney=document.getElementById("addDivMoney").style.display;
                displayAddUser=document.getElementById("addDivUser").style.display;
                displayAdd= document.getElementById("addDiv").style.display;
                if(displayMoney=='block'){
                    addMoney();
                }
                if(displayAddUser=='block'){
                    addUser();
                }
                if(displayAdd=='none'){
                    display = document.getElementById("delDiv").style.display;
                    if(display=='none'){
                        document.getElementById("delDiv").style.display='block';
                        document.getElementById("InputDelAuto").required = true;
                    }
                    else{
                        document.getElementById("delDiv").style.display='none';
                        document.getElementById("InputDelAuto").required = false;
                    }
                }
                else{
                    document.getElementById("addDiv").style.display='block';
                    addFunc();
                    delFunc();
                }
           };


</script>
</body>
</html>