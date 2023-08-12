package org.example.view;

import org.example.entities.BankTransaction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.Comparator;
import java.util.List;

public class ExpensesBarChart extends ApplicationFrame {

    public ExpensesBarChart(String applicationTitle, String chartTitle, List<BankTransaction> bankTransactions) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Months",
                "Value",
                createDataset(bankTransactions),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560 , 367) );
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<BankTransaction> bankTransactions) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<BankTransaction> sortedTransactions = bankTransactions.stream()
                .filter(x -> x.getAmount() < 0)
                .sorted(Comparator.comparing(o -> o.getDate().getMonth()))
                .toList();

        for (BankTransaction bankTransaction : sortedTransactions) {
            dataset.addValue(-bankTransaction.getAmount(), bankTransaction.getDescription(), bankTransaction.getDate().getMonth());
        }

        return dataset;
    }
}
