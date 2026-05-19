import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Calendar {
    // ターミナルで色をつけるための特殊な文字列（ANSIエスケープシーケンス）
    public static final String RESET = "\u001b[0m";  // 色を元に戻す
    public static final String RED = "\u001b[31m";    // 文字を赤色にする
    public static final String BLUE = "\u001b[34m";   // 文字を青色にする

    public static void display(ArrayList<Transaction> transactions, int year, int month) throws IllegalArgumentException {
        YearMonth yearMonth = YearMonth.of(year, month);

        // 収入データ（青色で表示）
        //ArrayList<Transaction>から日付と金額を抽出して、日付をキー、金額を値とするMapに格納する
        Map<Integer, Integer> incomes = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.TransactionKind.INCOME) {
                int day = transaction.getDate().getDayOfMonth();
                int amount = transaction.getAmount();
                // 同じ日に複数の収入がある場合は、金額を合算する
                incomes.put(day, incomes.getOrDefault(day, 0) + amount);
            }
            if (transaction.getDate().getMonthValue() != yearMonth.getMonthValue() || transaction.getDate().getYear() != yearMonth.getYear()) {
                throw new IllegalArgumentException("すべての取引は同じ年月でなければなりません");
            }
        }

        // 支出データ（赤色で表示）
        Map<Integer, Integer> expenses = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.TransactionKind.EXPENSE) {
                int day = transaction.getDate().getDayOfMonth();
                int amount = transaction.getAmount();
                // 同じ日に複数の支出がある場合は、金額を合算する
                expenses.put(day, expenses.getOrDefault(day, 0) + amount);
            }
            if (transaction.getDate().getMonthValue() != yearMonth.getMonthValue() || transaction.getDate().getYear() != yearMonth.getYear()) {
                throw new IllegalArgumentException("すべての取引は同じ年月でなければなりません");
            }
        }

        String separator = "+---------+---------+---------+---------+---------+---------+---------+";

        System.out.println("                               " + yearMonth.getYear() + "年 " + yearMonth.getMonthValue() + "月 家計簿");
        System.out.println(separator);
        System.out.println("|   日    |   月    |   火    |   水    |   木    |   金    |   土    |");
        System.out.println(separator);

        LocalDate firstDay = yearMonth.atDay(1);
        int dayOfWeekValue = firstDay.getDayOfWeek().getValue();
        int spaces = (dayOfWeekValue == 7) ? 0 : dayOfWeekValue;
        int lengthOfMonth = yearMonth.lengthOfMonth();

        int currentDay = 1;

        while (currentDay <= lengthOfMonth) {
            // 1日を3段（日付、収入、支出）で表現するため、3つの行を準備
            StringBuilder dateLine = new StringBuilder("|");
            StringBuilder incomeLine = new StringBuilder("|");
            StringBuilder expenseLine = new StringBuilder("|");

            for (int i = 0; i < 7; i++) {
                if ((currentDay == 1 && i < spaces) || currentDay > lengthOfMonth) {
                    // 空白の日
                    dateLine.append("         |");
                    incomeLine.append("         |");
                    expenseLine.append("         |");
                } else {
                    // 1段目：日付
                    dateLine.append(String.format(" %-8d|", currentDay));
                    
                    // 2段目：収入（青）
                    Integer income = incomes.get(currentDay);
                    if (income != null) {
                        String formatted = String.format("%6d円", income);
                        // 色を変えてから文字を足し、最後に色をリセットする
                        incomeLine.append(BLUE).append(formatted).append(RESET).append(" |");
                    } else {
                        incomeLine.append("       - |");
                    }

                    // 3段目：支出（赤）
                    Integer expense = expenses.get(currentDay);
                    if (expense != null) {
                        String formatted = String.format("%6d円", expense);
                        // 色を変えてから文字を足し、最後に色をリセットする
                        expenseLine.append(RED).append(formatted).append(RESET).append(" |");
                    } else {
                        expenseLine.append("       - |");
                    }

                    currentDay++;
                }
            }

            // 1週間分（3行）を出力して、下の枠線を引く
            System.out.println(dateLine.toString());
            System.out.println(incomeLine.toString());
            System.out.println(expenseLine.toString());
            System.out.println(separator);
        }
    }
}