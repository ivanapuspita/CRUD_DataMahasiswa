
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class EditData extends JFrame {
    JLabel lnim,lnama,lalamat,ljudul;
    JTextField txnim,txnama,txalamat;
    JButton btnUpdate, btnKembali;
    Statement statement;
    ResultSet resultSet;
    
    public EditData(){
        ljudul = new JLabel("Masukan NIM yang akan di update");
        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");        
        
        lalamat = new JLabel("Alamat");
        
        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        
        btnUpdate  = new JButton("Update");
        btnKembali  = new JButton("Kembali");
        
        setTitle("Update data mahasiswa");
        setSize(300,370);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        add(ljudul);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(btnUpdate);
        add(btnKembali);
        
        ljudul.setBounds(0,10,300,25);
        lnim.setBounds(50,50,100,25);
        txnim.setBounds(100,50,100,25);
        lnama.setBounds(50,90,100,25);
        txnama.setBounds(100,90,100,25);
        lalamat.setBounds(50,210,100,25);
        txalamat.setBounds(100,210,100,25);
        btnKembali.setBounds(30,290,100,25);
        btnUpdate.setBounds(140,290,100,25);
        
        btnKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new formmhs();
            }
            
        });
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdateactionListener();
            }
            
        });
    }
    private void btnUpdateactionListener() {
                KoneksiDB koneksi = new KoneksiDB();
                 try{
            
                statement = koneksi.getKoneksi().createStatement();
                statement.executeUpdate("UPDATE data_mhs SET nama='" + txnama.getText() + "',"+ "alamat='" +
                        txalamat.getText() + "' WHERE nim='" + txnim.getText() + "'");
                
                JOptionPane.showMessageDialog(null, "data berhasil di update!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                statement.close();
                
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "data gagal di update!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, "driver tidak ditemukan!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            }
                }
}
