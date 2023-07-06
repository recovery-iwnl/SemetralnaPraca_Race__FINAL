package sk.uniza.fri.ui;

/**
 * Tri pevne určené inštancie (obtiažnosti).
 * 
 * @author Jakub Mišina
 */
public enum Obtiaznosti {
    EASY(1),
    NORMAL(2),
    HARD(3);
    private final int maxSkore;
    /**
     * Konštruktor.
     * @param maxSkore int
     */
    Obtiaznosti(int maxSkore) {
        this.maxSkore = maxSkore;
    }
    
    /**
     * Vracia index maximálne skóre Obtiažnosti.
     */
    public int getMaxSkore() {
        return this.maxSkore;
    }

}
