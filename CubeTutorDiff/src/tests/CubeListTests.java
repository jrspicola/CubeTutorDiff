package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import enums.CardType;
import enums.ColorCode;
import structs.Card;
import structs.CubeList;

public class CubeListTests {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testCubeListSimple() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");

        assertEquals("TestCube", cl.getName());
        assertEquals("Joe Schmoe", cl.getOwner());
        assertEquals(-1, cl.getID());
        assertEquals(null, cl.getUrl());
        assertEquals(0, cl.getCubeContents().size());
    }
    
    @Test
    public void testCubeListImport() throws IOException {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        
        String fileName = "testData/CubeListTest.csv";
        File file = new File(fileName);
        
        cl.importCubeContents(file);
        assertEquals(1,cl.getCubeContents().size());
    }

    @Test
    public void testCubeListAddCard() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        
        cl.addCardToContents(c);
        assertEquals(1,cl.getCubeContents().size());
    }
    
    @Test
    public void testCubeListAddDuplicateCard() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        
        cl.addCardToContents(c);
        cl.addCardToContents(c);
        assertEquals(1,cl.getNumOfUniqueCards());
        assertEquals(2,cl.getNumOfTotalCards());
    }
    
    @Test
    public void testCubeListRemoveCard() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        
        cl.addCardToContents(c);
        assertEquals(1,cl.getNumOfUniqueCards());
        
        assertTrue(cl.removeCardFromContents(c));
        assertEquals(0,cl.getNumOfUniqueCards());
        
        assertFalse(cl.removeCardFromContents(c));
        assertEquals(0,cl.getNumOfUniqueCards());
        
        cl.addCardToContents(c);
        cl.addCardToContents(c);
        assertTrue(cl.removeCardFromContents(c));
        assertEquals(1,cl.getNumOfTotalCards());
    }
    
    @Test
    public void testCubeCheckIfCardExists() {
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c1 = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        Card c2 = new Card("Black Lotus",ColorCode.COLOURLESS,CardType.ARTIFACT,0);
        
        cl.addCardToContents(c1);
        
        assertTrue(cl.doesCardExist(c1));
        assertFalse(cl.doesCardExist(c2));
    }

    @Test
    public void testCubeListFindSameCards() throws IOException {
        CubeList cl1 = new CubeList("TestCube", "Joe Schmoe");
        CubeList cl2 = new CubeList("TestCube2", "Chuck Duck");
        Card c = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        Set<Card> similar = new HashSet<>();
        similar.add(c);
        
        String fileName = "testData/CubeListTest.csv";
        File file = new File(fileName);
        cl1.importCubeContents(file);
        
        fileName = "testData/CubeListTest2.csv";
        file = new File(fileName);
        cl2.importCubeContents(file);
        
        
        Set<Card> diff = cl1.findSameCardsBetweenLists(cl2);
        assertEquals(similar,diff);
        
        diff = cl1.findSameCardsBetweenLists(cl1);
        assertEquals(similar,diff);
    }
    
    @Test
    public void testCubeListFindDifferentCardCounts() throws IOException {
        CubeList cl1 = new CubeList("TestCube", "Joe Schmoe");
        CubeList cl2 = new CubeList("TestCube2", "Chuck Duck");
        Card c1 = new Card("Black Lotus",ColorCode.COLOURLESS,CardType.ARTIFACT,0);
        Card c2 = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        Map<Card, Integer> missing = new HashMap<>();
        missing.put(c1, 2);

        cl1.addCardToContents(c1);
        cl1.addCardToContents(c1);
        cl1.addCardToContents(c1);
        cl1.addCardToContents(c2);
        
        cl2.addCardToContents(c1);
        cl2.addCardToContents(c2);
        
        Map<Card, Integer> diff = cl1.findMissingCardsBetweenLists(cl2);
        assertEquals(missing,diff);
        
        diff = cl1.findMissingCardsBetweenLists(cl1);
        assertEquals(null,diff);
    }
    
    @Test
    public void testCubeListFindDifferentCards() throws IOException {
        CubeList cl1 = new CubeList("TestCube", "Joe Schmoe");
        CubeList cl2 = new CubeList("TestCube2", "Chuck Duck");
        Card c = new Card("Black Lotus",ColorCode.COLOURLESS,CardType.ARTIFACT,0);
        Set<Card> similar = new HashSet<>();
        similar.add(c);
        
        String fileName = "testData/CubeListTest.csv";
        File file = new File(fileName);
        cl1.importCubeContents(file);
        
        fileName = "testData/CubeListTest2.csv";
        file = new File(fileName);
        cl2.importCubeContents(file);
        
        
        Set<Card> diff = cl1.findDifferentCardsBetweenLists(cl2);
        assertEquals(similar,diff);
        
        diff = cl1.findDifferentCardsBetweenLists(cl1);
        assertEquals(null,diff);
    }
    
    @Test
    public void testCubeListWriteNewFileFromSet() throws IOException {
        
        CubeList cl = new CubeList("TestCube", "Joe Schmoe");
        Card c = new Card("Black Lotus",ColorCode.COLOURLESS,CardType.ARTIFACT,0);
        cl.addCardToContents(c);
        
        File tempFile = tempFolder.newFile("file.txt");
        assertTrue(CubeList.writeCardSetToNewFile(tempFile.getAbsolutePath(), cl.getSetOfUniqueCards()));
        assertFalse(CubeList.writeCardSetToNewFile("", cl.getSetOfUniqueCards()));
        
        
    }
}
