package sk.uniza.fri.okoloiduceObjekty;

import sk.uniza.fri.defaultTriedy.Obrazok;

import java.util.Random;

/**
 * Trieda Entita (predok triedy ZberatelnyItem), objekt hry s ktorým sa dá mať interakciu.
 *
 * @author Jakub Mišina
 */
public abstract class Entita {
    private final Obrazok obrazok;
    private final Random cislo;
    /**
     * Vytvorí inštanciu na základe názvu obrázka a náhodného čísla.
     * @param cislo Random
     * @param nazov String
     */
    public Entita(String nazov, Random cislo) {
        this.obrazok = new Obrazok("src/pics/entity/" + nazov + ".png");
        this.cislo = cislo;
    }
    /**
     * (Entita) Vracia obrázok Entity.
     */
    public Obrazok getObrazok() {
        return this.obrazok;
    }
    /**
     * (Entita) Vracia náhodné číslo Entity.
     */
    public Random getCislo() {
        return this.cislo;
    }

}
