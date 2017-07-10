package structs;

import enums.CardType;
import enums.ColorCode;

/**
 * @author Joe Spicola
 *
 * An individual MTG card. It has a name, a color, a type, and a cmc.
 */
public class Card {
    private String name;
    private ColorCode color;
    private CardType type;
    private int cmc;
    
    public Card(String name, ColorCode color, CardType type, int cmc){
        this.name = name;
        this.color = color;
        this.type = type;
        this.cmc = cmc;
    }
    
    public Card(String name, String color, String type, String cmc){
        this.name = name;
        this.color = ColorCode.valueOf(color);
        this.type = CardType.valueOf(type);
        this.cmc = Integer.parseInt(cmc);
    }

    public String getName() {
        return name;
    }

    public ColorCode getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public int getCmc() {
        return cmc;
    }
    
    
    
}
