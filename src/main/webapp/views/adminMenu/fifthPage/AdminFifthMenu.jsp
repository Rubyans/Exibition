<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/fifthPage/FormArt.css"%> </style>
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
                sweetAlert("Addition", "This product already exists, please enter a different name!", "error");
            </script>
        </c:if>

        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("Removal", "There is no data for this name!", "error");
            </script>
        </c:if>

        <form action="adminart" id="ArtForm" class="ArtForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="artHello">Список витворів мистецтва</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
            </div>
            <div class="buttonArt">
                <button class="updateButton" id="updateButton" name="updateButton" >Оновити</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">Відхилити</button>
                <button class="saveButton" id="saveButton" name="saveButton">Зберегти</button>
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
                            <h6 class="h6Add" id="authorChange">Оберіть автора</h6>
                            <select class="SelectAdd" name="author" id="SelectAuthor" multiple>
                                <c:forEach var="fullName" items="${addFirstPage.getFullName()}">
                                    <option value="${fullName}">${fullName}</option>
                                </c:forEach>
                            </select>
                            <h6 class="h6Add" id="genreChange">Оберіть жанр</h6>
                            <select class="SelectAdd" name="view" id="SelectView" multiple>
                                <c:forEach var="viewArt" items="${addFirstPage.getView()}">
                                    <option value="${viewArt}">${viewArt}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" id="addButtonArt" name="addButtonArt">Додати</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                    <div class="InputDelDiv">
                        <input class="inputDel" id="InputDelArt" type="text" id="InputDelArt" placeholder="Введіть назву витвору мистецтва" name="artDel">
                    </div>
                    <button class="delButtonServlet" id="delButtonArt" name="delButtonArt">Видалити</button>
            </div>

            <table id="myTable">
                <tr>
                    <th id="art">Витвір мистецтва</th>
                    <th id="year">Рік створення</th>
                    <th id="price">Ціна</th>
                    <th id="genreArt">Жанр</th>
                    <th id="authorArt">Автор</th>
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
                        sweetAlert("Error", "Database problems, try again later!", "error");
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
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="»"></a>
            </div>
        </form>
    <script src="./JS/adminJS/fifthPage/AdminFifthMenu1.js" type="text/javascript"></script>
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