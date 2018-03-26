package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        
    }
    
    @Test
    public void kassanRahamaaraJaMyytyjenLounaidenMaaraOnOikein(){
        assertTrue(100000==kassa.kassassaRahaa() && 0==(kassa.edullisiaLounaitaMyyty()+kassa.maukkaitaLounaitaMyyty()));
    }
    
    @Test
    public void kateisostoToimiiEdellisenLounaanKohdalla(){
        int takaisin = kassa.syoEdullisesti(500);
        assertTrue(takaisin==260 && 
                kassa.kassassaRahaa()==(100000+240) && 
                kassa.edullisiaLounaitaMyyty()==1);
    }
    
    @Test 
    public void kateisostoToimiiMaukkaanLounaanKohdalla(){
        int takaisin = kassa.syoMaukkaasti(500);
        assertTrue(100==takaisin && kassa.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test 
    public void josMaksuRiittaaKassanRahanmaaraKasvaaJaVaihtorahanSuuruusOnOikea() {
        int takaisin = kassa.syoMaukkaasti(500);
        assertTrue(100==takaisin && kassa.kassassaRahaa()==100400);
    }
    
    @Test 
    public void josMaksuOnRiittäväMyytyjenLounaidenMääräKasvaa() {
        kassa.syoMaukkaasti(1000);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }
    @Test
    public void maksuEiOleRiittava() {
        int vaihtoraha = kassa.syoMaukkaasti(100);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0 && 
                kassa.kassassaRahaa()==100000 &&
                vaihtoraha==100);
    }
    @Test
    public void kortillaOstetaanEdullisenLounaan() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }
    @Test
    public void kortillaOstetaanMaukkaanLounaan() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    @Test
    public void korillaOnTarpeeksiRahaaVeloitetaanSummanJaPalautetaanTrue() {
        boolean a = kassa.syoMaukkaasti(kortti);
        assertTrue(a && kortti.saldo()==600);
        
    }
    @Test
    public void korillaOnTarpeeksiRahaaJaMyytyjenLounaidenMaaraMuuttuu() {
        assertTrue(kassa.syoMaukkaasti(kortti) && 
                kassa.maukkaitaLounaitaMyyty()==1 &&
                kassa.edullisiaLounaitaMyyty() == 0);   
    }
    @Test
    public void kortillaEiOleTarpeeksiRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==200 && 
                kassa.maukkaitaLounaitaMyyty() == 2 &&
                !kassa.syoMaukkaasti(kortti));
    }
    @Test
    public void ostetaanKortillaJaKassassaOlevaRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa()==100000);
    }
    @Test
    public void ladataanRahaaKortilleJaKassanSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue(kassa.kassassaRahaa()==100010 && kortti.saldo()==1010);
    }
    
    @Test 
    public void kortilleHalutaanLadataanNegatiivisenSumman() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertTrue(kortti.saldo()==1000);
    }
    @Test
    public void edellisenLounaanOstaminenEiOnnistuJosKortillaEiRiitaRahaa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty()==4);
    }
    @Test
    public void edellisenLounaanOstaminenEiOnnistuJosEiRiitaKateista() {
        kassa.syoEdullisesti(10);
        assertTrue(kassa.edullisiaLounaitaMyyty()==0 && kassa.syoEdullisesti(10)==10);
    }
}
