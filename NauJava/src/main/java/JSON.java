import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

import static java.net.http.HttpClient.newHttpClient;

public class JSON {
    public static void main(String[] args) {
        //URL для запроса User-Agent
        String url = "https://httpbin.org/user-agent";

        //HTTP-клиент
        try (HttpClient client = newHttpClient()) {

            //формируем GET-запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            try {
                //отправляем запрос и получаем ответ
                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                //проверяем код ответа (200 = OK)
                if (response.statusCode() == 200) {
                    String jsonResponse = response.body();
                    //извлекаем User-Agent из JSON
                    String userAgent = jsonResponse.split("\"user-agent\": \"")[1].split("\"")[0];
                    System.out.println("User-Agent: " + userAgent);
                } else {
                    System.out.println("ОЙ-ой, ошибка! Код ответа: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Ошибка при выполнении запроса: " + e.getMessage());
            }
        }
    }
}