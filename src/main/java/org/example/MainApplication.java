package org.example;

import org.example.entities.BankTransaction;
import org.example.exporters.Exporter;
import org.example.exporters.JsonExporter;
import org.example.exporters.XmlExporter;
import org.example.model.BankTransactionAnalyzer;
import org.example.parsers.BankStatementCSVParser;
import org.example.parsers.BankStatementParser;
import org.example.view.ExpensesBarChart;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        BankTransactionAnalyzer bankTransactionAnalyzer = new BankTransactionAnalyzer();
        BankStatementParser bankStatementParser = new BankStatementCSVParser();
        bankTransactionAnalyzer.analyze("test.csv", bankStatementParser);

        List<BankTransaction> bankTransactions = bankTransactionAnalyzer.getBankTransactions();

        Exporter exporter = new JsonExporter();
        System.out.println(exporter.export(bankTransactionAnalyzer.getSummaryStatistics()));

        exporter = new XmlExporter();
        System.out.println(exporter.export(bankTransactionAnalyzer.getSummaryStatistics()));

        ExpensesBarChart chart = new ExpensesBarChart("Expenses","Expenses", bankTransactions);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
