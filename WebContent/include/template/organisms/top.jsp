<!DOCTYPE html>
<html>

<!-- Incluir o head -->
<%@ include file='/include/template/atoms/head.jsp' %>
<!-- Incluir o head -->

<body>

    <div class="wrapper">

	<% // if(request.getSession().getAttribute("administrador") == null) { %>
        <%//@ include file='/include/template/organisms/sidebarAluno.jsp'%>
    <% // } else if(request.getSession().getAttribute("administrador") == 0) { %>
        <%//@ include file='/include/template/organisms/sidebarProf.jsp'%>
    <% // } else { %>
        <%@ include file='/include/template/organisms/sidebarAdmin.jsp'%>
    <% // } %>

        <!-- Page Content  -->
        <div id="content">

<% if(request.getSession().getAttribute("idusuario") != null) { %>
	<%@include file='/include/template/organisms/navbarUser.jsp'%>
<% } else { %>
	<%@include file='/include/template/organisms/navbarGuest.jsp'%>
<% } %>