/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import models.Conexao;

/**
 *
 * @author joaop
 */
@WebServlet(name = "UsuarioDAO", urlPatterns = {"/UsuarioDAO"})
public class UsuarioDAO extends HttpServlet {

   // Constantes do Tipo de Usuário
   public final String TIPO_ADMINISTRADOR = "0";
   public final String TIPO_VENDEDOR = "1";
   public final String TIPO_COMPRADOR = "2";
   
   // Atributo do nome da tabela de Usuários no banco de dados
    private String tabelaUsuario = "usuarios";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor do UsuárioDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public UsuarioDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão UsuarioDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todos os Usuários cadastrados.
     * 
     * @return Retorna um ArrayList com todos os Usuários cadastrados no banco.
     */
    public ArrayList<Usuario> pegarTodos() {
        
        // Lista que vai armazenar todos os Usuários cadastrados no banco de dados
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario);
            
            while (rs.next()) {
                
                // Instânciando um novo Usuário
                Usuario usuario = new Usuario();
                
                // Setando os atributos do cliente
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                
                // Adicionando o Usuário na lista 
                listaUsuarios.add(usuario);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os clientes 
        return listaUsuarios;
    }
    
    /**
     * @brief Método que busca no banco de dados todos os Usuários cadastrados que são Vendedores.
     * 
     * @return Retorna um ArrayList com todos os Usuários que são Vendedores cadastrados no banco.
     */
    public ArrayList<Usuario> pegarTodosVendedores() {
        
        // Lista que vai armazenar todos os Vendedores cadastrados no banco de dados
        ArrayList<Usuario> listaVendedores = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario + " WHERE tipo = " + this.TIPO_VENDEDOR);
            
            while (rs.next()) {
                
                // Instânciando um novo Usuário
                Usuario vendedor = new Usuario();
                
                // Setando os atributos do Vendedor
                vendedor.setId(rs.getInt("id"));
                vendedor.setNome(rs.getString("nome"));
                vendedor.setCpf(rs.getString("cpf"));
                vendedor.setSenha(rs.getString("senha"));
                vendedor.setTipo(rs.getString("tipo"));
                
                // Adicionando o Vendedor na lista 
                listaVendedores.add(vendedor);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Vendedores 
        return listaVendedores;
    }
    
    /**
     * @brief Método que busca no banco de dados todos os Usuários cadastrados que são Compradores.
     * 
     * @return Retorna um ArrayList com todos os Usuários que são Compradores cadastrados no banco.
     */
    public ArrayList<Usuario> pegarTodosCompradores() {
        
        // Lista que vai armazenar todos os Compradores cadastrados no banco de dados
        ArrayList<Usuario> listaCompradores = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario + " WHERE tipo = " + this.TIPO_COMPRADOR);
            
            while (rs.next()) {
                
                // Instânciando um novo Usuário
                Usuario comprador = new Usuario();
                
                // Setando os atributos do Comprador
                comprador.setId(rs.getInt("id"));
                comprador.setNome(rs.getString("nome"));
                comprador.setCpf(rs.getString("cpf"));
                comprador.setSenha(rs.getString("senha"));
                comprador.setTipo(rs.getString("tipo"));
                
                // Adicionando o Comprador na lista 
                listaCompradores.add(comprador);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Compradores 
        return listaCompradores;
    }
    
    /**
     * @brief Método que busca no banco de dados todos os Usuários cadastrados que são Administradores.
     * 
     * @return Retorna um ArrayList com todos os Usuários que são Administradores cadastrados no banco.
     */
    public ArrayList<Usuario> pegarTodosAdministradores() {
        
        // Lista que vai armazenar todos os Administradores cadastrados no banco de dados
        ArrayList<Usuario> listaAdministradores = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario + " WHERE tipo = " + this.TIPO_ADMINISTRADOR);
            
            while (rs.next()) {
                
                // Instânciando um novo Usuário
                Usuario administrador = new Usuario();
                
                // Setando os atributos do Administrador
                administrador.setId(rs.getInt("id"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getString("cpf"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setTipo(rs.getString("tipo"));
                
                // Adicionando o Administrador na lista 
                listaAdministradores.add(administrador);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Administradores 
        return listaAdministradores;
    }
    
    /**
     * @brief Método que busca no banco de dados um Usuário específico cadastrado.
     * 
     * @param usuarioId
     * @return Retorna um Usuário cadastrado no banco.
     */
    public Usuario pegarUsuario(int usuarioId) {
        
        // Variável que vai armazenar o Usuário específico caso exista
        Usuario usuario = new Usuario();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario + " WHERE id = " + usuarioId);
            
            if (rs.next()) {
                
                // Setando os atributos do cliente
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Usuário específico do banco de dados
        return usuario;
    }
    
    /**
     * @brief Método que busca no banco de dados um Usuário específico cadastrado.
     * 
     * @param cpf
     * @param senha
     * @param tipo
     * @return Retorna um Usuário cadastrado no banco.
     */
    public Usuario fazerLogin(String cpf, String senha, int tipo) {
        
        // Variável que vai armazenar o Usuário específico caso exista
        Usuario usuario = new Usuario();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaUsuario + " WHERE cpf = '" + cpf + "' AND senha = '" + senha + "' AND tipo = '" + tipo + "'");
            
            if (rs.next()) {
                
                // Setando os atributos do cliente
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Usuário específico do banco de dados
        return usuario;
    }
    
    /**
     * @brief Método que insere ou atuliza um Usuário no banco de dados.
     * 
     * @param usuario
     * @return Retorna True caso o Usuário seja inserido ou atulizado e False caso não.
     */
    public boolean inserirAlterarUsuario(Usuario usuario) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            
            if (usuario.getId() == 0) {
                query = "INSERT INTO " + this.tabelaUsuario + " (nome, cpf, senha, tipo) VALUES (?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaUsuario + " SET nome=?, cpf=?, senha=?, tipo=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo());
            
            if (usuario.getId() != 0) {
                ps.setInt(5, usuario.getId());
            }
            
            ps.executeUpdate();
            
            // Caso a exclusão se realize
            return true;
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * @brief Método que excluir do banco de dados um Usuário específico cadastrado.
     * 
     * @param usuarioId
     * @return Retorna True caso o Usuário seja excluído e False caso não.
     */
    public boolean excluirUsuario(int usuarioId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaUsuario + " WHERE id = " + usuarioId;
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.execute();
            
            // Caso a exclusão se realize
            return true;
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
            return false;
        }
    }
   
}
