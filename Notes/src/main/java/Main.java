
import Dao.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
public class Main extends Application{

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:notes.db");
//        database.init();
//        UserDao userDao = new UserDao(database);
//        User alisa = new User("alisa.elizarova@gmail.com", "passwordRu", "Alice");
//        alisa = userDao.saveOrUpdate(alisa);
//        alisa.setPassword("newPasswordRu");
//        userDao.saveOrUpdate(alisa);
//        User alisaa = new User("alisa.elizarova@gmail.com", "trr", "Alir");
//        alisaa = userDao.saveOrUpdate(alisaa);
//        User liisa = userDao.saveOrUpdate(new User("liisa@gmail.com","pawpaw"));
//        FileDao fileDao = new FileDao(database, alisa);
//        File f = new File(1, Calendar.getInstance());
//        f = fileDao.save(f);
//        System.out.println(f.getId());
//        List<File> list = fileDao.findAll();
//        for (File a : list) {
//            System.out.println(a.getId());
//        }
//        fileDao.update(f, "Hello world");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
