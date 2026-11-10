import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionIngestor {

    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getDataByFileName(String fileName) {

        try (FileInputStream fis = new FileInputStream(fileName);
             Scanner scanner = new Scanner(fis)) {

            int lineCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //IO.println(line);

                lineCount++;

                if(lineCount == 1) {
                    continue;
                }
                if(lineCount >= 1000) {
                    break;
                }

                String[] chuncks = line.split(",");

                int step = Integer.parseInt(chuncks[0]);
                TransactionType type = TransactionType.valueOf(chuncks[1]);
                BigDecimal amount = new BigDecimal(chuncks[2]);

                TransactionCustomer origin = new TransactionCustomer(chuncks[3],new BigDecimal(chuncks[4]),new BigDecimal(chuncks[5]));
                TransactionCustomer recipient = new TransactionCustomer(chuncks[6],new BigDecimal(chuncks[7]),new BigDecimal(chuncks[8]));

                boolean isFraud = "1".equals(chuncks[9]);
                boolean isFlaggedFraud = "1".equals(chuncks[10]);

                var transaction = new Transaction(step,type,amount,origin,recipient,isFraud,isFlaggedFraud);
                transactions.add(transaction);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error, file not found " + fileName + e);
        }
        return transactions;
    }

    private List<Transaction> getTransactions() {
        return transactions;
    }
}
