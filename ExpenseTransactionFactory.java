package prog4_jiyukadai;

import java.time.LocalDate;

//支出用のFactory
public class ExpenseTransactionFactory extends TransactionFactory {

    @Override
    public Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date) {
        return new Transaction(
                category,
                amount,
                Transaction.Type.EXPENSE,
                date);
    }
}
