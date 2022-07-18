<%-- 
    Document   : homeComprador
    Created on : 15/03/2021, 12:04:47
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
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
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="HomeCompradorController"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="FornecedorController?acao=listar"><i class="fas fa-truck"></i> Fornecedores</a>
                        </li>
                        <li class="nav-item dropdown mx-3">
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
            
            <!-- TITULO -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 d-flex flex-column justify-content-center align-items-flex-start p-2">
                    <h3><b>Bem Vindo, </b></h3>
                    <h4><b><%= usuario.getNome() %></b></h4>
                </div>
            </div> <!-- ROW -->
        </div>
                    
        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>