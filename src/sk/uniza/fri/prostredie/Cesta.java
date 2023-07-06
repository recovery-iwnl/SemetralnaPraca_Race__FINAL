package sk.uniza.fri.prostredie;

import sk.uniza.fri.rozhranie.IOkoloiduceProstredie;
import sk.uniza.fri.defaultTriedy.Obrazok;

/**
 * 2 Obrázky Cesty, s ktorými sa dá pohybovať (dole).
 *
 * @author Jakub Mišina
 */
public class Cesta implements IOkoloiduceProstredie {
    private final Obrazok cesta;
    private final Obrazok cesta2;
    private static final int DLZKA_CESTY = 1300;

    /**
     * Vytvorí 2 nové obrázky Cesty a zmení ich polohu.
     */
    public Cesta() {
        this.cesta = new Obrazok("src/pics/cesta/road4.jpg");
        this.cesta2 = new Obrazok("src/pics/cesta/road4.jpg");
        this.obnov();
    }
    /**
     * (Cesta) Cesty sa obnovia.
     */
    @Override
    public void obnov() {
        this.cesta.zmenPolohu(0, -DLZKA_CESTY);
        this.cesta2.zmenPolohu(0, 0);
    }

    /**
     * (Cesta) Cesty sa posunú sa dole o pevnú dĺžku.
     * @param zrychlenie int
     */
    @Override
    public void posunDole(int zrychlenie) {
        this.cesta.posunZvisle(zrychlenie);
        this.cesta2.posunZvisle(zrychlenie);
    }

    /**
     * (Cesta) Vracia pozíciu Y prvej cesty.
     */
    public int getPolohaCesty() {
        return this.cesta.getPolohaY();
    }

    /**
     * (Cesta) Vracia pozíciu Y druhej cesty.
     */
    public int getPolohaCesty2() {
        return this.cesta2.getPolohaY();
    }

    /**
     * (Cesta) Zistí, ak sa prvá alebo druhá cesta posunula o svoju dĺžku, tak zmení ich pozíciu.
     */
    @Override
    public boolean jeCez() {
        if (this.getPolohaCesty() >= DLZKA_CESTY) {
            this.cesta.zmenPolohu(0, this.getPolohaCesty2() - DLZKA_CESTY);
            return true;
        } else if (this.getPolohaCesty2() >= DLZKA_CESTY) {
            this.cesta2.zmenPolohu(0, this.getPolohaCesty() - DLZKA_CESTY);
            return true;
        }
        return false;
    }
}