<%-- 
    Document   : vendedores
    Created on : 26/03/2021, 08:50:24
    Author     : joaop
--%>

<%@page import="models.categoria.CategoriaProduto"%>
<%@page import="java.util.ArrayList"%>
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
                        <li class="nav-item mx-3 active">
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
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="ProdutoController?acao=liberar"><i class="fas fa-unlock-alt"></i> Liberação Produtos</a>
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

            <!-- TITULO  +  BOTÃO DE ADICIONAR USUÁRIOS -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Usuários</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-end align-items-center p-2">
                    <a href="UsuarioController?acao=inserir" class="btn btn-primary"><i class="fas fa-plus"></i> Adicionar Novo Usuário</a>
                </div>
            </div> <!-- ROW -->

            <!-- LISTA DE USUÁRIOS -->
                <!-- EDITAR E EXCLUIR USUÁRIOS -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%
                        // Recuperando do Controller a lista de todos os Categorias
                        ArrayList<Usuario> todosUsuarios = (ArrayList<Usuario>) request.getAttribute("todosUsuarios");
                    %>
                    <caption>Lista de Categorias - Total: <%= todosUsuarios.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">CPF</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Categorias
                            for (int index = 0; index < todosUsuarios.size(); index++) {
                                
                                // Pegando um categoria da lista de todos os categorias
                                Usuario umUsuario = todosUsuarios.get(index);
                                
                                // Criando os links de edição e exclusão
                                String linkEditar = "UsuarioController?acao=editar&id=" + umUsuario.getId();
                                String linkExcluir = "UsuarioController?acao=excluir&id=" + umUsuario.getId();
                        %>
                            <tr>
                                <td><%= umUsuario.getCpf() %></td>
                                <td><%= umUsuario.getNome() %></td>
                                <%
                                    if (umUsuario.getTipo().equals("0")) {
                                %>
                                    <td>Administrador</td>
                                <%
                                    } else if (umUsuario.getTipo().equals("1")) {
                                %>
                                    <td>Vendedor</td>
                                <%
                                    } else {
                                %>
                                    <td>Comprador</td>
                                <%
                                    }
                                %>
                                <td class="d-flex flex-row justify-content-center align-items-center p-2">
                                    <a href="<%= linkEditar %>" class="btn btn-info m-1"><i class="fas fa-pen"></i></a>
                                    <a href="<%= linkExcluir %>" class="btn btn-danger m-1"><i class="fas fa-trash"></i></a>
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