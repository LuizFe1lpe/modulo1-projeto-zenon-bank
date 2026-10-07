import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        List<Transaction> list = new ArrayList<>();
        TransactionIngestor ingestor = new TransactionIngestor();
        list = ingestor.getDataByFileName("base_de_dados");
    }
}