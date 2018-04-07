/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import tikape.notes.File;
import tikape.notes.User;

/**
 *
 * @author alisaelizarova
 */
public class FileDao implements Dao<File,Integer>{
    private Database db;
    public FileDao(Database db){
        this.db = db;
    }
    @Override
    public File findOne(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM File WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("id");
        int userId = rs.getInt("userId");        
        Calendar dateCreated = this.stringToCalendar(
                rs.getString("dateCreated"));
        Calendar dateLastChanged = this.stringToCalendar(
                rs.getString("dateLastChanged"));

        rs.close();
        stmt.close();
        conn.close();

        return new File(id, userId, dateCreated, dateLastChanged);
    }

    @Override
    public List<File> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File saveOrUpdate(File object) throws SQLException {
        if (object.getId()==-1) {
            return this.save(object);
        } else {
            return this.update(object);
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Calendar stringToCalendar(String date) {
        String a[] = date.split(" ");
        String yearMonthDay[] = a[0].split("-");
        String hourMinuteSecond[] = a[1].split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(yearMonthDay[0]), 
                Integer.parseInt(yearMonthDay[1]), 
                Integer.parseInt(yearMonthDay[2]), 
                Integer.parseInt(hourMinuteSecond[0]), 
                Integer.parseInt(hourMinuteSecond[1]), 
                Integer.parseInt(hourMinuteSecond[2]));
        
        return cal;
    }

    private File save(File object) throws SQLException { 
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO File"
                + " (userId, dateCreated, dateLastChanged)"
                + " VALUES (?,?,?)");
        stmt.setInt(1, object.getUserId());
        stmt.setString(2, object.getDateCreated().toString());
        stmt.setString(3, object.getDateLastChanged().toString());

        stmt.executeUpdate();
        stmt.close();
 
        stmt = conn.prepareStatement("SELECT * FROM User"
                + " WHERE email = ?");
        stmt.setString(1, object.getEmail());
 
        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos
 
        User u = new User(rs.getInt("id"), rs.getString("email"), 
                rs.getString("password"), 
                rs.getString("name"), rs.getInt("userType"));
 
        stmt.close();
        rs.close();
        conn.close();
 
        return u;
        
    }

    private File update(File object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
