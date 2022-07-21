/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.fornecedor.Fornecedor;
import models.fornecedor.FornecedorDAO;
import validacao.ValidaCpf;

/**
 *
 * @author joaop
 */
@WebServlet(name = "FornecedorController", urlPatterns = {"/FornecedorController"})
public class FornecedorController extends HttpServlet {

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
        
        // Intância do FornecedorDAO
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        
        // Instanciândo variáveis do Fornecedor
        ArrayList<Fornecedor> todosFornecedores;
        Fornecedor fornecedor = new Fornecedor();
        int fornecedorId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todos os Fornecedores cadastrados no banco de dados
                todosFornecedores = fornecedorDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os fornecedores para a view
                request.setAttribute("todosFornecedores", todosFornecedores);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/fornecedores.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando um novo Fornecedor
                fornecedor.setId(0);
                fornecedor.setRazaoSocial("");
                fornecedor.setCnpj("");
                fornecedor.setEndereco("");
                fornecedor.setBairro("");
                fornecedor.setCidade("");
                fornecedor.setUf("");
                fornecedor.setCep("");
                fornecedor.setTelefone("");
                fornecedor.setEmail("");
                
                // Enviando o ArrayList de todos os fornecedores para a view
                request.setAttribute("fornecedor", fornecedor);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirFornecedor.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando um Fornecedor específico no banco de dados
                fornecedorId = Integer.parseInt(request.getParameter("id"));
                fornecedor = fornecedorDAO.pegarFornecedor(fornecedorId);
                
                // Enviando o ArrayList de todos os fornecedores para a view
                request.setAttribute("fornecedor", fornecedor);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarFornecedor.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando um Fornecedor específico no banco de dados
                fornecedorId = Integer.parseInt(request.getParameter("id"));
                fornecedorDAO.excluirFornecedor(fornecedorId);
                
                // Pegando todos os Fornecedores cadastrados no banco de dados
                todosFornecedores = fornecedorDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os fornecedores para a view
                request.setAttribute("todosFornecedores", todosFornecedores);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/fornecedores.jsp");
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
        
        // Intância do FornecedorDAO
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando Fornecedor
            Fornecedor fornecedor = new Fornecedor();
            
            // Validando dados recebidos do form
            if (request.getParameter("razao_social").equals("")) {
                mensagem = "O campo Razão Social não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("cnpj").equals("")) {
                mensagem = "O campo CNPJ não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
//            if (ValidaCpf.cpfValido(request.getParameter("cpf"))) {
//                mensagem = "O CPF digitado não é válido!";
//                // Setando erro
//                request.setAttribute("erro", 1);
//            }
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
                // Salvando os dados do Fornecedor na variável
                fornecedor.setId(Integer.parseInt(request.getParameter("id")));
                fornecedor.setRazaoSocial(request.getParameter("razao_social"));
                fornecedor.setCnpj(request.getParameter("cnpj"));
                fornecedor.setEndereco(request.getParameter("endereco"));
                fornecedor.setBairro(request.getParameter("bairro"));
                fornecedor.setCidade(request.getParameter("cidade"));
                fornecedor.setUf(request.getParameter("uf"));
                fornecedor.setCep(request.getParameter("cep"));
                fornecedor.setTelefone(request.getParameter("telefone"));
                fornecedor.setEmail(request.getParameter("email"));

                // Salvando Fornecedor no banco de dados
                if (fornecedorDAO.inserirAlterarFornecedor(fornecedor)) {
                    mensagem = "Fornecedor salvo com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar o fornecedor!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todos os Fornecedores cadastrados no banco de dados
            ArrayList<Fornecedor> todosFornecedores;
            todosFornecedores = fornecedorDAO.pegarTodos();

            // Enviando o ArrayList de todos os fornecedores para a view
            request.setAttribute("todosFornecedores", todosFornecedores);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/fornecedores.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todos os Fornecedores cadastrados no banco de dados
            ArrayList<Fornecedor> todosFornecedores;
            todosFornecedores = fornecedorDAO.pegarTodos();

            // Enviando o ArrayList de todos os fornecedores para a view
            request.setAttribute("todosFornecedores", todosFornecedores);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/fornecedores.jsp");
            listar.forward(request, response);
        }
    }
}
