/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D17CNPM2;

import frm.Home;
import frm.Login;

/**
 *
 * @author xuuan
 */
public class D17CNPM2 {
    public static frm.Home Home = new Home();
    public static database.Connect connection = new database.Connect();
    public static void main(String[] args) {
        frm.Login Login = new Login();
        Login.setVisible(true);
    }
}
