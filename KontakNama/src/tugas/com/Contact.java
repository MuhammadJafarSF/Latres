package tugas.com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Contact extends JFrame {

    private JLabel lblNama;
    private JTextField txtNama;
    private JTextField txtHp;
    private JLabel lblNohpTelp;
    private JLabel lblSimpan;
    private JLabel lblUbah;
    private JLabel lblHapus;
    private JLabel lblRefresh;
    private JTable table;
    private JLabel lblPencarian;
    private JTextField txtCari;
    DefaultTableModel tabelModel;
    String data[] = {  "Nama", "Telp",};


    public Contact() {
        setTitle("Tambah Kontak");
        setResizable(false);
        setBounds(100, 100, 751, 256);
        getContentPane().setLayout(new BorderLayout());

        lblNama = new JLabel("Nama : ");
        lblNama.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblNama.setBounds(12, 50, 60, 15);

        txtNama = new JTextField();
        txtNama.setFont(new Font("Droid Sans", Font.BOLD, 12));
        txtNama.setBounds(120, 44, 205, 27);
        txtNama.setColumns(10);

        lblNohpTelp = new JLabel("No.HP / Telp : ");
        lblNohpTelp.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblNohpTelp.setBounds(12, 83, 93, 15);

        txtHp = new JTextField();
        txtHp.setFont(new Font("Droid Sans", Font.BOLD, 12));
        txtHp.setBounds(120, 77, 205, 27);
        txtHp.setColumns(10);

        lblSimpan = new JLabel("Simpan");
        lblSimpan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection konek = Database.getDatabase();
                    String sql = "INSERT INTO data VALUES (?,?)";
                    PreparedStatement prepare = konek.prepareStatement(sql);

                    prepare.setString(1, txtNama.getText());
                    prepare.setString(2, txtHp.getText());

                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil ditambahkan");
                    prepare.close();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal ditambahkan");
                    System.out.println(ex);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                    txtNama.requestFocus();
                }
            }
        });
        lblSimpan.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblSimpan.setHorizontalTextPosition(SwingConstants.CENTER);
        lblSimpan.setHorizontalAlignment(SwingConstants.CENTER);
        lblSimpan.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblSimpan.setBounds(525, 172, 60, 51);

        lblUbah = new JLabel("Ubah");
        lblUbah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection konek = Database.getDatabase();
                    String sql = "UPDATE data SET nama = ? WHERE hp = ? ";
                    PreparedStatement prepare = konek.prepareStatement(sql);
                    prepare.setString(1, txtNama.getText());
                    prepare.setString(2, txtHp.getText());
                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil diubah");
                    prepare.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal diubah");
                    System.out.println(e1);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                }
            }
        });
        lblUbah.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblUbah.setHorizontalTextPosition(SwingConstants.CENTER);
        lblUbah.setHorizontalAlignment(SwingConstants.CENTER);
        lblUbah.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblUbah.setBounds(578, 172, 60, 51);

        lblHapus = new JLabel("Hapus");
        lblHapus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Connection konek = Database.getDatabase();
                    String sql = "DELETE FROM data WHERE no = ?";
                    PreparedStatement prepare = konek.prepareStatement(sql);

                    prepare.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data kontak berhasil dihapus");
                    prepare.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Data kontak gagal dihapus");
                    System.out.println(ex);
                } finally {
                    autonumber();
                    tampilDataTabel();
                    txtNama.setText("");
                    txtHp.setText("");
                    txtNama.requestFocus();
                }
            }
        });

        lblHapus.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblHapus.setHorizontalTextPosition(SwingConstants.CENTER);
        lblHapus.setHorizontalAlignment(SwingConstants.CENTER);
        lblHapus.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblHapus.setBounds(630, 172, 60, 51);

        lblRefresh = new JLabel("Refresh");
        lblRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                autonumber();
                tampilDataTabel();
                txtNama.setText("");
                txtHp.setText("");
                txtCari.setText("");
                txtNama.requestFocus();
            }
        });
        lblRefresh.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblRefresh.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRefresh.setHorizontalAlignment(SwingConstants.CENTER);
        lblRefresh.setFont(new Font("Droid Sans", Font.BOLD, 12));
        lblRefresh.setBounds(683, 172, 60, 51);

        tabelModel = new DefaultTableModel(null, data);
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pilih = table.getSelectedRow();
                if (pilih == -1) {
                    return;
                }

                String nama = (String) table.getValueAt(pilih, 1);
                txtNama.setText(nama);
                String hp = (String) table.getValueAt(pilih, 2);
                txtHp.setText(hp);
            }
        });
        table.setModel(tabelModel);
        lblPencarian = new JLabel("Cari : ");
        lblPencarian.setBounds(473, 18, 69, 15);

        txtCari = new JTextField();
        txtCari.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tabelModel.getDataVector().removeAllElements();
                tabelModel.fireTableDataChanged();
                try {
                    Connection konek = Database.getDatabase();
                    Statement state = konek.createStatement();
                    String query = "SELECT * FROM data WHERE nama LIKE '"
                            + txtCari.getText() + "%'";

                    ResultSet rs = state.executeQuery(query);
                    while (rs.next()) {
                        Object obj[] = new Object[5];
                        obj[0] = rs.getString(1);
                        obj[1] = rs.getString(2);

                        tabelModel.addRow(obj);
                    }
                    rs.close();
                    state.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        txtCari.setBounds(554, 12, 189, 27);
        txtCari.setColumns(10);
        tampilDataTabel();
        autonumber();
    }

    public void tampilDataTabel() {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try {
            Connection konek = Database.getDatabase();
            Statement state = konek.createStatement();
            String sql = "SELECT * FROM data";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);

                tabelModel.addRow(obj);
            }
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void autonumber() {
        try {
            Connection konek = Database.getDatabase();
            Statement state = konek.createStatement();
            String query = "SELECT MAX(no) FROM data";

            ResultSet rs = state.executeQuery(query);
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

