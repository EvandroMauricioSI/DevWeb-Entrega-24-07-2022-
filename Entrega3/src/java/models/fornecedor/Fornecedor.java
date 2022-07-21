/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.fornecedor;

/**
 * Classe de Fornecedor.
 * @author joaop
 */
public class Fornecedor {
    // Atributos
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;
    
    // Set e Get Id
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    // Set e Get Razão Social
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    public String getRazaoSocial() {
        return this.razaoSocial;
    }
    
    // Set e Get Cnpj
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getCnpj() {
        return this.cnpj;
    }
    
    // Set e Get Endereço
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    // Set e Get Bairro
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getBairro() {
        return this.bairro;
    }
    
    // Set e Get Cidade
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getCidade() {
        return this.cidade;
    }
    
    // Set e Get Uf
    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getUf() {
        return this.uf;
    }
    
    // Set e Get Cep
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getCep() {
        return this.cep;
    }
    
    // Set e Get Telefone
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    // Set e Get Email
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return this.email;
    }
}
