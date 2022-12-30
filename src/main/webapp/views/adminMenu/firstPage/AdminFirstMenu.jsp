<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tagfile" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/firstPage/FormExhibition.css"%> </style>
    </head>
    <body>
        <c:if test="${requestScope.TrueAdd}">
            <script>
                    sweetAlert("${languageChange.get(1)}", "${languageChange.get(5)}", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.TrueChange}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(4)}", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("${languageChange.get(3)}", "${languageChange.get(6)}", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.AddError}">
                <script>
                    sweetAlert("${languageChange.get(1)}", "${languageChange.get(7)}", "error");
                </script>
        </c:if>
        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("${languageChange.get(3)}", "${languageChange.get(8)}", "error");
            </script>
        </c:if>
        <c:if test="${requestScope.ChangeError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(9)}", "error");
            </script>
        </c:if>
        <form action="exhibition?command=adminmain"  id="adminForm" class="adminForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                 <h3 class="h3Add" id="exhibitionHello">${languageChange.get(10)}</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(11)}</button>
            </div>
            <div class="buttonAdmin">
                <button class="updateButton" name="updateButton" id="updateButton">${languageChange.get(12)}</button>
                <button class="addButton" name="addButton" id="addButton" onclick="addFunc()" type="Button">${languageChange.get(13)}</button>
                <button class="deleteButton" name="deleteButton" id="deleteButton" onclick="delFunc()" type="Button" >${languageChange.get(14)}</button>
                <button class="accessButton" name="accessButton" id="accessButton" onclick="accessFunc()" type="Button">${languageChange.get(15)}</button>
                <button class="roleBackButton" name="roleBackButton" id="roleBackButton">${languageChange.get(16)}</button>
                <button class="saveButton" name="saveButton" id="saveButton">${languageChange.get(17)}</button>
            </div>

            <div id="addDiv" style="display: none;">
                <div class="wrapper">
                    <div class="left_block">
                        <input type="text" id="InputNameEx" placeholder="${languageChange.get(18)}" name="nameExibition" >
                        <input type="text" id="InputDescriptEx" placeholder="${languageChange.get(19)}" name="description" >
                        <input type="number" id="InputPriceEx" placeholder="${languageChange.get(20)}" step="any" name="price" >
                        <input type="text" id="InputHours" placeholder="${languageChange.get(21)}" name="hours" >
                        <h6 class="h6Add" id="hallChange">${languageChange.get(22)}</h6>
                        <input type="date" id="InputDatestartEx" name="start" >
                        <h6 class="h6Add" id="hallChange">${languageChange.get(23)}</h6>
                        <input type="date" id="InputDateendEx" name="end" >
                    </div>
                    <div class="right_block">
                        <c:forEach var="addFirstPage" items="${AddShow}">
                            <h6 class="h6Add" id="hallChange">${languageChange.get(24)}</h6>
                            <select class="SelectAdd" name="hall" id="SelectHall" multiple>
                                <c:forEach var="hall" items="${addFirstPage.getHall()}">
                                    <option value="${hall}">${hall}</option>
                                     </c:forEach>
                            </select>
                            <h6 class="h6Add" id="addressChange">${languageChange.get(25)}</h6>
                            <select class="SelectAdd" name="address" id="SelectAddress" multiple>
                                <c:forEach var="address" items="${addFirstPage.getAddress()}">
                                    <option value="${address}">${address}</option>
                                </c:forEach>
                            </select>
                                <h6 class="h6Add" id="expositionChange">${languageChange.get(26)}</h6>
                            <select class="SelectAdd" name="workArt" id="SelectArt" multiple>
                                <c:forEach var="art" items="${addFirstPage.getArt()}">
                                    <option value="${art}">${art}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" name="addButtonServlet" id="addButtonServlet">${languageChange.get(27)}</button>
                    </div>
                </div>
            </div>
            <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelName" placeholder="${languageChange.get(28)}" name="nameExhibitionDel">
                </div>
                <button class="delButtonServlet" id="delButtonServlet" name="delButtonServlet">${languageChange.get(29)}</button>
            </div>
            <div class="accessDiv" id="accessDiv" style="display: none;">
                  <div class="checkAccess">
                    <h6 class="h6Acc" id="exhibitionChange">${languageChange.get(30)}</h6>
                    <select class="SelectAccess" name="access" id="SelectAccess">
                            <option value="1">${languageChange.get(31)}</option>
                            <option value="2">${languageChange.get(32)}</option>
                    </select>
                  </div>
                 <div class="InputAccessDiv">
                    <input class="inputAccess" type="text" id="InputAccessName" placeholder="${languageChange.get(33)}" name="nameExhibitionAccess">
                 </div>
                 <button class="accessButtonServlet" id="accessButtonServlet" name="accessButtonServlet">${languageChange.get(34)}</button>
            </div>

        <table id="myTable">
            <tr>
                <th id="nameExhibition">${languageChange.get(35)}</th>
                <th id="description">${languageChange.get(36)}</th>
                <th id="exposition">${languageChange.get(37)}</th>
                <th id="price">${languageChange.get(38)}</th>
                <th id="hours">${languageChange.get(39)}</th>
                <th id="dateStart">${languageChange.get(40)}</th>
                <th id="dateEnd">${languageChange.get(41)}</th>
                <th id="hall">${languageChange.get(42)}</th>
                <th id="author">${languageChange.get(43)}</th>
                <th id="genre">${languageChange.get(44)}</th>
                <th id="address">${languageChange.get(45)}</th>
                <th id="access">${languageChange.get(46)}</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="admin" items="${FirstPage}">
                    <tr>
                        <td>${admin.getNameExhibition()}</td>
                        <td>${admin.getDescriptionExibition()}</td>
                        <td>
                            <c:forEach var="exposition" items="${admin.getExpositionName()}">
                                ${exposition}<br>
                            </c:forEach>
                        </td>
                        <td>${admin.getPrice()}</td>
                        <td>${admin.getHours()}</td>
                        <td>${admin.getDateStart()}</td>
                        <td>${admin.getDateEnd()}</td>
                        <td>
                            <c:forEach var="hell" items="${admin.getNameHall()}">
                                ${hell}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="nameAuthor" items="${admin.getNameAuthor()}">
                                ${nameAuthor}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="nameView" items="${admin.getNameview()}">
                                ${nameView}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="address" items="${admin.getAddressExibition()}">
                                ${address}<br>
                            </c:forEach>
                        </td>
                        <tagfile:tableColor name="${admin.getAccessExhibition()}"></tagfile:tableColor>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.Error}">
                <script>
                    sweetAlert("${languageChange.get(47)}", "${languageChange.get(48)}", "error");
                </script>
            </c:if>
        </table>
        <div class="pagination">
            <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="«"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
            <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
            <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="»"></a>
        </div>
    </form>
    <script src="./JS/adminJS/firstPage/AdminFirstMenu1.js" type="text/javascript"></script>
    </body>
</html>

