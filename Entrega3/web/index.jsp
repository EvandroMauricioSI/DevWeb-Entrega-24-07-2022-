<%-- 
    Document   : index
    Created on : 04/03/2021, 00:51:55
    Author     : joaop
--%>

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
                <a class="navbar-brand" href="#"><b>Desenvolvimento Web</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="#"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="ProdutoController?acao=disponiveis"><i class="fas fa-box-open"></i> Produtos Disponíveis</a>
                        </li>
                    </ul>
                </div>
                <a href="LoginController?acao=login" class="btn btn-outline-dark my-2 my-sm-0"><i class="fas fa-sign-in-alt"></i> Fazer Login</a>
            </div>
        </nav>

        <!-- CONTAINER PRINCIPAL -->
        <div class="container">



        </div>

        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>
