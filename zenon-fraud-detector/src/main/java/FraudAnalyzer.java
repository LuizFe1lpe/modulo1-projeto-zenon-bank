import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudAnalyzer {

    List<Transaction> transactions;

    private long totalFrauds;
    private List<BigDecimal> highestFrauds;
    private List<String> topClients;
    private BigDecimal totalLoss;
    private Map<TransactionType, Long> fraudType;

    public FraudAnalyzer(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public long countTotalFraud(){
        return totalFrauds = fraudStream()
                .count();
    }

    public void findHighestFrauds(int limit) {
        highestFrauds = fraudStream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(Transaction::amount)
                .limit(limit)
                .toList();
    }
    public List<BigDecimal> getHighestFrauds() {
        return highestFrauds;
    }

    public void findTopSuspiciousClients(int limit) {
        topClients = fraudStream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(transaction -> transaction.origin().name())
                .distinct()
                .limit(limit)
                .toList();
    }
    public List<String> getTopSuspiciousClients() {return topClients;}

    public BigDecimal calculateAndGetTotalLoss() {
        return totalLoss = fraudStream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> countFraudByType(){
        return fraudType = fraudStream()
                .collect(Collectors.groupingBy(Transaction::transactionType, Collectors.counting()));
    }

    private Stream<Transaction> fraudStream(){
        return transactions
                .stream()
                .filter(Transaction::isFraud);
    }
}
