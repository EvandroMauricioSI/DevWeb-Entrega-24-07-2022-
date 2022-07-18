<%-- 
    Document   : formCompra
    Created on : 06/03/2021, 21:11:05
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page import="models.fornecedor.Fornecedor"%>
<%@page import="models.produto.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.compra.Compra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    // Variável que recebe a compra do banco de dados
    Compra novaCompra = (Compra)request.getAttribute("compra");
    
    // Array que armazena todos os Produtos cadastrados no banco de dados
    ArrayList<Produto> todosProdutos = (ArrayList<Produto>) request.getAttribute("todosProdutos");
    
    // Array que armazena todos os Fornecedores cadastrados no banco de dados
    ArrayList<Fornecedor> todosFornecedores = (ArrayList<Fornecedor>) request.getAttribute("todosFornecedores");
    
    // Array que armazena todos os Compradores cadastrados no banco de dados
    ArrayList<Usuario> todosCompradores = (ArrayList<Usuario>) request.getAttribute("todosCompradores");
%>
<!-- FORM COMPRA -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="CompraController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_cliente" value="<%= novaCompra.getId() %>">
        <!-- PRODUTO -->
        <div class="form-group">
            <label for="produto"><b>Produto</b></label>
            <select class="form-control" id="produto"  name="id_produto" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Fornecedores
                    for (int index = 0; index < todosProdutos.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Produto umProduto = todosProdutos.get(index);
                %>
                    <option value="<%= umProduto.getId() %>" <%= umProduto.getId() == novaCompra.getIdProduto() ? "selected" : "" %>><%= umProduto.getNome() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- QUANTIDADE -->
        <div class="form-group">
            <label for="quantidade"><b>Quantidade da Compra</b></label>
            <input type="number" class="form-control" name="quantidade_compra" id="quantidade" autocomplete="off" value="<%= novaCompra.getQuantidade() == 0 ? "" : novaCompra.getQuantidade() %>" placeholder="Digite a Quantidade da Compra" required>
        </div>
        <!-- VALOR -->
        <div class="form-group">
            <label for="valor"><b>Valor da Compra</b></label>
            <input type="text" class="form-control" name="valor_compra" id="valor" autocomplete="off" value="<%= novaCompra.getValor() == 0.0 ? "" : novaCompra.getValor() %>" placeholder="Digite o Valor da Compra" required>
        </div>
        <!-- DATA COMPRA -->
        <div class="form-group">
            <label for="data"><b>Data da Compra</b></label>
            <input type="text" class="form-control data" name="data_compra" id="data" autocomplete="off" value="<%= novaCompra.getData() %>" placeholder="Digite a Data da Compra" required>
        </div>
        <!-- FORNECEDOR -->
        <div class="form-group">
            <label for="fornecedor"><b>Fornecedor</b></label>
            <select class="form-control" id="fornecedor" name="id_fornecedor" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Fornecedores
                    for (int index = 0; index < todosFornecedores.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Fornecedor umFornecedor = todosFornecedores.get(index);
                %>
                    <option value="<%= umFornecedor.getId() %>" <%= umFornecedor.getId() == novaCompra.getIdFornecedor() ? "selected" : "" %>><%= umFornecedor.getRazaoSocial() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- COMPRADOR -->
        <div class="form-group">
            <label for="comprador"><b>Comprador</b></label>
            <select class="form-control" id="comprador"  name="id_comprador" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Fornecedores
                    for (int index = 0; index < todosCompradores.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Usuario umComprador = todosCompradores.get(index);
                %>
                    <option value="<%= umComprador.getId() %>" <%= umComprador.getId() == novaCompra.getIdComprador() ? "selected" : "" %>><%= umComprador.getNome() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>