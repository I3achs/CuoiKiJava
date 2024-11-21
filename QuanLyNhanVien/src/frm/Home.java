/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author xuuan
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        setLocationRelativeTo(null);
        setJp(false);
        this.HomeJP.setVisible(true);
    }
    private void setJp(boolean a){
        this.DoiMK.setVisible(a);
        this.NhanVien.setVisible(a);
        this.HopDong.setVisible(a);
        this.HomeJP.setVisible(a);
        this.ChamCong.setVisible(a);
    }
    
    void loadDataToJTableHD() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "11111";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql ="SELECT HopDong.IdHD, HopDong.SoHD, HopDong.BatDau, HopDong.KetThuc, HopDong.NgayKy, "+
                        "HopDong.NoiDung, HopDong.LanKy, HopDong.HeSoLuong, HopDong.MaNV, NhanVien.HoTen "+
                        "FROM HopDong JOIN NhanVien ON HopDong.MaNV = NhanVien.MaNV";

            // Tạo PreparedStatement và thực thi câu truy vấn
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Tạo DefaultTableModel để điền dữ liệu vào JTable
            DefaultTableModel model1 = new DefaultTableModel();
            model1.addColumn("IdHD");
            model1.addColumn("SoHD");
            model1.addColumn("BatDau");
            model1.addColumn("KetThuc");
            model1.addColumn("NgayKy");
            model1.addColumn("NoiDung");
            model1.addColumn("LanKy");
            model1.addColumn("HeSoLuong");
            model1.addColumn("MaNV"); 
            model1.addColumn("HoTen");           
            boolean hasData = false;
            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (rs.next()) {
                hasData = true;
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("IdHD"));
                row.add(rs.getString("SoHD"));
                
                Date bd = rs.getDate("BatDau");
                row.add(bd != null ? bd.toString() : "");
                Date kt = rs.getDate("KetThuc");
                row.add(kt != null ? kt.toString() : "");
                Date ky = rs.getDate("NgayKy");
                row.add(ky != null ? ky.toString() : "");
                
                row.add(rs.getString("NoiDung"));
                row.add(rs.getInt("LanKy"));
                row.add(rs.getFloat("HeSoLuong"));
                 
                row.add(rs.getInt("MaNV"));
                row.add(rs.getString("HoTen"));

                model1.addRow(row);
            }

            // Gán DefaultTableModel vào JTable của NetBeans
            if (hasData) {
            // Gán DefaultTableModel vào JTable của NetBeans
                jTableHD.setModel(model1);  // Đây là đối tượng JTable đã được NetBeans tạo
            } else {
                JOptionPane.showMessageDialog(null, "Khong co du lieu de hien thi.");
            }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    void loadDataToJTableNV() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "11111";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT N.MaNV, N.HoTen, N.GioiTinh, N.DienThoai, N.CCCD, N.DiaChi, N.NgaySinh, " +
                        "BP.TenBP, PB.TenPB, CV.TenCV, TD.TrinhDo " +
                        "FROM NhanVien N " +
                        "JOIN BoPhan BP ON N.IDBP = BP.IDBP " +
                        "JOIN PhongBan PB ON N.IDPB = PB.IDPB " +
                        "JOIN ChucVu CV ON N.IDCV = CV.IDCV " +
                        "JOIN TrinhDo TD ON N.IDTD = TD.IDTD";

            // Tạo PreparedStatement và thực thi câu truy vấn
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Tạo DefaultTableModel để điền dữ liệu vào JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("MaNV");
            model.addColumn("HoTen");
            model.addColumn("GioiTinh");
            model.addColumn("NgaySinh");
            model.addColumn("DienThoai");
            model.addColumn("CCCD");
            model.addColumn("DiaChi");
            model.addColumn("TenPB");
            model.addColumn("TenBP"); 
            model.addColumn("TenCV");
            model.addColumn("TrinhDo");
            
            boolean hasData = false;
            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (rs.next()) {
                hasData = true;
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("MaNV"));
                row.add(rs.getString("HoTen"));
        
                // Xử lý giới tính
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                row.add(gioiTinh ? "Nam" : "Nu");  // Chuyển đổi bit thành chuỗi
                
                Date ngaySinh = rs.getDate("NgaySinh");
                row.add(ngaySinh != null ? ngaySinh.toString() : "");
                
                row.add(rs.getString("DienThoai"));
                row.add(rs.getString("CCCD"));
                row.add(rs.getString("DiaChi"));
                 
                row.add(rs.getString("TenPB"));
                row.add(rs.getString("TenBP"));
                row.add(rs.getString("TenCV"));
                row.add(rs.getString("TrinhDo"));
        
                model.addRow(row);
            }

            // Gán DefaultTableModel vào JTable của NetBeans
            if (hasData) {
            // Gán DefaultTableModel vào JTable của NetBeans
                jTable1.setModel(model);  // Đây là đối tượng JTable đã được NetBeans tạo
            } else {
                JOptionPane.showMessageDialog(null, "Khong co du lieu de hien thi.");
            }

            } catch (SQLException e) {
                e.printStackTrace();
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

        HomeJP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        NhanVien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        DoiMK = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mkMoi = new javax.swing.JPasswordField();
        mkMoi2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        luuMKmoi = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        HopDong = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHD = new javax.swing.JTable();
        btnAdd1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ChamCong = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        mnHeThong = new javax.swing.JMenu();
        mnDangXuat = new javax.swing.JMenuItem();
        mnDoiMK = new javax.swing.JMenuItem();
        mnHome = new javax.swing.JMenuItem();
        mnNhanSu = new javax.swing.JMenu();
        mnNhanVien = new javax.swing.JMenuItem();
        mnHopDong = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Thông tin nhân viên");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Hợp đồng");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 0, 204));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Tài khoản");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Chấm Công");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout HomeJPLayout = new javax.swing.GroupLayout(HomeJP);
        HomeJP.setLayout(HomeJPLayout);
        HomeJPLayout.setHorizontalGroup(
            HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeJPLayout.createSequentialGroup()
                .addGroup(HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        HomeJPLayout.setVerticalGroup(
            HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeJPLayout.createSequentialGroup()
                .addGroup(HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomeJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(HomeJP);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Danh sách nhân viên");

        javax.swing.GroupLayout NhanVienLayout = new javax.swing.GroupLayout(NhanVien);
        NhanVien.setLayout(NhanVienLayout);
        NhanVienLayout.setHorizontalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(333, 333, 333))
        );
        NhanVienLayout.setVerticalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addGap(39, 39, 39))
        );

        getContentPane().add(NhanVien);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Mật khẩu mới:");

        jLabel4.setText("Nhập lại mật khẩu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Đổi mật khẩu");

        luuMKmoi.setText("Lưu");

        Home.setText("Quay lại");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        jLabel6.setText("Tài khoản:");

        jTextField1.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(luuMKmoi)
                        .addGap(55, 55, 55)
                        .addComponent(Home)
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mkMoi)
                            .addComponent(mkMoi2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGap(110, 110, 110))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mkMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(mkMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(luuMKmoi)
                    .addComponent(Home))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout DoiMKLayout = new javax.swing.GroupLayout(DoiMK);
        DoiMK.setLayout(DoiMKLayout);
        DoiMKLayout.setHorizontalGroup(
            DoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoiMKLayout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );
        DoiMKLayout.setVerticalGroup(
            DoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoiMKLayout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        getContentPane().add(DoiMK);

        jTableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(jTableHD);

        btnAdd1.setText("Thêm");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Danh sách hợp đồng");

        javax.swing.GroupLayout HopDongLayout = new javax.swing.GroupLayout(HopDong);
        HopDong.setLayout(HopDongLayout);
        HopDongLayout.setHorizontalGroup(
            HopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HopDongLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(HopDongLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(btnAdd1)
                .addGap(108, 108, 108)
                .addComponent(btnDelete1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HopDongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(333, 333, 333))
        );
        HopDongLayout.setVerticalGroup(
            HopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HopDongLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(HopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAdd1)
                    .addComponent(btnDelete1))
                .addGap(39, 39, 39))
        );

        getContentPane().add(HopDong);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Chấm công");

        jLabel12.setText("Ngày:");

        jLabel13.setText("Tháng:");

        jLabel14.setText("Năm:");

        jLabel15.setText("Mã nhân viên:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField2.setEditable(false);

        jLabel16.setText("Tên nhân viên:");

        javax.swing.GroupLayout ChamCongLayout = new javax.swing.GroupLayout(ChamCong);
        ChamCong.setLayout(ChamCongLayout);
        ChamCongLayout.setHorizontalGroup(
            ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChamCongLayout.createSequentialGroup()
                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChamCongLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ChamCongLayout.createSequentialGroup()
                                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ChamCongLayout.createSequentialGroup()
                                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDayChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(ChamCongLayout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
        ChamCongLayout.setVerticalGroup(
            ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChamCongLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel11)
                .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChamCongLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(ChamCongLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(ChamCongLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(ChamCongLayout.createSequentialGroup()
                                .addComponent(jDayChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(ChamCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(ChamCong);

        mnHeThong.setText("Hệ thống");

        mnDangXuat.setText("Đăng xuất");
        mnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDangXuatActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDangXuat);

        mnDoiMK.setText("Đổi mật khẩu");
        mnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDoiMKActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDoiMK);

        mnHome.setText("Home");
        mnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHomeActionPerformed(evt);
            }
        });
        mnHeThong.add(mnHome);

        Menu.add(mnHeThong);

        mnNhanSu.setText("Nhân sự");

        mnNhanVien.setText("Nhân viên");
        mnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNhanVienActionPerformed(evt);
            }
        });
        mnNhanSu.add(mnNhanVien);

        mnHopDong.setText("Hợp đồng");
        mnHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHopDongActionPerformed(evt);
            }
        });
        mnNhanSu.add(mnHopDong);

        jMenuItem1.setText("Chấm công");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnNhanSu.add(jMenuItem1);

        Menu.add(mnNhanSu);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDoiMKActionPerformed
        // TODO add your handling code here:
        setJp(false);
        this.DoiMK.setVisible(true);
    }//GEN-LAST:event_mnDoiMKActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow(); // Lấy chỉ số dòng được chọn
        if (selectedRow != -1) { // Kiểm tra nếu có dòng nào được chọn
        // Lấy ID của nhân viên từ dòng được chọn (giả sử ID nằm ở cột 1)
        int idToDelete = (int) jTable1.getValueAt(selectedRow, 0); // Cột 1 chứa ID
        
        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Kết nối đến cơ sở dữ liệu và thực hiện xóa
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "11111";
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "DELETE FROM NhanVien WHERE MaNV = ?"; // Giả sử IDNV là tên cột chứa ID nhân viên
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idToDelete);
                stmt.executeUpdate();
                
                // Xóa dòng khỏi JTable
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
    }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new ThemNV(this).setVisible(true);
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Kiểm tra xem người dùng có chọn dòng nào trong JTable không
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa");
            return;
        }

        // Lấy dữ liệu từ dòng được chọn
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Vector<Object> rowData = new Vector<>();
        for (int i = 0; i < model.getColumnCount(); i++) {
            rowData.add(model.getValueAt(selectedRow, i));
        }

        // Mở form SuaNV và truyền dữ liệu
        SuaNV suaNVForm = new SuaNV(this);
        suaNVForm.setEmployeeData(rowData);
        suaNVForm.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void mnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
        System.out.println("Tai khoan da dang xuat.");
    }//GEN-LAST:event_mnDangXuatActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new ThemHD(this).setVisible(true);
        
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
            int selectedRow = jTableHD.getSelectedRow(); // Lấy chỉ số dòng được chọn
        if (selectedRow != -1) { // Kiểm tra nếu có dòng nào được chọn
        // Lấy ID của nhân viên từ dòng được chọn (giả sử ID nằm ở cột 1)
        int idToDelete = (int) jTableHD.getValueAt(selectedRow, 0); // Cột 1 chứa ID
        
        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Kết nối đến cơ sở dữ liệu và thực hiện xóa
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanVien;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "11111";
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "DELETE FROM HopDong WHERE IdHD = ?"; // Giả sử IDNV là tên cột chứa ID nhân viên
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idToDelete);
                stmt.executeUpdate();
                
                // Xóa dòng khỏi JTable
                ((DefaultTableModel) jTableHD.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        setJp(false);
        this.NhanVien.setVisible(true);
        loadDataToJTableNV();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void mnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHomeActionPerformed
        // TODO add your handling code here:
        setJp(false);
        HomeJP.setVisible(true);
    }//GEN-LAST:event_mnHomeActionPerformed

    private void mnHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHopDongActionPerformed
        // TODO add your handling code here:
        setJp(false);
        this.HopDong.setVisible(true);
        loadDataToJTableHD();
    }//GEN-LAST:event_mnHopDongActionPerformed

    private void mnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNhanVienActionPerformed
        // TODO add your handling code here:
        setJp(false);
        this.NhanVien.setVisible(true);
        loadDataToJTableNV();
    }//GEN-LAST:event_mnNhanVienActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        setJp(false);
        this.HopDong.setVisible(true);
        loadDataToJTableHD();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        setJp(false);
        this.DoiMK.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        setJp(false);
        HomeJP.setVisible(true);
    }//GEN-LAST:event_HomeActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        setJp(false);
        ChamCong.setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        setJp(false);
        ChamCong.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Home().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChamCong;
    private javax.swing.JPanel DoiMK;
    private javax.swing.JButton Home;
    private javax.swing.JPanel HomeJP;
    private javax.swing.JPanel HopDong;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JPanel NhanVien;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableHD;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton luuMKmoi;
    private javax.swing.JPasswordField mkMoi;
    private javax.swing.JPasswordField mkMoi2;
    private javax.swing.JMenuItem mnDangXuat;
    private javax.swing.JMenuItem mnDoiMK;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mnHome;
    private javax.swing.JMenuItem mnHopDong;
    private javax.swing.JMenu mnNhanSu;
    private javax.swing.JMenuItem mnNhanVien;
    // End of variables declaration//GEN-END:variables
}
