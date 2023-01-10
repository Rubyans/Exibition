<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/eighthPage/FormStatisticsExhibition.css"%> </style>
    </head>
    <body>
        <form action="exhibition?command=adminstatistics"  id="AdminStatistics" class="AdminStatistics" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                 <h3 class="h3Add" id="exhibitionHello">${languageChange.get(1)}</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(2)}</button>
            </div>
            <div class="buttonStatistics">
                <button class="updateButton" name="updateButton" id="updateButton" type="Button" onclick="updateFunc()">${languageChange.get(3)}</button>
            </div>

            <div class="updateDiv" id="updateDiv" style="display: none;">
                <div class="wrapper">
                    <h6 class="h6Upd" id="updateTxt">${languageChange.get(4)}</h6>
                    <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="all">${languageChange.get(5)}</option>
                    </select>
                    <div class="buttonAdd">
                        <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(6)}</button>
                    </div>
                </div>
            </div>

            <table id="myTable">
                <tr>
                    <th id="nameExhibition">${languageChange.get(7)}</th>
                    <th id="ticketExhibition">${languageChange.get(8)}</th>
                </tr>
                <c:if test="${not requestScope.Error}">
                    <c:forEach var="statistics" items="${EighthPageShow}">
                        <tr>
                            <td>${statistics.getNameExhibition()}</td>
                            <td>${statistics.getTicketCount()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.Error}">
                    <script>
                        sweetAlert("${languageChange.get(9)}", "${languageChange.get(10)}", "error");
                    </script>
                </c:if>
            </table>
            <div class="pagination">
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="«"></a>
                <a ><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminStatisticsPagination" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsPagination" value="»"></a>
            </div>
        </form>
        <script src="./JS/adminJS/eighthPage/AdminEighthMenu.js" type="text/javascript"></script>
    </body>
</html>

