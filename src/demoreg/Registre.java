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
public class Registre {

    private int valActu;

    public int getValActu() {
        return valActu;
    }

    public void setValActu(int valActu) {
        this.valActu = valActu;
    }

    public void somme(int v) {
        valActu+=v;
    }

    public void produit(int v) {
        valActu*=v;
    }

    public void difference(int v) {
        valActu -=v;
    }

    public void quotient(int v) throws Exception {
        if (v == 0) {
            throw new Exception("division par zéro");
        }
        valActu /=v;
    }
  
}
