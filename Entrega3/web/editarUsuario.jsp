<%-- 
    Document   : editarUsuario
    Created on : 26/03/2021, 09:22:49
    Author     : joaop
--%>

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

            <!-- TITULO  +  BOTÃO DE EDITAR USUARIO -->
            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-4 d-flex justify-content-start align-items-center p-2">
                    <h3><b>Editar Usuario</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-4 d-flex justify-content-end align-items-center p-2">
                    <a href="UsuarioController?acao=listar" class="btn btn-primary"><i class="fas fa-list"></i> Listar Usuários</a>
                </div>
            </div> <!-- ROW -->

            <!-- Formulário de Usuario -->
            <jsp:include page="components/formUsuario.jsp" />
        </div>

        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>
