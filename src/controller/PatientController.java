package controller;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {
    private static PatientController INSTANCE;
    private static final Object lockObject = new Object();

    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;

    private PatientController() {
    }
    public static PatientController getInstance() {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new PatientController();
                }
            }
        }
        return INSTANCE;
    }

    public Patient insertPatient(String id, String name, String birthdate, String phone, String location, String status, String type) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            ps=con.prepareStatement("insert into patient (id, name, birthdate, phone, location, status, type) values(?,?,?,?,?,?,?)");
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,birthdate.isEmpty() ? null : birthdate);
            ps.setString(4,phone);
            ps.setString(5,location);
            ps.setString(6,status);
            ps.setString(7,type);
            ps.executeUpdate();
            con.close();
        }
        catch(SQLException se) {
            throw new SQLException("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return new Patient(id,name,birthdate,phone,location,status,type);
    }

    public Patient updatePatient(String id, String name, String birthdate, String phone, String location, String status, String type) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str="UPDATE patient SET id=" + id + ", name='" + name + "', birthdate=" + (birthdate.isEmpty() ? "null" : "'"+birthdate+"'") + ", phone='" + phone + "', location='" + location + "', status=" + status + ", type=" + type + " where id=" + id;
            stmt.executeUpdate(str);
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return new Patient(id,name,birthdate,phone,location,status,type);
    }

    public void deletePatient(String id) throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt=con.createStatement();
            String str1="delete from patient where id=" + id;
            stmt.executeUpdate(str1);
            con.close();
        }
        catch(SQLException se) {
            throw new SQLException("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
    }

    public List<Patient> getPatient(String id, String name, String birthdate, String phone, String location, String status, String type) throws Exception {
        int r1 = 0;
        List<Patient> patients = new ArrayList<Patient>();
        try
        {
            String where_clause = " where 1=1 ";
            if(!id.isEmpty())
                where_clause = where_clause + " and id=" + id;
            if(!name.isEmpty())
                where_clause = where_clause + " and name like '%" + name + "%'";
            if(!birthdate.isEmpty())
                where_clause = where_clause + " and birthdate like '%" + birthdate + "%'";
            if(!phone.isEmpty())
                where_clause = where_clause + " and phone like '%" + phone + "%'";
            if(!location.isEmpty())
                where_clause = where_clause + " and location like '%" + location + "%'";
            if(!status.isEmpty())
                where_clause = where_clause + " and status=" + status;
            if(!type.isEmpty())
                where_clause = where_clause + " and type=" + type;

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT id, name, birthdate, phone, location, status, type, (case when status = 1 then \"infected\" else \"recovered\" end) status_descr, (case when type = 1 then \"private person\" else \"public place\" end) type_descr FROM patient " + where_clause);
            while(rs.next()) {
                patients.add(new Patient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return patients;
    }
    public List<Patient> getPatientIsNeed(int isNeed) throws Exception {
        int r1 = 0;
        List<Patient> patients = new ArrayList<Patient>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
            System.out.println("Connected to database.");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String s = "SELECT id, name, birthdate, phone, location, status, type, (select sum(needed) from patient_supply where id_patient = id) needed FROM patient ";
            if(isNeed == 1)
                s += "where (select sum(needed) from patient_supply where id_patient = id) > 0";
            rs = stmt.executeQuery(s);
            while(rs.next())
            {
                patients.add(new Patient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8)));
            }
            con.close();
        }
        catch(SQLException se) {
            throw new Exception("SQL Error:"+se);
        }
        catch(Exception e) {
            throw new Exception("Error:"+e);
        }
        return patients;
    }
}
