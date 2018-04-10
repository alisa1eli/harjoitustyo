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
    public String getDateCreatedYearMonthDay() {
        return this.getFromCalendarYearMonthDay(this.dateCreated);
    }
    public String getDateCreatedHourMinuteSecond() {
        return this.getFromCalendarHoutMinuteSecond(this.dateCreated);
    }
    public String getDateLastChangedYearMonthDay() {
        return this.getFromCalendarYearMonthDay(this.dateLastChanged);
    }
    public String getDateLastChangedHourMinuteSecond() {
        return this.getFromCalendarHoutMinuteSecond(this.dateLastChanged);
    }
    public String getFromCalendarHoutMinuteSecond(Calendar cal) {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        
        return this.numberIsSmallerThanTen(hour)+":"+
                this.numberIsSmallerThanTen(minute)+":"+
                this.numberIsSmallerThanTen(second);
    }
    public String getFromCalendarYearMonthDay(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        return this.numberIsSmallerThanTen(year)+"-"+
                this.numberIsSmallerThanTen(month)+"-"+
                this.numberIsSmallerThanTen(day);
    }
    public String numberIsSmallerThanTen(int a) {
        if (a < 10) {
            return "0"+a;
        } 
        return a+"";
    }
    
    public int getTextLimit() {
        return textLimit;
    }

    public PictureLimit getPictureLimit() {
        return pictureLimit;
    }
    @Override
    public String toString() {
        return this.id + " belongs to " + this.userId + ". Created "+this.getDateCreatedYearMonthDay();
    }

    public void setDateLastChanged(Calendar a) {
        this.dateLastChanged = a;
    }
    
    
}
