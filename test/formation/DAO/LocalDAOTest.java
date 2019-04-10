package formation.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import formation.info.Local;
import myconnections.DBConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


/**
 *
 * @author valentin
 */

// j'ai utilisé la classe FixMethodOrder car mes différents tests s'exécutaient dans un ordre aléatoire et faisaient planter tout le programme

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalDAOTest {

    static Connection dbConnect;

    public LocalDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }
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

    @Test

    public void atestCreate() throws Exception {
        System.out.println("create");
        Local obj = new Local(0, "TSigl", 10, "TestDescription");
        LocalDAO instance = new LocalDAO();
        instance.setConnection(dbConnect);
        Local expResult = new Local(0, "TSigl", 10, "TestDescription");
        Local result = instance.create(obj);

        assertEquals("Sigle Différents", expResult.getSigle(), result.getSigle());
        assertEquals("nombre de places différent", expResult.getPlaces(), result.getPlaces());
        assertEquals("descriptions différents", expResult.getDescription(), result.getDescription());
        assertNotEquals("id non généré", expResult.getIdlocal(), result.getIdlocal());
        int idlocal = result.getIdlocal();
        
        instance.delete(obj);
        /*obj = new Local(0, "TSigl", 50, "TestDescription");
        try {
            Local result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        } catch (SQLException e) {
        }
        instance.delete(result);

        obj = new Local(0, "TestSigle", 5, "TestDescription");
        try {
            Local result3 = instance.create(obj);
            fail("exception de code postal non déclenchée");
            instance.delete(result3);
        } catch (SQLException e) {
        }*/

    }

    @Test
    public void btestRead() throws Exception {
        System.out.println("read");
        int idlocal = 0;
        LocalDAO instance = new LocalDAO();
        instance.setConnection(dbConnect);
        Local obj = new Local(0, "TSigl", 10, "TestDescription");
        Local expResult = instance.create(obj);
        idlocal = expResult.getIdlocal();
        Local result = instance.read(idlocal);
        assertEquals("sigle différents", expResult.getSigle(), result.getSigle());
        assertEquals("nombre de places différent", expResult.getPlaces(), result.getPlaces());
        assertEquals("descriptions différents", expResult.getDescription(), result.getDescription());
        assertEquals("id différents", expResult.getIdlocal(), result.getIdlocal());
        try {
            result = instance.read(0);
            fail("exception d'id inconnu non générée");
        } catch (SQLException e) {
        }
        instance.delete(result);
    }
    
    @Test
    public void ctestUpdate() throws Exception {
        System.out.println("update");
        Local obj = new Local(0,"TSigl", 10, "TestDescription");
        LocalDAO instance = new LocalDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        obj.setSigle("TSig2");
        obj.setPlaces(20);
        obj.setDescription("TestDescription2");
  
        Local expResult=obj;
        Local result = instance.update(obj);
        assertEquals(expResult.getSigle(), result.getSigle());
        assertEquals(expResult.getPlaces(), result.getPlaces());
        assertEquals(expResult.getDescription(), result.getDescription());
        instance.delete(obj);
        //TODO verifier que si met à jour vers un triplet nom-prenom-tel déjà existant, on a une exception
        //TODO verifier que si on met à jour vers un cp non valide, on a une exception
    }
    
    @Test
    public void dtestDelete() throws Exception {
        System.out.println("delete");
        Local obj = new Local(0,"TSigl", 10, "TestDescription");
        LocalDAO instance = new LocalDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdlocal());
            fail("exception de record introuvable non générée");
        }
        catch(SQLException e){}
        //TODO vérifier qu'on a bien une exception en cas de record parent de clé étrangère
    }
    
    @Test
    public void etestRechDesc() throws Exception {
        System.out.println("rechNom");
        Local obj1 = new Local(0, "TSigl", 10, "TestDescription");
        Local obj2 = new Local(0,"TSigl", 20, "TestDescription");
        
        String desc = "TestDescription";
        LocalDAO instance = new LocalDAO();
        instance.setConnection(dbConnect);
        obj1=instance.create(obj1);
        obj2=instance.create(obj2);
        
      
        List<Local> result = instance.rechDesc(desc);
        if(result.indexOf(obj1)<0) fail("record introuvable "+obj1);
        if(result.indexOf(obj2)<0) fail("record introuvable"+obj2);
        instance.delete(obj1);
        instance.delete(obj2);
    }
    
    
}
