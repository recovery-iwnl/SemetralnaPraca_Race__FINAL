package sk.uniza.fri.ui;

import sk.uniza.fri.defaultTriedy.Manazer;
import sk.uniza.fri.defaultTriedy.Obrazok;

/**
 * Obrázky obtiažnosti, ktoré si môžem vybrať.
 * 
 * @author Jakub Mišina
 */
public class VyberObtiaznosti {
    private final Obrazok easy;
    private final Obrazok normal;
    private final Obrazok hard;
    private final Obrazok select;
    private Obtiaznosti vyber;
    private static final int SIRKA_OBRAZKA = 300;
    private static final int DLZKA_OBRAZKA = 185;
    /**
     * Vytvorí obrázky obtiažnosti a zmení ich polohu.
     */
    public VyberObtiaznosti() {
        this.select = new Obrazok("src/pics/obtiaznosti/select.png");
        this.select.zmenPolohu(155, 25);
        this.easy = new Obrazok("src/pics/obtiaznosti/easy1.png");
        this.easy.zmenPolohu(275, 150);
        this.normal = new Obrazok("src/pics/obtiaznosti/normal1.png");
        this.normal.zmenPolohu(275, 350);
        this.hard = new Obrazok("src/pics/obtiaznosti/hard1.png");
        this.hard.zmenPolohu(275, 550);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    
    /**
     * (VyberObtiaznosti) Vráti pravdivostnú hodnotu o tom, či obrazok easy obsahuje bod s parametrami x,y.
     * @param x int
     * @param y int
     */
    public boolean obsahujeBodE(int x, int y) {
        if (x < this.easy.getPolohaX() || x > this.easy.getPolohaX() + SIRKA_OBRAZKA) {
            return false;
        }
        return y >= this.easy.getPolohaY() && y <= this.easy.getPolohaY() + DLZKA_OBRAZKA;
    } 
    
    /**
     * (VyberObtiaznosti) Vráti pravdivostnú hodnotu o tom, či obrazok normal obsahuje bod s parametrami x,y.
     * @param x int
     * @param y int
     */
    public boolean obsahujeBodN(int x, int y) {        
        if (x < this.normal.getPolohaX() || x > this.normal.getPolohaX() + SIRKA_OBRAZKA) {
            return false;
        }
        return y >= this.normal.getPolohaY() && y <= this.normal.getPolohaY() + DLZKA_OBRAZKA;
    }

    /**
     * (VyberObtiaznosti) Vráti pravdivostnú hodnotu o tom, či obrazok hard obsahuje bod s parametrami x,y.
     * @param x int
     * @param y int
     */
    public boolean obsahujeBodH(int x, int y) {       
        if (x < this.hard.getPolohaX() || x > this.hard.getPolohaX() + SIRKA_OBRAZKA) {
            return false;
        }
        return y >= this.hard.getPolohaY() && y <= this.hard.getPolohaY() + DLZKA_OBRAZKA;
    }
    
    /**
     * (VyberObtiaznosti) Vybranie súradnice s parametrami x,y, zistenie, či sme klikli na jeden z obrázkov a vybrali si tak obtiažnosť.
     * @param x int
     * @param y int
     */
    public void vyberSuradnice(int x, int y) {
        Obrazok najdeny = null;
        if (this.obsahujeBodE(x, y)) {
            najdeny = this.easy;
        }

        if (this.obsahujeBodN(x, y)) {
            najdeny = this.normal;
        }

        if (this.obsahujeBodH(x, y)) {
            najdeny = this.hard;
        }

        if (najdeny == this.easy) {
            this.skry();
            this.vyber = Obtiaznosti.EASY;
        } else if (najdeny == this.normal) {
            this.skry();
            this.vyber = Obtiaznosti.NORMAL;
        } else if (najdeny == this.hard) {
            this.skry();
            this.vyber = Obtiaznosti.HARD;    
        }
    }
    
    /**
     * (VyberObtiaznosti) Vracia výber obtiažnosti.
     */
    public Obtiaznosti getVyber() {
        return this.vyber;
    }
    
    /**
     * (VyberObtiaznosti) Skryje obrázky na výber obtiažnosti.
     */
    public void skry() {
        this.easy.skry();
        this.normal.skry();
        this.hard.skry();
        this.select.skry();
    }
}
