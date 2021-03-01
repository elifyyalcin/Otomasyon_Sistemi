/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author user
 */
abstract class ogrGorevli {

    void ogrenciGoruntule() {
    }

    ;
    void notGir() {
    }

    ;
    void notGor() {
    }

;

}

interface ogrGorevli1 {

    void girisYap();

    void uyeOl();

}

public class ogretimGorevlisi extends ogrGorevli implements ogrGorevli1 {

    private String firstname;
    private String lastname;
    private String ogrGorevlisiId;
    private String password;
    private String derskodu;
    private String dersadi;
    private String derssaati;
    private String dersgunu;
    private String notu;
    private String harfnotu;
    private String studentid;
    static String university = "İstanbul University";
    private int devamsizlik = 0;

    public int getDevamsizlik() {
        return devamsizlik;
    }

    public void setDevamsizlik(int devamsizlik) {
        this.devamsizlik = devamsizlik;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getHarfnotu() {
        return harfnotu;
    }

    public String getNotu() {
        return notu;
    }

    public String getDerskodu() {
        return derskodu;
    }

    public void setDerskodu(String derskodu) {
        this.derskodu = derskodu;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public String getDerssaati() {
        return derssaati;
    }

    public void setDerssaati(String derssaati) {
        this.derssaati = derssaati;
    }

    public String getDersgunu() {
        return dersgunu;
    }

    public void setDersgunu(String dersgunu) {
        this.dersgunu = dersgunu;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOgrGorevlisiId() {
        return ogrGorevlisiId;
    }

    public void setOgrGorevlisiId(String studentid) {
        this.ogrGorevlisiId = studentid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getUniversity() {
        return university;
    }

    public void geriDon() {
        Scanner scan = new Scanner(System.in);

        System.out.println("1-Not Gir\n2-Öğrenci Görüntüle\n3-Notları Görüntüle\n4-ÇIKIŞ");
        System.out.println("Yapmak istediğiniz işlemi seçin");
        
        int x = scan.nextInt();

        switch (x) {
            case 1:
                notGir();
                break;
            case 2:
                ogrenciGoruntule();
                break;
            case 3:
                notGor();
                break;
            case 4:
                System.out.println("ÇIKIŞ YAPILDI");
                break;

        }

    }

    @Override
    public void uyeOl() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ad:");
        firstname = scan.nextLine();
        System.out.println("Soyad:");
        lastname = scan.nextLine();
        System.out.println("Öğretim Görevlisi Numarası:");
        ogrGorevlisiId = scan.nextLine();
        System.out.println("Şifre:");
        password = scan.nextLine();

        try {

            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            PreparedStatement addEntry = connect.prepareStatement("INSERT INTO OGRETIMGOREVLISI"
                    + "(firstname,lastname,ogrgorevlisiid,password)"
                    + "VALUES ( ?, ?, ?, ?)");

            addEntry.setString(1, getFirstname());
            addEntry.setString(2, getLastname());
            addEntry.setString(3, getOgrGorevlisiId());
            addEntry.setString(4, getPassword());

            addEntry.executeUpdate();
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Giriş Yapmak İçin 1'e Basınız");
        int x=scan.nextInt();
        if(x==1){
            girisYap();

        
    }
    }
    


    @Override
    public void girisYap() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ad:");
        firstname = scan.nextLine();
        System.out.println("Soyad:");
        lastname = scan.nextLine();

        System.out.println("Öğretim Görevlisi Numarası:");
        ogrGorevlisiId = scan.nextLine();
        System.out.println("Şifre:");
        password = scan.nextLine();

        boolean uyevar = false;
        int x;

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ogretimgorevlisi");
            while (rs.next()) {

                if (rs.getString(3).equals(ogrGorevlisiId) && rs.getString(4).equals(password)) {
                    uyevar = true;

                    System.out.println("1-Not gir\n2-Öğrencileri Görüntüle\n3-Öğrenci Notlarını Görüntüle\n4-Yoklama");
                    System.out.println("Yapmak istediğiniz işlemi seçin");
                    
                    x = scan.nextInt();

                    switch (x) {
                        case 1:
                            notGir();
                            break;
                        case 2:
                            ogrenciGoruntule();
                            break;
                        case 3:
                            notGor();
                            break;
                        case 4:
                            yoklama();
                            break;

                    }

                    break;

                }

            }
            if(uyevar == false) {
                System.out.println("Girdiğiniz bilgiler hatalı lütfen tekrar deneyiniz!");
                girisYap();
                
            }

            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void ogrenciGoruntule() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%-20s%-20s%-20s%-20s%n","Ad","Soyad","Öğrenci No","Email");

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ogrenci");
            while (rs.next()) {
                System.out.printf("%-20s%-20s%-20s%-20s%n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5));
            }
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Başka bir işlem için 0'a basın");
        int giris1 = scan.nextInt();

        if (giris1 == 0) {

            geriDon();

        }

    }

    @Override
    public void notGir() {

        Scanner scan = new Scanner(System.in);
        
         System.out.printf("%-20s%-20s%-20s%n","Ders Kodu","Ders Adı","Öğrenci No");

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM secilendersler");
            while (rs.next()) {
                System.out.printf("%-20s%-20s%-20s%n",rs.getString(1),rs.getString(2),rs.getString(3));
            }
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Scanner scan = new Scanner(System.in);
        System.out.println("Ders Kodu:");
        derskodu = scan.nextLine();
        System.out.println("Öğrenci Numarası:");
        studentid = scan.nextLine();
        System.out.println("Not:");
        notu = scan.nextLine();

        try {

            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            PreparedStatement addEntry = connect.prepareStatement("UPDATE secilendersler SET notu = ? WHERE derskodu=? AND ogrencino=?");

            addEntry.setString(1, getNotu());
            addEntry.setString(2, getDerskodu());
            addEntry.setString(3, getStudentid());

            addEntry.executeUpdate();
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("cikmak icin 0a devam etmek icin 1e basınız");
        int giris = scan.nextInt();
        if (giris == 1) {
            notGir();
        }
        if (giris == 0) {
         

                geriDon();

            
        }

    }

    @Override
    public void notGor() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%-20s%-20s%n","Ders Kodu","Ders Adı");
        
                try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dersprogrami");
            while (rs.next()) {
                System.out.printf("%-20s%-20s%n",rs.getString(1),rs.getString(2));
            }

        
        System.out.println("Notlarını görüntülemek istediğiniz ders kodunu giriniz:");
        derskodu = scan.nextLine();

        
            

            Statement stmt1 = connect.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM secilendersler");
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n","Ders Kodu","Öğrenci No","Öğrenci Adı","Öğrenci Soyadı", "Notu","Harf Notu");

            while (rs1.next()) {

                if (rs1.getString(1).equals(derskodu)) {

                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n",rs1.getString(1) , rs1.getString(3) , rs1.getString(4) , rs1.getString(5) ,rs1.getString(6) , rs1.getString(7));

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Başka bir işlem yapmak için 0'a başka derslerin notlarını görmek için 1'e basınız");
        int giris = scan.nextInt();
        if (giris == 1) {
            notGor();
        }
        if (giris == 0) {

            geriDon();

        }
    }

    public void yoklama() {
        
        Scanner scan = new Scanner(System.in);
        System.out.printf("%-20s%-20s%n","Ders Kodu","Öğrenci No");
                        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM secilendersler");
            while (rs.next()) {
                System.out.printf("%-20s%-20s%n",rs.getString(1),rs.getString(3));
            }

        
        System.out.println("Ders Kodu:");
        derskodu = scan.nextLine(); 
        System.out.println("Öğrenci Numarası:");
        studentid = scan.nextLine();


            PreparedStatement addEntry = connect.prepareStatement("UPDATE yoklama SET devamsizlik = ? WHERE derskodu=? AND studentid=?");

            System.out.println("VAR : +");
            System.out.println("YOK : -");
            String devam = scan.nextLine();

            if (devam.equals("+")) {

                addEntry.setInt(1, getDevamsizlik());
                addEntry.setString(2, getDerskodu());
                addEntry.setString(3, getStudentid());

                addEntry.executeUpdate();
            }

            if (devam.equals("-")) {

                addEntry.setInt(1, getDevamsizlik() + 1);
                addEntry.setString(2, getDerskodu());
                addEntry.setString(3, getStudentid());

                addEntry.executeUpdate();

            }
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("cikmak icin 0a devam etmek icin 1e basınız");
        int giris = scan.nextInt();
        if (giris == 1) {
            yoklama();
        }
        if (giris == 0) {


                geriDon();

            
        }

    }

}
