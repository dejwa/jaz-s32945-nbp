package pl.pjatk.jaz_s32945_nbp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="currency_logs")
public class CurrencyLog {
    @Id
    private Long id;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double averageRate;

    @Column
    private LocalDateTime queryTimestamp;

    public CurrencyLog() {
        //pusty bo musi być
    }

    public CurrencyLog(String currency, LocalDate startDate, LocalDate endDate, Double averageRate, LocalDateTime queryTimestamp) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.averageRate = averageRate;
        this.queryTimestamp = queryTimestamp;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Double getAverageRate() {
        return averageRate;
    }
    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }
    public LocalDateTime getQueryTimestapmp() {
        return this.queryTimestamp;
    }
    public void setQueryTimestapmp(LocalDateTime queryTimestapmp) {
        this.queryTimestamp = queryTimestapmp;
    }
}
