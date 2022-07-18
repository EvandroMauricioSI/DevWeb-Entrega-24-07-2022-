/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.fornecedor;

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
@WebServlet(name = "FornecedorDAO", urlPatterns = {"/FornecedorDAO"})
public class FornecedorDAO extends HttpServlet {

    // Atributo do nome da tabela de Fornecedors no banco de dados
    private String tabelaFornecedor = "fornecedores";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor do FornecedorDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public FornecedorDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão FornecedorDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todos os Fornecedors cadastrados.
     * 
     * @return Retorna um ArrayList com todos os Fornecedors cadastrados no banco.
     */
    public ArrayList<Fornecedor> pegarTodos() {
        
        // Lista que vai armazenar todos os fornecedores cadastrados no banco de dados
        ArrayList<Fornecedor> listaFornecedors = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaFornecedor);
            
            while (rs.next()) {
                
                // Instânciando um novo fornecedor
                Fornecedor fornecedor = new Fornecedor();
                
                // Setando os atributos do fornecedor
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                
                // Adicionando o fornecedor na lista 
                listaFornecedors.add(fornecedor);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os fornecedores 
        return listaFornecedors;
    }
    
    /**
     * @brief Método que busca no banco de dados um Fornecedor específico cadastrado.
     * 
     * @param fornecedorId
     * @return Retorna um Fornecedor cadastrado no banco.
     */
    public Fornecedor pegarFornecedor(int fornecedorId) {
        
        // Variável que vai armazenar o fornecedor específico caso exista
        Fornecedor fornecedor = new Fornecedor();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaFornecedor + " WHERE id = " + fornecedorId);
            
            if (rs.next()) {
                
                // Setando os atributos do fornecedor
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna fornecedor específico do banco de dados
        return fornecedor;
    }
    
    /**
     * @brief Método que insere ou atuliza um Fornecedor no banco de dados.
     * 
     * @param fornecedor
     * @return Retorna True caso o fornecedor seja inserido ou atulizado e False caso não.
     */
    public boolean inserirAlterarFornecedor(Fornecedor fornecedor) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            
            if (fornecedor.getId() == 0) {
                query = "INSERT INTO " + this.tabelaFornecedor + " (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaFornecedor + " SET razao_social=?, cnpj=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, fornecedor.getRazaoSocial());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCidade());
            ps.setString(6, fornecedor.getUf());
            ps.setString(7, fornecedor.getCep());
            ps.setString(8, fornecedor.getTelefone());
            ps.setString(9, fornecedor.getEmail());
            
            if (fornecedor.getId() != 0) {
                ps.setInt(10, fornecedor.getId());
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
     * @brief Método que excluir do banco de dados um Fornecedor específico cadastrado.
     * 
     * @param fornecedorId
     * @return Retorna True caso o fornecedor seja excluído e False caso não.
     */
    public boolean excluirFornecedor(int fornecedorId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaFornecedor + " WHERE id = " + fornecedorId;
            
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
