<%-- 
    Document   : formVendedor
    Created on : 26/03/2021, 08:33:57
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario novoUsuario = (Usuario)request.getAttribute("usuario");
%>
<!-- FORM VENDEDOR -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="UsuarioController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_usuario" value="<%= novoUsuario.getId() %>">
        <!-- NOME -->
        <div class="form-group">
            <label for="nome"><b>Nome</b></label>
            <input type="text" class="form-control" name="nome" id="nome" autocomplete="off" value="<%= novoUsuario.getNome() %>" placeholder="Digite o Nome do Usuario" required>
        </div>
        <!-- CPF -->
        <div class="form-group">
            <label for="cpf"><b>CPF</b></label>
            <input type="text" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" value="<%= novoUsuario.getCpf() %>" placeholder="Digite o CPF do Usuario" required>
        </div>
        <!-- SENHA -->
        <div class="form-group">
            <label for="senha"><b>Senha</b></label>
            <input type="password" class="form-control" name="senha" id="senha" autocomplete="off" value="<%= novoUsuario.getSenha() %>" placeholder="Digite a Senha do Usuario" required>
        </div>
        <!-- TIPO USUÁRIO -->
        <div class="form-group">
            <label for="tipo_usuario"><b>Tipo Usuário</b></label>
            <select class="form-control" id="tipo_usuario"  name="tipo_usuario" required>
                <option value="0" <%= novoUsuario.getTipo().equals("0") ? "selected" : "" %>>Administrador</option>                 
                <option value="1" <%= novoUsuario.getTipo().equals("1") ? "selected" : "" %>>Vendedor</option>
                <option value="2" <%= novoUsuario.getTipo().equals("2") ? "selected" : "" %>>Comprador</option>
            </select>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>
