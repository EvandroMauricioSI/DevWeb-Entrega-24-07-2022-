<%-- 
    Document   : formVenda
    Created on : 06/03/2021, 21:11:05
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page import="models.cliente.Cliente"%>
<%@page import="models.produto.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.venda.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    // Variável que recebe a venda do banco de dados
    Venda novaVenda = (Venda)request.getAttribute("venda");
    
    // Array que armazena todos os Produtos cadastrados no banco de dados
    ArrayList<Produto> todosProdutos = (ArrayList<Produto>) request.getAttribute("todosProdutos");
    
    // Array que armazena todos os Clientes cadastrados no banco de dados
    ArrayList<Cliente> todosClientes = (ArrayList<Cliente>) request.getAttribute("todosClientes");
    
    // Array que armazena todos os Vendedores cadastrados no banco de dados
    ArrayList<Usuario> todosVendedores = (ArrayList<Usuario>) request.getAttribute("todosVendedores");
%>
<!-- FORM CLIENTE -->
<div class="rounded border border-dark p-4 m-5">
    <form method="POST" action="VendaController">                   
        <!-- ID (hidden) -->
        <input type="hidden" class="form-control" name="id" id="id_novo_cliente" value="<%= novaVenda.getId() %>">
        <!-- PRODUTO -->
        <div class="form-group">
            <label for="produto"><b>Produto</b></label>
            <select class="form-control" id="produto"  name="id_produto" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Clientes
                    for (int index = 0; index < todosProdutos.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Produto umProduto = todosProdutos.get(index);
                %>
                    <option value="<%= umProduto.getId() %>" <%= umProduto.getId() == novaVenda.getIdProduto() ? "selected" : "" %>><%= umProduto.getNome() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- QUANTIDADE -->
        <div class="form-group">
            <label for="quantidade"><b>Quantidade da Venda</b></label>
            <input type="number" class="form-control" name="quantidade_venda" id="quantidade" autocomplete="off" value="<%= novaVenda.getQuantidade() == 0 ? "" : novaVenda.getQuantidade() %>" placeholder="Digite a Quantidade da Venda" required>
        </div>
        <!-- VALOR -->
        <div class="form-group">
            <label for="valor"><b>Valor da Venda</b></label>
            <input type="text" class="form-control" name="valor_venda" id="valor" autocomplete="off" value="<%= novaVenda.getValor() == 0.0 ? "" : novaVenda.getValor() %>" placeholder="Digite o Valor da Venda" required>
        </div>
        <!-- DATA VENDA -->
        <div class="form-group">
            <label for="data"><b>Data da Venda</b></label>
            <input type="text" class="form-control data" name="data_venda" id="data" autocomplete="off" value="<%= novaVenda.getData() %>" placeholder="Digite a Data da Venda" required>
        </div>
        <!-- CLIENTE -->
        <div class="form-group">
            <label for="cliente"><b>Cliente</b></label>
            <select class="form-control" id="id_cliente" name="id_cliente" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Clientes
                    for (int index = 0; index < todosClientes.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Cliente umCliente = todosClientes.get(index);
                %>
                    <option value="<%= umCliente.getId() %>" <%= umCliente.getId() == novaVenda.getIdCliente() ? "selected" : "" %>><%= umCliente.getNome() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- VENDEDOR -->
        <div class="form-group">
            <label for="vendedor"><b>Vendedor</b></label>
            <select class="form-control" id="vendedor"  name="id_vendedor" required>
                <option id=""></option>
                <%
                    // Iterando pela lista de todos os Clientes
                    for (int index = 0; index < todosVendedores.size(); index++) {

                        // Pegando um cliente da lista de todos os clientes
                        Usuario umVendedor = todosVendedores.get(index);
                %>
                    <option value="<%= umVendedor.getId() %>" <%= umVendedor.getId() == novaVenda.getIdVendedor() ? "selected" : "" %>><%= umVendedor.getNome() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark"><i class="fas fa-save"></i> Salvar</button>
    </form>
</div>