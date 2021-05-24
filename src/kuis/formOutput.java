package kuis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

public class formOutput {
    private JButton btnKeluar;
    private JTextField tfOutEmail;
    private JTextField tfOutUsername;
    private JTextField tfOutPassword;
    private JTextField tfOutNama;
    private JTextField tfOutTempatLahir;
    private JTextField tfOutDomisili;
    private JTextArea taOutDeskDiri;
    private JTextField tfOutTglLahir;
    private JPanel formOutPanel;

    public formOutput() throws IOException {

        JFrame frame = new JFrame("Form Pendaftaran");
        frame.setContentPane(formOutPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        FileReader fileInput = new FileReader("data.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();

        while (data != null) {

                StringTokenizer stringToken = new StringTokenizer(data, ",");

                tfOutEmail.setText(stringToken.nextToken());
                tfOutUsername.setText(stringToken.nextToken());
                tfOutPassword.setText(stringToken.nextToken());
                tfOutNama.setText(stringToken.nextToken());
                tfOutTempatLahir.setText(stringToken.nextToken());
                tfOutTglLahir.setText(stringToken.nextToken());
                tfOutDomisili.setText(stringToken.nextToken());
                taOutDeskDiri.setText(stringToken.nextToken());

            data = bufferInput.readLine();
        }

        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//    public static void main (String[] args) throws IOException{
//        formOutput formOutput = new formOutput();
//    }
//
}
