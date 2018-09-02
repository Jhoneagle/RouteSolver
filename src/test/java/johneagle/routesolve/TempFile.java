package johneagle.routesolve;

import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Only Super class for actual junit test classes.
 * Gives them same support for to do preparations and end work without repeating it in every one of them.
 *
 * @author Johneagle
 */
public class TempFile {

    protected File tempConfig;
    protected File tempMapFile;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @After
    public void restore() {
        if (tempConfig != null) {
            this.tempConfig.delete();
        }
        if (tempMapFile != null) {
            this.tempMapFile.delete();
        }
    }

    protected void insertConfig(String bass, String unbass) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempConfig);
            writer.write("bassable=" + bass + "\n" +
                    "unbassable=" + unbass + "");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TempFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void insertMap(String data) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempMapFile);
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TempFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}