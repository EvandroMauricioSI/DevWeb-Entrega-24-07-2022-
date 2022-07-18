/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatacao;

/**
 *
 * @author joaop
 */
public class Formatacao {
    
    public String formataData(String data, String tipo){
        
        String resultado = "";
        String[] dataSeparada; 
        String dia, mes, ano;
        
        switch (tipo) {
            case "banco":
                
                if (data != null) {
                    // Caso a data venha no formato brasileiro [dd/mm/yyyy]
                    // Separando dia, mês e ano.
                    dataSeparada = data.split("/");

                    dia = dataSeparada[0];
                    mes = dataSeparada[1];
                    ano = dataSeparada[2];

                    // Juntamos agora na ordem inversa e trocando as / por - assim: [yyyy-mm-dd]
                    resultado = String.join("-", ano, mes, dia);
                }
                                
                break;
            case "brasil":
                
                if (data != null) {
                    // Caso a data venha no formato americano padrão do banco de dados [yyyy-mm-dd]
                    // Separando dia, mês e ano.
                    dataSeparada = data.split("-");

                    ano = dataSeparada[0];
                    mes = dataSeparada[1];
                    dia = dataSeparada[2];

                    // Juntamos agora na ordem inversa e trocando as - por / assim: [dd/mm/yyyy]
                    resultado = String.join("/", dia, mes, ano);
                }
                
                break;
        }
        
        return resultado;
    }    
}
