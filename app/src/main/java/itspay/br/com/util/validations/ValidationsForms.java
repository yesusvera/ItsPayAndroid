package itspay.br.com.util.validations;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yesus on 16/12/16.
 */

public class ValidationsForms {
    public static boolean isCPF(String CPF) {


    CPF = CPF.replace(".", "").replace("-", "");

// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

//    public static boolean isEmail(String email){
//        if(email==null || email.length() < 3){
//            return false;
//        }
//
//        if(email.indexOf("@") == -1){
//            return false;
//        }
//
//        return true;
//    }

    public static boolean isEmail(String email) {
        if ((email == null) || (email.trim().length() == 0))
            return false;

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Deve possuir letras e números e ter entre 6 e 30 caracteres.
     * @param senha
     * @return
     */
    public static boolean senhaValida(String senha){
        if(senha==null || senha.length() < 6 || senha.length()>30) return false;

        boolean temLetra = false;
        boolean temDigito = false;

        for(int i=0; i<senha.length(); i++){
            if(Character.isLetter(senha.charAt(i))){
                temLetra = true;
            }else if (Character.isDigit(senha.charAt(i))) {
                temDigito = true;
            }
        }

        if(temDigito && temLetra){
            return true;
        }else{
            return false;
        }
    }


    /**
     * Deve possuir letras e números e ter entre 4 e 30 caracteres.
     * @param senha
     * @return
     */
    public static boolean senhaCartaoValida(String senha){
        if(senha==null || senha.length() < 4 || senha.length()>30)
            return false;
        else
            return true;
    }
}
