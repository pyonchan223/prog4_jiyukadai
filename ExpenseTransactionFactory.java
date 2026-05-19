package prog4_jiyukadai;

import java.time.LocalDate;

//支出用のFactory
public final class ExpenseTransactionFactory implements TransactionFactory {

    @Override
    public Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date) {
        return new Transaction(
                category,
                amount,
                Transaction.TransactionKind.EXPENSE,
                date);
    }
}
