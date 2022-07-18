/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.venda;

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
@WebServlet(name = "VendaDAO", urlPatterns = {"/VendaDAO"})
public class VendaDAO extends HttpServlet {

    // Atributo do nome da tabela de Vendas no banco de dados
    private String tabelaVenda = "vendas";
    
    // Atributo do nome da tabela de Produtos no banco de dados
    private String tabelaProduto = "produtos";
    
    // Atributo de conexão com o banco de dados
    private Connection conexao;
    
    /**
     * @brief Construtor da VendaDAO que garante uma conexão ativa com o banco de dados.
     * 
     */
    public VendaDAO() {
        try {
            // Cria uma nova conexão
            conexao = Conexao.criaConexao();
            
        } catch(SQLException e) {
            System.out.println("Erro - Criação de conexão VendaDAO!");
        }
    }
    
    private boolean validaQuantidadeProduto(int produtoId, int quantidadeVenda) {
        
        try {
            int quantidadeAtual = 0;
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT quantidade_disponível FROM " + this.tabelaProduto + " WHERE id = " + produtoId);
            
            if (rs.next()) {
                quantidadeAtual = rs.getInt("quantidade_disponível");
            }
            
            if (quantidadeAtual - quantidadeVenda < 0 ) return true;
            
        } catch (SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        return false;
    }
    
    private boolean validaValorProduto(int produtoId, double valorVenda) {
        
        try {
            double valorCompra = 0.0;
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT preco_compra FROM " + this.tabelaProduto + " WHERE id = " + produtoId);
            
            if (rs.next()) {
                valorCompra = rs.getDouble("preco_compra");
            }
            
            // Calculando o valor de compra acrescido de 10%, que é o valor mínimo de venda de um produto
            double dezPorCento = valorCompra / 10;
            valorCompra = valorCompra + dezPorCento;
            
            if (valorVenda - valorCompra < 0 ) return true;
            
        } catch (SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        return false;
    }

    /**
     * @brief Método que busca no banco de dados todas as Vendas cadastradas.
     * 
     * @return Retorna um ArrayList com todas as Vendas cadastradas no banco.
     */
    public ArrayList<Venda> pegarTodas() {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Lista que vai armazenar todas as vendas cadastradas no banco de dados
        ArrayList<Venda> listaVendas = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaVenda);
            
            while (rs.next()) {
                
                // Instânciando uma nova venda
                Venda venda = new Venda();
                
                // Setando os atributos da venda
                venda.setId(rs.getInt("id"));
                venda.setQuantidade(rs.getInt("quantidade_venda"));
                venda.setData(formata.formataData(rs.getString("data_venda"), "brasil"));
                venda.setValor(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setIdVendedor(rs.getInt("id_vendedor"));
                
                // Adicionando a venda na lista 
                listaVendas.add(venda);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todas as vendas
        return listaVendas;
    }
    
    /**
     * @brief Método que busca no banco de dados uma Venda específica cadastrada.
     * 
     * @param vendaId
     * @return Retorna uma Venda cadastrada no banco.
     */
    public Venda pegarVenda(int vendaId) {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Variável que vai armazenar a venda específica caso exista
        Venda venda = new Venda();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tabelaVenda + " WHERE id = " + vendaId);
            
            if (rs.next()) {
                
                // Setando os atributos do cliente
                venda.setId(rs.getInt("id"));
                venda.setQuantidade(rs.getInt("quantidade_venda"));
                venda.setData(formata.formataData(rs.getString("data_venda"), "brasil"));
                venda.setValor(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setIdVendedor(rs.getInt("id_vendedor"));
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna venda específico do banco de dados
        return venda;
    }
    
    /**
     * @brief Método que busca no banco de dados todas as Vendas cadastradas com os joins com as tabelas de Cliente, Produto e Usuário.
     * 
     * @return Retorna um ArrayList com todas as Vendas cadastradas no banco e com os nomes dos Clientes, Produtos e Usuários.
     */
    public ArrayList<VendaView> pegarVendaView() {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Lista que vai armazenar todas as vendas cadastradas no banco de dados
        ArrayList<VendaView> listaVendas = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT v.id, v.quantidade_venda, v.data_venda, v.valor_venda, v.id_cliente, c.nome as nome_cliente, v.id_produto, p.nome_produto, v.id_vendedor, u.nome as nome_vendedor FROM vendas as v INNER JOIN clientes as c ON v.id_cliente = c.id INNER JOIN produtos AS p ON v.id_produto = p.id INNER JOIN usuarios AS u ON v.id_vendedor = u.id");
            
            while (rs.next()) {
                
                // Instânciando uma nova venda
                VendaView venda = new VendaView();
                
                // Setando os atributos do cliente
                venda.setId(rs.getInt("id"));
                venda.setQuantidade(rs.getInt("quantidade_venda"));
                venda.setData(formata.formataData(rs.getString("data_venda"), "brasil"));
                venda.setValor(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setNomeCliente(rs.getString("nome_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setNomeProduto(rs.getString("nome_produto"));
                venda.setIdVendedor(rs.getInt("id_vendedor"));               
                venda.setNomeVendedor(rs.getString("nome_vendedor"));
                
                // Adicionando a venda na lista 
                listaVendas.add(venda);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os clientes 
        return listaVendas;
    }
    
    /**
     * @brief Método que busca no banco de dados todas as Vendas cadastradas e retorna a soma das vendas por dia.
     * 
     * @return Retorna um ArrayList com a soma das Vendas por dia.
     */
    public ArrayList<VendaView> pegarVendaDiaria() {
        
        // Intância da Classe Formatação
        Formatacao formata = new Formatacao();
        
        // Lista que vai armazenar todas as vendas cadastradas no banco de dados
        ArrayList<VendaView> listaVendas = new ArrayList<>();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            Statement stmt = conexao.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT data_venda, SUM(valor_venda) AS venda_diaria FROM vendas GROUP BY data_venda ORDER BY data_venda ASC");
            
            while (rs.next()) {
                
                // Instânciando uma nova venda
                VendaView venda = new VendaView();
                
                // Setando os atributos do cliente
                venda.setId(0);
                venda.setQuantidade(0);
                venda.setData(formata.formataData(rs.getString("data_venda"), "brasil"));
                venda.setValor(rs.getDouble("venda_diaria"));
                venda.setIdCliente(0);
                venda.setNomeCliente("");
                venda.setIdProduto(0);
                venda.setNomeProduto("");
                venda.setIdVendedor(0);               
                venda.setNomeVendedor("");
                
                // Adicionando a venda na lista 
                listaVendas.add(venda);
            }
            
        } catch(SQLException e) {
            System.out.println("Erro SQL - " + e.getMessage());
        }
        
        // Retorna lista de todos os clientes 
        return listaVendas;
    }
    
    /**
     * @brief Método que insere ou atuliza uma Venda no banco de dados.
     * 
     * @param venda
     * @return Retorna True caso a venda seja inserida ou atulizada e False caso não.
     */
    public boolean inserirAlterarVenda(Venda venda) {
        
        // Instância do ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query;
            int edit = 0;
            int quantidadeVenda = 0;
            
            // Validando Regra de Negócio (R3) - Venda não pode ocorrer caso a quantidade se torne menor que 0
            if (validaQuantidadeProduto(venda.getIdProduto(), venda.getQuantidade())) {
                Exception e = new Exception("Não é possível realizar a venda porque não há quantidade suficiente!");
                throw e;
            }
            
            // Validando Regra de Negócio (R5) - Venda não pode ocorrer caso o valor de venda seja menor que o valor de compra do produto acrescido de 10%
            if (validaValorProduto(venda.getIdProduto(), venda.getValor())) {
                Exception e = new Exception("Não é possível realizar a venda porque o valor está abaixo do mínimo possível!");
                throw e;
            }
            
            if (venda.getId() == 0) {
                query = "INSERT INTO " + this.tabelaVenda + " (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_vendedor) VALUES (?,?,?,?,?,?)";
            } else {
                query = "UPDATE " + this.tabelaVenda + " SET quantidade_venda=?, data_venda=?, valor_venda=?, id_cliente=?, id_produto=?, id_vendedor=? WHERE id=?";
                
                // Flagando o Edit
                edit = 1;
                
                // Pegando a quantidade de compra da compra que será editada
                Statement stmt = conexao.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT quantidade_venda FROM " + this.tabelaVenda + " WHERE id = " + venda.getId());

                if (rs.next()) {

                    // Setando os atributos de quantidade
                    quantidadeVenda = rs.getInt("quantidade_venda");
                }
            }
            
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ps.setInt(1, venda.getQuantidade());
            ps.setString(2, venda.getData());
            ps.setDouble(3, venda.getValor());
            ps.setInt(4, venda.getIdCliente());
            ps.setInt(5, venda.getIdProduto());
            ps.setInt(6, venda.getIdVendedor());
            
            if (venda.getId() != 0) {
                ps.setInt(7, venda.getId());
            }
            
            ps.executeUpdate();
            
            // Atualizando quantidade na tabela de Produtos
            produtoDAO.venderProduto(venda.getIdProduto(), venda.getQuantidade(), quantidadeVenda, edit);
            
            // Caso a exclusão se realize
            return true;
            
        } catch(Exception e) {
            System.out.println("Erro SQL - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * @brief Método que excluir do banco de dados uma Venda específica cadastrada.
     * 
     * @param vendaId
     * @return Retorna True caso a venda seja excluída e False caso não.
     */
    public boolean excluirVenda(int vendaId) {
        try {
            
            // Separar isso eventualmente e tentar estender a classe
            String query = "DELETE FROM " + this.tabelaVenda + " WHERE id = " + vendaId;
            
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
