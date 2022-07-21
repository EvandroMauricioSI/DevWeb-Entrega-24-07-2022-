<%-- 
    Document   : formFornecedor
    Created on : 15/03/2021, 20:40:40
    Author     : joaop
--%>

<%@page import="models.fornecedor.Fornecedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Fornecedor novoFornecedor = (Fornecedor)request.getAttribute("fornecedor");
%>
<!-- FORM CLIENTE -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="FornecedorController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_fornecedor" value="<%= novoFornecedor.getId() %>">
        <!-- RAZÃO SOCIAL -->
        <div class="form-group">
            <label for="razao_social"><b>Razão Social</b></label>
            <input type="text" class="form-control" name="razao_social" id="razao_social" autocomplete="off" value="<%= novoFornecedor.getRazaoSocial() %>" placeholder="Digite o Razão Social do Fornecedor" required>
        </div>
        <!-- CNPJ -->
        <div class="form-group">
            <label for="cnpj"><b>CNPJ</b></label>
            <input type="text" class="form-control cnpj" name="cnpj" id="cnpj" autocomplete="off" value="<%= novoFornecedor.getCnpj() %>" placeholder="Digite o CNPJ do Fornecedor" required>
        </div>
        <!-- ENDEREÇO -->
        <div class="form-group">
            <label for="endereco"><b>Endereço</b></label>
            <input type="text" class="form-control" name="endereco" id="endereco" autocomplete="off" value="<%= novoFornecedor.getEndereco() %>" placeholder="Digite o Endereço do Fornecedor" required>
        </div>
        <!-- BAIRRO -->
        <div class="form-group">
            <label for="bairro"><b>Bairro</b></label>
            <input type="text" class="form-control" name="bairro" id="bairro" autocomplete="off" value="<%= novoFornecedor.getBairro() %>" placeholder="Digite o Bairro do Fornecedor" required>
        </div>
        <!-- CIDADE -->
        <div class="form-group">
            <label for="cidade"><b>Cidade</b></label>
            <input type="text" class="form-control" name="cidade" id="cidade" autocomplete="off" value="<%= novoFornecedor.getCidade() %>" placeholder="Digite a Cidade do Fornecedor" required>
        </div>
        <!-- UF -->
        <div class="form-group">
            <label for="uf"><b>UF</b></label>
            <input type="text" class="form-control" name="uf" id="uf" autocomplete="off" maxlength="2" value="<%= novoFornecedor.getUf() %>" placeholder="Digite a UF do Fornecedor" required>
        </div>
        <!-- CEP -->
        <div class="form-group">
            <label for="cep"><b>CEP</b></label>
            <input type="text" class="form-control cep" name="cep" id="cep" autocomplete="off" maxlength="8" value="<%= novoFornecedor.getCep() %>" placeholder="Digite o CEP do Fornecedor" required>
        </div>
        <!-- TELEFONE -->
        <div class="form-group">
            <label for="telefone"><b>Telefone</b></label>
            <input type="text" class="form-control telefone" name="telefone" id="telefone" autocomplete="off" value="<%= novoFornecedor.getTelefone() %>" placeholder="Digite o Telefone do Fornecedor" required>
        </div>
        <!-- EMAIL -->
        <div class="form-group">
            <label for="email"><b>Email</b></label>
            <input type="email" class="form-control" name="email" id="email" autocomplete="off" value="<%= novoFornecedor.getEmail() %>" placeholder="Digite o Email do Fornecedor" required>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>
