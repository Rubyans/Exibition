<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleAdmin/fourthPage/FormAuthor123.css" rel="stylesheet"/>
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

    <c:if test="${requestScope.AddError}">
        <script>
            sweetAlert("Помилка", "Даний автор вже існує, введіть інші дані!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
            sweetAlert("Помилка", "Даних за даною поштою не існує!", "error");
        </script>
    </c:if>

    <form action="adminauthor"  id="AuthorForm" class="AuthorForm" method="POST">
        <div class="nameExit">
            <h3 class="h3Add">Список авторів</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="buttonAuthor">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one" name="addButton"   onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
            <button class="saveButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="text" id="InputFirstNameAd" placeholder="Введіть прізвище автора" name="firstNameAuthor" >
                    <br><input type="text" id="InputLastNameAd" placeholder="Введіть ім`я автора" name="lastNameAuthor" >
                    <br><input type="email" id="InputEmailAd" placeholder="Введіть поштову скриньку автора" name="emailAuthor" >
                </div>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonAuthor">Додати</button>
                </div>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="email" id="InputDelAuthor" placeholder="Введіть поштову скриньку автора" name="authorDel">
                </div>
                <button class="delButtonServlet" name="delButtonAuthor">Видалити</button>
        </div>

    <table id="myTable">
            <tr>
                <th>Прізвище</th>
                <th>Ім`я</th>
                <th>Електронна адреса</th>
            </tr>

            <c:if test="${not requestScope.Error}">
                        <c:forEach var="author" items="${FouthPageShow}">
                            <tr>
                                <td>${author.getFirstName()}</td>
                                <td>${author.getLastName()}</td>
                                <td>${author.getEmail()}</td>
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
                <a href="/exhibition/adminaddress">«</a>
                <a href="/exhibition/adminmain">1</a>
                <a href="/exhibition/adminhall">2</a>
                <a href="/exhibition/adminaddress">3</a>
                <a class="active" href="/exhibition/adminauthor">4</a>
                <a href="/exhibition/adminart">5</a>
                <a href="/exhibition/adminview">6</a>
                <a href="/exhibition/userautorized">7</a>
                <a href="/exhibition/adminart">»</a>
            </div>
    </form>

    <script>
           function addFunc(){

                displayAnother= document.getElementById("delDiv").style.display;
                if(displayAnother=='none')
                {
                    display = document.getElementById("addDiv").style.display;
                    if(display=='none'){
                        document.getElementById("addDiv").style.display='block';
                        document.getElementById("InputFirstNameAd").required = true;
                        document.getElementById("InputLastNameAd").required = true;
                        document.getElementById("InputEmailAd").required = true;

                    }else{
                        document.getElementById("addDiv").style.display='none';
                        document.getElementById("InputFirstNameAd").required = false;
                        document.getElementById("InputLastNameAd").required = false;
                        document.getElementById("InputEmailAd").required = false;
                    }
                }
                else
                {
                    document.getElementById("delDiv").style.display='block';
                    delFunc();
                    addFunc();
                }
           };

           function delFunc(){
                displayAnother= document.getElementById("addDiv").style.display;
                if(displayAnother=='none'){
                    display = document.getElementById("delDiv").style.display;
                    if(display=='none'){
                        document.getElementById("delDiv").style.display='block';
                        document.getElementById("InputDelAuthor").required = true;
                    }
                    else{
                        document.getElementById("delDiv").style.display='none';
                        document.getElementById("InputDelAuthor").required = false;
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