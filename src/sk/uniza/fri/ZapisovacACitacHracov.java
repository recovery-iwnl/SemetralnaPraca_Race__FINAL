package sk.uniza.fri;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Zapisuje a číta zo súboru.
 *
 * @author Jakub Mišina
 */
public class ZapisovacACitacHracov {
    private File subor;
    private ArrayList<String> zoznamHracov;

    /**
     * Inicializácia.
     */
    public ZapisovacACitacHracov() {
        this.zoznamHracov = new ArrayList<>();

        this.subor = new File("C:\\Users\\jaime\\Desktop\\College\\2.semester\\Informatika2\\Cvika_Ulohy\\SemetralnaPraca_Race_POLYMORFIZMUS_FINAL\\src\\tabulkaHračov");
    }

    /**
     *  (ZapisovacACitacHracov) Číta zo súboru.
     */
    private void citajZoSuboru() throws IOException {
        Scanner citac = new Scanner(this.subor);
        this.zoznamHracov.clear();
        while (citac.hasNextLine()) {
            String riadok = citac.nextLine();
            this.zoznamHracov.add(riadok);
        }
        citac.close();
    }

    /**
     *  (ZapisovacACitacHracov) Zapisuje do súboru.
     *  @param meno String
     *  @param skore int
     *  @param obtiaznost String
     */
    public void ulozDoSuboru(String meno, int skore, String obtiaznost) throws IOException {
        this.citajZoSuboru();
        PrintWriter zapisovac = new PrintWriter(this.subor);
        this.zoznamHracov.add(meno + " " + skore + " " + obtiaznost);

        for (String riadok : this.zoznamHracov) {
            zapisovac.println(riadok);
        }
        zapisovac.close();
    }
    /**
     *  (ZapisovacACitacHracov) Vracia zoznam Hráčov a skóre.
     */
    public ArrayList<String> getZoznamHracov() {
        return this.zoznamHracov;
    }
}
