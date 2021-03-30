<%-- 
    Document   : index.jsp
    Created on : Mar 27, 2021, 6:08:25 PM
    Author     : camran1234
--%>
<% session.removeAttribute("user");
    session.removeAttribute("login");%>
<%@ include file = "./Menu/menu.jsp"%>