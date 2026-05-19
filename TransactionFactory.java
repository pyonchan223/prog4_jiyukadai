package prog4_jiyukadai;

import java.time.LocalDate;

//sealed インターフェースでFactory Method パターンを実装
public sealed interface TransactionFactory permits IncomeTransactionFactory, ExpenseTransactionFactory {
    
    static TransactionFactory of(Transaction.Type type) {
        return switch(type) {
            case INCOME -> new IncomeTransactionFactory();
            case EXPENSE -> new ExpenseTransactionFactory();
        };
    }
    
    Transaction createTransaction(
            Transaction.Category category,
            int amount,
            LocalDate date);
}
