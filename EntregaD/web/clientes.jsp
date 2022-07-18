<%-- 
    Document   : clientes
    Created on : 04/03/2021, 02:12:40
    Author     : joaop
--%>

<%@page import="models.cliente.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- Incluindo a tag head que é comum em toda a aplicação -->
    <%@include file="components/head.html" %>
    <body>
        
        <!-- Validação do Login para acesso à página -->
        <%@include file="components/validaLoginVendedor.jsp" %>

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
                            <a class="nav-link" href="HomeVendedorController"><i class="fas fa-home"></i> Início <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="ClienteController?acao=listar"><i class="fas fa-user-friends"></i> Clientes</a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="VendaController?acao=listar"><i class="fas fa-store"></i> Vendas</a>
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
                    <h3><b>Clientes</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-end align-items-center p-2">
                    <a href="ClienteController?acao=inserir" class="btn btn-primary"><i class="fas fa-plus"></i> Adicionar Novo Cliente</a>
                </div>
            </div> <!-- ROW -->

            <!-- LISTA DE CLIENTES -->
                <!-- EDITAR E EXCLUIR CLIENTE -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%
                        // Recuperando do Controller a lista de todos os Clientes
                        ArrayList<Cliente> todosClientes = (ArrayList<Cliente>) request.getAttribute("todosClientes");
                    %>
                    <caption>Lista de Clientes - Total: <%= todosClientes.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">CPF</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Email</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Clientes
                            for (int index = 0; index < todosClientes.size(); index++) {
                                
                                // Pegando um cliente da lista de todos os clientes
                                Cliente umCliente = todosClientes.get(index);
                                
                                // Criando os links de edição e exclusão
                                String linkEditar = "ClienteController?acao=editar&id=" + umCliente.getId();
                                String linkExcluir = "ClienteController?acao=excluir&id=" + umCliente.getId();
                        %>
                            <tr>
                                <td class="cpf"><%= umCliente.getCpf() %></td>
                                <td><%= umCliente.getNome() %></td>
                                <td class="telefone"><%= umCliente.getTelefone() %></td>
                                <td><%= umCliente.getEmail() %></td>
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