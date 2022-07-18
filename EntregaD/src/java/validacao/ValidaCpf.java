package validacao;

import java.util.InputMismatchException;

//Classe que faz a validação do CPF no backend.
public class ValidaCpf {
    public static boolean cpfValido(String CPF) {
        // Retirando os  pontos e traços do CPF
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");
        // CPF's inválidos (formados por uma sequência de números iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || CPF.length() != 11) {
            return(false);
        }
        char dig10, dig11;
        int soma, index, resto, numero, peso;

        try {
            // Cálculo do primeiro Dígito Verificador
            soma = 0;
            numero = 0;
            peso = 10;
            for (index = 0; index < 9; index++) {
                numero = (int)(CPF.charAt(index) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11)) {
                 dig10 = '0';
            } else {
                dig10 = (char)(resto + 48);
            }

            // Cálculo do segundo Dígito Verificador
            soma = 0;
            peso = 11;
            for(index = 0; index < 10; index++) {
                numero = (int)(CPF.charAt(index) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11)) {
                 dig11 = '0';
            } else {
                dig11 = (char)(resto + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                 return(true);
            } else {
                return(false);
            }
            
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}
