package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;

import java.util.Random;

/**
 * Trieda Star(potomok triedy ZberatelnyItem), objekt hry, ktorý hlavné auto môže "zobrať" a pripočíta skóre.
 *
 * @author Jakub Mišina
 */
public class Star extends ZberatelnyItem {
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka, náhodného čísla, prav.hodnoty či vybuchuje a hodnoty zrýchlenia.
     * @param nazov String
     * @param cislo Random
     */
    public Star(String nazov, Random cislo) {
        super(nazov, cislo, false, 0);
    }
    /**
     * (Star) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    @Override
    public void koliduje(Hra hra) {
        hra.getHudba().setFile(1);
        hra.getHudba().play();
        hra.getStar().skry();
        hra.zvysPocetJednotiek();
        hra.zvysSkore();
        if (hra.getJ() == 10) {
            hra.setJ(0);
            hra.zvysDesiatky();
        }
        hra.getUI().zmenSkore(hra.getD(), hra.getJ());
        hra.setFlag(true);
    }
    /**
     * (Star) Vykoná sa metóda s abilitou itemu.
     * @param hra Hra
     */
    @Override
    public void ability(Hra hra) {
    }
}
