package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;

import java.util.Random;

/**
 * Trieda Bomba(potomok triedy ZberatelnyItem), objekt hry, ktorý hlavné auto môže "zobrať" - vybuchuje.
 *
 * @author Jakub Mišina
 */
public class Bomba extends ZberatelnyItem {
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka, náhodného čísla, prav.hodnoty či vybuchuje a hodnoty zrýchlenia.
     * @param cislo Random
     * @param nazov String
     */
    public Bomba(String nazov, Random cislo) {
        super(nazov, cislo, true, -5);
    }
    /**
     * (Bomba) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    @Override
    public void koliduje(Hra hra) {
        hra.getHudba().setFile(2);
        hra.getHudba().play();
        hra.zvysPocetBomb();
        hra.getBomba().skry();
        hra.getAuto().getObrazok().zmenObrazok("src/pics/entity/autoNaburane.png");
        if (hra.getBomba().isVybuch() && hra.getPocetB() == 2) {
            hra.setVybuch(this.isVybuch());
        }
    }
    /**
     * (Bomba) Vykoná sa metóda s abilitou itemu.
     * @param hra Hra
     */
    @Override
    public void ability(Hra hra) {
        this.skry();
        hra.getCesta().posunDole(this.getZrychlenie());
        hra.getAutoNpc().posunDole(this.getZrychlenie());
        hra.getStar().posunDole(this.getZrychlenie());
    }
}
