package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;

import java.util.Random;

/**
 * Trieda Slimak(potomok triedy ZberatelnyItem), objekt hry, ktorý hlavné auto môže "zobrať" a spomalí auto.
 *
 * @author Jakub Mišina
 */
public class Slimak extends ZberatelnyItem {
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka, náhodného čísla, prav.hodnoty či vybuchuje a hodnoty zrýchlenia.
     * @param nazov String
     * @param cislo Random
     */
    public Slimak(String nazov, Random cislo) {
        super(nazov, cislo, false, -5);
    }
    /**
     * (Slimak) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    @Override
    public void koliduje(Hra hra) {
        hra.getHudba().setFile(5);
        hra.getHudba().play();
    }
    /**
     * (Slimak) Vykoná sa metóda s abilitou itemu.
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
