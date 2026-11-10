import java.util.List;

public class Main {
    static void main() {
        String path = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados.csv";
        String path2 = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados2.csv";


        TransactionIngestor transactionsIngestor = new TransactionIngestor();
        List<Transaction> transactionsList = transactionsIngestor.getDataByFileName(path2);
        IO.println("\nQuantity of transactions: " + transactionsList.size() + "\n");

        transactionsList.forEach(IO::println);
    }
}