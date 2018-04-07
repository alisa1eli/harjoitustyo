/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import tikape.notes.User;

/**
 *
 * @author alisaelizarova
 */
public class UserTest {
    
    public UserTest() {
    }
    @Before
    public void setUp() {
    }
    
    @Test
    public void userCanBeCreatedWithEmailAndPassword() {
        User u = new User("email@email.com", "Password1");
        assertTrue(u.getEmail().equals("email@email.com")&&
                u.getPassword().equals("Password1")
                &&u.getName().equals(""));
    }
    @Test
    public void userCanBeCreatedWithEmailPasswordAndName() {
        User u = new User("email@email.com", "Password1","user");
        assertTrue(u.getEmail().equals("email@email.com")&&u.getName().equals("user"));
    }
    @Test
    public void userCanBeCreatedWithIdEmailPasswordNameAndUserType() {
        User u = new User(1,"email@email.com", "Password1","user",1);
        assertTrue(u.getEmail().equals("email@email.com")&&
                u.getName().equals("user")&&u.getId()==1&&
                u.getUserType()==1);
    }
    @Test
    public void ifUserNotSavedInDatabaseTheyUserTypeIsOne() {
        User u = new User("email@email.com", "Password1");
        assertTrue(u.getEmail().equals("email@email.com")&&
                u.getUserType()==1);
    }
    @Test
    public void emailCanBeChanged() {
        User u = new User("email@email.com", "Password1");
        u.setEmail("new@email.com");
        assertTrue(u.getEmail().equals("new@email.com"));
    }
    @Test
    public void passwordCanBeChanged() {
        User u = new User("email@email.com", "Password1");
        u.setPassword("pass");
        assertTrue(u.getPassword().equals("pass"));
    }
    @Test
    public void nameCanBeChanged() {
        User u = new User("email@email.com", "Password1", "name");
        u.setName("newName");
        assertTrue(u.getName().equals("newName"));
    }
}
