import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TransactionType transactionType, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud) {
    public Transaction{
        Objects.requireNonNull(transactionType);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(recipient);

        if(step <= 0) throw new  IllegalArgumentException("Step needs to be positive and greater than zero");
        if(amount.signum() < 0) throw new  IllegalArgumentException("Amount should be positive or zero");
    }
}