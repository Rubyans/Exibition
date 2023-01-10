<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/sixthPage/FormView.css"%> </style>
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

        <form action="exhibition?command=adminview" id="ViewForm" class="ViewForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="genreHello">${languageChange.get(7)}</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(8)}</button>
            </div>
            <div class="buttonView">
                <button class="updateButton" id="updateButton" name="updateButton" onclick="updateFunc()" type="Button">${languageChange.get(9)}</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">${languageChange.get(10)}</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >${languageChange.get(11)}</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">${languageChange.get(12)}</button>
                <button class="saveButton" id="saveButton" name="saveButton">${languageChange.get(13)}</button>
            </div>

            <div class="updateDiv" id="updateDiv" style="display: none;">
                <div class="wrapper">
                    <h6 class="h6Upd" id="updateTxt">${languageChange.get(14)}</h6>
                    <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="all">${languageChange.get(15)}</option>
                    </select>
                    <div class="buttonAdd">
                        <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(16)}</button>
                    </div>
                </div>
            </div>

            <div id="addDiv" style="display: none;">
                <div class="wrapper">
                    <div class="InputAddDiv">
                        <input type="text" id="InputNameView" placeholder="${languageChange.get(17)}" name="NameView" >
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" id="addButtonView" name="addButtonView">${languageChange.get(18)}</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelView" placeholder="${languageChange.get(17)}" name="viewDel">
                </div>
            <button class="delButtonServlet" id="delButtonView" name="delButtonView">${languageChange.get(19)}</button>
            </div>

        <table id="myTable">
                <tr>
                    <th id="uniqueNumber">${languageChange.get(20)}</th>
                    <th id="nameGenre">${languageChange.get(21)}</th>
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
                        sweetAlert("${languageChange.get(22)}", "${languageChange.get(23)}", "error");
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
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsPagination" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="»"></a>
            </div>
        </form>
    <script src="./JS/adminJS/sixthPage/AdminSixthMenu1.js" type="text/javascript"></script>
    </body>
</html>