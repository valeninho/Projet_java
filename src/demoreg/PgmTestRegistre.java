/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoreg;

/**
 *
 * @author Michel
 */
public class PgmTestRegistre {
    public static void main(String[] args) {
        Registre reg=new Registre();
        int r,rattendu;
        
        System.out.println("smoke test");
        reg.setValActu(5);
        r=reg.getValActu();
        rattendu=5;
        if(r!=rattendu)System.out.println("erreur de smoke test obtenu" +r+" attendu "+rattendu);
        reg.setValActu(5);
        
        System.out.println("test somme ");
        reg.somme(3);
        r=reg.getValActu();
        rattendu=8;
        if(r!=rattendu) System.out.println("erreur de somme obtenu "+r+" attendu "+rattendu);
        reg.setValActu(5);
        
        System.out.println("test différence ");
        reg.difference(-30);
        r=reg.getValActu();
        rattendu=35;
        if(r!=rattendu) System.out.println("erreur de différence obtenu "+r+" attendu "+rattendu);
        reg.setValActu(5);
       
        System.out.println("test produit");
        reg.produit(-5);
        r=reg.getValActu();
        rattendu=-25;
        if(r!=rattendu) System.out.println("erreur de produit obtenu "+r+" attendu "+rattendu);
        reg.setValActu(5);
        
        System.out.println("test de division ");
           
         try{
            reg.quotient(4);
            r=reg.getValActu();
            rattendu=1;
            if(r!=rattendu)System.out.println("erreur de division obtenu "+r+" attendu "+rattendu);
         }
         catch(Exception e){
             System.out.println("erreur de division classique obtenu "+e.getMessage());
         }
         reg.setValActu(5);
         
         try{
            reg.quotient(0);
            System.out.println("erreur exception de division par zéro non générée");
        }
        catch(Exception e){
           
        }
     
    }
}
