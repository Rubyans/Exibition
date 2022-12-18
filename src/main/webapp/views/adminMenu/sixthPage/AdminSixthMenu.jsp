<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/sixthPage/FormView.css"%> </style>
    </head>
    <body>
        <c:if test="${requestScope.TrueAdd}">
            <script>
                sweetAlert("Addition", "You have successfully added data. Save the changes!", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("Removal", "You have successfully deleted the data. Save the changes!", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("Addition", "This genre already exists, enter another!", "error");
            </script>
        </c:if>

        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("Removal", "There is no data for this name!", "error");
            </script>
        </c:if>

        <form action="adminview" id="ViewForm" class="ViewForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="genreHello">Список жанрів</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
            </div>
            <div class="buttonView">
                <button class="updateButton" id="updateButton" name="updateButton" >Оновити</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">Відхилити</button>
                <button class="saveButton" id="saveButton" name="saveButton">Зберегти</button>
            </div>

            <div id="addDiv" style="display: none;">
                <div class="wrapper">
                    <div class="InputAddDiv">
                        <input type="text" id="InputNameView" placeholder="Введіть назву жанра" name="NameView" >
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" id="addButtonView" name="addButtonView">Додати</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelView" placeholder="Введіть назву жанра" name="viewDel">
                </div>
            <button class="delButtonServlet" id="delButtonView" name="delButtonView">Видалити</button>
            </div>

        <table id="myTable">
                <tr>
                    <th id="uniqueNumber">Унікальний номер</th>
                    <th id="nameGenre">Назва жанру</th>
                </tr>
                <c:if test="${not requestScope.Error}">
                            <c:forEach var="view" items="${SixthPageShow}">
                                <tr>
                                    <td>${view.getId()}</td>
                                    <td>${view.getName()}</td>
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
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="»"></a>
            </div>
        </form>
    <script src="./JS/adminJS/sixthPage/AdminSixthMenu.js" type="text/javascript"></script>
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