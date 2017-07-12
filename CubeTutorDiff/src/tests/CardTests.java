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

        assertEquals(c.getName(), "Black Lotus");
        assertEquals(c.getColor(), ColorCode.COLOURLESS);
        assertEquals(c.getType(), CardType.ARTIFACT);
        assertEquals(c.getCmc(), 0);
        assertEquals(c.toString(), "Black Lotus, Colorless, Artifact, 0");
    }
    
    @Test
    public void testCardWithStrings() {
        Card c = new Card("Black Lotus", "COLOURLESS", "ARTIFACT", "0");

        assertEquals(c.getName(), "Black Lotus");
        assertEquals(c.getColor(), ColorCode.COLOURLESS);
        assertEquals(c.getType(), CardType.ARTIFACT);
        assertEquals(c.getCmc(), 0);
        assertEquals(c.toString(), "Black Lotus, Colorless, Artifact, 0");
    }
    
    @Test
    public void testCardWithStringsHavingQuotes() {
        Card c = new Card("Black Lotus", "\"COLOURLESS\"", "\"ARTIFACT\"", "\"0\"");

        assertEquals(c.getName(), "Black Lotus");
        assertEquals(c.getColor(), ColorCode.COLOURLESS);
        assertEquals(c.getType(), CardType.ARTIFACT);
        assertEquals(c.getCmc(), 0);
        assertEquals(c.toString(), "Black Lotus, Colorless, Artifact, 0");
    }    
    
}
