import java.util.*;

public class Table {
    //列挙型と日本語を対応させる
    private static final Map<Transaction.Category, String> CATEGORY_MAP = new HashMap<>();
    static {
        CATEGORY_MAP.put(Transaction.Category.FOOD, "食費");
        CATEGORY_MAP.put(Transaction.Category.TRANSPORT, "交通費");
        CATEGORY_MAP.put(Transaction.Category.DAILYGOODS, "日用品");
        CATEGORY_MAP.put(Transaction.Category.UTILITIES, "光熱費");
        CATEGORY_MAP.put(Transaction.Category.RENT, "家賃");
        CATEGORY_MAP.put(Transaction.Category.PHONE, "電話代");
        CATEGORY_MAP.put(Transaction.Category.ENTERTAINMENT, "娯楽費");
        CATEGORY_MAP.put(Transaction.Category.SALARY, "給与");
        CATEGORY_MAP.put(Transaction.Category.BONUS, "ボーナス");
        CATEGORY_MAP.put(Transaction.Category.SIDEINCOME, "副業");
        CATEGORY_MAP.put(Transaction.Category.INVESTMENT, "投資");
        CATEGORY_MAP.put(Transaction.Category.ALLOWANCE, "お小遣い");
        CATEGORY_MAP.put(Transaction.Category.OTHER, "その他");
    }

    public static void display(ArrayList<Transaction> transactions, int year, int month) {
        // 取引を日付ごとにグループ化するためのMapを作成
        Map<String, List<Transaction>> transactionsByDate = new HashMap<>();
        for (Transaction transaction : transactions) {
            String dateKey = transaction.getDate().toString(); // 取引の日付をキーとして使用
            transactionsByDate.putIfAbsent(dateKey, new ArrayList<>());
            transactionsByDate.get(dateKey).add(transaction);
        }

        // グループ化された取引を表示
        for (Map.Entry<String, List<Transaction>> entry : transactionsByDate.entrySet()) {
            String date = entry.getKey();
            List<Transaction> dailyTransactions = entry.getValue();

            System.out.println(date);
            for (Transaction transaction : dailyTransactions) {
                String categoryName = CATEGORY_MAP.get(transaction.getCategory());
                
                // 1. カテゴリ名を最大4文字（全角）に揃えるため、不足分を全角スペースで埋める
                while (categoryName.length() < 4) {
                    categoryName += "　";
                }

                // 2. printfを使って金額を右詰め（ここでは7桁）で表示する
                // %s: カテゴリ名, %7d: 金額を7桁分の幅で右詰め, %n: プラットフォーム依存の改行
                System.out.printf("  %s %7d円%n", categoryName, transaction.getAmount());
            }
        }
    }
}