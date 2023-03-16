package project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author User
 */
public class Database {

    private Connection con;
    private PreparedStatement prepare;
    private Statement stmt;
    private ResultSet rs;
    public static String firstname = "";
    public static String lastname = "";

    public Database() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://31.220.110.101:3306/u350327849_oop_project", "u350327849_phufaa", "Ph10100667");

        } catch (Exception e) {
            System.out.println("Connection error");
        }
    }

    public boolean signIn(String sql, String username, String password) {
        try {
            this.prepare = this.con.prepareStatement(sql);
            this.prepare.setString(1, username);
            this.prepare.setString(2, password);
            this.rs = this.prepare.executeQuery();
            if (rs.next()) {
                firstname = rs.getString(1);
                lastname = rs.getString(2);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Login Faild");
        }
        return false;
    }

    public ArrayList<String> selectData(String query, String gt) {
        ArrayList<String> result = new ArrayList<>();
        try {
            this.stmt = this.con.createStatement();
            this.rs = this.stmt.executeQuery(query);
            while (this.rs.next()) {
                String number = this.rs.getString(gt);
                result.add(number);

            }
            return result;

        } catch (Exception e) {
            System.out.println("Select Failed");
        }
        return null;
    }

    public String[][] selectAllPatient() {
        int dataLength = Integer.parseInt(this.selectData("select count(id) as `number` from Patient", "number").get(0));
        String[][] arr = new String[dataLength][7];
        try {
            this.stmt = this.con.createStatement();
            this.rs = this.stmt.executeQuery("SELECT * FROM Patient");
            int i = 0;
            while (this.rs.next()) {
                arr[i][0] = this.rs.getString("id");
                arr[i][1] = this.rs.getString("firstName") + " " + this.rs.getString("lastName");
                arr[i][2] = this.rs.getString("idCard");
                arr[i][3] = this.rs.getString("email");
                arr[i][4] = this.rs.getString("phone");
                arr[i][5] = this.rs.getString("drug");
                arr[i][6] = this.rs.getString("disease");
                i++;
            }
            return arr;
        } catch (Exception e) {
            System.out.println("Select Failed");
        }
        return null;
    }

    public String[][] selectAllReserve() {
        int dataLength = Integer.parseInt(this.selectData("select count(ID) as `number` from HistoryReserve", "number").get(0));
        String[][] arr = new String[dataLength][6];
        try {
            this.stmt = this.con.createStatement();
            this.rs = this.stmt.executeQuery("SELECT * FROM HistoryReserve");
            int i = 0;
            while (this.rs.next()) {
                arr[i][0] = this.rs.getString("patient");
                arr[i][1] = this.rs.getString("doctor");
                arr[i][2] = this.rs.getString("disease");
                arr[i][3] = this.rs.getString("detail");
                arr[i][4] = this.rs.getString("ap_time");
                arr[i][5] = this.rs.getString("ap_date");
                i++;
            }
            return arr;
        } catch (Exception e) {
            System.out.println("Select Failed");
        }
        return null;
    }

    public void insertData(String query, ArrayList<String> arr) {
        try {
            this.stmt = this.con.createStatement();
            this.prepare = this.con.prepareStatement(query);
            for (int i = 0; i < arr.size(); i++) {
                this.prepare.setString(i + 1, arr.get(i));
            }
            this.prepare.executeUpdate();
            this.prepare.close();
            System.out.println("Inserted records into the table...");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> selectDataParams(String query, String qt, String get) {
        ArrayList<String> result = new ArrayList<>();
        
        try {
            this.stmt = this.con.createStatement();
            this.prepare = this.con.prepareStatement(query);
            this.prepare.setString(1, qt);
            this.rs = this.prepare.executeQuery();
            while (this.rs.next()) {
                String txt = this.rs.getString(get);
                result.add(txt);

            }
            return result;
        } catch (Exception e) {
            System.out.println("Select Params Error");
        }
        return null;
    }

    public void updateData(String query, ArrayList<String> arr) {
        try {
            this.stmt = this.con.createStatement();
            this.prepare = this.con.prepareStatement(query);
            for (int i = 0; i < arr.size(); i++) {
                this.prepare.setString(i + 1, arr.get(i));
                System.out.println(arr.get(i));
            }
            this.prepare.executeUpdate();
            this.prepare.close();
            System.out.println("Updated Successfully");

        } catch (Exception e) {
            System.out.println("Updated Failed");
        }
    }
    public void deleteData(String query, String qt){
        try {
            this.stmt = this.con.createStatement();
            this.prepare = this.con.prepareStatement(query);
            this.prepare.setString(1, qt);
            this.prepare.executeUpdate();
            System.out.println("success");
         
        } catch (Exception e) {
            System.out.println("Select Params Error");
        }
    }
            
    
}
