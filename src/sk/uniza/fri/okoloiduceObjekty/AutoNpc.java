package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.rozhranie.IOkoloiduceProstredie;

import java.util.Random;

/**
 * Obrázok AutaNpc s pevne určenými stranami, s ktorým sa dá pohybovať (dole).
 *
 * @author Jakub Mišina
 */
public class AutoNpc extends Entita implements IOkoloiduceProstredie {
    private boolean vSmere;
    private static final int SIRKA = 75;
    private static final int DLZKA = 157;
    private static final int DLZKA_PLATNA = 1000;

    /**
     * Vytvorí náhodne 1 zo 4 nových obrázkov AutaNPC.
     *
     * @param cislo náhodné číslo.
     * @param nazov String.
     */
    public AutoNpc(String nazov, Random cislo) {
        super(nazov, cislo);
        this.obnov();
    }

    /**
     * (AutoNpc) Zmení jeho polohu na náhodnú pozíciu na ceste a podľa nej zmení jeho uhol.
     */
    @Override
    public void obnov() {
        this.zmenPolohu(-650);
    }

    /**
     * (AutoNpc) Posunie sa dole o pevnú dĺžku, ak je v smere alebo protismere jazdy, tak sa aj posúva hore alebo dole.
     * @param zrychlenie int
     */
    @Override
    public void posunDole(int zrychlenie) {
        super.getObrazok().posunZvisle(zrychlenie);
        if (this.vSmere) {
            super.getObrazok().posunZvisle(-5);
        } else {
            super.getObrazok().posunZvisle(5);
            this.vSmere = false;
        }
    }

    /**
     * (AutoNpc) Zobrazí AutoNpc.
     */
    public void zobraz() {
        super.getObrazok().zobraz();
    }

    /**
     * (AutoNpc) Skryje AutoNpc.
     */
    public void skry() {
        super.getObrazok().skry();
    }

    /**
     * (AutoNpc) Vracia pozíciu X AutaNpc.
     */
    public int getPolohaNaCesteX() {
        return super.getObrazok().getPolohaX();
    }

    /**
     * (AutoNpc) Vracia pozíciu Y AutaNpc.
     */
    public int getPolohaNaCesteY() {
        return super.getObrazok().getPolohaY();
    }

    /**
     * (AutoNpc) Vracia šírku AutaNpc.
     */
    public int getSirka() {
        return SIRKA;
    }

    /**
     * (AutoNpc) Vracia dĺžku AutaNpc.
     */
    public int getDlzka() {
        return DLZKA;
    }

    /**
     * (AutoNpc) Zmení polohu auto.
     * @param poloha int
     */
    public void zmenPolohu(int poloha) {
        int i = (super.getCislo().nextInt(500)) + 140;
        if (i >= 370 && i <= 390) {
            i = i + 30;
            //hore + 40
        }
        super.getObrazok().zmenPolohu(i, poloha);
        if (i >= 140 && i < 370) {
            super.getObrazok().zmenUhol(180);
            this.vSmere = false;
        } else {
            super.getObrazok().zmenUhol(0);
            this.vSmere = true;
        }
    }

    /**
     * (AutoNpc) Zistí, ak je mimo plátna, tak zmení jeho obrázok a jeho polohu na náhodnú pozíciu nad plátnom, podľa nej zmení jeho uhol a zobrazí ho.
     */
    @Override
    public boolean jeCez() {
        if (super.getObrazok().getPolohaY() >= DLZKA_PLATNA) {
            int a = (super.getCislo().nextInt(4));
            super.getObrazok().zmenObrazok("src/pics/entity/" + a + ".png");
            this.zmenPolohu(-150);
            super.getObrazok().zobraz();
            return true;
        }
        return false;
    }
}