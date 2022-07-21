<%-- 
    Document   : relatorioEstoque
    Created on : 30/03/2021, 01:53:44
    Author     : joaop
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.produto.Produto"%>
<%@page import="models.categoria.CategoriaProduto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- Incluindo a tag head que é comum em toda a aplicação -->
    <%@include file="components/head.html" %>
    <body>
        
        <!-- Validação do Login para acesso à página -->
        <%@include file="components/validaLoginAdministrador.jsp" %>

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
                            <a class="nav-link" href="HomeAdministradorController"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="UsuarioController?acao=listar"><i class="fas fa-users"></i> Usuários</a>
                        </li>
                        <li class="nav-item dropdown mx-3 active">
                            <a class="nav-link dropdown-toggle" href="#" id="relatorios" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-chart-bar"></i> Relatórios
                            </a>
                            <div class="dropdown-menu" aria-labelledby="relatorios">
                                <a class="dropdown-item" href="RelatorioController?acao=estoque">Estoque</a>
                                <a class="dropdown-item" href="RelatorioController?acao=venda">Vendas</a>
                            </div>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="ProdutoController?acao=liberar"><i class="fas fa-unlock-alt"></i> Liberação Produtos</a>
                        </li>
                    </ul>
                </div>
                <a href="LoginController?acao=logout" class="btn btn-outline-dark my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Fazer Logout</a>
            </div>
        </nav>

        <div class="container">
            
            <!-- TITULO  +  BOTÃO DE ADICIONAR CLIENTE -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-6 col-xl-6 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Estoque</b></h3>
                </div>
            </div> <!-- ROW -->
            
            <%
                Produto produtoMaisCaro = (Produto) request.getAttribute("produtoMaisCaro");
                Produto produtoMaisBarato = (Produto) request.getAttribute("produtoMaisBarato");
                Produto produtoMaiorQuantidade = (Produto) request.getAttribute("produtoMaiorQuantidade");
                Produto produtoMenorQuantidade = (Produto) request.getAttribute("produtoMenorQuantidade");
                
                int ordem = Integer.parseInt(request.getAttribute("ordem").toString());
                
                // Recuperando do Controller a lista de todas as Categorias
                ArrayList<CategoriaProduto> todasCategorias = (ArrayList<CategoriaProduto>) request.getAttribute("todasCategorias");
                int categoria = Integer.parseInt(request.getAttribute("categoria").toString());;
            %>
            
            <div class="mx-2">
                <form method="POST" action="RelatorioController">
                    <!-- TIPO DE RELATORIO -->
                    <input type="hidden" class="form-control" name="relatorio" id="relatorio" value="estoque">
                    <div class="row">
                        <div class="col-sm-5">
                            <!-- ORDEM ESTOQUE -->
                            <div class="form-group">
                                <label for="ordem_estoque"><b>Ordenar</b></label>
                                <select class="form-control" id="ordem_estoque"  name="ordem_estoque" required>
                                    <option value="0" <%= ordem == 0 ? "selected" : "" %>>Selecione um Filtro</option>
                                    <option value="1" <%= ordem == 1 ? "selected" : "" %>>Maior Preço</option>
                                    <option value="2" <%= ordem == 2 ? "selected" : "" %>>Menor Preço</option>
                                    <option value="3" <%= ordem == 3 ? "selected" : "" %>>Maior Quantidade</option>
                                    <option value="4" <%= ordem == 4 ? "selected" : "" %>>Menor Quantidade</option>                                   
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <!-- CATEGORIA -->
                            <div class="form-group">
                                <label for="categoria_estoque"><b>Filtrar (Categorias)</b></label>
                                <select class="form-control" id="categoria_estoque"  name="categoria_estoque" required>
                                    <option value="0" <%= categoria == 0 ? "selected" : "" %>>Todas as Categorias</option>
                                    <%
                                        // Iterando pela lista de todos os Fornecedores
                                        for (int index = 0; index < todasCategorias.size(); index++) {

                                            // Pegando um cliente da lista de todos os clientes
                                            CategoriaProduto umaCategoria = todasCategorias.get(index);
                                    %>
                                        <option value="<%= umaCategoria.getId() %>" <%= umaCategoria.getId() == categoria ? "selected" : "" %>><%= umaCategoria.getCategoria() %></option>
                                    <%
                                        }
                                    %>                                   
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-2 d-flex justify-content-center align-items-center">
                            <!-- BOTÃO -->
                            <button type="submit" class="btn btn-dark"><i class="fas fa-sort-amount-down-alt"></i> Ordenar/Filtrar</button>
                        </div>
                    </div>
                </form>
            </div>
            
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%
                        // Recuperando do Controller a lista de todos os Clientes
                        ArrayList<Produto> todosProdutos = (ArrayList<Produto>) request.getAttribute("todosProdutos");
                    %>
                    <caption>Lista de Produtos - Total: <%= todosProdutos.size() %></caption>
                    <col width=25%>
                    <col width=40%>
                    <col width=10%>
                    <col width=10%>
                    <col width=15%>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Produto</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Preço R$</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Categoria</th>
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
                                <%
                                    // Iterando pela lista de todos os Clientes
                                    for (int i = 0; i < todasCategorias.size(); i++) {
                                        
                                        CategoriaProduto umaCategoria = todasCategorias.get(i);

                                        if (umProduto.getIdCategoria() == umaCategoria.getId()) {
                                %>
                                            <td><%= umaCategoria.getCategoria() %></td>
                                <%
                                        }
                                    }
                                %>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <!-- PRODUTO MAIS CARO -->
                <div class="col-sm-12 col-md-6 p-2">
                    <h5><b>Produto Mais Caro</b></h5>
                    <h6><%= produtoMaisCaro.getNome() %></h6>
                </div>
                <!-- PRODUTO MAIS BARATO -->
                <div class="col-sm-12 col-md-6 p-2">
                    <h5><b>Produto Mais Barato</b></h5>
                    <h6><%= produtoMaisBarato.getNome() %></h6>
                </div>
            </div>
                
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <!-- PRODUTO COM MAIS QUANTIDADE -->
                <div class="col-sm-12 col-md-6 p-2">
                    <h5><b>Produto com Maior Quantidade</b></h5>
                    <h6><%= produtoMaiorQuantidade.getNome() %></h6>
                </div>
                <!-- PRODUTO COM MENOS QUANTIDADE -->
                <div class="col-sm-12 col-md-6 p-2">
                    <h5><b>Produto com Menor Quantidade</b></h5>
                    <h6><%= produtoMenorQuantidade.getNome() %></h6>
                </div>
            </div>     
        </div>
                    
        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>
