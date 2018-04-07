/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.notes;

/**
 *
 * @author alisaelizarova
 */
class PictureLimit {
    int sizeMb;
    int sizePxY;
    int sizePxX;
    String format;
    
    public PictureLimit() {
        this.sizeMb =4;
        this.sizePxX = 100;
        this.sizePxY = 100;
        this.format = "";
    }

    public int getSizeMb() {
        return sizeMb;
    }

    public int getSizePxY() {
        return sizePxY;
    }

    public int getSizePxX() {
        return sizePxX;
    }

    public String getFormat() {
        return format;
    }
}
