package enums;

public enum ColorCode {
    MONO_WHITE("White"),
    MONO_BLUE("Blue"),
    MONO_BLACK("Black"),
    MONO_RED("Red"),
    MONO_GREEN("Green"),
    AZORIUS("Azorius"),
    DIMIR("Dimir"),
    RAKDOS("Rakdos"),
    GRUUL("Gruul"),
    SELESNYA("Selesnya"),
    ORZHOV("Orzhov"),
    GOLGARI("Golgari"),
    SIMIC("Simic"),
    IZZET("Izzet"),
    BOROS("Boros"),
    JUND("Jund"),
    NAYA("Naya"),
    BANT("Bant"),
    ESPER("Esper"),
    GRIXIS("Grixis"),
    JESKAI("Jeskai"),
    MARDU("Mardu"),
    SULTAI("Sultai"),
    ABZAN("Abzan"),
    TEMUR("Temur"),
    YORE_TILLER("WUBR"),
    GLINT_EYE("UBRG"),
    DUNE_BROOD("WBRG"),
    INK_TREADER("WURG"),
    WITCH_MAW("WUBG"),
    COLOURLESS("Colorless");
    
    private String print;
    
    ColorCode(String print){
        this.print = print;
    }
    
    public String print(){
        return print;
    }
    
}
