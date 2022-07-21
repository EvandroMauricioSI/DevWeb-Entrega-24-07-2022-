/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import formatacao.Formatacao;
import models.compra.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.fornecedor.Fornecedor;
import models.fornecedor.FornecedorDAO;
import models.produto.Produto;
import models.produto.ProdutoDAO;
import models.usuario.Usuario;
import models.usuario.UsuarioDAO;

/**
 *
 * @author joaop
 */
@WebServlet(name = "CompraController", urlPatterns = {"/CompraController"})
public class CompraController extends HttpServlet {
    
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
        
        // Intância do CompraDAO
        CompraDAO compraDAO = new CompraDAO();
        
        // Intância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> todosProdutos;
        
        // Intância do FornecedorDAO
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ArrayList<Fornecedor> todosFornecedores;
        
        // Intância do FornecedorDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> todosCompradores;
        
        // Instanciândo variáveis da Compra
        ArrayList<CompraView> todasComprasView;
        Compra compra = new Compra();
        int compraId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todas as Compras cadastradas no banco de dados
                todasComprasView = compraDAO.pegarCompraView();
                
                // Enviando o ArrayList de todas as compras para a view
                request.setAttribute("todasCompras", todasComprasView);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/compras.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando uma nova Compra
                compra.setId(0);
                compra.setData("");
                
                // Pegando todos os Produtos
                todosProdutos = produtoDAO.pegarTodos();
                request.setAttribute("todosProdutos", todosProdutos);
                
                // Pegando todos os Fornecedores
                todosFornecedores = fornecedorDAO.pegarTodos();
                request.setAttribute("todosFornecedores", todosFornecedores);
                
                // Pegando todos os Compradores
                todosCompradores = usuarioDAO.pegarTodosCompradores();
                request.setAttribute("todosCompradores", todosCompradores);
                
                // Enviando o ArrayList de todas as compras para a view
                request.setAttribute("compra", compra);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirCompra.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando uma Compra específica no banco de dados
                compraId = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.pegarCompra(compraId);
                
                 // Pegando todos os Produtos
                todosProdutos = produtoDAO.pegarTodos();
                request.setAttribute("todosProdutos", todosProdutos);
                
                // Pegando todos os Fornecedores
                todosFornecedores = fornecedorDAO.pegarTodos();
                request.setAttribute("todosFornecedores", todosFornecedores);
                
                // Pegando todos os Compradores
                todosCompradores = usuarioDAO.pegarTodosCompradores();
                request.setAttribute("todosCompradores", todosCompradores);
                
                // Enviando o ArrayList de todas as compras para a view
                request.setAttribute("compra", compra);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarCompra.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando uma Compra específica no banco de dados
                compraId = Integer.parseInt(request.getParameter("id"));
                compraDAO.excluirCompra(compraId);
                
                // Pegando todas as Compras cadastradas no banco de dados
                todasComprasView = compraDAO.pegarCompraView();
                
                // Enviando o ArrayList de todas as compras para a view
                request.setAttribute("todasCompras", todasComprasView);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/compras.jsp");
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
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Intância do CompraDAO
        CompraDAO compraDAO = new CompraDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando a Compra
            Compra compra = new Compra();
            
            // Validando dados recebidos do form
            if (request.getParameter("id_produto") == null) {
                mensagem = "O campo Produto não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("quantidade_compra") == null) {
                mensagem = "O campo Quantidade da Compra não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("data_compra").equals("")) {
                mensagem = "O campo Data da Compra não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("valor_compra") == null) {
                mensagem = "O campo Valor da Compra não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("id_fornecedor") == null) {
                mensagem = "O campo Fornecedor não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("id_comprador") == null) {
                mensagem = "O campo Comprador não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Salvando os dados do Compra na variável
                compra.setId(Integer.parseInt(request.getParameter("id")));
                compra.setQuantidade(Integer.parseInt(request.getParameter("quantidade_compra")));
                compra.setData(formata.formataData(request.getParameter("data_compra"), "banco"));
                compra.setValor(Double.parseDouble(request.getParameter("valor_compra")));
                compra.setIdFornecedor(Integer.parseInt(request.getParameter("id_fornecedor")));
                compra.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
                compra.setIdComprador(Integer.parseInt(request.getParameter("id_comprador")));
                
                // Salvando Compra no banco de dados
                if (compraDAO.inserirAlterarCompra(compra)) {
                    mensagem = "Compra salva com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar a compra no Banco de dados!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }

            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todas as Compras cadastradas no banco de dados
            ArrayList<CompraView> todasComprasView;
            todasComprasView = compraDAO.pegarCompraView();

            // Enviando o ArrayList de todos os compras para a view
            request.setAttribute("todasCompras", todasComprasView);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/compras.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todas as Compras cadastradas no banco de dados
            ArrayList<CompraView> todasComprasView;
            todasComprasView = compraDAO.pegarCompraView();

            // Enviando o ArrayList de todos os compras para a view
            request.setAttribute("todasCompras", todasComprasView);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/compras.jsp");
            listar.forward(request, response);
        }
    }
}
