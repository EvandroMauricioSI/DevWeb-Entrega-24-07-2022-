/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.categoria;

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
@WebServlet(name = "CategoriaProdutoDAO", urlPatterns = {"/CategoriaProdutoDAO"})
public class CategoriaProdutoDAO extends HttpServlet {

    // Atributo do nome da tabela de Categoria Produto no banco de dados
    private String tabelaCategoriaProduto = "categorias";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor da CategoriaProdutoDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public CategoriaProdutoDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão CategoriaProdutoDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todas as Categorias de Produto cadastradas.
     * 
     * @return Retorna um ArrayList com todas as Categorias de Produto cadastradas no banco.
     */
    public ArrayList<CategoriaProduto> pegarTodas() {
        
        // Lista que vai armazenar todas as vendas Categorias de Produto no banco de dados
        ArrayList<CategoriaProduto> listaCategoriasProduto = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCategoriaProduto);
            
            while (rs.next()) {
                
                // Instânciando uma nova Categoria de Produto
                CategoriaProduto categoria = new CategoriaProduto();
                
                // Setando os atributos da Categoria de Produto
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("nome_categoria"));
                
                // Adicionando a Categoria de Produto na lista 
                listaCategoriasProduto.add(categoria);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todas as Categorias de Produto
        return listaCategoriasProduto;
    }
    
    /**
     * @brief Método que busca no banco de dados uma Categoria de Produto específica cadastrada.
     * 
     * @param categoriaId
     * @return Retorna uma Categoria de Produto cadastrada no banco.
     */
    public CategoriaProduto pegarCategoriaProduto(int categoriaId) {
        
        // Variável que vai armazenar a Categoria de Produto específica caso exista
        CategoriaProduto categoria = new CategoriaProduto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCategoriaProduto + " WHERE id = " + categoriaId);
            
            if (rs.next()) {
                
                // Setando os atributos da Categoria de Produto
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("nome_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna uma Categoria de Produto específico do banco de dados
        return categoria;
    }
    
    /**
     * @brief Método que insere ou atuliza uma Categoria de Produto no banco de dados.
     * 
     * @param categoria
     * @return Retorna True caso a Categoria de Produto seja inserida ou atulizada e False caso não.
     */
    public boolean inserirAlterarCategoriaProduto(CategoriaProduto categoria) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            
            if (categoria.getId() == 0) {
                query = "INSERT INTO " + this.tabelaCategoriaProduto + " (nome_categoria) VALUES (?)";
            } else {
                query = "UPDATE " + this.tabelaCategoriaProduto + " SET nome_categoria=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, categoria.getCategoria());
            
            if (categoria.getId() != 0) {
                ps.setInt(2, categoria.getId());
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
     * @brief Método que excluir do banco de dados uma Categoria de Produto específica cadastrada.
     * 
     * @param categoriaId
     * @return Retorna True caso a Categoria de Produto seja excluída e False caso não.
     */
    public boolean excluirCategoriaProduto(int categoriaId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaCategoriaProduto + " WHERE id = " + categoriaId;
            
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
