<%-- 
    Document   : compras
    Created on : 05/03/2021, 14:21:52
    Author     : joaop
--%>

<%@page import="models.compra.CompraView"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- Incluindo a tag head que é comum em toda a aplicação -->
    <%@include file="components/head.html" %>
    <body>
        
        <!-- Validação do Login para acesso à página -->
        <%@include file="components/validaLoginComprador.jsp" %>

        <!-- NAVBAR -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!-- CONTAINER NAVBAR -->
            <div class="container">
                <a class="navbar-brand" href="index.jsp"><b>Dev Web</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="HomeCompradorController"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="FornecedorController?acao=listar"><i class="fas fa-truck"></i> Fornecedores</a>
                        </li>
                        <li class="nav-item dropdown mx-3 active">
                            <a class="nav-link dropdown-toggle" href="#" id="produtos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-boxes"></i> Produtos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="produtos">
                                <a class="dropdown-item" href="ProdutoController?acao=listar">Produtos</a>
                                <a class="dropdown-item" href="CategoriaProdutoController?acao=listar">Categorias</a>
                                <a class="dropdown-item" href="CompraController?acao=listar">Compra</a>
                                <a class="dropdown-item" href="ProdutoController?acao=liberar">Liberação</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <a href="LoginController?acao=logout" class="btn btn-outline-dark my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Fazer Logout</a>
            </div>
        </nav>


        <div class="container">
            <!-- ALERTA DE SUCESSO/ERRO -->
            <%                       
                // Existem mensagens que devem ser mostradas?
                if (request.getAttribute("mensagem") != null) {
            %>
                    <%                       
                        // Existem erros?
                        if ((int) request.getAttribute("erro") != 1) {
                    %>
                        <div class="alert alert-success my-5" role="alert">
                            <%= (String) request.getAttribute("mensagem") %>  
                        </div>
                    <%
                        } else {
                    %>
                        <div class="alert alert-danger my-5" role="alert">
                            <%= (String) request.getAttribute("mensagem") %>
                        </div>
                    <%
                        }
                }
            %>
            <!-- TITULO  +  BOTÃO DE ADICIONAR CLIENTE -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Compras</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-end align-items-center p-2">
                    <a href="CompraController?acao=inserir" class="btn btn-primary"><i class="fas fa-plus"></i> Adicionar Nova Compra</a>
                </div>
            </div> <!-- ROW -->
            
            <!-- LISTA DE VENDAS -->
                <!-- EDITAR E EXCLUIR VENDA -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%                        
                        // Recuperando do Controller a lista de todos os Fornecedores
                        ArrayList<CompraView> todasCompras = (ArrayList<CompraView>) request.getAttribute("todasCompras");
                    %>
                    <caption>Lista de Compras - Total: <%= todasCompras.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Produto</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Valor R$</th>
                            <th scope="col">Data</th>
                            <th scope="col">Fornecedor</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Fornecedores
                            for (int index = 0; index < todasCompras.size(); index++) {

                                // Pegando um cliente da lista de todos os clientes
                                CompraView umaCompra = todasCompras.get(index);

                                // Criando os links de edição e exclusão
                                String linkEditar = "CompraController?acao=editar&id=" + umaCompra.getId();
                                String linkExcluir = "CompraController?acao=excluir&id=" + umaCompra.getId();
                        %>
                        <tr>
                            <td><%= umaCompra.getNomeProduto() %></td>
                            <td><%= umaCompra.getQuantidade() %></td>
                            <td><%= umaCompra.getValor() %></td>
                            <td class="data"><%= umaCompra.getData() %></td>
                            <td><%= umaCompra.getNomeFornecedor() %></td>
                            <td class="d-flex flex-row justify-content-center align-items-center p-2">
                                <%
                                    // Validando se a venda foi realizada pelo vendedor que está logado
                                    if (usuario.getId() == umaCompra.getIdComprador()) {
                                %>
                                        <a href="<%= linkEditar %>" class="btn btn-info m-1"><i class="fas fa-pen"></i></a>
                                        <a href="<%= linkExcluir %>" class="btn btn-danger m-1"><i class="fas fa-trash"></i></a>
                                <%
                                    } else {
                                %>
                                        <a href="<%= linkEditar %>" class="btn btn-info m-1 disabled"><i class="fas fa-pen"></i></a>
                                        <a href="<%= linkExcluir %>" class="btn btn-danger m-1 disabled"><i class="fas fa-trash"></i></a>
                                <%
                                    }
                                %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>

        </div>



        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>