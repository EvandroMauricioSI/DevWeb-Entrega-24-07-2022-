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

/**
 *
 * @author joaop
 */
@WebServlet(name = "CategoriaProdutoController", urlPatterns = {"/CategoriaProdutoController"})
public class CategoriaProdutoController extends HttpServlet {

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
        
        // Intância do CategoriaProdutoDAO
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
        
        // Instanciândo variáveis da Categoria Produto
        ArrayList<CategoriaProduto> todasCategoriaProdutos;
        CategoriaProduto categoria = new CategoriaProduto();
        int categoriaId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todas as Categorias cadastradas no banco de dados
                todasCategoriaProdutos = categoriaDAO.pegarTodas();
                
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("todasCategorias", todasCategoriaProdutos);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/categorias.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando uma nova Categoria Produto
                categoria.setId(0);
                categoria.setCategoria("");
                
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("categoria", categoria);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirCategoriaProduto.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando uma Categoria Produto específica no banco de dados
                categoriaId = Integer.parseInt(request.getParameter("id"));
                categoria = categoriaDAO.pegarCategoriaProduto(categoriaId);
                
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("categoria", categoria);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarCategoriaProduto.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando uma Categoria Produto específica no banco de dados
                categoriaId = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.excluirCategoriaProduto(categoriaId);
                
                // Pegando todas as Categorias cadastradas no banco de dados
                todasCategoriaProdutos = categoriaDAO.pegarTodas();
                
                // Enviando o ArrayList de todas as categorias para a view
                request.setAttribute("todasCategorias", todasCategoriaProdutos);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/categorias.jsp");
                excluir.forward(request, response);
                
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
        
        // Intância do CategoriaProdutoDAO
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando a Categoria Produto
            CategoriaProduto categoria = new CategoriaProduto();
            
            // Validando dados recebidos do form
            if (request.getParameter("categoria") == null) {
                mensagem = "O campo Categoria não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Salvando os dados do Categoria Produto na variável
                categoria.setId(Integer.parseInt(request.getParameter("id")));
                categoria.setCategoria(request.getParameter("categoria"));
                
                // Salvando Categoria Produto no banco de dados
                if (categoriaDAO.inserirAlterarCategoriaProduto(categoria)) {
                    mensagem = "Categoria do Produto salva com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar a Categoria do Produto no Banco de dados!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }

            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todas as Categorias cadastradas no banco de dados
            ArrayList<CategoriaProduto> todasCategoriaProdutos = categoriaDAO.pegarTodas();

            // Enviando o ArrayList de todas as categorias para a view
            request.setAttribute("todasCategorias", todasCategoriaProdutos);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/categorias.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
           // Pegando todas as Categorias cadastradas no banco de dados
            ArrayList<CategoriaProduto> todasCategoriaProdutos = categoriaDAO.pegarTodas();

            // Enviando o ArrayList de todas as categorias para a view
            request.setAttribute("todasCategorias", todasCategoriaProdutos);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/categorias.jsp");
            listar.forward(request, response);
        }
    }
}