/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.notes;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alisaelizarova
 */
public class File {
    int id;
    int userId;
    Calendar dateCreated;
    Calendar dateLastChanged;
//    Text text;
    int textLimit;
    PictureLimit pictureLimit;

    //the file is not in the database
    public File(int userId, Calendar date) {
        this.id = -1;
        this.userId = userId;
        this.dateCreated = date;
        this.dateLastChanged = date;
        this.textLimit = 500;
        this.pictureLimit = new PictureLimit();
    }
    public File(int id, int userId, Calendar dateCreated, 
            Calendar dateChanged) {
        this.id = id;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.dateLastChanged = dateChanged;
        this.textLimit = 500;
        this.pictureLimit = new PictureLimit();
    }
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }
    
    public Calendar getDateLastChanged() {
        return this.dateLastChanged;
    }
    
    public int getTextLimit() {
        return textLimit;
    }

    public PictureLimit getPictureLimit() {
        return pictureLimit;
    }
    
    
    
}
