package sk.uniza.fri.hra;

import sk.uniza.fri.hratelnyObjekt.Auto;
import sk.uniza.fri.Hudba;
import sk.uniza.fri.okoloiduceObjekty.*;
import sk.uniza.fri.prostredie.Cesta;
import sk.uniza.fri.rozhranie.IOkoloiduceProstredie;
import sk.uniza.fri.ui.UI;
import sk.uniza.fri.ZapisovacACitacHracov;
import sk.uniza.fri.defaultTriedy.Manazer;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Zjednotí triedy a dodá hre funkčnosť (možnosť hrať hru).
 *
 * @author Jakub Mišina
 */
public class Hra {
    private final Auto auto;
    private final Cesta cesta;
    private final AutoNpc autoNPC;

    private final Star star;
    private final Nitro nitro;
    private final Bomba bomba;
    private final Slimak slimak;
    private final Oprava oprava;

    private final UI ui;
    private final Random cislo;


    private int j; //jednotky
    private int d; //desiatky
    private int skore;

    private boolean jeZ; //jeZamrazena
    private boolean spusti;

    private boolean flag;
    private boolean collect;
    private boolean collectStar;

    private boolean vybuch;
    private int pocetB;
    private int cisloItemu;

    private final Hudba hudba;

    private final ArrayList<ZberatelnyItem> zoznamPowerUps;

    private final ArrayList<IOkoloiduceProstredie> zoznamOkoloiduce;

    private final Object[][] rows;

    /**
     * Vytvorí Hru.
     */
    public Hra() {
        this.cislo = new Random();
        this.auto = new Auto("auto", this.cislo);
        Manazer manazer = new Manazer();
        this.cesta = new Cesta();
        this.autoNPC = new AutoNpc(String.valueOf(this.cislo.nextInt(4)), this.cislo);

        this.star = new Star("star", this.cislo);
        this.nitro = new Nitro("nitro", this.cislo);
        this.bomba = new Bomba("bomba", this.cislo);
        this.slimak = new Slimak("slimak", this.cislo);
        this.oprava = new Oprava("oprava", this.cislo);

        this.zoznamPowerUps = new ArrayList<>();
        this.zoznamPowerUps.add(this.oprava);
        this.zoznamPowerUps.add(this.bomba);
        this.zoznamPowerUps.add(this.slimak);
        this.zoznamPowerUps.add(this.nitro);

        this.zoznamOkoloiduce = new ArrayList<>();
        this.zoznamOkoloiduce.add(this.autoNPC);
        this.zoznamOkoloiduce.add(this.cesta);
        this.zoznamOkoloiduce.add(this.star);


        this.ui = new UI();
        manazer.spravujObjekt(this);


        this.jeZ = false;
        this.spusti = false;
        this.flag = false;
        this.collect = true;
        this.collectStar = true;

        this.vybuch = false;
        this.cisloItemu = 0;
        this.pocetB = 0;

        this.j = 0;
        this.d = 0;
        this.skore = 0;

        this.auto.zobraz();

        this.hudba = new Hudba();
        this.hudba.setFile(6);
        this.hudba.play();

        this.rows = new Object[50][3];
    }

    /**
     * (Hra) Podľa ifov sa rozhoduje o spustení stave hry (spustení, výhra a prehra, stav skóre).
     */
    public void tik() {
        // Pokial nebola vybraná obtiažnosť, tak sa hra nebude dať spustiť.
        if (!this.ui.boloVybrate()) {
            this.ui.maxSkore();
            this.spusti = false;
        }

        // Pokial je hra spustená a nie je zamrazená tak sa v tiku vykonajú príkazy.
        if (this.spusti && !this.jeZ) {
            // Posiela okoloiducim entitam spravy
            for (IOkoloiduceProstredie i : this.zoznamOkoloiduce) {
                i.posunDole(20);
                if (this.star.jeCez()) {
                    this.collectStar = true;
                    this.minusScore();
                }
                i.jeCez();
            }
            // Ak auto koliduje s hviezdou
            if (this.auto.kolidujeItem(this.star) && this.collectStar) {
                this.star.koliduje(this);
                this.collectStar = false;
            }

            this.zoznamPowerUps.get(this.cisloItemu).posunDole(20);
            // Posiela power-upom správy
            for (ZberatelnyItem z : this.zoznamPowerUps) {
                if (this.auto.kolidujeItem(z) && this.collect) {
                    z.koliduje(this);
                    this.collect = false;
                }
                if (z.jeCez()) {
                    if (this.pocetB == 1) {
                        this.cisloItemu = this.cislo.nextInt(2);
                    } else {
                        this.cisloItemu = this.cislo.nextInt(3) + 1;
                    }
                    this.collect = true;
                }
                if (!this.collect) {
                    this.zoznamPowerUps.get(this.cisloItemu).ability(this);
                }
            }


            // Podľa podmienky sa zistí či hráč zobral s Autom požadovaný počet hviezd aby hru vyhral.
            if (this.d == this.ui.getMS() && this.d != 0) {
                this.ui.win();
                this.hudba.setFile(7);
                this.hudba.play();
                this.auto.zobraz();
                this.autoNPC.skry();
                int volba = JOptionPane.showConfirmDialog(null, "YOU WON!\nPLAY AGAIN?", "YOU WIN", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                this.zapisDoTabulky();
                this.reset(volba);
            }

            // Podľa podmienky sa zistí či hráčove Auto koliduje s iným autom, ak áno tak hráč prehral.
            if (this.auto.kolidujeA(this.autoNPC) || this.vybuch) {
                this.ui.over();
                this.hudba.setFile(4);
                this.hudba.play();
                this.auto.getObrazok().zmenObrazok("src/pics/entity/autoNaburane.png");
                this.auto.zobraz();
                this.autoNPC.zobraz();
                this.hudba.setFile(8);
                this.hudba.play();
                int volba = JOptionPane.showConfirmDialog(null, "YOU LOST!\nYOUR SCORE WAS " + this.skore + ". \nPLAY AGAIN?", "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                this.zapisDoTabulky();
                this.reset(volba);
            }

            this.auto.vyrovnaj();
            this.autoNPC.zobraz();
        }

    }

    /**
     * (Hra) Zapíše do tabuľky meno, skóre a obtiaznosť hráča.
     */
    public void zapisDoTabulky() {
        String meno = JOptionPane.showInputDialog("PLAYER NAME:");
        ZapisovacACitacHracov zapis = new ZapisovacACitacHracov();
        try {
            zapis.ulozDoSuboru(meno, this.skore, this.ui.getNazov());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0;

        for (String riadok : zapis.getZoznamHracov()) {
            String[] zoznam = (riadok.split(" "));
            this.rows[index][0] = zoznam[0];
            this.rows[index][1] = zoznam[1];
            this.rows[index][2] = zoznam[2];
            index++;
        }

        Object[] cols = {"NAME", "SCORE", "DIFFICULTY"};
        JTable table = new JTable(this.rows, cols);
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "SCORES", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * (Hra) Posunie objekt Auto vlavo o pevnú dĺžku.
     */
    public void posunVlavo() {
        if (!this.jeZ) {
            this.auto.posunVlavo();
            this.auto.zmenUholL();
        }
    }

    /**
     * (Hra) Zmení skóre o -1 ak sa nezoberie hviezda.
     */
    public void minusScore() {
        if (!this.flag) {
            this.hudba.setFile(9);
            this.hudba.play();
            if ((this.d >= 1) && this.j == 0) {
                this.d--;
                this.j = 10;
            }
            if (this.j != 0) {
                this.j--;
                this.skore--;
            }
            this.ui.zmenSkore(this.d, this.j);
        }
        this.collect = true;
        this.flag = false;
    }

    /**
     * (Hra) Posunie objekt Auto vpravo o pevnú dĺžku.
     */
    public void posunVpravo() {
        if (!this.jeZ) {
            this.auto.posunVpravo();
            this.auto.zmenUholR();
        }
    }

    /**
     * (Hra) Vyhodí GUI panel a podľa volby uživatela skončí alebo vyresetuje hru.
     * @param volba int
     */
    public void reset(int volba) {
        this.ui.over();
        this.zmraz();
        switch (volba) {
            case JOptionPane.YES_OPTION -> {
                this.aktivuj();
                this.cesta.obnov();
                this.jeZ = false;
                this.skore = 0;
                this.j = 0;
                this.d = 0;

                this.autoNPC.zobraz();
                this.auto.vygenerujAuto();
                this.collect = true;
                this.collectStar = true;
                for (IOkoloiduceProstredie i : this.zoznamOkoloiduce) {
                    i.obnov();
                }
                for (ZberatelnyItem z : this.zoznamPowerUps) {
                    z.obnov();
                }
                this.ui.vynulujVyber();
                this.ui.vynulujSkore();
                this.spusti = false;
                this.vybuch = false;
                this.flag = false;
                this.pocetB = 0;
                this.auto.getObrazok().zmenObrazok("src/pics/entity/auto.png");
                for (int i = 0; i < 50; i++) {
                    this.rows[i][0] = null;
                    this.rows[i][1] = null;
                    this.rows[i][2] = null;
                }
            }
            case JOptionPane.NO_OPTION -> this.zrus();
        }
    }

    /**
     * (Hra) Spustí hru stlačením tlačidla space.
     */
    public void aktivuj() {
        this.spusti = true;
        this.ui.skrySpace();
    }

    /**
     * (Hra) Zmrazí hru (pozastaví ju).
     */
    public void zmraz() {
        this.jeZ = true;
    }

    /**
     * (Hra) Zruší hru.
     */
    public void zrus() {
        System.exit(0);
    }
    /**
     * (Hra) Zvýší počet zobratých bomb.
     */
    public void zvysPocetBomb() {
        this.pocetB++;
    }
    /**
     * (Hra) Zvýší počet jednotiek (skore).
     */
    public void zvysPocetJednotiek() {
        this.j++;
    }
    /**
     * (Hra) Zvýší počet zobratých hviezd.
     */
    public void zvysSkore() {
        this.skore++;
    }
    /**
     * (Hra) Zvýší počet desiatok (skore).
     */
    public void zvysDesiatky() {
        this.d++;
    }
    /**
     * (Hra) Vracia cestu.
     */
    public IOkoloiduceProstredie getCesta() {
        return this.cesta;
    }
    /**
     * (Hra) Vracia autoNpc.
     */
    public IOkoloiduceProstredie getAutoNpc() {
        return this.autoNPC;
    }
    /**
     * (Hra) Vracia star.
     */
    public ZberatelnyItem getStar() {
        return this.star;
    }
    /**
     * (Hra) Vracia bombu.
     */
    public ZberatelnyItem getBomba() {
        return this.bomba;
    }
    /**
     * (Hra) Vracia auto.
     */
    public Entita getAuto() {
        return this.auto;
    }
    /**
     * (Hra) Vracia pocet bomb.
     */
    public int getPocetB() {
        return this.pocetB;
    }
    /**
     * (Hra) Vracia jednotky.
     */
    public int getJ() {
        return this.j;
    }
    /**
     * (Hra) Vracia UI.
     */
    public UI getUI() {
        return this.ui;
    }
    /**
     * (Hra) Vracia desiatky.
     */
    public int getD() {
        return this.d;
    }
    /**
     * (Hra) Vracia hudbu.
     */
    public Hudba getHudba() {
        return this.hudba;
    }
    /**
     * (Hra) Setter - udáva hodnotu počtu bomb.
     * @param h int
     */
    public void setPocetB(int h) {
        this.pocetB = h;
    }
    /**
     * (Hra) Setter - udáva pravdivostu hodnotu vybuchu.
     * @param b pravdivostna hodnota
     */
    public void setVybuch(boolean b) {
        this.vybuch = b;
    }
    /**
     * (Hra) Setter - udáva hodnotu jednotkam.
     * @param i int
     */
    public void setJ(int i) {
        this.j = i;
    }
    /**
     * (Hra) Setter - udáva pravdivostu hodnotu flagu.
     * @param b pravdivostna hodnota
     */
    public void setFlag(boolean b) {
        this.flag = true;
    }
}