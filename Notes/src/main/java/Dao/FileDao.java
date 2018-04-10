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
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private User user;
    public FileDao(Database db, User user){
        this.db = db;
        this.user = user;
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
        long dateCreated = rs.getLong("dateCreated");
        long dateLastChanged = rs.getLong("dateLastChanged");
        
        rs.close();
        stmt.close();
        conn.close();

        return new File(id, userId, 
                this.longToCalendar(dateCreated), 
                this.longToCalendar(dateLastChanged));
    }

    @Override
    public List<File> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM File WHERE userId = ?");
        stmt.setObject(1, this.user.getId());

        ResultSet rs = stmt.executeQuery();
        List<File> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new File(rs.getInt("id"), this.user.getId(), 
                    this.longToCalendar(rs.getLong("dateCreated")),
                    this.longToCalendar(rs.getLong("dateLastChanged"))));
        }
        rs.close();
        stmt.close();
        conn.close();

        return list;
    }

//    public File saveOrUpdate(File object) throws SQLException {
//        if (object.getId()==-1) {
//            // object is not saved in the database
//            return this.save(object);
//        } else {
//            return this.update(object);
//        }
//    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public File save(File object) throws SQLException { 
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO File"
                + " (userId, dateCreated, dateLastChanged, text)"
                + " VALUES (?,?,?,?)");
        stmt.setInt(1, object.getUserId());
        long date = this.calendarToLong(object.getDateCreated());
        stmt.setLong(2, date);
        stmt.setLong(3, date);
        stmt.setString(4, "");

        stmt.executeUpdate();
        stmt.close();
 
        stmt = conn.prepareStatement("SELECT * FROM File"
                + " WHERE userId = ? AND dateCreated = ?");
        stmt.setInt(1, object.getUserId());
        stmt.setLong(2, date);
 
        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos
        int id = rs.getInt("id");
        int userId = rs.getInt("userId");
        stmt.close();
        rs.close();
        conn.close();
        Calendar cal = this.longToCalendar(date);
        return new File(id, userId, cal, cal);        
    }

    public void update(File object, String text) throws SQLException {
        int id = object.getId();
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE File"
                + " SET dateLastChanged = ?, text = ? WHERE id = ?");
        Calendar dateLastChanged = Calendar.getInstance();
        stmt.setLong(1, this.calendarToLong(dateLastChanged));
        stmt.setString(2, text);
        stmt.setInt(3, id);
        stmt.executeUpdate();
        
//        return new File(id, object.getUserId(), 
//                object.getDateCreated(), dateLastChanged);
    }
    
   
    
    public Calendar longToCalendar(long a) {
        int second = (int) (a%100);
        a = a/100;
        int minute = (int) (a%100);
        a = a/100;
        int hour = (int) (a%100);
        a = a/100;
        int day = (int) (a%100);
        a = a/100;
        int b = (int) a;
        int month = (b%100)-1;
        b = b/100;
        int year = b + 2000;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);
        return cal;
    }
    
    public long calendarToLong(Calendar cal) {
        return ((long) cal.get(Calendar.SECOND)+cal.get(Calendar.MINUTE)*100+
                cal.get(Calendar.HOUR_OF_DAY)*10000+ cal.get(Calendar.DATE)*1000000+
                cal.get(Calendar.MONTH)*100000000+((cal.get(Calendar.YEAR)%100)*10000000000L));
    }

    @Override
    public File saveOrUpdate(File object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
