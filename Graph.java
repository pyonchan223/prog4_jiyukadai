import java.util.*;

public class Graph {
    // ターミナルで色をつけるための特殊な文字列（ANSIエスケープシーケンス）
    public static final String RESET = "\u001b[0m"; // 色を元に戻す
    public static final String RED = "\u001b[31m"; // 文字を赤色にする
    public static final String BLUE = "\u001b[34m"; // 文字を青色にする

    public static void displayIncomes(ArrayList<Transaction> transactions, int year) throws IllegalArgumentException {
        // LinkedHashMapを使うことで、追加した順（1月〜12月）を維持します
        Map<Integer, Integer> monthlyIncomes = new LinkedHashMap<>();

        // 月ごとの収入データを抽出
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.Type.INCOME) {
                int month = transaction.getDate().getMonthValue();
                int amount = transaction.getAmount();
                monthlyIncomes.put(month, monthlyIncomes.getOrDefault(month, 0) + amount);
            }
        }
        System.out.println("【月別 収入グラフ（" + year + "年）】");
        System.out.println("------------------------------------------------------------");

        // グラフを表示するメソッドを呼び出す
        showGraph(monthlyIncomes, BLUE); 
    }   

    public static void displayExpenses(ArrayList<Transaction> transactions, int year) throws IllegalArgumentException {
        // LinkedHashMapを使うことで、追加した順（1月〜12月）を維持します
        Map<Integer, Integer> monthlyExpenses = new LinkedHashMap<>();

        // ArrayListを合計して、月ごとの支出データを抽出
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.Type.EXPENSE) {
                int month = transaction.getDate().getMonthValue();
                int amount = transaction.getAmount();
                monthlyExpenses.put(month, monthlyExpenses.getOrDefault(month, 0) + amount);
            }
        }
    
        System.out.println("【月別 支出グラフ（" + year + "年）】");
        System.out.println("------------------------------------------------------------");
        
        // グラフを表示するメソッドを呼び出す
        showGraph(monthlyExpenses, RED);
    }

    private static void showGraph(Map<Integer, Integer> monthlyExpenses, String color) {
        // 【ステップ1】 グラフの幅を決めるための「最大値」を探す
        int maxExpense = 0;
        for (int expense : monthlyExpenses.values()) {
            if (expense > maxExpense) {
                maxExpense = expense;
            }
        }

        // ターミナルで表示できるグラフの最大幅（文字数）
        int maxBarLength = 40;

        // 【ステップ2】 各月のデータをループしてグラフを出力
        for (Map.Entry<Integer, Integer> entry : monthlyExpenses.entrySet()) {
            int month = entry.getKey();
            int expense = entry.getValue();

            // 金額に合わせて「■」をいくつ表示するか計算（最大値との割合を使う）
            int barLength = (int) Math.round((double) expense / maxExpense * maxBarLength);

            // 棒（バー）の文字列を作成
            StringBuilder bar = new StringBuilder();
            for (int i = 0; i < barLength; i++) {
                bar.append("■");
            }

            // 【ステップ3】 出力形式を整えて表示
            // %2d : 月を2桁で右詰め
            // %-40s: グラフの棒を40文字分で左詰め（右端の金額の位置を揃えるため）
            // %,8d : 金額をカンマ区切りで8桁右詰め
            System.out.printf("%2d月 | %s%-" + maxBarLength + "s%s | %,8d円%n",
                    month, color, bar.toString(), RESET, expense);
        }

        System.out.println("------------------------------------------------------------");
    }

}
