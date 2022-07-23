<%-- 
    Document   : login
    Created on : 10/03/2021, 11:32:50
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
                <a class="navbar-brand" href="index.jsp"><b>Desenvolvimento Web</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="ProdutoController?acao=disponiveis"><i class="fas fa-box-open"></i> Produtos Disponíveis</a>
                        </li>
                    </ul>
                </div>
                <a class="btn btn-outline-dark my-2 my-sm-0 disabled"><i class="fas fa-sign-in-alt"></i> Fazer Login</a>
            </div>
        </nav>

        <div class="container">
            <!-- ALERTA DE SUCESSO/ERRO -->
            <%                       
                // Existem mensagens que devem ser mostradas?
                if (request.getAttribute("mensagem") != null) {
            %>
                    <div class="alert alert-danger m-5" role="alert">
                        <%= (String) request.getAttribute("mensagem") %>
                    </div>
            <%
                }
            %>
            <div class="rounded border border-dark p-4 m-5">
                <!-- FORMULÁRIO LOGIN -->
                <form method="POST" action="LoginController">
                    <!-- CPF -->
                    <div class="form-group">
                        <label for="cpf"><b>CPF</b></label>
                        <input type="text" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF do Usuário" required>
                    </div>
                    <!-- SENHA -->
                    <div class="form-group">
                        <label for="senha"><b>Senha</b></label>
                        <input type="password" class="form-control" name="senha" id="senha" autocomplete="off" placeholder="Digite a Senha do Usuário" required>
                    </div>
                    <!-- TIPO DE CONTA -->
                    <div class="form-group">
                        <label><b>Tipo de Conta</b></label>
                        <br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="administrador" value="0" checked>
                            <label class="form-check-label" for="administrador">Administrador</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="vendedor" value="1" >
                            <label class="form-check-label" for="vendedor">Vendedor</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="comprador" value="2">
                            <label class="form-check-label" for="comprador">Comprador</label>
                        </div>
                    </div>
                    <!-- BOTÃO SUBMIT -->
                    <button type="submit" class="btn btn-dark"><i class="fas fa-check"></i> OK </button>
                </form>
            </div>
        </div>

        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>