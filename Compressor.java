import javax.swing.*;
import java.io.*;
import java.util.zip.GZIPOutputStream;

public class Compressor {
    private JProgressBar progressBar;

    public Compressor(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void compressFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".gz");
            GZIPOutputStream gzipOS = new GZIPOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;
            long totalBytesRead = 0;
            long fileSize = file.length();

            progressBar.setValue(0);

            while ((len = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, len);
                totalBytesRead += len;
                
                // Update progress bar
                int progress = (int)((totalBytesRead * 100) / fileSize);
                progressBar.setValue(progress);
            }

            gzipOS.close();
            fos.close();
            fis.close();

            JOptionPane.showMessageDialog(null, "File Compressed Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
