import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        String path = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados.csv";
        List<Transaction> list = new ArrayList<>();
        TransactionIngestor ingestor = new TransactionIngestor();

        list = ingestor.getDataByFileName(path);

        list.stream().limit(10).forEach(IO::println);
    }
}