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
import models.usuario.Usuario;
import models.usuario.UsuarioDAO;
import validacao.ValidaCpf;

/**
 *
 * @author joaop
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

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
        
        // Intância do UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Instanciândo variáveis do Usuario
        ArrayList<Usuario> todosUsuarios;
        Usuario usuario = new Usuario();
        int usuarioId;
        
        // Pegando o ação que deverá ser realizada
        String acao = (String)request.getParameter("acao");
        
        switch (acao) {
            case "listar":
                // Pegando todos os Usuarios cadastrados no banco de dados
                todosUsuarios = usuarioDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os usuarios para a view
                request.setAttribute("todosUsuarios", todosUsuarios);
                RequestDispatcher listar = getServletContext().getRequestDispatcher("/usuarios.jsp");
                listar.forward(request, response);
                
                break;
                
            case "inserir":
                // Criando um novo Usuario
                usuario.setId(0);
                usuario.setNome("");
                usuario.setCpf("");
                usuario.setSenha("");
                usuario.setTipo("");
                
                // Enviando o ArrayList de todos os usuarios para a view
                request.setAttribute("usuario", usuario);
                RequestDispatcher inserir = getServletContext().getRequestDispatcher("/inserirUsuario.jsp");
                inserir.forward(request, response);
                
                break;
                
            case "editar":
                // Pegando um Usuario específico no banco de dados
                usuarioId = Integer.parseInt(request.getParameter("id"));
                usuario = usuarioDAO.pegarUsuario(usuarioId);
                
                // Enviando o ArrayList de todos os usuarios para a view
                request.setAttribute("usuario", usuario);
                RequestDispatcher editar = getServletContext().getRequestDispatcher("/editarUsuario.jsp");
                editar.forward(request, response);
                
                break;
                
            case "excluir":
                // Pegando um Usuario específico no banco de dados
                usuarioId = Integer.parseInt(request.getParameter("id"));
                usuarioDAO.excluirUsuario(usuarioId);
                
                // Pegando todos os Usuarios cadastrados no banco de dados
                todosUsuarios = usuarioDAO.pegarTodos();
                
                // Enviando o ArrayList de todos os usuarios para a view
                request.setAttribute("todosUsuarios", todosUsuarios);
                RequestDispatcher excluir = getServletContext().getRequestDispatcher("/usuarios.jsp");
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
        
        // Intância do UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Setando UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // Variável que recebe a mensagem de resultado da alteração no banco
        String mensagem = "";
        
        try {
            
            // Instanciando Usuario
            Usuario usuario = new Usuario();
            
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
            if (request.getParameter("senha").equals("")) {
                mensagem = "O campo Senha não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            if (request.getParameter("tipo_usuario").equals("")) {
                mensagem = "O campo Tipo Usuário não pode ser vazio!";
                // Setando erro
                request.setAttribute("erro", 1);
            }
            
            // Se não tiver nenhum erro salvar os dados
            if (mensagem.equals("")) {
                // Salvando os dados do Usuario na variável
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setNome(request.getParameter("nome"));
                usuario.setCpf(request.getParameter("cpf"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setTipo(request.getParameter("tipo_usuario"));

                // Salvando Usuario no banco de dados
                if (usuarioDAO.inserirAlterarUsuario(usuario)) {
                    mensagem = "Usuario salvo com sucesso!";
                    // Setando sucesso
                    request.setAttribute("erro", 0);
                } else {
                    mensagem = "Erro ao salvar o usuario!";
                    // Setando erro
                    request.setAttribute("erro", 1);
                }
            }
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            
            // Pegando todos os Usuarios cadastrados no banco de dados
            ArrayList<Usuario> todosUsuarios;
            todosUsuarios = usuarioDAO.pegarTodos();

            // Enviando o ArrayList de todos os usuarios para a view
            request.setAttribute("todosUsuarios", todosUsuarios);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/usuarios.jsp");
            listar.forward(request, response);
            
        } catch(IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro: " + e.getMessage();
            
            System.out.println(mensagem);
            
            // Setando valor da mensagem
            request.setAttribute("mensagem", mensagem);
            // Setando erro
            request.setAttribute("erro", 1);
            
            // Pegando todos os Usuarios cadastrados no banco de dados
            ArrayList<Usuario> todosUsuarios;
            todosUsuarios = usuarioDAO.pegarTodos();

            // Enviando o ArrayList de todos os usuarios para a view
            request.setAttribute("todosUsuarios", todosUsuarios);
            RequestDispatcher listar = getServletContext().getRequestDispatcher("/usuarios.jsp");
            listar.forward(request, response);
        }
    }
}
