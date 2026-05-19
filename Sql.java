package prog4_jiyukadai;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;

public class sql {
    private static final String db = "accountBook";  //データベース名
    private static final String table = "book";      //テーブル名
    public static Connection con = null;
    PreparedStatement pstmt = null;
    
    public static void sqlInit() {
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + db);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //SQLのtableのデータ数を返す。
    public static int getDataNum() {
        pstmt = con.prepareStatement("SELECT COUNT(*) FROM ?");
        pstmt.setString(1,table);
        ResuleSet rs = pstmt.executeQuery();
        return rs.getInt(1);
    }
    //SQLからデータを1つ取得し、Transactionインスタンスにして返す。
    public static Transaction readDB(int id) {
        pstmt = con.prepareStatement("SELECT * FROM ? WHERE id = ?");
        pstmt.setString(1,table);
        pstmt.setInt(2,id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            String categoryName = rs.getString("category");
            Category category = Category.valueOf(categoryName);
            int amount = rs.getInt("amount");
            String typeName = rs.getString("type");
            Type type = Type.valuof(typeName);
            String dateText = rs.getString("date");
            Localdate date = LocalDate.parse(dateText);

            return new Transaction(id,category,amount,type,date);
        } else {
            return null;
        }
    }

    //SQLからデータをすべて取得し、TransactionのArrayListで返す。
    public static ArraList<Transaction> readDBAll(){
        ArrayList<Transaction> list = new ArraList<>();
        for(int id = 1; id < getDataNum(); id++) {
            list.add(readDB(id));
        }
        return list;
    }




}
//aaaaa