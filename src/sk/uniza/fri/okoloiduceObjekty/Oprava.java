package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.hra.Hra;

import java.util.Random;

/**
 * Trieda Oprava(potomok triedy ZberatelnyItem), objekt hry, ktorý hlavné auto môže "opraviť".
 *
 * @author Jakub Mišina
 */
public class Oprava extends ZberatelnyItem {
    /**
     * Vytvorí inštanciu na základe zdedeného názvu obrázka a náhodného čísla, a ďalších parametrov.
     * @param nazov String
     * @param cislo Random
     */
    public Oprava(String nazov, Random cislo) {
        super(nazov, cislo, false, 0);
    }
    /**
     * (Oprava) Vykoná sa metóda pri kolízii auta so zberatelnym itemom.
     * @param hra Hra
     */
    @Override
    public void koliduje(Hra hra) {
        hra.getHudba().setFile(10);
        hra.getHudba().play();
        this.skry();
        hra.getAuto().getObrazok().zmenObrazok("src/pics/entity/auto.png");
    }
    /**
     * (Oprava) Vykoná sa metóda s abilitou itemu.
     * @param hra Hra
     */
    @Override
    public void ability(Hra hra) {
        hra.setPocetB(0);
    }
}
