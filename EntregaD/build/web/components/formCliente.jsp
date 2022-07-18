<%-- 
    Document   : formCliente
    Created on : 04/03/2021, 03:07:10
    Author     : joaop
--%>

<%@page import="models.cliente.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cliente novoCliente = (Cliente)request.getAttribute("cliente");
%>
<!-- FORM CLIENTE -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="ClienteController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_cliente" value="<%= novoCliente.getId() %>">
        <!-- NOME -->
        <div class="form-group">
            <label for="nome"><b>Nome</b></label>
            <input type="text" class="form-control" name="nome" id="nome" autocomplete="off" value="<%= novoCliente.getNome() %>" placeholder="Digite o Nome do Cliente" required>
        </div>
        <!-- CPF -->
        <div class="form-group">
            <label for="cpf"><b>CPF</b></label>
            <input type="text" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" value="<%= novoCliente.getCpf() %>" placeholder="Digite o CPF do Cliente" required>
        </div>
        <!-- ENDEREÇO -->
        <div class="form-group">
            <label for="endereco"><b>Endereço</b></label>
            <input type="text" class="form-control" name="endereco" id="endereco" autocomplete="off" value="<%= novoCliente.getEndereco() %>" placeholder="Digite o Endereço do Cliente" required>
        </div>
        <!-- BAIRRO -->
        <div class="form-group">
            <label for="bairro"><b>Bairro</b></label>
            <input type="text" class="form-control" name="bairro" id="bairro" autocomplete="off" value="<%= novoCliente.getBairro() %>" placeholder="Digite o Bairro do Cliente" required>
        </div>
        <!-- CIDADE -->
        <div class="form-group">
            <label for="cidade"><b>Cidade</b></label>
            <input type="text" class="form-control" name="cidade" id="cidade" autocomplete="off" value="<%= novoCliente.getCidade() %>" placeholder="Digite a Cidade do Cliente" required>
        </div>
        <!-- UF -->
        <div class="form-group">
            <label for="uf"><b>UF</b></label>
            <input type="text" class="form-control" name="uf" id="uf" autocomplete="off" maxlength="2" value="<%= novoCliente.getUf() %>" placeholder="Digite a UF do Cliente" required>
        </div>
        <!-- CEP -->
        <div class="form-group">
            <label for="cep"><b>CEP</b></label>
            <input type="text" class="form-control cep" name="cep" id="cep" autocomplete="off" maxlength="8" value="<%= novoCliente.getCep() %>" placeholder="Digite o CEP do Cliente" required>
        </div>
        <!-- TELEFONE -->
        <div class="form-group">
            <label for="telefone"><b>Telefone</b></label>
            <input type="text" class="form-control telefone" name="telefone" id="telefone" autocomplete="off" value="<%= novoCliente.getTelefone() %>" placeholder="Digite o Telefone do Cliente" required>
        </div>
        <!-- EMAIL -->
        <div class="form-group">
            <label for="email"><b>Email</b></label>
            <input type="email" class="form-control" name="email" id="email" autocomplete="off" value="<%= novoCliente.getEmail() %>" placeholder="Digite o Email do Cliente" required>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>
