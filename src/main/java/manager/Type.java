package manager;

import manager.database.DML;

import java.sql.*;

public class Type {

    private DML dml = new DML();
    void insertTypeOnly(String type) throws ClassNotFoundException{
            dml.dataManipulation("INSERT INTO type VALUES (NULL,'"+type+"',NULL);");
    }

    void insert(String type, String information) throws ClassNotFoundException{
        dml.dataManipulation("INSERT INTO type VALUES (NULL,'"+type+"','"+information+"');");
    }

    void delete(double id) throws ClassNotFoundException{
            dml.dataManipulation("DELETE FROM type WHERE id_type='"+id+"';");
    }

    void selectAll()throws ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type");

            conn.setAutoCommit(false);

            conn.commit();

            while(rs.next()) {
                System.out.println(rs.getInt("id_type")
                        + " : " + rs.getString("type")
                        + " , " + rs.getString("information"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

