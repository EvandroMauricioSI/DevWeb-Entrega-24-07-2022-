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

/**
 *
 * @author joaop
 */
@WebServlet(name = "ProdutoController", urlPatterns = {"/ProdutoController"})
public class ProdutoController extends HttpServlet {

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
        
        // Intância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        // Intância do ProdutoDAO
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
        
        // Instanciândo variáveis do Produto
        ArrayList<Produto> todosProdutos;
        Produto produto = new Produto();
        int produtoId;
        
        // Instanciândo variáveis de Categorias
        ArrayList<CategoriaProduto> todasCategorias;

        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todos os Produtos cadastrados no banco de dados
                todosProdutos = produtoDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os Produtos para a view
                request.setAttribute("todosProdutos", todosProdutos);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/produtos.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando um novo Produto
                produto.setId(0);
                produto.setNome("");
                produto.setDescricao("");
                produto.setPrecoCompra(0.00);
                produto.setPrecoVenda(0.00);
                produto.setQuantidade(0);
                produto.setLiberado("");
                produto.setIdCategoria(-1);
                
                todasCategorias = categoriaDAO.pegarTodas();
                
                // Enviando o ArrayList de todos os Produtos para a view
                request.setAttribute("produto", produto);
                // Enviando o ArrayList de todas as Categorias para a view
                request.setAttribute("todasCategorias", todasCategorias);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirProduto.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando um Produto específico no banco de dados
                produtoId = Integer.parseInt(request.getParameter("id"));
                produto = produtoDAO.pegarProduto(produtoId);
               
                todasCategorias = categoriaDAO.pegarTodas();
                
                // Enviando o ArrayList de todos os Produtos para a view
                request.setAttribute("produto", produto);
                // Enviando o ArrayList de todas as Categorias para a view
                request.setAttribute("todasCategorias", todasCategorias);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarProduto.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando um Produto específico no banco de dados
                produtoId = Integer.parseInt(request.getParameter("id"));
                produtoDAO.excluirProduto(produtoId);
                
                // Pegando todos os Produtos cadastrados no banco de dados
                todosProdutos = produtoDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os Produtos para a view
                request.setAttribute("todosProdutos", todosProdutos);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/produtos.jsp");
                excluir.forward(request, response);
                
                break;
            
            case "disponiveis":
                // Pegando todos os Produtos cadastrados no banco de dados
                ArrayList<Produto> todosProdutosDisponiveis = produtoDAO.pegarTodosDisponiveis();
                
                // Enviando o ArrayList de todos os Produtos para a view
                request.setAttribute("todosProdutosDisponiveis", todosProdutosDisponiveis);
                RequestDispatcher disponiveis = getServletContext().getRequestDispatcher("/produtosDisponiveis.jsp");
                disponiveis.forward(request, response);
                
                break;
                
            case "liberar":
                
                // Se houver o Id do Produto alternar o status de liberação
                if (request.getParameter("id") != null) {
                    produtoId = Integer.parseInt(request.getParameter("id"));
                    
                    // Pegar o Produto que se deseja alterar o Status
                    Produto produtoAux = produtoDAO.pegarProduto(produtoId);
                    
                    // Pegar o Status do Produto
                    String status = produtoAux.getLiberado();
                    
                    // Se ele estiver LIBERADO, atualizar para NÃO LIBERADO
                    if ("S".equals(status)) produtoDAO.liberarProduto(produtoId, "N");
                    // Se ele estiver NÃO LIBERADO, atualizar para LIBERADO
                    else if ("N".equals(status)) produtoDAO.liberarProduto(produtoId, "S");
                    
                    response.sendRedirect("http://localhost:8084/Entrega1/ProdutoController?acao=liberar");
                } else {
                    // Pegando todos os Produtos cadastrados no banco de dados
                    todosProdutos = produtoDAO.pegarTodos();

                    // Enviando o ArrayList de todos os Produtos para a view
                    request.setAttribute("todosProdutos", todosProdutos);
                    RequestDispatcher liberar = getServletContext().getRequestDispatcher("/liberacaoProdutos.jsp");
                    liberar.forward(request, response);
                }
                
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
        
        // Intância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem;
        
        try {
            
            // Instanciando Cliente
            Produto produto = new Produto();
            
            // Salvando os dados do Produto na variável
            produto.setId(Integer.parseInt(request.getParameter("id")));
            produto.setNome(request.getParameter("nome_produto"));
            produto.setDescricao(request.getParameter("descricao"));
            produto.setPrecoCompra(Double.parseDouble(request.getParameter("preco_compra")));
            produto.setPrecoVenda(Double.parseDouble(request.getParameter("preco_venda")));
            produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade_disponivel")));
            produto.setLiberado(request.getParameter("liberado_venda"));
            produto.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            
            // Salvando Produto no banco de dados
            if (produtoDAO.inserirAlterarProduto(produto)) {
                mensagem = "Produto salvo com sucesso!";
                // Setando sucesso
                request.setAttribute("erro", 0);
            } else {
                mensagem = "Erro ao salvar o produto!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todos os Produtos cadastrados no banco de dados
            ArrayList<Produto> todosProdutos;
            todosProdutos = produtoDAO.pegarTodos();

            // Enviando o ArrayList de todos os Produtos para a view
            request.setAttribute("todosProdutos", todosProdutos);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/produtos.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todos os Produtos cadastrados no banco de dados
            ArrayList<Produto> todosProdutos;
            todosProdutos = produtoDAO.pegarTodos();

            // Enviando o ArrayList de todos os clientes para a view
            request.setAttribute("todosProdutos", todosProdutos);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/produtos.jsp");
            listar.forward(request, response);
        }
    }
}
