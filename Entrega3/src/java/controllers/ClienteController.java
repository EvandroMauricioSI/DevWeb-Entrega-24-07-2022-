/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.cliente.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validacao.ValidaCpf;

/**
 *
 * @author joaop
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

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
        
        // Intância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // Instanciândo variáveis do Cliente
        ArrayList<Cliente> todosClientes;
        Cliente cliente = new Cliente();
        int clienteId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todos os Clientes cadastrados no banco de dados
                todosClientes = clienteDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("todosClientes", todosClientes);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/clientes.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando um novo Cliente
                cliente.setId(0);
                cliente.setNome("");
                cliente.setCpf("");
                cliente.setEndereco("");
                cliente.setBairro("");
                cliente.setCidade("");
                cliente.setUf("");
                cliente.setCep("");
                cliente.setTelefone("");
                cliente.setEmail("");
                
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("cliente", cliente);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirCliente.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando um Cliente específico no banco de dados
                clienteId = Integer.parseInt(request.getParameter("id"));
                cliente = clienteDAO.pegarCliente(clienteId);
                
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("cliente", cliente);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarCliente.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando um Cliente específico no banco de dados
                clienteId = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluirCliente(clienteId);
                
                // Pegando todos os Clientes cadastrados no banco de dados
                todosClientes = clienteDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os clientes para a view
                request.setAttribute("todosClientes", todosClientes);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/clientes.jsp");
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
        
        // Intância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando Cliente
            Cliente cliente = new Cliente();
            
            // Validando dados recebidos do form
            if (request.getParameter("nome").equals("")) {
                mensagem = "O campo Nome não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("cpf").equals("")) {
                mensagem = "O campo CPF não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (!ValidaCpf.cpfValido(request.getParameter("cpf"))) {
                mensagem = "O CPF digitado não é válido!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("endereco").equals("")) {
                mensagem = "O campo Endereço não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("bairro").equals("")) {
                mensagem = "O campo Bairro não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("cidade").equals("")) {
                mensagem = "O campo Cidade não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("uf").equals("")) {
                mensagem = "O campo UF não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("cep").equals("")) {
                mensagem = "O campo CEP não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("telefone").equals("")) {
                mensagem = "O campo Telefone não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("email").equals("")) {
                mensagem = "O campo Email não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Salvando os dados do Cliente na variável
                cliente.setId(Integer.parseInt(request.getParameter("id")));
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setEndereco(request.getParameter("endereco"));
                cliente.setBairro(request.getParameter("bairro"));
                cliente.setCidade(request.getParameter("cidade"));
                cliente.setUf(request.getParameter("uf"));
                cliente.setCep(request.getParameter("cep"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setEmail(request.getParameter("email"));

                // Salvando Cliente no banco de dados
                if (clienteDAO.inserirAlterarCliente(cliente)) {
                    mensagem = "Cliente salvo com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar o cliente!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todos os Clientes cadastrados no banco de dados
            ArrayList<Cliente> todosClientes;
            todosClientes = clienteDAO.pegarTodos();

            // Enviando o ArrayList de todos os clientes para a view
            request.setAttribute("todosClientes", todosClientes);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/clientes.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todos os Clientes cadastrados no banco de dados
            ArrayList<Cliente> todosClientes;
            todosClientes = clienteDAO.pegarTodos();

            // Enviando o ArrayList de todos os clientes para a view
            request.setAttribute("todosClientes", todosClientes);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/clientes.jsp");
            listar.forward(request, response);
        }
    }
}
