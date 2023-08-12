import org.example.parsers.BankStatementCSVParser;
import org.example.parsers.BankStatementParser;
import org.example.entities.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BankStatementCSVParserTest {
    private BankStatementParser bankStatementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        String line = "30-01-2017,-50,Tesco";
        BankTransaction result = bankStatementParser.parseFrom(line);
        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseAllLinesCorrect() throws Exception {
        List<String> lines = Arrays.asList("30-01-2017,-50,Tesco", "02-02-2017,2000,Royalties", "05-02-2017,-30,Cinema");
        List<BankTransaction> result = bankStatementParser.parseLinesFrom(lines);
        List<BankTransaction> expected = Arrays.asList(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50.0, "Tesco"),
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 2), 2000.0, "Royalties"),
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 5), -30.0, "Cinema"));
        Assert.assertEquals(result, expected);
    }
}
