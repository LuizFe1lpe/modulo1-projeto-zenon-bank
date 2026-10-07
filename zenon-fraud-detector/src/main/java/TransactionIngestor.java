import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TransactionIngestor {
    public List<Transaction> getDataByFileName(String fileName){
        Path  path = Paths.get(fileName);
        List<Transaction> lines = List.of(new Transaction[1000]);
        return lines;
    }
}
