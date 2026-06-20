package pl.pjatk.jaz_s32945_nbp.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.jaz_s32945_nbp.Repos.CurrencyRepo;
import pl.pjatk.jaz_s32945_nbp.Services.CurrencyService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/average")
    public ResponseEntity<Object> average(
            @RequestParam String currency,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        Double avg = currencyService.getAvgCurrency(currency, startDate, endDate);

        return ResponseEntity.ok(avg);
    }

}
