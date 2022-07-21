/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.categoria.CategoriaProduto;
import models.categoria.CategoriaProdutoDAO;
import models.produto.Produto;
import models.produto.ProdutoDAO;
import models.venda.VendaDAO;
import models.venda.VendaView;

/**
 *
 * @author joaop
 */
@WebServlet(name = "RelatorioController", urlPatterns = {"/RelatorioController"})
public class RelatorioController extends HttpServlet {

     /**
     * @brief Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Intância do VendaDAO
        VendaDAO vendaDAO = new VendaDAO();
        
        // Intância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
        
        ArrayList<Produto> todosProdutos;
        ArrayList<CategoriaProduto> todasCategorias;
        ArrayList<VendaView> todasVendas;
        ArrayList<VendaView> todasVendasDiarias;
        
        Produto produtoMaisCaro;
        Produto produtoMaisBarato;
        Produto produtoMaiorQuantidade;
        Produto produtoMenorQuantidade;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "venda":
                // Pegando os dados do Relatório de Vendas
                todasVendas = vendaDAO.pegarVendaView();
                // Pegando os dados do Relatório de Vendas
                todasVendasDiarias = vendaDAO.pegarVendaDiaria();
                
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("todasVendas", todasVendas);
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("todasVendasDiarias", todasVendasDiarias);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/relatorioVenda.jsp");
                listar.forward(request, response);
                
                break;
                
            case "estoque":
                // Pegando os dados do Relatório de Estoque
                todosProdutos = produtoDAO.pegarTodos();
                todasCategorias = categoriaDAO.pegarTodas();
                produtoMaisCaro = produtoDAO.pegarProdutoMaisCaro();
                produtoMaisBarato = produtoDAO.pegarProdutoMaisBarato();
                produtoMaiorQuantidade = produtoDAO.pegarProdutoMaiorQuantidade();
                produtoMenorQuantidade = produtoDAO.pegarProdutoMenorQuantidade();
                
                // Enviando o ArrayList de todos os produtos para a view
                request.setAttribute("todosProdutos", todosProdutos);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("todasCategorias", todasCategorias);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("categoria", 0);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("ordem", 0);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("produtoMaisCaro", produtoMaisCaro);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("produtoMaisBarato", produtoMaisBarato);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("produtoMaiorQuantidade", produtoMaiorQuantidade);
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("produtoMenorQuantidade", produtoMenorQuantidade);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/relatorioEstoque.jsp");
                inserir.forward(request, response);
                
                break;
        }       
    }

    /**
     * @brief Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Intância do VendaDAO
        VendaDAO vendaDAO = new VendaDAO();
        
        // Intância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        ArrayList<VendaView> todasVendas;
        ArrayList<VendaView> todasVendasDiarias;
        
        ArrayList<Produto> todosProdutos;
        ArrayList<CategoriaProduto> todasCategorias;
        Produto produtoMaisCaro;
        Produto produtoMaisBarato;
        Produto produtoMaiorQuantidade;
        Produto produtoMenorQuantidade;
       
        
        try {
            // Salvando os dados do Relatorio na variável
            String tipoRelatorio = request.getParameter("relatorio");
            String categoria = request.getParameter("categoria_estoque");
            String ordem = request.getParameter("ordem_estoque");
            
            switch (tipoRelatorio) {
                case "venda":
                    // Pegando os dados do Relatório de Vendas
                    todasVendas = vendaDAO.pegarVendaView();
                    // Pegando os dados do Relatório de Vendas
                    todasVendasDiarias = vendaDAO.pegarVendaDiaria();

                    // Enviando o ArrayList de todos os clientes para a view
                    request.setAttribute("todasVendas", todasVendas);
                    // Enviando o ArrayList de todos os clientes para a view
                    request.setAttribute("todasVendasDiarias", todasVendasDiarias);
                    RequestDispatcher listar = getServletContext().getRequestDispatcher("/relatorioVenda.jsp");
                    listar.forward(request, response);

                    break;

                case "estoque":
                    // Pegando os dados do Relatório de Estoque
                    todosProdutos = produtoDAO.pegarTodosFiltrado(categoria, ordem);
                    todasCategorias = categoriaDAO.pegarTodas();
                    produtoMaisCaro = produtoDAO.pegarProdutoMaisCaro();
                    produtoMaisBarato = produtoDAO.pegarProdutoMaisBarato();
                    produtoMaiorQuantidade = produtoDAO.pegarProdutoMaiorQuantidade();
                    produtoMenorQuantidade = produtoDAO.pegarProdutoMenorQuantidade();

                    // Enviando o ArrayList de todos os produtos para a view
                    request.setAttribute("todosProdutos", todosProdutos);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("todasCategorias", todasCategorias);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("categoria", categoria);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("ordem", ordem);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("produtoMaisCaro", produtoMaisCaro);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("produtoMaisBarato", produtoMaisBarato);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("produtoMaiorQuantidade", produtoMaiorQuantidade);
                    // Enviando o ArrayList de todas as categorias para a view
                    request.setAttribute("produtoMenorQuantidade", produtoMenorQuantidade);
                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/relatorioEstoque.jsp");
                    inserir.forward(request, response);

                    break;
            }
        } catch(IOException | NumberFormatException | ServletException e) {
           
            // Enviando o ArrayList de todos os clientes para a view
            RequestDispatcher home = getServletContext().getRequestDispatcher("/homeAdministrador.jsp");
            home.forward(request, response);
        }
    }
}
