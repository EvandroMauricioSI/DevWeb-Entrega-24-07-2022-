/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.usuario.Usuario;
import models.usuario.UsuarioDAO;
import validacao.ValidaCpf;

/**
 *
 * @author joaop
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        
        // Recebendo a sessão
        HttpSession session = request.getSession();
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "login":
                
                // Conferindo se já existe uma sessão aberta
                if (session.getAttribute("usuario") != null) {
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    
                    switch (usuario.getTipo()) {
                        case "0": // Administrador
                            // Login já realizado! Enviando para a Home do Administrador
                            session.setAttribute("usuario", usuario);
                            RequestDispatcher loginAdministrador = getServletContext().getRequestDispatcher("/homeAdministrador.jsp");
                            loginAdministrador.forward(request, response);
                            break;
                        case "1": // Vendedor
                            // Login já realizado! Enviando para a Home do Vendedor
                            session.setAttribute("usuario", usuario);
                            RequestDispatcher loginVendedor = getServletContext().getRequestDispatcher("/homeVendedor.jsp");
                            loginVendedor.forward(request, response);
                            break;
                        case "2": // Comprador
                            // Login já realizado! Enviando para a Home do Comprador
                            session.setAttribute("usuario", usuario);
                            RequestDispatcher loginComprador = getServletContext().getRequestDispatcher("/homeComprador.jsp");
                            loginComprador.forward(request, response);
                            break;
                    }
                }
                
                // Redirecionando para tela de Login
                RequestDispatcher login = getServletContext().getRequestDispatcher("/login.jsp");
                login.forward(request, response);
                
                break;
                
            case "logout":
                
                // Fechando a sessão e fazendo o Logout
                session.invalidate();
                
                // Login Inválido, enviando para tela de Login novamente
                RequestDispatcher logout = getServletContext().getRequestDispatcher("/index.jsp");
                logout.forward(request, response);
                
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
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            // Instância do UsuárioDAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            // Validando dados recebidos do form
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
            if (request.getParameter("senha").equals("")) {
                mensagem = "O campo Senha não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (Integer.parseInt(request.getParameter("tipoUsuario")) != 2 && Integer.parseInt(request.getParameter("tipoUsuario")) != 1 && Integer.parseInt(request.getParameter("tipoUsuario")) != 0) {
                mensagem = "O campo Tipo Usuário é inválido!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Pegando os dados de Login
                String cpf = request.getParameter("cpf");
                String senha = request.getParameter("senha");
                int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
                
                // Buscando o Usuário no banco de dados
                Usuario usuario = usuarioDAO.fazerLogin(cpf, senha, tipoUsuario);
                
                // Se o Usuário existir fazer o Login dependendo do cargo dele
                if (usuario.getCpf() != null) {
                    HttpSession session = request.getSession();
                    switch (usuario.getTipo()) {
                        case "0": // Administrador
                            // Login realizado! Enviando para a Home do Administrador
                            session.setAttribute("usuario", usuario);
                            RequestDispatcher loginAdministrador = getServletContext().getRequestDispatcher("/homeAdministrador.jsp");
                            loginAdministrador.forward(request, response);
                            break;
                        case "1": // Vendedor
                            // Login realizado! Enviando para a Home do Vendedor
                            session.setAttribute("usuario", usuario);
                            request.setAttribute("usuario", usuario);
                            RequestDispatcher loginVendedor = getServletContext().getRequestDispatcher("/homeVendedor.jsp");
                            loginVendedor.forward(request, response);
                            break;
                        case "2": // Comprador
                            // Login realizado! Enviando para a Home do Comprador
                            session.setAttribute("usuario", usuario);
                            RequestDispatcher loginComprador = getServletContext().getRequestDispatcher("/homeComprador.jsp");
                            loginComprador.forward(request, response);
                            break;
                    }
                } else {
                    mensagem = "Login inválido! Vefirique os dados e tente novamente!";
                    // Setando erro
                    request.setAttribute("erro", 1);  
                }
            }
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);

            // Login Inválido, enviando para tela de Login novamente
            RequestDispatcher loginInvalido = getServletContext().getRequestDispatcher("/login.jsp");
            loginInvalido.forward(request, response);
        } catch(IOException | NumberFormatException | ServletException e) {
            System.out.println("Erro Controller Home Vendedor - " + e.getMessage());
        }
    }
}
