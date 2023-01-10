<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8"/>
        <title>${languageChange.get(0)}</title>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleGuest/FormGuest.css"%> </style>
    </head>
    <body>
        <form action="exhibition?command=guest"  id="guestForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <h3 class="text"><p id="guestHello">${languageChange.get(1)}</p><h3>
            <div class="buttonAuto">
                <button type="button" class="updateButton" name="updateButton" id="updateButton" onclick="updateFunc()">${languageChange.get(2)}</button>
                <button type="button" class="sortButton" name="sortButton" id="sortButton" onclick="sortFunc()">${languageChange.get(3)}</button>
                <button name="autoButton" class="autoButton" id="autoButton">${languageChange.get(4)}</button>
            </div>

            <div id="updateDiv" style="display: none;">
                 <div class="wrapper">
                    <h6 class="h6Add" id="updateTxt">${languageChange.get(5)}</h6>
                    <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="all">${languageChange.get(6)}</option>
                    </select>
                    <div class="buttonAdd">
                        <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(7)}</button>
                    </div>
                 </div>
            </div>

            <div id="sortDiv" style="display: none;">
                 <div class="wrapper">
                    <h6 class="h6Add" id="sortTxt">${languageChange.get(8)}</h6>
                    <select class="SelectSort" name="SelectSort" id="SelectSort">
                        <option value="1">${languageChange.get(9)}</option>
                        <option value="2">${languageChange.get(10)}</option>
                    </select>
                    <div class="buttonAdd">
                        <button class="sortButtonServlet" id="sortButton" name="sortButton">${languageChange.get(11)}</button>
                    </div>
                 </div>
            </div>
            <table id="myTable">
              <tr>
                <th id="nameExhibition">${languageChange.get(12)}</th>
                <th id="description">${languageChange.get(13)}</th>
                <th id="art">${languageChange.get(14)}</th>
                <th id="price">${languageChange.get(15)}</th>
                <th id="dataStart">${languageChange.get(16)}</th>
                <th id="dataEnd">${languageChange.get(17)}</th>
                <th id="Hours">${languageChange.get(18)}</th>
              </tr>
                <c:if test="${not requestScope.Error}">
                        <c:forEach var="guest" items="${GuestList}">
                            <tr>
                                <td>${guest.getNameExhibition()}</td>
                                <td>${guest.getDescriptionExibition()}</td>
                                <td>
                                    <c:forEach var="exposition" items="${guest.getExpositionName()}">
                                     ${exposition}<br>
                                     </c:forEach>
                                 </td>
                            <td>${guest.getPrice()}</td>
                            <td>${guest.getDateStart()}</td>
                            <td>${guest.getDateEnd()}</td>
                            <td>${guest.getHours()}</td>
                            </tr>
                         </c:forEach>
                </c:if>
                <c:if test="${requestScope.Error}">
                    <script>
                        sweetAlert("${languageChange.get(19)}", "${languageChange.get(20)}", "error");
                    </script>
                </c:if>
            </table>
        </form>
        <script src="./JS/functionsGuest12.js" type="text/javascript"></script>
    </body>
</html>