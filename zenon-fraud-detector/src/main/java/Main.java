import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        String path = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\paysim_with_bad_data.csv";
        List<Transaction> list = new ArrayList<>();
        TransactionIngestor ingestor = new TransactionIngestor();

        list = ingestor.getDataByFileName(path);

        list.forEach(IO::println);
        System.out.println("\n Quantity of transactions: " + list.size());
    }
}