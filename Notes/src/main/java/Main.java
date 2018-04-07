
import Dao.*;
import java.sql.SQLException;
import java.util.Calendar;
import tikape.notes.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alisaelizarova
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:notes.db");
        database.init();
//        UserDao userDao = new UserDao(database);
//        User alisa = new User("alisa.elizarova@gmail.com", "passwordRu", "Alice");
//        alisa = userDao.saveOrUpdate(alisa);
//        alisa.setPassword("newPasswordRu");
//        userDao.saveOrUpdate(alisa);
//        User alisaa = new User("alisa.elizarova@gmail.com", "trr", "Alir");
//        alisaa = userDao.saveOrUpdate(alisaa);
//        User liisa = userDao.saveOrUpdate(new User("liisa@gmail.com","pawpaw"));
//        FileDao fileDao = new FileDao(database);
//        fileDao.findOne(1);

        Calendar cal = Calendar.getInstance();
        System.out.println(cal.toString());
    }
}
