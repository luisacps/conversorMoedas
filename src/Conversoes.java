import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Conversoes {

    public void ConverterMoeda(String enderecoRequisicao) throws IOException, InterruptedException {
        try {
            // fazendo a conexao com a API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(enderecoRequisicao)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // pegando o corpo do json todinho
            String json = response.body();
            JsonObject object = JsonParser.parseString(json).getAsJsonObject();

            // extra para complementar as informações retornadas:
            var moeda1 = object.get("base_code").getAsString();
            var moeda2 = object.get("target_code").getAsString();

            // pegando a taxa de conversão atualizada do objeto json selecionado anteriormente
            var taxa = object.get("conversion_rate").getAsDouble();
            BigDecimal bd = new BigDecimal(taxa);
            // transformando a taxa de conversão num numero com 2 casas decimais
            bd.setScale(2, RoundingMode.HALF_UP);
            double taxaDeConversao = bd.doubleValue();

            // pedindo ao usuario que inclua um valor para conversao
            Scanner valor = new Scanner(System.in);
            System.out.println("Insira um valor para conversão: ");
            var valorParaConversao = valor.nextDouble();

            CalcularCambio(taxaDeConversao, valorParaConversao, moeda1, moeda2);

        } catch (IOException e) {
            System.err.println("Há algo de errado na conexão com a API :( " + e.getMessage());
        } catch (InterruptedException i) {
            System.out.println("O processo de conexão com a API foi interrompido :( " + i.getMessage());
        }
    }

    // metodo que faz a operacao de cambio
    public double CalcularCambio(double taxa, double valor, String m1, String m2) {
        double resultado = valor * taxa;
        System.out.println("Após a conversão, o valor de " + valor + " " + m1 + " passa a ser de " + resultado + " " + m2);
        return resultado;
    }
}
