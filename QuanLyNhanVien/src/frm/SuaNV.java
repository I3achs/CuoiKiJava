/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author xuuan
 */
public class SuaNV extends javax.swing.JFrame {

    /**
     * Creates new form ThemSuaNV
     */
    private Home home;
    public SuaNV(Home home) {
        initComponents();
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.home = home;
    }
    
    public void setEmployeeData(Vector<Object> rowData) {
        txtMaNV.setText(rowData.get(0).toString());
        txtTen.setText(rowData.get(1).toString());

        // Giới tính (bit -> JCheckBox)
        String gioiTinh = rowData.get(2).toString();
        cbGioiTinh.setSelected(gioiTinh.equals("Nam"));

        // Ngày sinh (Date -> JCalendar)
        try {
            Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(rowData.get(3).toString());
            txtBorn.setDate(ngaySinh);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtSDT.setText(rowData.get(4).toString());
        txtCCCD.setText(rowData.get(5).toString());
        txtDiaChi.setText(rowData.get(6).toString());

        cbPB.setSelectedItem(rowData.get(7).toString());
        cbBP.setSelectedItem(rowData.get(8).toString());
        cbCV.setSelectedItem(rowData.get(9).toString());
        cbTD.setSelectedItem(rowData.get(10).toString());
    }
    
    private void loadChucVuToComboBox() {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
    String user = "sa";
    String password = "11111";
    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "SELECT TenCV FROM ChucVu";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        cbCV.removeAllItems();

        while (rs.next()) {
            String tenChucVu = rs.getString("TenCV");
            cbCV.addItem(tenChucVu);
        }

    } catch (SQLException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
      }
    }
    
    private void loadPhongBanToComboBox() {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
    String user = "sa";
    String password = "11111";
    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "SELECT TenPB FROM PhongBan";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        cbPB.removeAllItems();

        while (rs.next()) {
            String tenChucVu = rs.getString("TenPB");
            cbPB.addItem(tenChucVu);
        }

    } catch (SQLException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
      }
    }
    
    private void loadBoPhanToComboBox() {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
    String user = "sa";
    String password = "11111";
    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "SELECT TenBP FROM BoPhan";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        cbBP.removeAllItems();

        while (rs.next()) {
            String tenChucVu = rs.getString("TenBP");
            cbBP.addItem(tenChucVu);
        }

    } catch (SQLException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
      }
    }
    
    private void loadTrinhDoToComboBox() {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
    String user = "sa";
    String password = "11111";
    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "SELECT TrinhDo FROM TrinhDo";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        cbTD.removeAllItems();

        while (rs.next()) {
            String tenChucVu = rs.getString("TrinhDo");
            cbTD.addItem(tenChucVu);
        }

    } catch (SQLException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
      }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        cbGioiTinh = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtBorn = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        cbTD = new javax.swing.JComboBox<>();
        cbCV = new javax.swing.JComboBox<>();
        cbPB = new javax.swing.JComboBox<>();
        cbBP = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa nhân viên");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setText("Họ tên:");

        cbGioiTinh.setText("Nam/Nữ");
        cbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGioiTinhActionPerformed(evt);
            }
        });

        jLabel3.setText("Giới tính:");

        txtBorn.setMinimumSize(new java.awt.Dimension(64, 22));

        jLabel4.setText("Ngày sinh:");

        jLabel5.setText("CCCD:");

        jLabel6.setText("SĐT:");

        jLabel7.setText("Địa chỉ:");

        jLabel8.setText("Trình độ:");

        jLabel9.setText("Chức vụ:");

        jLabel10.setText("Phòng ban:");

        jLabel11.setText("Bộ phận:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        cbTD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTDActionPerformed(evt);
            }
        });

        cbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbBP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUnSave.setText("K.Lưu");
        btnUnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã NV:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Sửa thông tin nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbTD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbBP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbPB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCV, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(btnSave)
                            .addGap(109, 109, 109)
                            .addComponent(btnUnSave)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel4)))
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbGioiTinh))
                            .addComponent(txtTen)
                            .addComponent(txtCCCD)
                            .addComponent(txtSDT)
                            .addComponent(jScrollPane1)
                            .addComponent(txtBorn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel12)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbGioiTinh)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtBorn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUnSave))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        home.setEnabled(true);
        home.setVisible(true);
        home.loadDataToJTableNV();
    }//GEN-LAST:event_formWindowClosed

    private void cbTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTDActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "11111";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
        String sql = "UPDATE NhanVien SET HoTen=?, GioiTinh=?, NgaySinh=?, DienThoai=?, " +
                     "CCCD=?, DiaChi=?, IDPB=?, IDBP=?, IDCV=?, IDTD=? WHERE MaNV=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, txtTen.getText());
        stmt.setBoolean(2, cbGioiTinh.isSelected());
        stmt.setDate(3, new java.sql.Date(txtBorn.getDate().getTime()));
        stmt.setString(4, txtSDT.getText());
        stmt.setString(5, txtCCCD.getText());
        stmt.setString(6, txtDiaChi.getText());

        stmt.setInt(7, (cbPB.getSelectedIndex())+1);
        stmt.setInt(8, (cbBP.getSelectedIndex())+1);
        stmt.setInt(9, (cbCV.getSelectedIndex())+1);
        stmt.setInt(10, (cbTD.getSelectedIndex())+1);

        stmt.setInt(11, Integer.parseInt(txtMaNV.getText()));

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        }
        dispose(); // Đóng form SuaNV sau khi lưu
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnSaveActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnUnSaveActionPerformed

    private void cbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGioiTinhActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadChucVuToComboBox();
        loadBoPhanToComboBox();
        loadPhongBanToComboBox();
        loadTrinhDoToComboBox();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ThemSuaNV().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUnSave;
    private javax.swing.JComboBox<String> cbBP;
    private javax.swing.JComboBox<String> cbCV;
    private javax.swing.JCheckBox cbGioiTinh;
    private javax.swing.JComboBox<String> cbPB;
    private javax.swing.JComboBox<String> cbTD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser txtBorn;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
