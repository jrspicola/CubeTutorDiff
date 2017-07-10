package enums;

public enum CardType {
        CREATURE("Creature"),
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
