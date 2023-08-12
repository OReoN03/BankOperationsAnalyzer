package org.example.parsers;

import org.example.entities.BankTransaction;
import org.example.exceptions.CSVSyntaxException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final int EXPECTED_ATTRIBUTES_LENGTH = 3;
    public BankTransaction parseFrom(String line) {
        String[] columns = line.split(",");
        if (columns.length < EXPECTED_ATTRIBUTES_LENGTH) {
            throw new CSVSyntaxException();
        }

        LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        double amount = Double.parseDouble(columns[1]);
        String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        return lines
                .stream()
                .map(this::parseFrom)
                .toList();
    }
}
