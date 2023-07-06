package sk.uniza.fri.ui;

import sk.uniza.fri.defaultTriedy.Obrazok;

/**
 * Obrázky user interfacu (grafického rozhrania).
 * 
 * @author Jakub Mišina
 */
public class UI {
    private final Obrazok number1;
    private final Obrazok number2;
    private final Obrazok number3;
    private final Obrazok number4;
    private final Obrazok win;
    private final Obrazok over;
    private final Obrazok space;
    private int mS;
    private VyberObtiaznosti difficulty;

    /**
     * Vytvorí obrázky user interfacu (grafického rozhrania - skóre, ovládanie, popis).
     */
    public UI() {
        this.difficulty = new VyberObtiaznosti();
        this.mS = 0;

        Obrazok ui = new Obrazok("src/pics/ui/ui.jpg");
        ui.zmenPolohu(840, -5);

        this.win = new Obrazok("src/pics/ui/win.png");
        this.win.zmenPolohu(150, 300);
        this.win.skry();

        this.over = new Obrazok("src/pics/ui/over.png");
        this.over.zmenPolohu(150, 300);
        this.over.skry();

        this.number1 = new Obrazok("src/pics/cisla/00.png");
        this.number1.zmenPolohu(850, 150);

        this.number2 = new Obrazok("src/pics/cisla/00.png");
        this.number2.zmenPolohu(912, 150);

        Obrazok slash = new Obrazok("src/pics/ui/slash.png");
        slash.zmenPolohu(975, 150);

        this.number3 = new Obrazok("src/pics/cisla/00.png");
        this.number3.zmenPolohu(1020, 150);

        this.number4 = new Obrazok("src/pics/cisla/00.png");
        this.number4.zmenPolohu(1082, 150);

        Obrazok star = new Obrazok("src/pics/ui/star1.png");
        star.zmenPolohu(1138, 170);

        this.space = new Obrazok("src/pics/ui/spacebar1.png");
        this.space.zmenPolohu(150, 300);
        this.space.skry();
    }

    /**
     * (UI) Zmení skóre, podľa parametrov d - desiatky, j - jednotky.
     * @param d int
     * @param j int
     */
    public void zmenSkore(int d, int j) {
        this.number2.zmenObrazok("src/pics/cisla/0" + j + ".png");
        this.number1.zmenObrazok("src/pics/cisla/0" + d + ".png");
        if (d == 1) {
            this.number1.zmenPolohu(870, 150);
        } else {
            this.number1.zmenPolohu(850, 150); 
        }
    }
    
    /**
     * (UI) Zmení maximálne skóre, podľa výberu obtiažnosti.
     */
    public void maxSkore() {
        this.mS = this.difficulty.getVyber().getMaxSkore();
        if (this.mS == Obtiaznosti.EASY.getMaxSkore()) {
            this.number3.zmenObrazok("src/pics/cisla/01.png");
            this.number4.zmenPolohu(1062, 150);
            this.vynulujDifficulty();
            this.zobrazSpace();
        }

        if (this.mS == Obtiaznosti.NORMAL.getMaxSkore()) {
            this.number3.zmenObrazok("src/pics/cisla/02.png");
            this.number4.zmenPolohu(1082, 150);
            this.vynulujDifficulty();
            this.zobrazSpace();
        }

        if (this.mS == Obtiaznosti.HARD.getMaxSkore()) {
            this.number3.zmenObrazok("src/pics/cisla/03.png");
            this.number4.zmenPolohu(1082, 150);
            this.vynulujDifficulty();
            this.zobrazSpace();
        }
    }
    
    /**
     * (UI) Nastaví hodnotu výberu obtiažnosti na null.
     */
    public void vynulujDifficulty() {
        this.difficulty = null;
    }
    
    /**
     * (UI) Vracia index maximálneho skóre podľa výberu Obtiažnosti.
     */
    public int getMS() {
        return this.mS;
    }

    /**
     * (UI) Vracia názov Obtiažnosti.
     */
    public String getNazov() {
        return switch (this.mS) {
            case 1 -> Obtiaznosti.EASY.name();
            case 2 -> Obtiaznosti.NORMAL.name();
            case 3 -> Obtiaznosti.HARD.name();
            default -> null;
        };
    }
    
    /**
     * (UI) Nastaví hodnotu a zmení obrázok maximálneho skóre na 0.
     */
    public void vynulujVyber() {
        this.mS = 0;
        this.vynulujDifficulty();
        this.difficulty = new VyberObtiaznosti();
        this.number3.zmenObrazok("src/pics/cisla/00.png");
        this.number4.zmenPolohu(1082, 150);
    }
    
    /**
     * (UI) Vracia pravdivostnú hodnotu, o tom či už bola vybratá obtiažnosť.
     */
    public boolean boloVybrate() {
        return this.mS != 0;
    }
    
    /**
     * (UI) Nastaví hodnotu a zmení obrázok získaného skóre na 0.
     */
    public void vynulujSkore() {
        this.number1.zmenObrazok("src/pics/cisla/00.png");
        this.number1.zmenPolohu(850, 150);

        this.number2.zmenObrazok("src/pics/cisla/00.png");
        this.number2.zmenPolohu(912, 150);

        this.win.skry();
        this.over.skry();
    }
    
    /**
     * (UI) Zobrazí obrázok Press Space to Start.
     */
    public void zobrazSpace() {
        this.space.zobraz();
    }
    
    /**
     * (UI) Skryje obrázok Press Space to Start.
     */
    public void skrySpace() {
        this.space.skry();
    }
    
    /**
     * (UI) Zobrazí obrázok YOU WIN.
     */
    public void win() {
        this.win.zobraz();
    }
    
    /**
     * (UI) Zobrazí obrázok GAME OVER.
     */
    public void over() {
        this.over.zobraz();
    }
}
