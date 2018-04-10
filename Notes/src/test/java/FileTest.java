/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tikape.notes.File;

/**
 *
 * @author alisaelizarova
 */
public class FileTest {
    Calendar date;
    File file;
    public FileTest() {
        this.date = Calendar.getInstance();
        date.set(2010, 10, 01, 12, 13, 14);
        this.file = new File(101, date);
        
    }    
    @Before
    public void setUp() {
    }
    
    @Test 
    public void newFileCanBeCreated() {
        assertEquals("-1 belongs to 101. Created 2010-10-01",file.toString());
    }
    @Test
    public void numberIsSmallerThatTenContainsTwoSings() {
        assertEquals("01", file.numberIsSmallerThanTen(1));
    }
    @Test
    public void numberIsBiggerThanTenDoesNotChange() {
        assertEquals("11", file.numberIsSmallerThanTen(11));
    }
    @Test
    public void dateIsShownAsItShoudTo() {
        assertEquals("2010-10-01 12:13:14", file.getDateCreatedYearMonthDay()+" "
        +file.getDateCreatedHourMinuteSecond());
    }
    @Test
    public void dateCreatedIsEqualToDateLastChanged() {
        assertEquals(file.getDateCreatedYearMonthDay()+" "+file.getDateCreatedHourMinuteSecond(),
                file.getDateLastChangedYearMonthDay()+" "+file.getDateLastChangedHourMinuteSecond());
    }
    @Test
    public void dateLastChangedCanBeChanged() {
        Calendar newDate = Calendar.getInstance();
        newDate.set(2010,10,2,12,13,14);
        file.setDateLastChanged(newDate);
        assertEquals("2010-10-02 12:13:14",
                file.getDateLastChangedYearMonthDay()+" "+
                        file.getDateLastChangedHourMinuteSecond());
    }
    
}
