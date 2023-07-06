package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;

import java.util.Random;

/**
 * Trieda Nitro(potomok triedy ZberatelnyItem), objekt hry, ktorý hlavné auto môže "zobrať" a zrýchli auto.
 *
 * @author Jakub Mišina
 */
public class Nitro extends ZberatelnyItem {
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka, náhodného čísla, prav.hodnoty či vybuchuje a hodnoty zrýchlenia.
     * @param nazov String
     * @param cislo Random
     */
    public Nitro(String nazov, Random cislo) {
        super(nazov, cislo, false, 10);
    }
    /**
     * (Nitro) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    @Override
    public void koliduje(Hra hra) {
        hra.getHudba().setFile(3);
        hra.getHudba().play();
    }
    /**
     * (Nitro) Vykoná sa metóda s abilitou itemu.
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
