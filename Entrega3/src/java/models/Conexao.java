/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @brief Classe que cria a conexão com o banco de dados do estoque.
 * 
 * @author joaop
 */
@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class Conexao extends HttpServlet {

    /**
     * @brief Atributo privado que armazena a conexão ativa com o banco de dados. 
     */
    private static Connection conexao = null;
    
    /**
     * @brief Método que cria uma nova conexão caso não exista nenhuma conexão ativa.
     * @throws java.sql.SQLException
     * @return Retorna uma conexão ativa.
     */
    public static Connection criaConexao() throws SQLException {
        
        if (conexao == null) {
            try {
                
                // Carrega o Driver JDBC na memória
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver foi localizado!");
                
                // Cria uma nova conexão com o banco
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
                System.out.println("Conectado com sucesso!");
                
            } catch(ClassNotFoundException e) {
                System.out.println("Driver não foi localizado!");
            }
        }
        
        // Retorna um objeto do tipo Connection com uma conexão ativa
        return conexao;
    }

}
