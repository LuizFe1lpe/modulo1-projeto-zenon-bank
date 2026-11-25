import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {
     private final List<Transaction> transactionList;

    public TransactionListRepository(List<Transaction> transactionList) {
        Objects.requireNonNull(transactionList);
        this.transactionList = transactionList;
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        return transactionList
                .stream()
                .filter(transaction -> transaction.origin().name().equals(originName))
                .findFirst();
    }
}
