/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otomasyon2;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Otomasyon2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Ogrenci o1 = new Ogrenci();
        ogretimGorevlisi o2 = new ogretimGorevlisi();
        idariMemur I = new idariMemur();
        
        
        int x;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("1-Öğrenci Üyeliği\n2-Öğretim Üyesi Üyeliği\n3-İdari Memur Üyeliği\n4-Öğrenci Girişi\n5-Öğretim Üyesi Girişi\n6-İdari Memur Girişi\n");
        
        System.out.println("Yapmak istediğiniz işlemi seçiniz:");
        x = scan.nextInt();
        
        
        
        switch(x){
            case 1:
                o1.uyeOl();
                break;
            case 2:
                o2.uyeOl();
                break;
            case 3:
                I.uyeOl();
                break;
            case 4:
                o1.girisYap();
                break;
            case 5:
                o2.girisYap();
                break;
            case 6:
                I.girisYap();
                break;
            
            
        
        
        
        }

        
        
    }
    
}
