<%-- 
    Document   : editarCategoria
    Created on : 15/03/2021, 20:58:45
    Author     : joaop
--%>

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
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="FornecedoreController?acao=listar"><i class="fas fa-truck"></i> Fornecedores</a>
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

            <!-- TITULO  +  BOTÃO DE ADICIONAR CATEGORIA -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-4 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Editar Categoria</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-4 d-flex justify-content-end align-items-center p-2">
                    <a href="CategoriaProdutoController?acao=listar" class="btn btn-primary"><i class="fas fa-list"></i> Listar Categorias</a>
                </div>
            </div> <!-- ROW -->

            <!-- Formulário de Categoria do Produto -->
            <jsp:include page="components/formCategoriaProduto.jsp" />
        </div>

        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>
