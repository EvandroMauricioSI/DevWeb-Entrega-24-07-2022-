/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.compra;

/**
 *
 * @author joaop
 */
public class CompraView {
    
    // Atributos
    private int id;
    private int quantidade;
    private String data;
    private double valor;
    private int idFornecedor;
    private String nomeFornecedor;
    private int idProduto;
    private String nomeProduto;
    private int idComprador;
    private String nomeComprador;
    
    // Set e Get Id
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    // Set e Get Quantidade
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    // Set e Get Data
    public void setData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return this.data;
    }
    
    // Set e Get Valor
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    // Set e Get Id Fornecedor
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
    public int getIdFornecedor() {
        return this.idFornecedor;
    }
    
    // Set e Get Nome Fornecedor
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    
    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }
    
    // Set e Get Id Produto
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public int getIdProduto() {
        return this.idProduto;
    }
    
    // Set e Get Nome Produto
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    // Set e Get Id Comprador
    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
    
    public int getIdComprador() {
        return this.idComprador;
    }
    
    // Set e Get Nome Comprador
    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }
    
    public String getNomeComprador() {
        return this.nomeComprador;
    }
}
