/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.produto;

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
@WebServlet(name = "ProdutoDAO", urlPatterns = {"/ProdutoDAO"})
public class ProdutoDAO extends HttpServlet {
    
    // Constantes de Produto liberado e não liberado para compra 
    private final String PRODUTO_LIBERADO = "S";
    private final String PRODUTO_NAO_LIBERADO = "N";

    // Atributo do nome da tabela de Produtos no banco de dados
    private String tabelaProduto = "produtos";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor do ProdutoDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public ProdutoDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão ProdutoDAO!");
        }
    }

    /**
     * @brief Método que busca no banco de dados todos os Produtos cadastrados.
     * 
     * @return Retorna um ArrayList com todos os Produtos cadastrados no banco.
     */
    public ArrayList<Produto> pegarTodos() {
        
        // Lista que vai armazenar todos os Produtos cadastrados no banco de dados
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto);
            
            while (rs.next()) {
                
                // Instânciando um novo Produto
                Produto produto = new Produto();
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                
                // Adicionando o Produto na lista 
                listaProdutos.add(produto);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Produtos 
        return listaProdutos;
    }
    
    /**
     * @brief Método que busca no banco de dados todos os Produtos cadastrados.
     * 
     * @return Retorna um ArrayList com todos os Produtos cadastrados no banco.
     */
    public ArrayList<Produto> pegarTodosDisponiveis() {
        
        // Lista que vai armazenar todos os Produtos cadastrados no banco de dados
        ArrayList<Produto> listaProdutosDisponiveis = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " WHERE liberado_venda = '" + this.PRODUTO_LIBERADO + "' AND quantidade_disponível > 0");
            
            while (rs.next()) {
                
                // Instânciando um novo Produto
                Produto produto = new Produto();
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                
                // Adicionando o Produto na lista 
                listaProdutosDisponiveis.add(produto);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Produtos 
        return listaProdutosDisponiveis;
    }
    
    /**
     * @brief Método que busca no banco de dados todos os Produtos cadastrados a partir de um filtro.
     * @param categoria Categoria do Produto
     * @param ordem Ordenação da Lista de Produtos
     * @return Retorna um ArrayList com todos os Produtos cadastrados no banco.
     */
    public ArrayList<Produto> pegarTodosFiltrado(String categoria, String ordem) {
        
        // Lista que vai armazenar todos os Produtos cadastrados no banco de dados
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            String query = "SELECT * FROM " + this.tabelaProduto;
            
            if (!categoria.equals("0")) {
                query = query + " WHERE id_categoria = " + categoria;
            }
            
            if (!ordem.equals("0")) {
                
                switch (ordem) {
                    case "1":
                        // Maior Preco
                        query = query + " ORDER BY preco_venda DESC";
                        break;
                    case "2":
                        // Menor Preco
                        query = query + " ORDER BY preco_venda ASC";
                        break;
                    case "3":
                        // Maior Quantidade
                        query = query + " ORDER BY quantidade_disponível DESC";
                        break;
                    case "4":
                        // Menor Quantidade
                        query = query + " ORDER BY quantidade_disponível ASC";
                        break;
                }
            }
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                // Instânciando um novo Produto
                Produto produto = new Produto();
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                
                // Adicionando o Produto na lista 
                listaProdutos.add(produto);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os Produtos 
        return listaProdutos;
    }
    
    /**
     * @brief Método que busca no banco de dados um Produto específico cadastrado.
     * 
     * @param produtoId
     * @return Retorna um Produto cadastrado no banco.
     */
    public Produto pegarProduto(int produtoId) {
        
        // Variável que vai armazenar o Produto específico caso exista
        Produto produto = new Produto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " WHERE id = " + produtoId);
            
            if (rs.next()) {
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Produto específico do banco de dados
        return produto;
    }
    
    /**
     * @brief Método que insere ou atuliza um Prouto no banco de dados.
     * 
     * @param produto
     * @return Retorna True caso o Produto seja inserido ou atulizado e False caso não.
     */
    public boolean inserirAlterarProduto(Produto produto) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            
            if (produto.getId() == 0) {
                query = "INSERT INTO " + this.tabelaProduto + " (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponível, liberado_venda, id_categoria) VALUES (?,?,?,?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaProduto + " SET nome_produto=?, descricao=?, preco_compra=?, preco_venda=?, quantidade_disponível=?, liberado_venda=?, id_categoria=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPrecoCompra());
            ps.setDouble(4, produto.getPrecoVenda());
            ps.setInt(5, produto.getQuantidade());
            ps.setString(6, produto.getLiberado());
            ps.setInt(7, produto.getIdCategoria());
            
            if (produto.getId() != 0) {
                ps.setInt(8, produto.getId());
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
     * @brief Método que excluir do banco de dados um Produto específico cadastrado.
     * 
     * @param produtoId
     * @return Retorna True caso a venda seja excluída e False caso não.
     */
    public boolean excluirProduto(int produtoId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaProduto + " WHERE id = " + produtoId;
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.execute();
            
            // Caso a exclusão se realize
            return true;
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * @brief Método que atualiza a quantidade de um determinado Produto que foi vendido.
     * 
     * @param produtoId
     * @param quantidade
     * @param quantidadeVenda
     * @param edit
     */
    public void venderProduto(int produtoId, int quantidade, int quantidadeVenda, int edit) {
        try {
            
            int quantidadeOriginal = 0;
                
            // Pegando a quantidade original do produto
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT quantidade_disponível FROM " + this.tabelaProduto + " WHERE id = " + produtoId);

            if (rs.next()) {

                // Setando os atributos de quantidade
                quantidadeOriginal = rs.getInt("quantidade_disponível");
            }
            
            if (edit == 1) {
                
                // Alterando a quantidade que deve ser adicionada para que a tabela de produto fique com a quantidade correta
                quantidade = quantidade - quantidadeVenda;
            }
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "UPDATE " + this.tabelaProduto + " SET quantidade_disponível=" + (quantidadeOriginal - quantidade) + " WHERE id = " + produtoId;

            PreparedStatement ps = conexao.prepareStatement(query);

            ps.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
    }
    
    /**
     * @brief Método que atualiza a tabela de Produtos ao comprar um produto.
     * 
     * @param produtoId
     * @param quantidade
     * @param preco_compra
     * @param quantidadeCompra
     * @param edit
     */
    public void comprarProduto(int produtoId, int quantidade, double preco_compra, int quantidadeCompra, int edit) {
        try {
            
            int quantidadeOriginal = 0;
                
            // Pegando a quantidade original do produto
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT quantidade_disponível FROM " + this.tabelaProduto + " WHERE id = " + produtoId);

            if (rs.next()) {

                // Setando os atributos de quantidade
                quantidadeOriginal = rs.getInt("quantidade_disponível");
            }
            
            if (edit == 1) {
                
                // Alterando a quantidade que deve ser adicionada para que a tabela de produto fique com a quantidade correta
                quantidade = quantidade - quantidadeCompra;
            }
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "UPDATE " + this.tabelaProduto + " SET quantidade_disponível=" + (quantidade + quantidadeOriginal) + ", preco_compra=" + preco_compra + " WHERE id = " + produtoId;

            PreparedStatement ps = conexao.prepareStatement(query);

            ps.executeUpdate();
                
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
    }
    
    /**
     * @brief Método que atualiza o status de um determinado Produto que foi liberado.
     * 
     * @param produtoId
     * @param liberado
     */
    public void liberarProduto(int produtoId, String liberado) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "UPDATE " + this.tabelaProduto + " SET liberado_venda='" + liberado + "' WHERE id = " + produtoId;
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
    }
    
    /**
     * @brief Método que retorna o Produto mais Caro do Estoque.
     * @return String Produto Mais Caro
     */
    public Produto pegarProdutoMaisCaro() {
        // Variável que vai armazenar o Produto específico caso exista
        Produto produto = new Produto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " ORDER BY preco_venda DESC LIMIT 1");
            
            if (rs.next()) {
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Produto específico do banco de dados
        return produto;
    }
    
    /**
     * @brief Método que retorna o Produto mais Barato do Estoque.
     * @return String Produto Mais Barato
     */
    public Produto pegarProdutoMaisBarato() {
        // Variável que vai armazenar o Produto específico caso exista
        Produto produto = new Produto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " ORDER BY preco_venda ASC LIMIT 1");
            
            if (rs.next()) {
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Produto específico do banco de dados
        return produto;
    }
    
    /**
     * @brief Método que retorna o Produto com mais Quantidade do Estoque.
     * @return String Produto com mais Quantidade
     */
    public Produto pegarProdutoMaiorQuantidade() {
        // Variável que vai armazenar o Produto específico caso exista
        Produto produto = new Produto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " ORDER BY quantidade_disponível DESC LIMIT 1");
            
            if (rs.next()) {
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Produto específico do banco de dados
        return produto;
    }
    
    /**
     * @brief Método que retorna o Produto com menos Quantidade do Estoque.
     * @return String Produto com menos Quantidade
     */
    public Produto pegarProdutoMenorQuantidade() {
        // Variável que vai armazenar o Produto específico caso exista
        Produto produto = new Produto();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaProduto + " ORDER BY quantidade_disponível ASC LIMIT 1");
            
            if (rs.next()) {
                
                // Setando os atributos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna um Produto específico do banco de dados
        return produto;
    }
}
