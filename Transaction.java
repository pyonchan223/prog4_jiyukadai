package prog4_jiyukadai;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    // 収支の種類を表す列挙型
    public enum TransactionKind {
        EXPENSE, INCOME
    }

    // カテゴリを表す列挙型
    public enum Category {
        FOOD, TRANSPORT, DAILYGOODS, UTILITIES, RENT, PHONE, ENTERTAINMENT,
        SALARY, BONUS, SIDEINCOME, INVESTMENT, ALLOWANCE, OTHER
    }

    private int id;
    private final Category category;
    private final int amount;
    private final TransactionKind kind;
    private final LocalDate date;

    // 新規作成時に使用するコンストラクタ
    // idはデータベースで自動生成されるため、コンストラクタには含めない
    // requiredなフィールドはnullを許容しないようにする
    public Transaction(Category category, int amount, TransactionKind kind, LocalDate date) {
        this.category = Objects.requireNonNull(category, "カテゴリはnullであってはなりません");
        if (amount < 0) {
            throw new IllegalArgumentException("金額は0以上でなければなりません");
        }
        this.amount = amount;
        this.kind = Objects.requireNonNull(kind, "種類はnullであってはなりません");
        this.date = Objects.requireNonNull(date, "日付はnullであってはなりません");
    }

    // データベースから読み込む際に使用するコンストラクタ
    public Transaction(int id, Category category, int amount, TransactionKind kind, LocalDate date) {
        this.id = id;
        this.category = Objects.requireNonNull(category, "カテゴリはnullであってはなりません");
        if (amount < 0) {
            throw new IllegalArgumentException("金額は0以上でなければなりません");
        }
        this.amount = amount;
        this.kind = Objects.requireNonNull(kind, "種類はnullであってはなりません");
        this.date = Objects.requireNonNull(date, "日付はnullであってはなりません");
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionKind getKind() {
        return kind;
    }

    public Category getCategory() {
        return category;
    }

    public TransactionKind getType() {
        return kind;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", category=" + category +
                ", amount=" + amount +
                ", type=" + kind +
                ", date=" + date +
                '}';
    }
}