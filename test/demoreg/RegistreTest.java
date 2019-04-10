/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoreg;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michel
 */
public class RegistreTest {
    
    public RegistreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
      
    }
    
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
       
    }

    /**
     * Test of getValActu method, of class Registre.
     */
    @Test
    public void testGetValActu() {
        System.out.println("getValActu");
        Registre instance = new Registre();
        int expResult = 0;
        int result = instance.getValActu();
        assertEquals("erreur d'initialisation ",expResult, result);
           
    }

    /**
     * Test of setValActu method, of class Registre.
     */
    @Test
    public void testSetValActu() {
        System.out.println("setValActu");
        int valActu = 5;
        Registre instance = new Registre();
        instance.setValActu(valActu);
        int expResult = 5;
        int result = instance.getValActu();
        assertEquals("erreur de setter ",expResult, result);
    }

    /**
     * Test of somme method, of class Registre.
     */
    @Test
    public void testSomme() {
        System.out.println("somme");
        int v = 3;
        Registre instance = new Registre();
        instance.setValActu(5);
        instance.somme(v);
        int expResult = 8;
        int result = instance.getValActu();
        assertEquals("erreur de somme ",expResult, result);
    }

    /**
     * Test of produit method, of class Registre.
     */
    @Test
    public void testProduit() {
        System.out.println("produit");
        int v = -5;
        Registre instance = new Registre();
        instance.setValActu(5);
        instance.produit(v);
        int expResult = -25;
        int result = instance.getValActu();
        assertEquals("erreur de produit ",expResult, result);
    }

    /**
     * Test of difference method, of class Registre.
     */
    @Test
    public void testDifference() {
        System.out.println("difference");
        int v = -30;
        Registre instance = new Registre();
        instance.setValActu(5);
        instance.difference(v);
       int expResult = 35;
        int result = instance.getValActu();
        assertEquals("erreur de différence ",expResult, result);
    }

    /**
     * Test of quotient method, of class Registre.
     */
    @Test
    public void testQuotient() throws Exception {
        System.out.println("quotient");
        int v = 4;
        Registre instance = new Registre();
        instance.setValActu(5);
        instance.quotient(v);
        int expResult=1;
        int result = instance.getValActu();
        assertEquals("erreur de quotient ",expResult, result);
        try {
             instance.setValActu(5);
             v=0;
             instance.quotient(v);
              fail("pas d'erreur de division par zéro");
        }
       catch(Exception e){}
    }
    
}
