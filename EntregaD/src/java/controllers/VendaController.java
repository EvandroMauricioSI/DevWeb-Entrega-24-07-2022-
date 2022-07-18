/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import formatacao.Formatacao;
import models.venda.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.cliente.Cliente;
import models.cliente.ClienteDAO;
import models.produto.Produto;
import models.produto.ProdutoDAO;
import models.usuario.Usuario;
import models.usuario.UsuarioDAO;

/**
 *
 * @author joaop
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {
    
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
        ArrayList<Produto> todosProdutos;
        
        // Intância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> todosClientes;
        
        // Intância do ClienteDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> todosVendedores;
        
        // Instanciândo variáveis da Venda
        ArrayList<Venda> todasVendas;
        ArrayList<VendaView> todasVendasView;
        Venda venda = new Venda();
        int vendaId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todas as Vendas cadastradas no banco de dados
                todasVendasView = vendaDAO.pegarVendaView();
                
                // Enviando o ArrayList de todas as vendas para a view
                request.setAttribute("todasVendas", todasVendasView);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/vendas.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando uma nova Venda
                venda.setId(0);
                venda.setData("");
                
                // Pegando todos os Produtos
                todosProdutos = produtoDAO.pegarTodosDisponiveis();
                request.setAttribute("todosProdutos", todosProdutos);
                
                // Pegando todos os Clientes
                todosClientes = clienteDAO.pegarTodos();
                request.setAttribute("todosClientes", todosClientes);
                
                // Pegando todos os Vendedores
                todosVendedores = usuarioDAO.pegarTodosVendedores();
                request.setAttribute("todosVendedores", todosVendedores);
                
                // Enviando o ArrayList de todas as vendas para a view
                request.setAttribute("venda", venda);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirVenda.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando uma Venda específica no banco de dados
                vendaId = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.pegarVenda(vendaId);
                
                 // Pegando todos os Produtos
                todosProdutos = produtoDAO.pegarTodos();
                request.setAttribute("todosProdutos", todosProdutos);
                
                // Pegando todos os Clientes
                todosClientes = clienteDAO.pegarTodos();
                request.setAttribute("todosClientes", todosClientes);
                
                // Pegando todos os Vendedores
                todosVendedores = usuarioDAO.pegarTodosVendedores();
                request.setAttribute("todosVendedores", todosVendedores);
                
                // Enviando o ArrayList de todas as vendas para a view
                request.setAttribute("venda", venda);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarVenda.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando uma Venda específica no banco de dados
                vendaId = Integer.parseInt(request.getParameter("id"));
                vendaDAO.excluirVenda(vendaId);
                
                // Pegando todas as Vendas cadastradas no banco de dados
                todasVendasView = vendaDAO.pegarVendaView();
                
                // Enviando o ArrayList de todas as vendas para a view
                request.setAttribute("todasVendas", todasVendasView);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/vendas.jsp");
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
        
        // Intância do VendaDAO
        VendaDAO vendaDAO = new VendaDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando a Venda
            Venda venda = new Venda();
            
            // Validando dados recebidos do form
            if (request.getParameter("id_produto") == null) {
                mensagem = "O campo Produto não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("quantidade_venda") == null) {
                mensagem = "O campo Quantidade da Venda não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("data_venda").equals("")) {
                mensagem = "O campo Data da Venda não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("valor_venda") == null) {
                mensagem = "O campo Valor da Venda não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("id_cliente") == null) {
                mensagem = "O campo Cliente não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("id_vendedor") == null) {
                mensagem = "O campo Vendedor não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Salvando os dados do Venda na variável
                venda.setId(Integer.parseInt(request.getParameter("id")));
                venda.setQuantidade(Integer.parseInt(request.getParameter("quantidade_venda")));
                venda.setData(formata.formataData(request.getParameter("data_venda"), "banco"));
                venda.setValor(Double.parseDouble(request.getParameter("valor_venda")));
                venda.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));
                venda.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
                venda.setIdVendedor(Integer.parseInt(request.getParameter("id_vendedor")));
                
                // Salvando Venda no banco de dados
                if (vendaDAO.inserirAlterarVenda(venda)) {
                    mensagem = "Venda salva com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar a venda no Banco de dados!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }

            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todas as Vendas cadastradas no banco de dados
            ArrayList<VendaView> todasVendasView;
            todasVendasView = vendaDAO.pegarVendaView();

            // Enviando o ArrayList de todos os vendas para a view
            request.setAttribute("todasVendas", todasVendasView);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/vendas.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todas as Vendas cadastradas no banco de dados
            ArrayList<VendaView> todasVendasView;
            todasVendasView = vendaDAO.pegarVendaView();

            // Enviando o ArrayList de todos os vendas para a view
            request.setAttribute("todasVendas", todasVendasView);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/vendas.jsp");
            listar.forward(request, response);
        }
    }
}
