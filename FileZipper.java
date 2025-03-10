import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.io.File;

public class FileZipper extends JFrame {
    
    private JButton compressFileButton;
    private JButton decompressFileButton;
    private JProgressBar progressBar;

    public FileZipper() {
        // Buttons
        compressFileButton = new JButton("Select & Compress File");
        decompressFileButton = new JButton("Select & Decompress File");

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(50, 200, 400, 30);

        // Button Bounds
        compressFileButton.setBounds(50, 100, 400, 30);
        decompressFileButton.setBounds(50, 150, 400, 30);

        // Add Components
        add(compressFileButton);
        add(decompressFileButton);
        add(progressBar);

        // File Selection for Compression
        compressFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                
                // Run in new thread to avoid UI blocking
                new Thread(() -> {
                    new Compressor(progressBar).compressFile(selectedFile);
                }).start();
            }
        });

        // File Selection for Decompression
        decompressFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                
                // Run in new thread to avoid UI blocking
                new Thread(() -> {
                    new Decompressor(progressBar).decompressFile(selectedFile);
                }).start();
            }
        });

        // Frame Settings
        setSize(500, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new FileZipper();
    }
}
