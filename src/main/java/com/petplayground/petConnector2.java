package com.petplayground;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class petConnector2 {

    private final String DB_URL = "jdbc:mysql://localhost:3306/db1";
    private final String USER = "root";
    private final String PASS = "";
    private Connection connection;
    private Statement statement;

    public void setConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public void closeConnection() throws SQLException{
        connection.close();
    }

    public void select(String[] colName, String tableName) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("select ");
        for(int i = 0; i < colName.length - 1; i++) {
            sbQuery.append(colName[i]);
            sbQuery.append(", ");
        }
        sbQuery.append(colName[colName.length - 1]);
        sbQuery.append(" from ");
        sbQuery.append(tableName);
        sbQuery.append(";");
        System.out.println(sbQuery);
        ResultSet rs = statement.executeQuery(String.valueOf(sbQuery));
//        for(String col : colName) {
//            System.out.print(col + " ");
//        }
//        System.out.println("");
        while (rs.next()) {
            for(int i = 1; i <= colName.length; i++) {
                System.out.print(colName[i - 1] + ": " + rs.getString(i) + " ");
            }
            System.out.println("");
        }
    }

    public void insert(String tableName, String[] colName, String[] values) throws SQLException {
        StringBuilder sbInsert = new StringBuilder("insert into ");
        sbInsert.append(tableName);
        sbInsert.append(" (");
        for(int i = 0; i < colName.length - 1; i++) {
            sbInsert.append(colName[i]);
            sbInsert.append(", ");
        }
        sbInsert.append(colName[colName.length - 1]);
        sbInsert.append(") values('");
        for(int i = 0; i < values.length - 1; i++) {
            sbInsert.append(values[i]);
            sbInsert.append("', '");
        }
        sbInsert.append(values[values.length - 1]);
        sbInsert.append("');");
        System.out.println(sbInsert);
        int flag = statement.executeUpdate(String.valueOf(sbInsert));
        if(flag > 0)
            System.out.println("Successfully Inserted");
        else
            System.out.println("Insert Failed");
    }

    public void insert(String tableName, String[] values) throws SQLException {
        StringBuilder sbInsert = new StringBuilder("insert into ");
        sbInsert.append(tableName);
        sbInsert.append(" values('");
        for(int i = 0; i < values.length - 1; i++) {
            sbInsert.append(values[i]);
            sbInsert.append("', '");
        }
        sbInsert.append(values[values.length - 1]);
        sbInsert.append("');");
        System.out.println(sbInsert);
        int flag = statement.executeUpdate(String.valueOf(sbInsert));
        if(flag > 0)
            System.out.println("Successfully Inserted");
        else
            System.out.println("Insert Failed");
    }

    public void update(String tableName, String[] setColName, String[] setValues, String[] conColName, String[] conValues, String[] con) throws SQLException {
        StringBuilder sbUpdate = new StringBuilder("update ");
        sbUpdate.append(tableName);
        sbUpdate.append(" set ");
        for(int i = 0; i < setColName.length - 1; i++) {
            sbUpdate.append(setColName[i]);
            sbUpdate.append(" = '");
            sbUpdate.append(setValues[i]);
            sbUpdate.append("', ");
        }
        sbUpdate.append(setColName[setColName.length - 1]);
        sbUpdate.append(" = '");
        sbUpdate.append(setValues[setValues.length - 1]);
        sbUpdate.append("' where ");
        for(int i = 0; i < conColName.length - 1; i++) {
            sbUpdate.append(conColName[i]);
            sbUpdate.append(" = '");
            sbUpdate.append(conValues[i]);
            sbUpdate.append("' ");
            sbUpdate.append(con[i]);
            sbUpdate.append(" ");
        }
        sbUpdate.append(conColName[conColName.length - 1]);
        sbUpdate.append(" = '");
        sbUpdate.append(conValues[conValues.length - 1]);
        sbUpdate.append("';");
        System.out.println(sbUpdate);
        int flag = statement.executeUpdate(String.valueOf(sbUpdate));
        if(flag > 0)
            System.out.println("Successfully Updated");
        else
            System.out.println("Update Failed");
    }

    public void delete(String tableName, String[] conColName, String[] conValues, String[] con) throws SQLException {
        StringBuilder sbDelete = new StringBuilder("delete from ");
        sbDelete.append(tableName);
        sbDelete.append(" where ");
        for(int i = 0; i < conColName.length - 1; i++) {
            sbDelete.append(conColName[i]);
            sbDelete.append(" = '");
            sbDelete.append(conValues[i]);
            sbDelete.append("' ");
            sbDelete.append(con[i]);
            sbDelete.append(" ");
        }
        sbDelete.append(conColName[conColName.length - 1]);
        sbDelete.append(" = '");
        sbDelete.append(conValues[conValues.length - 1]);
        sbDelete.append("';");
        System.out.println(sbDelete);
        int flag = statement.executeUpdate(String.valueOf(sbDelete));
        if(flag > 0)
            System.out.println("Successfully Deleted");
        else
            System.out.println("Delete Failed");
    }

    public static void testSelect(petConnector2 pc) throws SQLException {
        String[] colName = {"name", "owner", "species", "sex", "birth", "death"};
        String tableName = "pet";
        pc.select(colName, tableName);
    }

    public static void testInsert(petConnector2 pc) throws SQLException {
        String tableName = "pet";
        String[] colName = {"name", "owner", "species", "sex", "birth", "death"};
        String[] values1 = {"Puffball6", "Diane6", "hamster6", "m", "1991-01-01", "2200-01-01"};
        String[] values2 = {"Puffball7", "Diane7", "hamster7", "f", "1994-01-01", "2400-01-01"};
//        pc.select(colName, tableName);
        pc.insert(tableName, colName, values1);
        pc.select(colName, tableName);
        pc.insert(tableName, values2);
        pc.select(colName, tableName);
    }

    public static void testUpdate(petConnector2 pc) throws SQLException {
        String tableName = "pet";
        String[] colName = {"name", "owner", "species", "sex", "birth", "death"};
        String[] setColName = {"owner", "species"};
        String[] setValues = {"Diane0", "hamster0"};
        String[] conColName = {"name", "owner", "sex"};
        String[] conValues = {"Puffball", "Diane2", "f"};
        String[] con = {"or", "or"};
//        pc.select(colName, tableName);
        pc.update(tableName, setColName, setValues, conColName, conValues, con);
        pc.select(colName, tableName);
    }

    public static void testDelete(petConnector2 pc) throws SQLException {
        String tableName = "pet";
        String[] colName = {"name", "owner", "species", "sex", "birth", "death"};
        String[] conColName = {"name", "owner"};
        String[] conValues = {"Puffball7", "Diane6"};
        String[] con = {"or"};
//        pc.select(colName, tableName);
        pc.delete(tableName, conColName, conValues, con);
        pc.select(colName, tableName);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        petConnector2 pc = new petConnector2();
        pc.setConnection();
        testSelect(pc);
        testInsert(pc);
        testUpdate(pc);
        testDelete(pc);
        pc.closeConnection();
    }
}
