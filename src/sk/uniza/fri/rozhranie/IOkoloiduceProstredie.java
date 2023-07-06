package sk.uniza.fri.rozhranie;

/**
 * Interface IOkoloiduceProstredie s danými metódami, ktoré používajú Triedy AutoNpc, Cesta, ZberatelnyItem.
 *
 * @author Jakub Mišina
 */
public interface IOkoloiduceProstredie {
    void obnov();
    void posunDole(int zrychlenie);
    boolean jeCez();
}
