import java.util.List;

public class Main {
    static void main() {
        String path = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados.csv";
        String path2 = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados2.csv";


        TransactionIngestor transactionsIngestor = new TransactionIngestor();
        transactionsIngestor.setMaxTransactions(50000);

        List<Transaction> transactionsList = transactionsIngestor.getDataByFileName(path);
        IO.println("\nQuantity of transactions: " + transactionsList.size() + "\n");

        transactionsList.forEach(IO::println);

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer();
        fraudAnalyzer.setTotalFraud(transactionsList);
    }
}