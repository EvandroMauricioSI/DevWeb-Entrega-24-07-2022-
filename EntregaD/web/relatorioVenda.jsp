<%-- 
    Document   : relatorioVenda
    Created on : 30/03/2021, 01:53:59
    Author     : joaop
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.venda.VendaView"%>
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
                    <h3><b>Vendas</b></h3>
                </div>
            </div> <!-- ROW -->
            
            <div class="table-responsive my-3">
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
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Iterando pela lista de todos os Clientes
                            for (int index = 0; index < todasVendas.size(); index++) {
                                
                                // Pegando um cliente da lista de todos os clientes
                                VendaView umaVenda = todasVendas.get(index);
                        %>
                        <tr>
                            <td><%= umaVenda.getNomeProduto() %></td>
                            <td><%= umaVenda.getQuantidade() %></td>
                            <td><%= umaVenda.getValor() %></td>
                            <td class="data"><%= umaVenda.getData() %></td>
                            <td><%= umaVenda.getNomeCliente() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
                    
            <!-- TITULO  +  BOTÃO DE ADICIONAR CLIENTE -->
            <div class="d-flex flex-row justify-content-between align-items-center mt-5 ml-5 mr-5">
                <div class="col-sm-12 d-flex justify-content-start align-items-center p-2">
                    <h5><b>Venda Diária</b></h5>
                </div>
            </div> <!-- ROW -->
                    
            <!-- VENDA DIARIA -->
            <div class="table-responsive my-3">
                <table class="table table-borderless table-hover table-sm">
                    <%                        
                        // Recuperando do Controller a lista de todos os Clientes
                        ArrayList<VendaView> todasVendasDiarias = (ArrayList<VendaView>) request.getAttribute("todasVendasDiarias");
                    %>
                    <thead class="thead-light">
                        <tr>
                            <%
                                // Iterando pela lista de todas as Datas
                                for (int index = 0; index < todasVendasDiarias.size(); index++) {

                                    // Pegando um cliente da lista de todos os clientes
                                    VendaView umaVenda = todasVendasDiarias.get(index);
                            %>
                                <th scope="col" class="data"><%= umaVenda.getData() %></th>
                            <%
                                }
                            %>
                        </tr> 
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                // Iterando pela lista de todos os Valores
                                for (int index = 0; index < todasVendasDiarias.size(); index++) {

                                    // Pegando um cliente da lista de todos os clientes
                                    VendaView umaVenda = todasVendasDiarias.get(index);
                            %>
                                <td><%= umaVenda.getValor() %></td>
                            <%
                                }
                            %>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
                    
        <!-- Incluindo os scripts que são comuns em toda a aplicação -->
        <%@include file="components/scripts.html" %>
    </body>
</html>