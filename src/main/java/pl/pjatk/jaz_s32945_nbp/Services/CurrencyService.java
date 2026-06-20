package pl.pjatk.jaz_s32945_nbp.Services;

import org.springframework.stereotype.Service;
import pl.pjatk.jaz_s32945_nbp.Models.CurrencyLog;
import pl.pjatk.jaz_s32945_nbp.Repos.CurrencyRepo;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CurrencyService {
    private final CurrencyRepo repository;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public CurrencyService(CurrencyRepo repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    public Double getAvgCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        String url = "https://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+startDate+"/"+endDate+"/?format=json";

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + resp.statusCode());
            }

            JsonNode root = objectMapper.readTree(resp.body());
            JsonNode rates = root.get("rates");

            if (rates == null || rates.isEmpty()) {
                throw new RuntimeException("No rates found");
            }

            double sum = 0.00;
            int daysCount = rates.size();
            for (int i = 0; i < daysCount; i++) {
                JsonNode dayRate = rates.get(i);
                double midRate = dayRate.get("mid").asDouble();

                sum += midRate;
            }

            double average = sum / daysCount;

            CurrencyLog log = new CurrencyLog(currency, startDate, endDate, average, LocalDateTime.now());
            repository.save(log);

            return average;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
