<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleAdmin/secondPage/FormHall.css" rel="stylesheet"/>
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
            sweetAlert("Помилка", "Дана назва залу вже існує, введіть іншу назву!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
            sweetAlert("Помилка", "Даних за даною назвою не існує!", "error");
        </script>
    </c:if>

    <form action="adminhall"  id="hallForm" class="hallForm" method="POST">
         <div class="nameExit">
            <h3 class="h3Add">Список залів</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
         </div>
        <div class="buttonHall">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one" name="addButton"   onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
            <button class="saveButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="text" id="InputNameHL" placeholder="Введіть назву залу" name="nameHall" >
                    <br><input type="number" id="InputSquareHL" placeholder="Введіть площу залу" step="any" name="square" >
                </div>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonHall">Додати</button>
                </div>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelName" placeholder="Введіть назву залу" name="nameDel">
                </div>
                <button class="delButtonServlet" name="delButtonHall">Видалити</button>
        </div>

    <table id="myTable">
            <tr>
                <th>Назва залу</th>
                <th>Площа залу м²</th>
            </tr>

            <c:if test="${not requestScope.Error}">
                        <c:forEach var="hall" items="${SecondPageShow}">
                            <tr>
                                <td>${hall.getName()}</td>
                                <td>${hall.getSquare()}</td>
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
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="»"></a>
            </div>
    </form>
    <script src="./JS/adminJS/secondPage/AdminSecondMenu.js" type="text/javascript"></script>
</body>
</html>