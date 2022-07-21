<%-- 
    Document   : validaLoginVendedor
    Created on : 12/03/2021, 11:10:48
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Recuperando do Controller o Usuário que está logado na aplicação
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    // Se o Usuário for um Vendedor
    if (usuario == null || usuario.getTipo().equals("2")) {
        response.sendRedirect("LoginController?acao=login");
    }
%>
