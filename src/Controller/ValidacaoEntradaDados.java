package Controller;

import java.util.Scanner;

public class ValidacaoEntradaDados {

    Scanner scanner = new Scanner(System.in);

    /*Todos os metodos dessa classe serao Publicos.*/
    public String validaString(String texto) {
        while (texto.equals("") || texto.equals(" ")) {
            System.out.println("entrada incorreta!");
            texto = scanner.nextLine();

        }
        return texto;
    }

    public int validarINT(int numero) {

        while (numero <= 0) {
            System.out.println("numero invalido!");
            System.out.println("Informe outro numero Maior que 0 : ");
            numero = Integer.parseInt(scanner.nextLine());
        }
        return numero;
    }

    public double validarDoble(double numero) {

        while (numero <= 0) {
            System.out.println("numero invalido!");
            System.out.println("Informe outro numero Maior que 0 : ");
            numero = Integer.parseInt(scanner.nextLine());
        }
        return numero;
    }
    
}
