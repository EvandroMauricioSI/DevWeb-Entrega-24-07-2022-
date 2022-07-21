<%-- 
    Document   : validaLoginComprador
    Created on : 15/03/2021, 12:16:23
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Recuperando do Controller o Usuário que está logado na aplicação
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    // Se o Usuário for um Comprador
    if (usuario == null || usuario.getTipo().equals("1")) {
        response.sendRedirect("LoginController?acao=login");
    }
%>
