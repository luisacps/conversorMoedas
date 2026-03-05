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

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(enderecoRequisicao)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();

        // extra para complementar as informações retornadas:
        var moeda1 = object.get("base_code").getAsString();
        var moeda2 = object.get("target_code").getAsString();

        var taxa = object.get("conversion_rate").getAsDouble();
        BigDecimal bd = new BigDecimal(taxa);
        bd.setScale(2, RoundingMode.HALF_UP);
        double taxaDeConversao = bd.doubleValue();

        Scanner valor = new Scanner(System.in);
        System.out.println("Insira um valor para conversão: ");
        var valorParaConversao = valor.nextDouble();

        CalcularCambio(taxaDeConversao, valorParaConversao, moeda1, moeda2);
    }

    public double CalcularCambio(double taxa, double valor, String m1, String m2) {
        double resultado = valor * taxa;
        System.out.println("Após a conversão, o valor de " + valor + " " + m1 + " passa a ser de " + resultado + " " + m2);
        return resultado;
    }
}
