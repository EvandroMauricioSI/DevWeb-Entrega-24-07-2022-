<%-- 
    Document   : formCategoria
    Created on : 15/03/2021, 20:47:39
    Author     : joaop
--%>

<%@page import="models.categoria.CategoriaProduto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CategoriaProduto novoCategoriaProduto = (CategoriaProduto)request.getAttribute("categoria");
%>
<!-- FORM CLIENTE -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="CategoriaProdutoController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_categoria" value="<%= novoCategoriaProduto.getId() %>">
        <!-- NOME -->
        <div class="form-group">
            <label for="categoria"><b>Categoria</b></label>
            <input type="text" class="form-control" name="categoria" id="categoria" autocomplete="off" value="<%= novoCategoriaProduto.getCategoria() %>" placeholder="Digite o Nome da Categoria do Produto" required>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>
