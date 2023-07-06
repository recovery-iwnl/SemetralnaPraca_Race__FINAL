package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;
import sk.uniza.fri.rozhranie.IOkoloiduceProstredie;

import java.util.Random;

/**
 * Trieda ZberatelnyItem(predok tried Star, Slimak, Nitro, Bomba), objekt hry, ktorý hlavné auto môže "zobrať".
 *
 * @author Jakub Mišina
 */
public abstract class ZberatelnyItem extends Entita implements IOkoloiduceProstredie {
    private static final int VELKOST = 50;
    private final boolean vybuch;
    private final int zrychlenie;
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka a náhodného čísla, a ďalších parametrov.
     * @param nazov String
     * @param cislo Random
     * @param vybuch boolean
     * @param zrychlenie int
     */
    public ZberatelnyItem(String nazov, Random cislo, boolean vybuch, int zrychlenie) {
        super(nazov, cislo);
        this.zrychlenie = zrychlenie;
        this.vybuch = vybuch;
        this.obnov();
    }
    /**
     * (ZberatelnyItem) Obnoví zberatelny item na danú pozíciu.
     */
    @Override
    public void obnov() {
        int i = (this.getCislo().nextInt(500)) + 140;
        super.getObrazok().zmenPolohu(i, -1000);
        this.zobraz();
    }
    /**
     * (ZberatelnyItem) Obnoví zberatelny item na danú pozíciu.
     * @param zrychlenie int
     */
    @Override
    public void posunDole(int zrychlenie) {
        super.getObrazok().posunZvisle(zrychlenie);
    }
    /**
     * (ZberatelnyItem) Zobrazí obrázok zberatelného itemu.
     */
    public void zobraz() {
        super.getObrazok().zobraz();
    }
    /**
     * (ZberatelnyItem) Skryje obrázok zberatelného itemu.
     */
    public void skry() {
        super.getObrazok().skry();
    }
    /**
     * (ZberatelnyItem) Vráti velkosť zberatelneho itemu.
     */
    public int getVelkost() {
        return VELKOST;
    }
    /**
     * (ZberatelnyItem) Vráti x pozíciu zberatelneho itemu.
     */
    public int getPolohaNaCesteX() {
        return super.getObrazok().getPolohaX();
    }
    /**
     * (ZberatelnyItem) Vráti y pozíciu zberatelneho itemu.
     */
    public int getPolohaNaCesteY() {
        return super.getObrazok().getPolohaY();
    }
    /**
     * (ZberatelnyItem) Vráti pravdivostú hodnotu o tom že či zberatelny item spôsobuje výbuch.
     */
    public boolean isVybuch() {
        return this.vybuch;
    }
    /**
     * (ZberatelnyItem) Vráti zrýchlenie zberatelneho itemu.
     */
    public int getZrychlenie() {
        return this.zrychlenie;
    }
    /**
     * (ZberatelnyItem) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    public abstract void koliduje(Hra hra);
    /**
     * (ZberatelnyItem) Vykoná sa metóda s abilitou itemu.
     * @param hra Hra
     */
    public abstract void ability(Hra hra);

    /**
     * (ZberatelnyItem) Zistí, či je daný zber. item mimo plátna a zmení jeho polohu.
     */
    @Override
    public boolean jeCez() {
        if (super.getObrazok().getPolohaY() >= 1000) {
            int i = (super.getCislo().nextInt(500)) + 140;
            super.getObrazok().zmenPolohu(i, -650);
            super.getObrazok().zobraz();
            return true;
        }
        return false;
    }
}
