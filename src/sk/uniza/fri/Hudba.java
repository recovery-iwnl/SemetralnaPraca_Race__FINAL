package sk.uniza.fri;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Trieda Hudba, dodá hre funkciu zvukov/hudby.
 *
 * @author Jakub Mišina
 */
public class Hudba {
    private Clip clip;
    private final File[] file;

    /**
     * Dodá do poľa súborov, súbory zvukov.
     */
    public Hudba() {
        this.file = new File[11];
        this.file[1] = new File("src\\zvuky\\Collect.wav");
        this.file[2] = new File("src\\zvuky\\Small Bomb.wav");
        this.file[3] = new File("src\\zvuky\\NITR0.wav");
        this.file[4] = new File("src\\zvuky\\Crash.wav");
        this.file[5] = new File("src\\zvuky\\Brake.wav");
        this.file[6] = new File("src\\zvuky\\Start.wav");
        this.file[7] = new File("src\\zvuky\\WIN.wav");
        this.file[8] = new File("src\\zvuky\\OVER.wav");
        this.file[9] = new File("src\\zvuky\\LOSS.wav");
        this.file[10] = new File("src\\zvuky\\REPAIR.wav");
    }

    /**
     * (Hudba) Dá súboru funkionalitu vydávať zvuk.
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(this.file[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
            FloatControl hlasitost = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (i == 0) {
                hlasitost.setValue(-5.0f);
            } else {
                hlasitost.setValue(-15.0f);
            }
        } catch (Exception e) {

        }
    }

    /**
     * (Hudba) Začne vydávať zvuk.
     */
    public void play() {
        this.clip.start();
    }

    /**
     * (Hudba) Loopuje zvuk.
     */
    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * (Hudba) Prestane vydávať zvuk.
     */
    public void stop() {
        this.clip.stop();
    }

}
