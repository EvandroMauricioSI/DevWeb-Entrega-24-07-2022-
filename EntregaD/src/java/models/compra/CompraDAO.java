/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.compra;

import models.compra.*;
import formatacao.Formatacao;
import models.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import models.produto.ProdutoDAO;

/**
 *
 * @author joaop
 */
@WebServlet(name = "CompraDAO", urlPatterns = {"/CompraDAO"})
public class CompraDAO extends HttpServlet {

    // Atributo do nome da tabela de Compras no banco de dados
    private String tabelaCompra = "compras";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor da CompraDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public CompraDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão CompraDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todas as Compras cadastradas.
     * 
     * @return Retorna um ArrayList com todas as Compras cadastradas no banco.
     */
    public ArrayList<Compra> pegarTodas() {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Lista que vai armazenar todas as compras cadastradas no banco de dados
        ArrayList<Compra> listaCompras = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCompra);
            
            while (rs.next()) {
                
                // Instânciando uma nova compra
                Compra compra = new Compra();
                
                // Setando os atributos da compra
                compra.setId(rs.getInt("id"));
                compra.setQuantidade(rs.getInt("quantidade_compra"));
                compra.setData(formata.formataData(rs.getString("data_compra"), "brasil"));
                compra.setValor(rs.getDouble("valor_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setIdProduto(rs.getInt("id_produto"));
                compra.setIdComprador(rs.getInt("id_comprador"));
                
                // Adicionando a compra na lista 
                listaCompras.add(compra);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todas as compras
        return listaCompras;
    }
    
    /**
     * @brief Método que busca no banco de dados uma Compra específica cadastrada.
     * 
     * @param compraId
     * @return Retorna uma Compra cadastrada no banco.
     */
    public Compra pegarCompra(int compraId) {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Variável que vai armazenar a compra específica caso exista
        Compra compra = new Compra();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaCompra + " WHERE id = " + compraId);
            
            if (rs.next()) {
                
                // Setando os atributos do cliente
                compra.setId(rs.getInt("id"));
                compra.setQuantidade(rs.getInt("quantidade_compra"));
                compra.setData(formata.formataData(rs.getString("data_compra"), "brasil"));
                compra.setValor(rs.getDouble("valor_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setIdProduto(rs.getInt("id_produto"));
                compra.setIdComprador(rs.getInt("id_comprador"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna compra específico do banco de dados
        return compra;
    }
    
    /**
     * @brief Método que busca no banco de dados todas as Compras cadastradas com os joins com as tabelas de Cliente, Produto e Usuário.
     * 
     * @return Retorna um ArrayList com todas as Compras cadastradas no banco e com os nomes dos Clientes, Produtos e Usuários.
     */
    public ArrayList<CompraView> pegarCompraView() {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Lista que vai armazenar todas as compras cadastradas no banco de dados
        ArrayList<CompraView> listaCompras = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT c.id, c.quantidade_compra, c.data_compra, c.valor_compra, c.id_fornecedor, f.razao_social as nome_fornecedor, c.id_produto, p.nome_produto, c.id_comprador, u.nome as nome_comprador FROM compras as c INNER JOIN fornecedores as f ON c.id_fornecedor = f.id INNER JOIN produtos AS p ON c.id_produto = p.id INNER JOIN usuarios AS u ON c.id_comprador = u.id");
            
            while (rs.next()) {
                
                // Instânciando uma nova compra
                CompraView compra = new CompraView();
                
                // Setando os atributos do cliente
                compra.setId(rs.getInt("id"));
                compra.setQuantidade(rs.getInt("quantidade_compra"));
                compra.setData(formata.formataData(rs.getString("data_compra"), "brasil"));
                compra.setValor(rs.getDouble("valor_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setNomeFornecedor(rs.getString("nome_fornecedor"));
                compra.setIdProduto(rs.getInt("id_produto"));
                compra.setNomeProduto(rs.getString("nome_produto"));
                compra.setIdComprador(rs.getInt("id_comprador"));               
                compra.setNomeComprador(rs.getString("nome_comprador"));
                
                // Adicionando a compra na lista 
                listaCompras.add(compra);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os clientes 
        return listaCompras;
    }
    
    /**
     * @brief Método que insere ou atuliza uma Compra no banco de dados.
     * 
     * @param compra
     * @return Retorna True caso a compra seja inserida ou atulizada e False caso não.
     */
    public boolean inserirAlterarCompra(Compra compra) {
        
        // Instância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            int edit = 0;
            int quantidadeCompra = 0;
            
            if (compra.getId() == 0) {
                query = "INSERT INTO " + this.tabelaCompra + " (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_comprador) VALUES (?,?,?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaCompra + " SET quantidade_compra=?, data_compra=?, valor_compra=?, id_fornecedor=?, id_produto=?, id_comprador=? WHERE id=?";
                
                // Flagando o Edit
                edit = 1;
                
                // Pegando a quantidade de compra da compra que será editada
                Statement stmt = conexao.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT quantidade_compra FROM " + this.tabelaCompra + " WHERE id = " + compra.getId());

                if (rs.next()) {

                    // Setando os atributos de quantidade
                    quantidadeCompra = rs.getInt("quantidade_compra");
                }
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, compra.getQuantidade());
            ps.setString(2, compra.getData());
            ps.setDouble(3, compra.getValor());
            ps.setInt(4, compra.getIdFornecedor());
            ps.setInt(5, compra.getIdProduto());
            ps.setInt(6, compra.getIdComprador());
            
            if (compra.getId() != 0) {
                ps.setInt(7, compra.getId());
            }
            
            ps.executeUpdate();
            
            // Atualizando quantidade na tabela de Produtos
            produtoDAO.comprarProduto(compra.getIdProduto(), compra.getQuantidade(), compra.getValor(), quantidadeCompra, edit);
            
            // Caso a exclusão se realize
            return true;
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * @brief Método que excluir do banco de dados uma Compra específica cadastrada.
     * 
     * @param compraId
     * @return Retorna True caso a compra seja excluída e False caso não.
     */
    public boolean excluirCompra(int compraId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaCompra + " WHERE id = " + compraId;
            
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
