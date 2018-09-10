package com.example.demo.support.domain;

import com.example.demo.domain.Stock;

import java.sql.*;

public class Connecting {
        private Connection conn = null;
        private Statement stmt = null;
        private PreparedStatement pstmt = null;
        private ResultSet rs = null;

        public Connecting() {
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance(); // JDBC 드라이버 로드
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock?characterEncoding=UTF-8&serverTimezone=UTC", "jimmy", "12345");//URL,UID,PWD
                if(conn==null){
                    System.out.println("연결실패");
                }else{
                    System.out.println("연결성공");
                    Statement statement = conn.createStatement();
                    Stock stock = new Stock("naver","760,000", "1000","10%", "https://www.naver.com");
                    String query = "insert into stock (name, price, change_Money, change_Percent, detail_Url) values('" + stock.getName() + "','" + stock.getPrice() + "','" + stock.getChangeMoney() + "','" +  stock.getChangePercent() + "','" + stock.getDetailUrl() + "')";
                    System.out.println("query : " + query);
                    statement.execute(query);
                    statement.close();
                }
            }catch(ClassNotFoundException ce){
                ce.printStackTrace();
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(rs!=null)
                        rs.close();
                    if(pstmt!=null)
                        pstmt.close();
                    if(stmt!=null)
                        stmt.close();
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se2){
                    se2.printStackTrace();
                }
            }
        }

    public Connection getConn() {
        return conn;
    }
}
