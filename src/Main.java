import java.io.IOException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        var inicio = """
                --------------------------------------
                Seja bem-vindo ao Conversor de Moedas!
                
                Solicite uma operação de conversão:
                
                1) Dólar americano -> Real  brasileiro;
                2) Real brasileiro -> Dólar americano;
                3) Dólar americano -> Peso argentino;
                4) Peso argentino -> Dólar americano;
                5) Dólar americano -> Peso colombiano;
                6) Peso colombiano -> Dólar americano
                7) Encerrar conversões
                --------------------------------------
                """;

        boolean continuar = true;
        Scanner input = new Scanner(System.in);

        // enquanto a opção 7 não for selecionada, o boolean vai continuar true e novas operações continuarão sendo requisitadas ao sistema
        while (continuar) {

            System.out.println(inicio);

            // pega o numero inteiro correspondente a operacao requisitada pelo usuario
            int operacao = input.nextInt();
            // inicializacao da classe responsavel por todo o processo de conversao
            Conversoes conversao = new Conversoes();

            switch (operacao) {
                case 1:
                    conversao.ConverterMoeda(
                            "https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/USD/BRL");
                    break;
                case 2:
                    conversao.ConverterMoeda("https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/BRL/USD");
                    break;
                case 3:
                    conversao.ConverterMoeda("https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/USD/ARS");
                    break;
                case 4:
                    conversao.ConverterMoeda("https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/ARS/USD");
                    break;
                case 5:
                    conversao.ConverterMoeda("https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/USD/COP");
                    break;
                case 6:
                    conversao.ConverterMoeda("https://v6.exchangerate-api.com/v6/39c00e60156fd74b83101fb8/pair/COP/USD");
                    break;
                case 7:
                    System.out.println("Conversões encerradas.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
            }
        }
    }
}
