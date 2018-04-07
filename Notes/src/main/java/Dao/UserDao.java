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
import java.util.List;
import tikape.notes.User;

/**
 *
 * @author alisaelizarova
 */
public class UserDao implements Dao<User, Integer> {
    private Database db;
    
    public UserDao(Database db) {
        this.db = db;
    } 
    
    @Override
    public User findOne(Integer key) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String name = rs.getString("name");
        Integer userType = rs.getInt("userType");
        

        User u= new User(id, email, password, name, userType);

        rs.close();
        stmt.close();
        connection.close();

        return u;
    }
    
    public User findOneByEmail(String email) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE email = ?");
        stmt.setObject(1, email);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        Integer userType = rs.getInt("userType");
        

        User u= new User(id, email, password, name, userType);

        rs.close();
        stmt.close();
        connection.close();

        return u;
    }
    
    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User saveOrUpdate(User object) throws SQLException {
        if (object.getId() == -1) {
            return save(object);
        } else {
            return update(object);
        }
    }
    public User save(User object) throws SQLException {
        // if there is a user with the same email, the new user won't be saved
        User alreadyExists = this.findOneByEmail(object.getEmail());
        if (alreadyExists!=null) {
            return alreadyExists;
        }
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User"
                + " (email, password, name, userType)"
                + " VALUES (?,?,?,?)");
        stmt.setString(1, object.getEmail());
        stmt.setString(2, object.getPassword());
        stmt.setString(3, object.getName());
        stmt.setInt(4, object.getUserType());
         
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
    
    // everything unless id can be changed
    // object must be fully defined
    public User update(User object) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE User SET "
                + "password = ?, email = ?, name = ? where id = ?");
        stmt.setString(1, object.getPassword());
        stmt.setString(2, object.getEmail());
        stmt.setString(3, object.getName());
        stmt.setInt(4, object.getId());
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return object;
    }
}
