<%-- 
    Document   : vendas
    Created on : 05/03/2021, 14:21:52
    Author     : joaop
--%>

<%@page import="models.usuario.Usuario"%>
<%@page import="models.venda.VendaView"%>
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
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="ClienteController?acao=listar"><i class="fas fa-user-friends"></i> Clientes</a>
                        </li>
                        <li class="nav-item mx-3 active">
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
                    <h3><b>Vendas</b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-end align-items-center p-2">
                    <a href="VendaController?acao=inserir" class="btn btn-primary"><i class="fas fa-plus"></i> Adicionar Nova Venda</a>
                </div>
            </div> <!-- ROW -->
            
            <!-- LISTA DE VENDAS -->
                <!-- EDITAR E EXCLUIR VENDA -->
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <%                        
                        // Recuperando do Controller a lista de todos os Clientes
                        ArrayList<VendaView> todasVendas = (ArrayList<VendaView>) request.getAttribute("todasVendas");
                    %>
                    <caption>Lista de Vendas - Total: <%= todasVendas.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Produto</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Valor R$</th>
                            <th scope="col">Data</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Clientes
                            for (int index = 0; index < todasVendas.size(); index++) {
                                
                                // Pegando um cliente da lista de todos os clientes
                                VendaView umaVenda = todasVendas.get(index);

                                // Criando os links de edição e exclusão
                                String linkEditar = "VendaController?acao=editar&id=" + umaVenda.getId();
                                String linkExcluir = "VendaController?acao=excluir&id=" + umaVenda.getId();
                                
                        %>
                        <tr>
                            <td><%= umaVenda.getNomeProduto() %></td>
                            <td><%= umaVenda.getQuantidade() %></td>
                            <td><%= umaVenda.getValor() %></td>
                            <td class="data"><%= umaVenda.getData() %></td>
                            <td><%= umaVenda.getNomeCliente() %></td>
                            <td class="d-flex flex-row justify-content-center align-items-center p-2">
                                <%
                                    // Validando se a venda foi realizada pelo vendedor que está logado
                                    if (usuario.getId() == umaVenda.getIdVendedor()) {
                                %>
                                        <a href="<%= linkEditar %>" class="btn btn-info m-1"><i class="fas fa-pen"></i></a>
                                        <a href="<%= linkExcluir %>" class="btn btn-danger m-1"><i class="fas fa-trash"></i></a>
                                <%
                                    } else {
                                %>
                                        <a href="<%= linkEditar %>" class="btn btn-info m-1 disabled"><i class="fas fa-pen"></i></a>
                                        <a href="<%= linkExcluir %>" class="btn btn-danger m-1 disabled"><i class="fas fa-trash"></i></a>
                                <%
                                    }
                                %>
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