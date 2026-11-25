import java.util.List;

public class Main {
    static void main() {
        String path = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados.csv";
        String badDataPath = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\Pós Graduação\\Projetos\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\base_de_dados2.csv";


        TransactionIngestor transactionsIngestor = new TransactionIngestor();

        IO.println("\nBAD DATA:\n");
        List<Transaction> badTransactionsList = transactionsIngestor.getDataByFileName(badDataPath);
        IO.println("Quantity of transactions: " + badTransactionsList.size() + "\n");

        IO.println("GOOD DATA:\n");
        List<Transaction> transactionsList = transactionsIngestor.getDataByFileName(path);
        IO.println("Quantity of transactions: " + transactionsList.size());
        transactionsList.forEach(IO::println);
        IO.println("\n");

        IO.println("Frauds:\n");
        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactionsList);
        IO.println("1. Total Frauds: " + fraudAnalyzer.countTotalFraud());

        IO.println("2. Top 3 Frauds with higher values:");
        fraudAnalyzer.findHighestFrauds(3);
        fraudAnalyzer.getHighestFrauds().forEach(amount -> IO.println(" - %.2f".formatted(amount)));

        IO.println("3. Suspect Clients:");
        fraudAnalyzer.findTopSuspiciousClients(5);
        fraudAnalyzer.getTopSuspiciousClients().forEach(IO::println);

        IO.println("4. Total loss: " + fraudAnalyzer.calculateAndGetTotalLoss());
        IO.println("5. Frauds by type: ");
        fraudAnalyzer.countFraudByType().forEach((transactionType, count) -> IO.println("%s: %d".formatted(transactionType, count)));

        TransactionRepository transactionRepository;

        transactionRepository = new TransactionListRepository(transactionsList);
        String originInexistente = "C12345";
        transactionRepository.findByOriginName(originInexistente)
                .ifPresentOrElse(IO::println, () -> IO.println("\nERROR: Origin not found! " + originInexistente));

        String originExistente = "C1231006815";
        transactionRepository.findByOriginName(originExistente)
                .ifPresentOrElse(IO::println, () -> IO.println("\nERROR: Origin not found! " + originInexistente));

        Long startTime = System.nanoTime();
        String origin = "C1868032458";
        transactionRepository.findByOriginName(origin)
                .ifPresentOrElse(IO::println, () -> IO.println("\nERROR: Origin not found! " + originInexistente));
        Long endTime = System.nanoTime();
        IO.println("\nSearch time - List (ms): " + (endTime - startTime) / 1000000.0);

        transactionRepository = new TransactionMapRepository(transactionsList);

        startTime = System.nanoTime();
        transactionRepository.findByOriginName(origin)
                .ifPresentOrElse(IO::println, () -> IO.println("\nERROR: Origin not found! " + originInexistente));
        endTime = System.nanoTime();
        IO.println("\nSearch time - Map (ms): " + (endTime - startTime) / 1000000.0);
    }
}