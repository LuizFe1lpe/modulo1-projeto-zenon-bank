import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionByOriginName;

    public TransactionMapRepository(List<Transaction> transactionList) {
        Objects.requireNonNull(transactionList);
        this.transactionByOriginName = transactionList
                .stream()
                .collect(
                        Collectors.toMap(
                                transaction -> transaction.origin().name(),
                                Function.identity()));
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        return Optional.ofNullable(transactionByOriginName.get(originName));
    }
}
