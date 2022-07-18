/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.categoria;

/**
 *
 * @author joaop
 */
public class CategoriaProduto {
    
    // Atributos
    private int id;
    private String categoria;
    
    // Set e Get Id
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    // Set e Get Categoria
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return this.categoria;
    }
}
