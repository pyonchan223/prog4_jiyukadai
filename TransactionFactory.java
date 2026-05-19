package prog4_jiyukadai;

import java.time.LocalDate;

//FactorMethodを実装した
public sealed abstract class TransactionFactory permits ExpenseTransactionFactory, IncomeTransactionFactory {
    public abstract Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date);
}
