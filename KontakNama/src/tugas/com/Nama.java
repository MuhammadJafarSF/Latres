package tugas.com;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Nama extends JFrame{
    JLabel nama, kontak;
    JTextField textkontak, textnama;

    public void Daftar(){
        setTitle("Nama Kontak");
        JButton btncetak = new JButton("Cetak");
        nama = new JLabel ("Nama");
        kontak = new JLabel ("No.Telp");

        textnama= new JTextField();
        textkontak =new JTextField();

        setLayout(null);
        add(btncetak);
        add(nama);
        add(kontak);
        add(textkontak);
        add(textnama);

        nama.setBounds(10,20,100,20);
        kontak.setBounds(10,50,100,20);
        textnama.setBounds(120,20,120,20);
        textkontak.setBounds(120,50,120,20);
        btncetak.setBounds(120,170,100,20);


        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btncetak.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           String nama = textnama.getText();
           String kontak = textkontak.getText();


           System.out.println("Nama = "+ nama);
           System.out.println("No.Telp = "+ kontak);
            }
        }
        );
    }
}
