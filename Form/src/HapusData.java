
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class HapusData extends JFrame{
    JLabel lnim,ljudul;
    JTextField txnim;
    JButton btnDel, btnKembali;
    Statement statement;
    ResultSet resultSet;
    
    public HapusData(){
        ljudul = new JLabel("Hapus data mahasiswa");
        lnim = new JLabel("NIM");
        txnim = new JTextField("");
        btnDel  = new JButton("Delete");
        btnKembali  = new JButton("Kembali");
        
        setTitle("Hapus data mahasiswa");
        setSize(300,200);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        add(ljudul);
        add(lnim);
        add(txnim);
        add(btnDel);
        add(btnKembali);
        
        ljudul.setBounds(50,10,150,25);
        lnim.setBounds(50, 50, 100, 25);
        txnim.setBounds(100, 50, 100, 25);
        btnDel.setBounds(30, 90, 100, 25);
        btnKembali.setBounds(140, 90, 100, 25);
        
         btnKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new formmhs();
            }
            
        });
        btnDel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuatactionListener();
            }
            
        });
    }
    private void btnBuatactionListener() {
                
         KoneksiDB koneksi = new KoneksiDB();
                 try{
            
                statement = koneksi.getKoneksi().createStatement();
                statement.executeUpdate("DELETE FROM data_mhs WHERE nim='" + txnim.getText() + "'");
                
                JOptionPane.showMessageDialog(null, "data berhasil di hapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                statement.close();
                
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "data gagal di hapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, "driver tidak ditemukan!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            }
            }
}
