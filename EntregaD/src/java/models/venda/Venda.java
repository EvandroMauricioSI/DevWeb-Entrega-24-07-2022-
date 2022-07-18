/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.venda;

/**
 *
 * @author joaop
 */
public class Venda {
    
    // Atributos
    private int id;
    private int quantidade;
    private String data;
    private double valor;
    private int idCliente;
    private int idProduto;
    private int idVendedor;
    
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
    
    // Set e Get Id Cliente
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public int getIdCliente() {
        return this.idCliente;
    }
    
    // Set e Get Id Produto
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public int getIdProduto() {
        return this.idProduto;
    }

    // Set e Get Id Vendedor
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public int getIdVendedor() {
        return this.idVendedor;
    }
}
