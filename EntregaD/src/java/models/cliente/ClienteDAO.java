/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.cliente;

import models.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author joaop
 */
@WebServlet(name = "ClienteDAO", urlPatterns = {"/ClienteDAO"})
public class ClienteDAO extends HttpServlet {

    // Atributo do nome da tabela de Clientes no banco de dados
    private String tabelaCliente = "clientes";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor do ClienteDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public ClienteDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão ClienteDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todos os Clientes cadastrados.
     * 
     * @return Retorna um ArrayList com todos os Clientes cadastrados no banco.
     */
    public ArrayList<Cliente> pegarTodos() {
        
        // Lista que vai armazenar todos os clientes cadastrados no banco de dados
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCliente);
            
            while (rs.next()) {
                
                // Instânciando um novo cliente
                Cliente cliente = new Cliente();
                
                // Setando os atributos do cliente
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                
                // Adicionando o cliente na lista 
                listaClientes.add(cliente);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os clientes 
        return listaClientes;
    }
    
    /**
     * @brief Método que busca no banco de dados um Cliente específico cadastrado.
     * 
     * @param clienteId
     * @return Retorna um Cliente cadastrado no banco.
     */
    public Cliente pegarCliente(int clienteId) {
        
        // Variável que vai armazenar o cliente específico caso exista
        Cliente cliente = new Cliente();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCliente + " WHERE id = " + clienteId);
            
            if (rs.next()) {
                
                // Setando os atributos do cliente
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna cliente específico do banco de dados
        return cliente;
    }
    
    /**
     * @brief Método que insere ou atuliza um Cliente no banco de dados.
     * 
     * @param cliente
     * @return Retorna True caso o cliente seja inserido ou atulizado e False caso não.
     */
    public boolean inserirAlterarCliente(Cliente cliente) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            
            if (cliente.getId() == 0) {
                query = "INSERT INTO " + this.tabelaCliente + " (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaCliente + " SET nome=?, cpf=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getBairro());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getUf());
            ps.setString(7, cliente.getCep());
            ps.setString(8, cliente.getTelefone());
            ps.setString(9, cliente.getEmail());
            
            if (cliente.getId() != 0) {
                ps.setInt(10, cliente.getId());
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
     * @brief Método que excluir do banco de dados um Cliente específico cadastrado.
     * 
     * @param clienteId
     * @return Retorna True caso o cliente seja excluído e False caso não.
     */
    public boolean excluirCliente(int clienteId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaCliente + " WHERE id = " + clienteId;
            
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
