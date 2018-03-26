package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test 
    public void kortinSaldoToimii() {
        kortti.lataaRahaa(20);
        assertEquals(30, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(100);
        assertTrue(110==kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenToimiiSaldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiSaldoVaheneeOikeinJosRahaaOnTarpeeksiSaldoOnEnemmanKuinKymennenSenttia() {
        kortti.lataaRahaa(100);
        kortti.otaRahaa(10);
        assertEquals("saldo: 1.00", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiSaldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test 
    public void rahanOttaminenToimiiMetodiPalauttaaTrueJosRahatRiittivatJaMuutenFalse() {
        assertFalse(kortti.otaRahaa(100));
    }
    
    
}
