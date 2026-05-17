package prog4_jiyukadai;

import java.time.LocalDate;

//FactorMethodを実装した
public abstract class TransactionFactory {
    public abstract Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date);
}
