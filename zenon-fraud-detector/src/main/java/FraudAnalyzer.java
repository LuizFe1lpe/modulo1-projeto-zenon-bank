import java.math.BigDecimal;
import java.util.List;

public class FraudAnalyzer {
    private int totalFrauds;
    private List<BigDecimal> topFrauds;
    private List<String> topCustomers;
    private BigDecimal totalLoss;
    private TransactionType fraudType;

    public void setTotalFraud(List<Transaction> transactionsList){
        totalFrauds = transactionsList.stream()
                .filter()
                .count();
        IO.println("Total Frauds: " + totalFrauds);
    }


}
