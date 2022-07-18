<%-- 
    Document   : liberacaoProdutos
    Created on : 19/03/2021, 19:27:27
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
        
        <!-- Validação do Login para acesso à página -->
        <%@include file="components/validaLoginComprador.jsp" %>

        
        <%                       
            // Existem mensagens que devem ser mostradas?
            if (usuario.getTipo().equals("2")) {
        %>
            <!-- NAVBAR COMPRADOR -->
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
        <%
            } else if (usuario.getTipo().equals("0")) {
        %>
            <!-- NAVBAR ADMINISTRADOR -->
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
                                <a class="nav-link" href="HomeAdministradorController"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item mx-3">
                                <a class="nav-link" href="UsuarioController?acao=listar"><i class="fas fa-users"></i> Usuários</a>
                            </li>
                            <li class="nav-item dropdown mx-3">
                                <a class="nav-link dropdown-toggle" href="#" id="relatorios" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-chart-bar"></i> Relatórios
                                </a>
                                <div class="dropdown-menu" aria-labelledby="relatorios">
                                    <a class="dropdown-item" href="RelatorioController?acao=estoque">Estoque</a>
                                    <a class="dropdown-item" href="RelatorioController?acao=venda">Vendas</a>
                                </div>
                            </li>
                            <li class="nav-item mx-3 active">
                                <a class="nav-link" href="ProdutoController?acao=liberar"><i class="fas fa-unlock-alt"></i> Liberação Produtos</a>
                            </li>
                        </ul>
                    </div>
                    <a href="LoginController?acao=logout" class="btn btn-outline-dark my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Fazer Logout</a>
                </div>
            </nav>
        <%
            }
        %>

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

            <!-- TITULO  +  BOTÃO DE ADICIONAR PRODUTOS -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Liberação de Produtos</b></h3>
                </div>
            </div> <!-- ROW -->

            <!-- LISTA DE PRODUTOS -->
                <!-- LIBERARÇÃO PRODUTOS -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%
                        // Recuperando do Controller a lista de todos os Fornecedores
                        ArrayList<Produto> todosProdutos = (ArrayList<Produto>) request.getAttribute("todosProdutos");
                    %>
                    <caption>Lista de Produtos - Total: <%= todosProdutos.size() %></caption>
                    <col width=50%>
                    <col width=15%>
                    <col width=10%>
                    <col width=10%>
                    <col width=15%>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Produto</th>
                            <th scope="col">Preço de Venda (R$)</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Liberado</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Fornecedores
                            for (int index = 0; index < todosProdutos.size(); index++) {
                                
                                // Pegando um fornecedor da lista de todos os fornecedores
                                Produto umProduto = todosProdutos.get(index);
                                
                                // Criando os links de edição e exclusão
                                String linkLiberacao = "ProdutoController?acao=liberar&id=" + umProduto.getId();
                        %>
                            <tr>
                                <td><%= umProduto.getNome() %></td>
                                <td><%= umProduto.getPrecoVenda() %></td>
                                <td><%= umProduto.getQuantidade() %></td>
                                <%
                                    if (umProduto.getLiberado().equals("S")) {
                                %>
                                        <td>Sim</td>
                                <%
                                    } else {
                                %>
                                        <td>Não</td>
                                <%
                                    }
                                %>
                                <%
                                    if (umProduto.getLiberado().equals("S")) {
                                %>
                                        <td><a href="<%= linkLiberacao %>" class="btn btn-sm btn-danger"><i class="fas fa-times"></i> Bloquear</a></td>
                                <%
                                    } else {
                                %>
                                        <td><a href="<%= linkLiberacao %>" class="btn btn-sm btn-success"><i class="fas fa-check"></i> Liberar</a></td>
                                <%
                                    }
                                %>
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