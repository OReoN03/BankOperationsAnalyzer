package org.example.model;

import org.example.entities.BankTransaction;
import org.example.entities.SummaryStatistics;
import org.example.filters.BankTransactionFilter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BankStatementProcessor {
    private List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions() {
        return bankTransactions
                .stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalInMonth(Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalForCategory(String category) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public BankTransaction findMostExpensiveBetween(LocalDate start, LocalDate end) {
        return bankTransactions
                .stream()
                .filter(bankTransaction -> bankTransaction.getAmount() < 0)
                .min(Comparator.comparing(BankTransaction::getAmount))
                .get();
    }

    public BankTransaction findLeastExpensiveBetween(LocalDate start, LocalDate end) {
        return bankTransactions
                .stream()
                .filter(bankTransaction -> bankTransaction.getAmount() < 0)
                .max(Comparator.comparing(BankTransaction::getAmount))
                .get();
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(double amount) {
        return bankTransactions
                .stream()
                .filter(x -> x.getAmount() >= amount)
                .toList();
    }

    public List<BankTransaction> findTransactionsInMonth(Month month) {
        return bankTransactions
                .stream()
                .filter(x -> x.getDate().getMonth() == month)
                .toList();
    }

    public List<BankTransaction> findTransactionsInMonthAndGreater(Month month, double amount) {
        return bankTransactions
                .stream()
                .filter(x -> x.getDate().getMonth() == month && x.getAmount() >= amount)
                .toList();
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter) {
        return bankTransactions
                .stream()
                .filter(bankTransactionFilter::test)
                .toList();
    }

    public SummaryStatistics getStatistics() {
        double min = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).min().getAsDouble();
        double max = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).max().getAsDouble();
        double average = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).average().getAsDouble();
        double sum = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
        SummaryStatistics summaryStatistics = new SummaryStatistics(sum, max, min, average);
        return  summaryStatistics;
    }
}