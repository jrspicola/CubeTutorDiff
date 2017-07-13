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
    
}
