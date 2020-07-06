package controller;

import model.PatientSupply;
import model.Supply;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplyController {
    private static SupplyController INSTANCE;
    private static final Object lockObject = new Object();

    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;

    private SupplyController() {
    }
    public static SupplyController getInstance() {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new SupplyController();
                }
            }
        }
        return INSTANCE;
    }

    public Supply insertSupply(String id, String name, String location, String device_name, String amount) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            String statement = "insert into patient_supply (id_patient, id_supply, needed, exist, need_from) values (200441467, 4, 3, 2, date('2020-06-29'));";
            ps=con.prepareStatement("insert into supply (id, name, location, device_name, amount) values(?,?,?,?,?)");
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,location);
            ps.setString(4,device_name);
            ps.setString(5,amount);
            ps.executeUpdate();
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return new Supply(id,name,location,device_name,amount);
    }

    public Supply updateSupply(String id, String name, String location, String device_name, String amount) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str="UPDATE supply SET id=" + id + ", name='" + name + "', location='" + location + "', device_name='" + device_name + "', amount=" + amount + " where id=" + id;
            stmt.executeUpdate(str);
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return new Supply(id,name,location,device_name,amount);
    }
    public void updatePatientSupply(String need, String exist, String p_id, String s_id, String amount) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str1="UPDATE patient_supply SET needed=" + need + ", exist=" + exist + " where id_patient=" + p_id + " and id_supply=" + s_id + ";";
            String str2=" UPDATE supply SET amount=" + amount + " where id=" + s_id + ";";
            stmt.executeUpdate(str1 );
            stmt.executeUpdate(str2 );
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
    }

    public void deleteSupply(String id) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str1="delete from supply where id=" + id;
            stmt.executeUpdate(str1);
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
    }
    public void deletePatientSupply(String p_id, String s_id) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str1="delete from patient_supply where id_patient=" + p_id + " and id_supply=" + s_id + ";";
            stmt.executeUpdate(str1);
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
    }

    public List<Supply> getSupply(String id, String name, String location, String device_name, String amount) throws Exception {
        int r1 = 0;
        List<Supply> supplies = new ArrayList<Supply>();
        try
        {
            String where_clause = " where 1=1 ";
            if(!id.isEmpty())
                where_clause = where_clause + " and id=" + id;
            if(!name.isEmpty())
                where_clause = where_clause + " and name like '%" + name + "%'";
            if(!location.isEmpty())
                where_clause = where_clause + " and location like '%" + location + "%'";
            if(!device_name.isEmpty())
                where_clause = where_clause + " and device_name like '%" + device_name + "%'";

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT id, name, location, device_name, amount FROM supply " + where_clause);
            while(rs.next()) {
                supplies.add(new Supply(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return supplies;
    }
    public List<PatientSupply> getSupplyForPatient(String patientId) throws Exception {
        List<PatientSupply> ps = new ArrayList<PatientSupply>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select name, device_name, location_patient, location_supply, needed, exist, s_id, s_name, id from patient_supply_view where id = " + patientId);
            while(rs.next()) {
                ps.add(new PatientSupply(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return ps;
    }
}
