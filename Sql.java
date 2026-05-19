package prog4_jiyukadai;

import java.sql.*;
import java.util.*;

public class sql {
    private static final String db = "accountBook";
    public static Connection con = null;
    PreparedStatement pstmt = null;
    
    public static void sqlInit() {
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:"+db);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Transaction readDB() {
        return null; //テスト
    }

    public static ArraList<Transaction> readDBAll(){
        ArrayList<Transaction> list = new ArraList<>();
        return list;  //テスト
    }




}
//aaaaa