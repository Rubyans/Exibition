<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleAdmin/fifthPage/FormArt12.css" rel="stylesheet"/>
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
            sweetAlert("Помилка", "Даний виріб вже існує, введіть іншу назву!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
            sweetAlert("Помилка", "Даних за даною назвою не існує!", "error");
        </script>
    </c:if>

    <form action="adminart" id="ArtForm" class="ArtForm" method="POST">
        <div class="nameExit">
            <h3 class="h3Add">Список витворів мистецтва</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="buttonArt">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
            <button class="saveButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="text" id="InputNameArt" placeholder="Введіть назву мистецтва" name="NameArt" >
                    <br><input type="number" id="InputCreationArt" placeholder="Введіть рік створення" name="CreationArt" >
                    <br><input type="number" id="InputPriceArt" placeholder="Введіть ціну витвору" step="any" name="PriceArt" >
                </div>
                <div class="right_block">
                    <c:forEach var="addFirstPage" items="${AddShow}">
                        <h6 class="h6Add">Оберіть автора</h6>
                        <select class="SelectAdd" name="author" id="SelectAuthor" multiple>
                            <c:forEach var="fullName" items="${addFirstPage.getFullName()}">
                                <option value="${fullName}">${fullName}</option>
                            </c:forEach>
                        </select>
                        <h6 class="h6Add">Оберіть жанр</h6>
                        <select class="SelectAdd" name="view" id="SelectView" multiple>
                            <c:forEach var="viewArt" items="${addFirstPage.getView()}">
                                <option value="${viewArt}">${viewArt}</option>
                            </c:forEach>
                        </select>
                    </c:forEach>
                </div>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonArt">Додати</button>
                </div>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelArt" placeholder="Введіть назву витвору мистецтва" name="artDel">
                </div>
                <button class="delButtonServlet" name="delButtonArt">Видалити</button>
        </div>

    <table id="myTable">
            <tr>
                <th>Витвір мистецтва</th>
                <th>Рік створення</th>
                <th>Ціна</th>
                <th>Жанр</th>
                <th>Автор</th>
            </tr>

            <c:if test="${not requestScope.Error}">
                        <c:forEach var="art" items="${FifthPageShow}">
                            <tr>
                                <td>${art.getName()}</td>
                                <td>${art.getCreation()}</td>
                                <td>${art.getPrice()}</td>
                                <td>
                                    <c:forEach var="nameView" items="${art.getNameView()}">
                                        ${nameView}<br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="author" items="${art.getFullName()}">
                                        ${author}<br>
                                    </c:forEach>
                                </td>
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
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="»"></a>
            </div>
    </form>
    <script src="./JS/adminJS/fifthPage/AdminFifthMenu1.js" type="text/javascript"></script>
</body>
</html>