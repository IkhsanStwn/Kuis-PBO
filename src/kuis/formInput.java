package kuis;

import com.sun.tools.javac.Main;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class formInput {
    private JLabel judul;
    private JTextField tfUsername;
    private JTextField tfNama;
    private JTextField tfDomisili;
    private JTextArea taDeskDiri;
    private JTextField tfEmail;
    private JTextField tfTempatLahir;
    private JButton btnBatal;
    private JButton btnSimpan;
    private JPasswordField tfPassword;
    private JPanel formInputPanel;
    private JPanel jpCalender;
    private JButton btnClear;

    JDateChooser datePicker = new JDateChooser();

    public formInput() {
        JFrame frame = new JFrame("Form Pendaftaran");
        frame.setContentPane(formInputPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Kalender
        datePicker.setDateFormatString("dd/MM/yyyy");
        jpCalender.add(datePicker);


        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileWriter simpanData = new FileWriter("data.txt", true);

                    if(!Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$",tfEmail.getText())){
                        JOptionPane.showMessageDialog(null, "Please enter a valid email","Error",JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "The email is valid","Good!",JOptionPane.INFORMATION_MESSAGE);

                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String tglLahir = df.format(datePicker.getDate()).toString();

                        String email = tfEmail.getText().toString();
                        String username = tfUsername.getText().toString();
                        String password = tfPassword.getText().toString();
                        String nama = tfNama.getText().toString();
                        String tempatLahir = tfTempatLahir.getText().toString();
                        String domisili = tfDomisili.getText().toString();
                        String deskDiri = taDeskDiri.getText().toString();

                        simpanData.write(email+","+username+","+password+","+nama+","+tempatLahir+","+tglLahir+","+domisili+","+deskDiri);
                        simpanData.write(System.getProperty("line.separator"));
                        simpanData.close();
                        JOptionPane.showMessageDialog(null,"Berhasil Menyimpan Data");

                        formOutput formOut = new formOutput();
                    }

                } catch (Exception evt){
                    JOptionPane.showMessageDialog(null,"Gagal Menyimpan");
                }

            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tfEmail.setText(null);
                tfUsername.setText(null);
                tfPassword.setText(null);
                tfNama.setText(null);
                tfTempatLahir.setText(null);
                datePicker.setCalendar(null);
                tfDomisili.setText(null);
                taDeskDiri.setText(null);
            }
        });

        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        tfUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char getUsername = e.getKeyChar();
                if (!(Character.isAlphabetic(getUsername))&&!(Character.isDigit(getUsername))){
                    e.consume();
                    JOptionPane.showMessageDialog(null,"Hanya bisa memasukkan Huruf dan Angka");
                }
            }
        });


        taDeskDiri.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int max = 199;
                if(taDeskDiri.getText().length() > max+1) {
                    e.consume();
                    String shortened = taDeskDiri.getText().substring(0, max);
                    taDeskDiri.setText(shortened);
                    JOptionPane.showMessageDialog(null,"Melebihi 200 Karakter");
                }else if(taDeskDiri.getText().length() > max) {
                    e.consume();
                    JOptionPane.showMessageDialog(null,"Melebihi 200 Karakter");
                }
            }
        });
    }

    public static void main (String[] args){
        new formInput();
    }

}
