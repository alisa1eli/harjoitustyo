/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.FileDao;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alisaelizarova
 */
public class FileDaoTest {
    FileDao fileDao;
    public FileDaoTest() {
        this.fileDao = new FileDao(null,null);
    }
    @Before
    public void setUp() {
    }

    @Test
    public void methodLongToCalendarWorksProperly() {
        long a = 111231121516L;
        Calendar cal = this.fileDao.longToCalendar(a);
        assertEquals("2011-11-31 12:15:16",
                cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+
                        cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+
                        cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
    }
    @Test 
    public void methodCalendarToLongWorksProperly() {
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 0, 1, 12, 14, 15);
        long a = this.fileDao.calendarToLong(cal);
        long correct = 100001121415L;
        System.out.println((long)a);
        assertTrue((correct-a)==0);
    }
}
