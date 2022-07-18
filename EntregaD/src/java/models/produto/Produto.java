/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.produto;

/**
 *
 * @author joaop
 */
public class Produto {
   
    // Atributos
    private int id;
    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidade;
    private String liberado;
    private int idCategoria;
    
    // Set e Get Id
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    // Set e Get Nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    // Set e Get Descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    // Set e Get Preço Compra
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }
    
    public double getPrecoCompra() {
        return this.precoCompra;
    }
    
    // Set e Get Preço Venda
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    public double getPrecoVenda() {
        return this.precoVenda;
    }
    
    // Set e Get Quantidade
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    // Set e Get Liberado
    public void setLiberado(String liberado) {
        this.liberado = liberado;
    }
    
    public String getLiberado() {
        return this.liberado;
    }
    
    // Set e Get Id Categoria
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public int getIdCategoria() {
        return this.idCategoria;
    }
}
