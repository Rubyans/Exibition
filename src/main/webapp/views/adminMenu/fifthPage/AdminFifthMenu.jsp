<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/fifthPage/FormArt.css"%> </style>
    </head>
    <body>

        <c:if test="${requestScope.TrueAdd}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(3)}", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(4)}", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(5)}", "error");
            </script>
        </c:if>

        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(6)}", "error");
            </script>
        </c:if>

        <form action="adminart" id="ArtForm" class="ArtForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="artHello">${languageChange.get(7)}</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(8)}</button>
            </div>
            <div class="buttonArt">
                <button class="updateButton" id="updateButton" name="updateButton" >${languageChange.get(9)}</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">${languageChange.get(10)}</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >${languageChange.get(11)}</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">${languageChange.get(12)}</button>
                <button class="saveButton" id="saveButton" name="saveButton">${languageChange.get(13)}</button>
            </div>

            <div id="addDiv" style="display: none;">
                <div class="wrapper">
                    <div class="left_block">
                        <input type="text" id="InputNameArt" placeholder="${languageChange.get(14)}" name="NameArt" >
                        <br><input type="number" id="InputCreationArt" placeholder="${languageChange.get(15)}" name="CreationArt" >
                        <br><input type="number" id="InputPriceArt" placeholder="${languageChange.get(16)}" step="any" name="PriceArt" >
                    </div>
                    <div class="right_block">
                        <c:forEach var="addFirstPage" items="${AddShow}">
                            <h6 class="h6Add" id="authorChange">${languageChange.get(17)}</h6>
                            <select class="SelectAdd" name="author" id="SelectAuthor" multiple>
                                <c:forEach var="fullName" items="${addFirstPage.getFullName()}">
                                    <option value="${fullName}">${fullName}</option>
                                </c:forEach>
                            </select>
                            <h6 class="h6Add" id="genreChange">${languageChange.get(18)}</h6>
                            <select class="SelectAdd" name="view" id="SelectView" multiple>
                                <c:forEach var="viewArt" items="${addFirstPage.getView()}">
                                    <option value="${viewArt}">${viewArt}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" id="addButtonArt" name="addButtonArt">${languageChange.get(19)}</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                    <div class="InputDelDiv">
                        <input class="inputDel" id="InputDelArt" type="text" id="InputDelArt" placeholder="${languageChange.get(20)}" name="artDel">
                    </div>
                    <button class="delButtonServlet" id="delButtonArt" name="delButtonArt">${languageChange.get(21)}</button>
            </div>

            <table id="myTable">
                <tr>
                    <th id="art">${languageChange.get(22)}</th>
                    <th id="year">${languageChange.get(23)}</th>
                    <th id="price">${languageChange.get(24)}</th>
                    <th id="genreArt">${languageChange.get(25)}</th>
                    <th id="authorArt">${languageChange.get(26)}</th>
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
                        sweetAlert("${languageChange.get(27)}", "${languageChange.get(28)}", "error");
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
    <script src="./JS/adminJS/fifthPage/AdminFifthMenu.js" type="text/javascript"></script>
    </body>
</html>