package enums;

/**
 * @author Joe Spicola
 *
 */

public enum CardType {
        CREATURE("Creature"),
        ARTIFACT_CREATURE("Artifact Creature"),
        PLANESWALKER("Planeswalker"),
        INSTANT("Instant"),
        SORCERY("Sorcery"),
        ENCHANTMENT("Enchantment"),
        ARTIFACT("Artifact"),
        LAND("Land");
        
        private String print;
        
        CardType(String print){
            this.print = print;
        }
        
        public String print(){
            return print;
        }
}
