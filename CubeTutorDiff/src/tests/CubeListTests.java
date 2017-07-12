package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import enums.CardType;
import enums.ColorCode;
import structs.Card;
import structs.CubeList;

public class CubeListTests {

    @Test
    public void testCubeListSimple() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");

        assertEquals(cl.getName(), "TestCube");
        assertEquals(cl.getOwner(), "Joe Schmoe");
        assertEquals(cl.getID(), -1);
        assertEquals(cl.getUrl(), null);
        assertEquals(cl.getCubeContents().size(), 0);
    }
    
    @Test
    public void testCubeListImport() throws IOException {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c1 = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        
        String fileName = "testData/CubeListTest.csv";
        File file = new File(fileName);
        
        cl.importCubeContents(file);
        assertEquals(cl.getCubeContents().size(),1);
    }
}
