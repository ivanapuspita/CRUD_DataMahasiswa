
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class LihatDataMahasiswa extends JFrame {
    String [][] data = new String [480][3];
    String[] kolom = {"nim","nama","alamat"};
    JTable tabel;
    JButton btnBack;
    JScrollPane scrollpane;
    Statement statement;
    ResultSet resultSet;
    
    public void LihatDataMahasiswa(){
        setTitle("Data Mahasiswa");
        
        btnBack = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        JScrollPane scrollPane = new JScrollPane(tabel);
        
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible (false);
                new formmhs();
            }
        });
        
        KoneksiDB koneksi = new KoneksiDB();
        try{
            statement = koneksi.getKoneksi().createStatement();
            String sql = "select *from data_mhs";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("nama");
                data[p][1] = resultSet.getString("nim");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        
    }

   

    
    
}
