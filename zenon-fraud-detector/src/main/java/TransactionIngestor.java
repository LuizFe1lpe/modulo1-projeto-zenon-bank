import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionIngestor {

    private List<Transaction> transactions = new ArrayList<>();
    private int maxTransactions;

    public List<Transaction> getDataByFileName(String fileName) {
        Path path = Path.of(fileName);
        try{
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(maxTransactions)
                    .map(this::parseTransaction)
                    .filter(Objects::nonNull) //avoid nullpointexception
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error, file not found " + fileName + e);
        }
    }

    private List<Transaction> getTransactions() {
        return transactions;
    }

    private  Optional<Transaction> parseTransaction(String line) {
        try{
            String[] chuncks = line.split(",");

            int step = Integer.parseInt(chuncks[0]);
            TransactionType type = TransactionType.valueOf(chuncks[1]);

            BigDecimal amount = new BigDecimal(chuncks[2]);

            TransactionCustomer origin = new TransactionCustomer(chuncks[3],new BigDecimal(chuncks[4]),new BigDecimal(chuncks[5]));
            TransactionCustomer recipient = new TransactionCustomer(chuncks[6],new BigDecimal(chuncks[7]),new BigDecimal(chuncks[8]));

            boolean isFraud = "1".equals(chuncks[9]);
            boolean isFlaggedFraud = "1".equals(chuncks[10]);

            return Optional.of(new Transaction(step,type,amount,origin,recipient,isFraud,isFlaggedFraud));
        } catch (Exception e) {
            System.out.println("Error while parsing, line " + line + e);
            return Optional.empty();
        }
    }

    public void setMaxTransactions(int maxTransactions) {
        this.maxTransactions = maxTransactions;
    }
}
