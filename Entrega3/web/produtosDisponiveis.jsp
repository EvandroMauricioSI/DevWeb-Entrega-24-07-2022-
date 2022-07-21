<%-- 
    Document   : produtosDisponiveis
    Created on : 07/03/2021, 16:49:26
    Author     : joaop
--%>

<%@page import="models.produto.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- Incluindo a tag head que é comum em toda a aplicação -->
    <%@include file="components/head.html" %>
    <body>

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
                            <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="ProdutoController?acao=disponiveis"><i class="fas fa-box-open"></i> Produtos Disponíveis</a>
                        </li>
                    </ul>
                </div>
                <a href="LoginController?acao=login" class="btn btn-outline-dark my-2 my-sm-0"><i class="fas fa-sign-in-alt"></i> Fazer Login</a>
            </div>
        </nav>


        <div class="container">

            <!-- TITULO  +  BOTÃO DE ADICIONAR CLIENTE -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-6 col-xl-6 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Produtos Disponíveis</b></h3>
                </div>
            </div> <!-- ROW -->

            <!-- LISTA DE CLIENTES -->
                <!-- EDITAR E EXCLUIR CLIENTE -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%
                        // Recuperando do Controller a lista de todos os Clientes
                        ArrayList<Produto> todosProdutos = (ArrayList<Produto>) request.getAttribute("todosProdutosDisponiveis");
                    %>
                    <caption>Lista de Produtos Disponíveis - Total: <%= todosProdutos.size() %></caption>
                    <col width=30%>
                    <col width=50%>
                    <col width=10%>
                    <col width=10%>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Produto</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Preço R$</th>
                            <th scope="col">Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Clientes
                            for (int index = 0; index < todosProdutos.size(); index++) {
                                
                                // Pegando um cliente da lista de todos os clientes
                                Produto umProduto = todosProdutos.get(index);
                        %>
                            <tr>
                                <td><%= umProduto.getNome() %></td>
                                <td><%= umProduto.getDescricao() %></td>
                                <td><%= umProduto.getPrecoVenda() %></td>
                                <td><%= umProduto.getQuantidade() %></td>
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