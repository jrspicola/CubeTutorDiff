package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import enums.CardType;
import enums.ColorCode;
import structs.Card;

public class CardTests {
    
    @Test
    public void testCardWithEnums() {
        Card c = new Card("Black Lotus", ColorCode.COLOURLESS, CardType.ARTIFACT, 0);

        assertEquals("Black Lotus", c.getName());
        assertEquals(ColorCode.COLOURLESS, c.getColor());
        assertEquals(CardType.ARTIFACT, c.getType());
        assertEquals(0, c.getCmc());
        assertEquals("Black Lotus, Colorless, Artifact, 0", c.toString());
    }
    
    @Test
    public void testCardWithStrings() {
        Card c = new Card("Black Lotus", "COLOURLESS", "ARTIFACT", "0");

        assertEquals("Black Lotus", c.getName());
        assertEquals(ColorCode.COLOURLESS, c.getColor());
        assertEquals(CardType.ARTIFACT, c.getType());
        assertEquals(0, c.getCmc());
        assertEquals("Black Lotus, Colorless, Artifact, 0", c.toString());
    }
    
    @Test
    public void testCardWithStringsHavingQuotes() {
        Card c = new Card("Black Lotus", "\"COLOURLESS\"", "\"ARTIFACT\"", "\"0\"");

        assertEquals("Black Lotus", c.getName());
        assertEquals(ColorCode.COLOURLESS, c.getColor());
        assertEquals(CardType.ARTIFACT, c.getType());
        assertEquals(0, c.getCmc());
        assertEquals("Black Lotus, Colorless, Artifact, 0", c.toString());
    }    

    @Test
    public void testCardHashFunction() {
        Card c = new Card("Black Lotus", ColorCode.COLOURLESS, CardType.ARTIFACT, 0);
        assertEquals(590168749, c.hashCode());
    }

    @Test
    public void testCardEquals() {
        Card c1 = new Card("Black Lotus", ColorCode.COLOURLESS, CardType.ARTIFACT, 0);
        Card c2 = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        assertTrue(c1.equals(c1));
        assertFalse(c1.equals(c2));
    }    
    @Test
    public void testCardCompareTo() {
        Card c1 = new Card("Black Lotus", ColorCode.COLOURLESS, CardType.ARTIFACT, 0);
        Card c2 = new Card("Boros Elite",ColorCode.MONO_WHITE,CardType.CREATURE,1);
        assertTrue(c1.compareTo(c1) == 0);
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }
    
    @Test
    public void testCardOutputs() {
        Card c = new Card("Black Lotus", ColorCode.COLOURLESS, CardType.ARTIFACT, 0);

        assertEquals("Black Lotus, Colorless, Artifact, 0", c.toString());
        assertEquals("Black Lotus,Colorless,Artifact,0", c.toCSV());
    }
    
}
