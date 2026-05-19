package prog4_jiyukadai;

//SQLのResultSetからTransactionオブジェクトを生成するためのマッパークラス
public class TransactionMapper {
    public static Transaction mapToTransaction(
            int id,
            Transaction.Category category,
            int amount,
            Transaction.Type type,
            java.sql.Date date) {

        return new Transaction(
                id,
                category,
                amount,
                type,
                date.toLocalDate());
    }
}
