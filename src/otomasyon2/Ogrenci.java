/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon2;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
abstract class Ogr {

    void notGor() {
    }

    ;
    void dersSec() {
    }

;

}

interface Ogr1 {

    void girisYap();

    void uyeOl();

}

public class Ogrenci extends Ogr implements Ogr1 {

    private String firstname;
    private String lastname;
    private String studentid;
    private String password;
    private String email;
    static String university = "İstanbul University";
    private String derskodu;
    private String dersadi;
    private String derssaati;
    private String dersgunu;
    private String notu;
    private String harfnotu;

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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getUniversity() {
        return university;
    }

    public void geriDon() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1-Notları Görüntüle\n2-Ders Seç\n3-Devamsızlık Bilgisi\n4-ÇIKIŞ\n");
        System.out.println("Yapmak istediğiniz işlemi seçin:");

        int x = scan.nextInt();

        switch (x) {
            case 1:
                notGor();
                break;
            case 2:
                dersSec();
                break;

            case 3:
                devamsizlikGoruntule();
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
        System.out.println("Öğrenci Numarası:");
        studentid = scan.nextLine();
        System.out.println("Şifre:");
        password = scan.nextLine();
        System.out.println("Eposta:");
        email = scan.nextLine();

        try {

            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            PreparedStatement addEntry = connect.prepareStatement("INSERT INTO OGRENCI"
                    + "(firstname,lastname,studentid,password,email,university)"
                    + "VALUES ( ?, ?, ?, ?,? , ?)");

            addEntry.setString(1, getFirstname());
            addEntry.setString(2, getLastname());
            addEntry.setString(3, getStudentid());
            addEntry.setString(4, getPassword());
            addEntry.setString(5, getEmail());
            addEntry.setString(6, getUniversity());

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

        // specify the PreparedStatement's arguments
    }

    @Override
    public void girisYap() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ad:");
        firstname = scan.nextLine();
        System.out.println("Soyad:");
        lastname = scan.nextLine();

        System.out.println("Öğrenci Numarası:");
        studentid = scan.nextLine();
        System.out.println("Şifre:");
        password = scan.nextLine();

        boolean uyevar = false;
        int x;

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ogrenci");
            while (rs.next()) {

                if (rs.getString(3).equals(studentid) && rs.getString(4).equals(password)) {
                    uyevar = true;
                    System.out.println("1-Notları Görüntüle\n2-Ders Seç\n3-Devamsızlık Bilgisi\n");
                    System.out.println("Yapmak istediğiniz işlemi seçin:");
                   
                    x = scan.nextInt();

                    switch (x) {
                        case 1:
                            notGor();
                            break;
                        case 2:
                            dersSec();
                            break;
                        case 3:
                            devamsizlikGoruntule();

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
    public void notGor() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Öğrenci Numarası:");
        studentid = scan.nextLine();
        
        System.out.printf("%-20s%-20s%-20s%-20s%n","Ders Kodu","Not","Harf Notu","Ders Adı");

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM secilendersler");

            while (rs.next()) {

                if (rs.getString(3).equals(studentid)) {

                    System.out.printf("%-20s%-20s%-20s%-20s%n",rs.getString(1), rs.getString(6),rs.getString(7),rs.getString(2));

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Başka bir işlem yapmak için 0'a basınız");
        int deger = scan.nextInt();
        if (deger == 0) {
            geriDon();
        }

    }

    @Override
    public void dersSec() {

        System.out.println("DERS PROGRAMI:\n");
        Scanner scan = new Scanner(System.in);
        System.out.printf("%-20s%-20s%-20s%-20s%n","Ders Kodu","Ders Saati","Ders Günü","Ders Adı");
        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dersprogrami");
            while (rs.next()) {
                System.out.printf("%-20s%-20s%-20s%-20s%n",rs.getString(1),rs.getString(3), rs.getString(4), rs.getString(2));
            }

            System.out.println("Seçmek istediğiniz ders kodu:");
            derskodu = scan.nextLine();
            System.out.println("Seçmek istediğiniz ders adı:");
            dersadi = scan.nextLine();

            PreparedStatement addEntry = connect.prepareStatement("INSERT INTO SECILENDERSLER"
                    + "(derskodu,dersadi,ogrencino,ogrenciadi,ogrencisoyadi,notu)"
                    + "VALUES ( ?, ?, ?, ?,?,?)");

            addEntry.setString(1, getDerskodu());
            addEntry.setString(2, getDersadi());
            addEntry.setString(3, getStudentid());
            addEntry.setString(4, getFirstname());
            addEntry.setString(5, getLastname());
            addEntry.setString(6, null);

            addEntry.executeUpdate();
            
                        PreparedStatement addEntry1 = connect.prepareStatement("INSERT INTO YOKLAMA"
                    + "(studentid, derskodu, devamsizlik)"
                    + "VALUES ( ?, ?, ?)");

            addEntry1.setString(1, getStudentid());
            addEntry1.setString(2, getDerskodu());
            addEntry1.setInt(3, 0);


            addEntry1.executeUpdate();

            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("cikmak icin 0a devam etmek icin 1e basınız");
        int giris = scan.nextInt();
        if (giris == 1) {
            dersSec();
        }
        if (giris == 0) {


                geriDon();

            }
        }
    
    public void devamsizlikGoruntule(){
        
               Scanner scan = new Scanner(System.in);
        //System.out.println("Öğrenci Numarası:");
        //studentid = scan.nextLine();
        
        System.out.printf("%-20s%-20s%n","Ders Kodu","Devamsızlık");

        try {
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/otomasyon", "elif", "elif");

            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM yoklama");

            while (rs.next()) {

                if (rs.getString(1).equals(studentid)) {

                    System.out.printf("%-20s%-20s%n",rs.getString(2), rs.getString(3));

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Başka bir işlem yapmak için 0'a basınız");
        int deger = scan.nextInt();
        if (deger == 0) {
            geriDon();
        }
        
    
    
    
    }

    }


