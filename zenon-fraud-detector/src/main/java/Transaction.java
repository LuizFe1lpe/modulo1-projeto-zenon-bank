import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        String nameOrig,
        BigDecimal oldbalanceOrg,
        BigDecimal newbalanceOrig,
        String nameDest,
        BigDecimal oldbalanceDest,
        BigDecimal newbalanceDest,
        boolean isFraud,
        boolean isFlaggedFraud
) {
    public enum TransactionType {
        CASH_IN,
        CASH_OUT,
        DEBIT,
        PAYMENT,
        TRANSFER
    }
}