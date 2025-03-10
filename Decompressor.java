import javax.swing.*;
import java.io.*;
import java.util.zip.GZIPInputStream;

public class Decompressor {
    private JProgressBar progressBar;

    public Decompressor(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void decompressFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            GZIPInputStream gzipIS = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath().replace(".gz", ""));

            byte[] buffer = new byte[1024];
            int len;
            long totalBytesRead = 0;
            long fileSize = file.length();

            progressBar.setValue(0);

            while ((len = gzipIS.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                totalBytesRead += len;

                // Update progress bar
                int progress = (int)((totalBytesRead * 100) / fileSize);
                progressBar.setValue(progress);
            }

            gzipIS.close();
            fos.close();
            fis.close();

            JOptionPane.showMessageDialog(null, "File Decompressed Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
