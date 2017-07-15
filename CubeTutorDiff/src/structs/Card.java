package structs;

import enums.CardType;
import enums.ColorCode;

/**
 * @author Joe Spicola
 *
 * An individual MTG card. It has a name, a color, a type, and a cmc.
 */
public class Card implements Comparable<Card> {
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
    
    public Card(String name, String color, String type, String cmc) {
        this.name = name;
        this.color = ColorCode.valueOf(stripQuotes(color));
        this.type = CardType.valueOf(stripQuotes(type));
        this.cmc = Integer.parseInt(stripQuotes(cmc));
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
    
    public String toString() {
        String del = ", ";
        return this.getName() + del + this.getColor().print() + del + this.getType().print() + del + this.getCmc();                
    }
    
    public String toCSV() {
        String del = ",";
        return this.getName() + del + this.getColor().print() + del + this.getType().print() + del + this.getCmc();  
    }

    @Override
    public int compareTo(Card c) {
        String n = c.getName();
        return this.getName().compareToIgnoreCase(n);
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (!Card.class.isAssignableFrom(o.getClass())) return false;
        final Card c = (Card) o;
        return this.name.equals(c.getName());
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    

    //helper function to remove extra quotation marks
    private String stripQuotes(String s) {
        char q = '\"';
        if (s.charAt(0) == q && s.charAt(s.length()-1) == q)
            return s.substring(1, s.length()-1);
        else
            return s;
    }
    
}
