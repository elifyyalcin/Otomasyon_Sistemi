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
abstract class Memur {

    void dersProgramiHazirla() {
    }

    ;
    void harfNotuBelirle() {
    }

    ;
    void ogretimGorevlisiGoruntule() {
    }

    ;
    void ogrenciGoruntule() {
    }

;

}
    interface Memur1 {

    void uyeOl();

    void girisYap();

}

public class idariMemur extends Memur implements Memur1 {

    private String firstname;
    private String lastname;
    private String memurid;
    private String password;
    private String derskodu;
    private String dersadi;
    private String harfnotu;
    private String dersgunu;
    private String derssaati;
    private String studentid;
    static String university = "İstanbul University";

    public String getDersgunu() {
        return dersgunu;
    }

    public String getDerssaati() {
        return derssaati;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getHarfnotu() {
        return harfnotu;
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

    public String getMemurid() {
        return memurid;
    }

    public void setMemurid(String memurid) {
        this.memurid = memurid;
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
        System.out.println("1-Harf Notu Belirle 2-Öğrencileri Görüntüle  3-Ders Programı Hazırla 4-ÇIKIŞ YAP");
        System.out.println("Yapmak istediğiniz işlemi seçin:");
        
        int x = scan.nextInt();

        switch (x) {
            case 1:
                harfNotuBelirle();
                break;
            case 2:
                ogrenciGoruntule();
                break;
      
                
            case 3:
                dersProgramiHazirla();
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
        System.out.println("Memur Numarası:");
        memurid = scan.nextLine();
        System.out.println("Şifre:");
        password = scan.nextLine();

        try {

            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            PreparedStatement addEntry = connect.prepareStatement("INSERT INTO IDARIMEMUR"
                    + "(firstname,lastname,memurid,password)"
                    + "VALUES ( ?, ?, ?, ?)");

            addEntry.setString(1, getFirstname());
            addEntry.setString(2, getLastname());
            addEntry.setString(3, getMemurid());
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
        Scanner sc = new Scanner(System.in); 
        System.out.println("Ad:");
        firstname = sc.nextLine();
        System.out.println("Soyad:");
        lastname = sc.nextLine();

        System.out.println("Memur Numarası:");
        memurid = sc.nextLine();
        System.out.println("Şifre:");
        password = sc.nextLine();

        int x;

        boolean uyevar = false;

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM idarimemur");
            while (rs.next()) {

                if (rs.getString(3).equals(memurid) && rs.getString(4).equals(password)) {
                    uyevar = true;
                    System.out.println("1-Harf Notu Belirle 2-Öğrencileri Görüntüle 3-Ders Programı Hazırla");
                    System.out.println("Yapmak istediğiniz işlemi seçin:");
                    
                    x = sc.nextInt();

                    switch (x) {
                        case 1:
                            harfNotuBelirle();
                            break;
                        case 2:
                            ogrenciGoruntule();
                            break;
                        
                        case 3:
                            dersProgramiHazirla();
                            break;

                    }

                    break;

                }

            }
            if (uyevar == false) {
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
    public void harfNotuBelirle() {
        
        

        Scanner scan = new Scanner(System.in);
        System.out.println("Öğrencinin Numarasını Giriniz");
        studentid = scan.nextLine();

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM secilendersler");

            while (rs.next()) {

                if (rs.getString(3).equals(studentid)) {
                    
                    System.out.printf("%-20s%-20s%n","Ders Kodu","Notu");

                    System.out.printf("%-20s%-20s%n",rs.getString(1),rs.getString(6));
                    
                    System.out.println("aşağıdaki not aralığına göre harf notu giriniz");

                    System.out.println("85-100:AA\n70-85:BB\n55-70:CC\n55-40:DD\n40-0:FF");
 
                    System.out.println("Ders kodunu giriniz:");
                    derskodu = scan.nextLine();
                    System.out.println("Harfnotu giriniz:");
                    harfnotu = scan.nextLine();


                    PreparedStatement addEntry = connect.prepareStatement("UPDATE secilendersler SET harfnotu = ? WHERE ogrencino=? AND derskodu=?");

                    addEntry.setString(1, getHarfnotu());
                    addEntry.setString(2, getStudentid());
                    addEntry.setString(3, getDerskodu());

                    addEntry.executeUpdate();

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Çıkmak için 0'a devam etmek için 1e basınız");
        int giris = scan.nextInt();
        if (giris == 1) {
            harfNotuBelirle();
        }
        if (giris == 0) {


                geriDon();

            }
        }

    

    @Override
    public void dersProgramiHazirla() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ders Kodu:");
        derskodu = scan.nextLine();
        System.out.println("Ders Adı:");
        dersadi = scan.nextLine();
        System.out.println("Ders Saati:");
        derssaati = scan.nextLine();
        System.out.println("Ders Günü:");
        dersgunu = scan.nextLine();

        try {

            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            PreparedStatement addEntry = connect.prepareStatement("INSERT INTO DERSPROGRAMI"
                    + "(derskodu,dersadi,derssaati,dersgunu)"
                    + "VALUES ( ?, ?, ?, ?)");

            addEntry.setString(1, getDerskodu());
            addEntry.setString(2, getDersadi());
            addEntry.setString(3, getDerssaati());
            addEntry.setString(4, getDersgunu());

            addEntry.executeUpdate();
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Çıkmak için 0'a devam etmek için 1'e basınız");
        int giris = scan.nextInt();

        if (giris == 1) {
            dersProgramiHazirla();
        }
        if (giris == 0) {


                geriDon();

            
        }

    }
}


