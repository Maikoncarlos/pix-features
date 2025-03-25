package com.github.maikoncarlos.pix.service.validation;

public class CNPJValidator {

    public static boolean isValid(String cnpj) {

        if (cnpj.length () != 14) {
            return false;
        }

        if (cnpj.matches ("(\\d)\\1{13}")) {
            return false;
        }

        int primeiroDigitoCalculado = calcularDigito (cnpj, 12);

        if (primeiroDigitoCalculado != Character.getNumericValue (cnpj.charAt (12))) {
            return false;
        }

        int segundoDigitoCalculado = calcularDigito (cnpj, 13);

        return segundoDigitoCalculado == Character.getNumericValue (cnpj.charAt (13));
    }

    private static int calcularDigito(String cnpj, int posicao) {
        int soma = 0;
        int peso = (posicao == 12) ? 5 : 6;

        for (int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue (cnpj.charAt (i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}