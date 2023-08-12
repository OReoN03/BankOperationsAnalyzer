package org.example.model;

import org.example.entities.BankTransaction;
import org.example.entities.SummaryStatistics;
import org.example.parsers.BankStatementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    private List<BankTransaction> bankTransactions;
    private SummaryStatistics summaryStatistics;

    public List<BankTransaction> getBankTransactions() {
        return bankTransactions;
    }

    public SummaryStatistics getSummaryStatistics() {
        return summaryStatistics;
    }

    public void analyze(String fileName, BankStatementParser bankStatementParser) throws IOException {
        Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        bankTransactions = bankStatementParser.parseLinesFrom(lines);

        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        summaryStatistics = bankStatementProcessor.getStatistics();
        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total amount of all transactions: "
                + bankStatementProcessor.summarizeTransactions());

        System.out.println("Transactions in January: "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("Transactions in February: "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received: "
                + bankStatementProcessor.calculateTotalForCategory("Salary"));

        System.out.println("The most expensive transaction between 30-01-2017 and 03-02-2017: "
                + bankStatementProcessor.findMostExpensiveBetween(LocalDate.of(2017, Month.JANUARY, 30), LocalDate.of(2017, Month.FEBRUARY, 3)));

        System.out.println("The least expensive transaction between 30-01-2017 and 03-02-2017: "
                + bankStatementProcessor.findLeastExpensiveBetween(LocalDate.of(2017, Month.JANUARY, 30), LocalDate.of(2017, Month.FEBRUARY, 3)));
    }
}
