package sk.uniza.fri.hratelnyObjekt;
import sk.uniza.fri.okoloiduceObjekty.Entita;
import sk.uniza.fri.okoloiduceObjekty.AutoNpc;
import sk.uniza.fri.okoloiduceObjekty.ZberatelnyItem;

import java.util.Random;
/**
 * Obrázok Auta s pevne určenými stranami, s ktorým sa dá pohybovať.
 * 
 * @author Jakub Mišina
 */
public class Auto extends Entita {
    private static final int SIRKA_MAIN = 85;
    private static final int DLZKA_MAIN = 170;

    /**
     * Vytvorí nový obrázok Auta, na náhodnej pozícii v smere jazdy.
     * @param nazov String
     * @param cislo Random
     */
    public Auto(String nazov, Random cislo) {
        super(nazov, cislo);
        this.vygenerujAuto();
    }

    /**
     * (Auto) Zmení svoju polohu na náhodnú pozíciu v smere jazdy.
     */
    public void vygenerujAuto() {
        int i = (super.getCislo().nextInt(200)) + 420;
        super.getObrazok().zmenPolohu(i, 800);
    }

    /**
     * (Auto) Posunie sa vlavo o pevnú dĺžku, ak nie je na takej pozícii, že by vyšlo mimo cesty.
     */
    public void posunVlavo() {
        if (super.getObrazok().getPolohaX() >= 140) {
            super.getObrazok().posunVlavo();
        }
    } 

    /**
     * (Auto) Posunie sa vpravo o pevnú dĺžku, ak nie je na takej pozícii, že by vyšlo mimo cesty.
     */
    public void posunVpravo() {
        if (super.getObrazok().getPolohaX() <= 630) {
            super.getObrazok().posunVpravo();
        }
    }

    /**
     * (Auto) Zmení svoj uhol o 10° dolava.
     */
    public void zmenUholL() {
        super.getObrazok().zmenUhol(-10);
    }

    /**
     * (Auto) Zmení svoj uhol o 10° doprava.
     */
    public void zmenUholR() {
        super.getObrazok().zmenUhol(10);
    }

    /**
     * (Auto) Zmení svoj uhol na 0°(pôvodný uhol).
     */
    public void vyrovnaj() {
        super.getObrazok().zmenUhol(0);
    }

    /**
     * (Auto) Zobrazí sa na plátne.
     */
    public void zobraz() {
        super.getObrazok().zobraz();
    }

    /**
     * (Auto) Vracia pozíciu X auta.
     */
    public int getPolohaNaCesteX() {
        return super.getObrazok().getPolohaX();
    }

    /**
     * (Auto) Vracia pozíciu Y auta.
     */
    public int getPolohaNaCesteY() {
        return super.getObrazok().getPolohaY();
    }


    /**
     * (Auto) Vráti pravdivostnú hodnotu o tom, či objekt v parametri (ZberatelnyItem) koliduje s autom.
     * @param item objekt typu ZberatelnyItem.java s ktorým koliduje Auto.
     */
    public boolean kolidujeItem(ZberatelnyItem item) {
        int pravoX = this.getPolohaNaCesteX() + SIRKA_MAIN;
        int doleY = this.getPolohaNaCesteY() + DLZKA_MAIN;
        int lavoX = this.getPolohaNaCesteX();
        int horeY = this.getPolohaNaCesteY();

        int starXL = item.getPolohaNaCesteX();
        int starYD = item.getPolohaNaCesteY() + item.getVelkost();

        return ((starYD >= horeY + 25) && (starYD <= doleY)) && ((starXL >= lavoX - 30) && (starXL <= pravoX - 20));
    }

    /**
     * (Auto) Vráti pravdivostnú hodnotu o tom, či objekt v parametri (autoNPC) koliduje s autom.
     * @param autoNPC objekt typu AutoNpc s ktorým koliduje Auto.
     */
    public boolean kolidujeA(AutoNpc autoNPC) {
        int pravoX = this.getPolohaNaCesteX() + SIRKA_MAIN;
        int doleY = this.getPolohaNaCesteY() + DLZKA_MAIN;
        int lavoX = this.getPolohaNaCesteX();
        int horeY = this.getPolohaNaCesteY();

        int autoNPCXL = autoNPC.getPolohaNaCesteX();
        int autoNPCXP = autoNPC.getPolohaNaCesteX() + autoNPC.getSirka();
        int autoNPCYH = autoNPC.getPolohaNaCesteY();
        int autoNPCYD = autoNPC.getPolohaNaCesteY() + autoNPC.getDlzka();

        return
            (((autoNPCYD >= horeY + 25) && (autoNPCYD <= doleY)) && (((autoNPCXL >= lavoX + 10) && (autoNPCXL <= pravoX - 10)) || ((autoNPCXP >= lavoX + 10) && (autoNPCXP <= pravoX + 10)))) || 
            (((autoNPCYH >= horeY) && (autoNPCYH <= doleY)) && (((autoNPCXP >= lavoX + 20) && (autoNPCXP <= pravoX - 20)) || ((autoNPCXL >= lavoX + 20) && (autoNPCXL <= pravoX - 20))));
    }   

}