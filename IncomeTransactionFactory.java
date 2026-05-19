package prog4_jiyukadai;

import java.time.LocalDate;

//収入用のFactory
public final class IncomeTransactionFactory extends TransactionFactory {

    @Override
    public Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date) {

        return new Transaction(
                category,
                amount,
                Transaction.Type.INCOME,
                date);
    }
}
