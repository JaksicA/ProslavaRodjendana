<%-- 
    Document   : index
    Created on : Sep 25, 2021, 1:36:43 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.Reservation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>

    <body>
        <jsp:include page="../navbar.jsp"/>

        <%
            List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
            String userId = (String) session.getAttribute("userId");
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Pocetak</th>
                                <th>Kraj</th>
                                <th>Ukupna cena</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Reservation reservation : reservations) {
                            %>
                            <tr>
                                <td><%=reservation.getStart()%></td>
                                <td><%=reservation.getEnd()%></td>
                                <td><%=reservation.getFinalPrice()%></td>
                                <%
                                    if (reservation.isCanceled() == true) {
                                %>
                                <td class="text-danger">Otkazano</td>
                                <%
                                } else {
                                %>
                                <td>
                                    <form method="POST" action="ReservationCrudServlet">
                                        <input type="hidden" name="<%=StringConst.ID%>" value="<%=reservation.getId()%>"/>
                                        <input type="hidden" name="celebrationId" value="<%=reservation.getCelebrationId()%>"/>
                                        <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Cancel"/>
                                        <input type="submit" value="Otkazi" class="btn btn-outline-danger btn-sm"/>
                                    </form>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>  
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
