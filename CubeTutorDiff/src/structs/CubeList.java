package structs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class CubeList {
    
    private String name;
    private String owner;
    private URL url;
    private int ID;
    private HashMap<Card, Integer> cubeContents;
    
    private static final char DEFAULT_QUOTE = '"';
    private static final char DEFAULT_SEPARATOR = ',';
    
    
    public CubeList(String name, String owner){
        this.name = name;
        this.owner = owner;
        this.url = null;
        this.ID = -1;
        this.cubeContents = new HashMap<Card,Integer>();
    }

    public String getName() {
        return name;
    }


    public String getOwner() {
        return owner;
    }


    public URL getUrl() {
        return url;
    }


    public int getID() {
        return ID;
    }

    public Set<Card> getSetOfUniqueCards() {
        return this.cubeContents.keySet();
    }
    
    public HashMap<Card, Integer> getCubeContents() {
        return cubeContents;
    }
    
    //Import contents from an exported .csv file
    //inputs: fileName
    //outputs: hashmap of the cards in the file
    public void importCubeContents(File file) throws IOException {
        
        //List<Card> lines = new ArrayList<>();
        Scanner inputStream;
        
        try{
            inputStream = new Scanner(file);
            
            while(inputStream.hasNext()){
                String line = inputStream.nextLine();
                ArrayList<String> values = splitCsvHelper(line);
                Card c = new Card(values.get(0),values.get(1),values.get(2),values.get(3));
                addCardToContents(c);
            }
            
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @SuppressWarnings("null")
    private ArrayList<String> splitCsvHelper(String line){
        ArrayList<String> result = new ArrayList<>();
        if (line == null && line.isEmpty()) return result;
        
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean isCollecting = false;
        boolean doubleQuoteEscape = false;
        
        char[] chars = line.toCharArray();
        
        for (char c : chars) {
            if (inQuotes) {
                isCollecting = true;
                if (c == DEFAULT_QUOTE) {
                    inQuotes = false;
                    doubleQuoteEscape = false;
                } else {
                    if (c == '\"') {
                        if (!doubleQuoteEscape) {
                            curVal.append(c);
                            doubleQuoteEscape = true;
                        }
                    } else {
                        curVal.append(c);
                    }
                }
            } else {
                if (c == DEFAULT_QUOTE) {
                    inQuotes = true;
                    
                    if (chars[0] != '"') curVal.append(c);
                    if (isCollecting) curVal.append(c);
                } else if (c == DEFAULT_SEPARATOR) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    isCollecting = false;
                } else if (c == '\r') {
                    continue;
                } else if (c == '\n') {
                    break;
                } else {
                    curVal.append(c);
                }
            }
        }
        
        result.add(curVal.toString());
        return result;
    }

    public void addCardToContents(Card c) {
        if (this.cubeContents.containsKey(c)) {
            this.cubeContents.put(c,this.cubeContents.get(c)+1);
        }
        else {
            this.cubeContents.put(c, 1);
        }
        
    }
    
    public int getNumOfTotalCards() {
        int sum = 0;
        for (int i : this.cubeContents.values()){
            sum += i;
        }
        
        return sum;
    }

    public int getNumOfUniqueCards() {
        return this.getSetOfUniqueCards().size();
    }

    
    //returns boolean if the card was removed
    public boolean removeCardFromContents(Card c) {
        if (this.cubeContents.containsKey(c)) {
            if (this.cubeContents.get(c) >= 2) {
                this.cubeContents.put(c, this.cubeContents.get(c) - 1);
            }
            else {
                this.cubeContents.remove(c);
            }
            return true;
        }
        return false;
    }

    public boolean doesCardExist(Card c) {
        return this.cubeContents.containsKey(c);
    }

    public Set<Card> findSameCardsBetweenLists(CubeList cl) {
        Set<Card> s1 = new HashSet<>();
        Set<Card> s2 = new HashSet<>();
        
        s1.addAll(this.getSetOfUniqueCards());
        s2.addAll(cl.getSetOfUniqueCards());
        if (s1.equals(s2)) {
            return s1; //lists are the same, so return one of the sets
        }
        s1.retainAll(s2);
        return s1;
    }

    public Set<Card> findDifferentCardsBetweenLists(CubeList cl) {
        Set<Card> s1 = new HashSet<>();
        Set<Card> s2 = new HashSet<>();
        
        s1.addAll(this.getSetOfUniqueCards());
        s2.addAll(cl.getSetOfUniqueCards());
        
        if (s1.equals(s2)) {
            return null; //lists are the same, so return one of the sets
        }
        
        s1.removeAll(cl.getSetOfUniqueCards());
        s2.removeAll(this.getSetOfUniqueCards());
        s1.addAll(s2);
        return s1;
    }
    
    public static boolean writeCardSetToNewFile(String filePath, Set<Card> s) throws IOException {
        try {
            FileWriter write = new FileWriter(filePath);
            PrintWriter printLine = new PrintWriter(write);
            for (Card c : s) {
                printLine.printf("%s" + "%n",  c.toCSV());
            }

            printLine.close();
            return true;
        }  catch (IOException e) {
            return false;
        }
    } 
    
    //use for the count - will iterate over list and add the card multiple times if necessary
    public static boolean writeCardSetToNewFile(String filePath, Map<Card, Integer> map) throws IOException {
        try {
            FileWriter write = new FileWriter(filePath);
            PrintWriter printLine = new PrintWriter(write);
            
            for (Entry<Card,Integer> e : map.entrySet()) {
                for (int i=0;i<e.getValue();i++) {
                    printLine.printf("%s" + "%n", e.getKey().toCSV());
                }
            }

            printLine.close();
            return true;
        }  catch (IOException e) {
            return false;
        }
    }

    public Map<Card, Integer> findMissingCardsBetweenLists(CubeList cl2) {
        Map<Card, Integer> result = new HashMap<>();
        Set<Card> cards = new HashSet<>();
        cards.addAll(this.getSetOfUniqueCards());
        cards.addAll(cl2.getSetOfUniqueCards());
        for (Card c : cards) {
            int left = 0;
            int right = 0;
            if (this.getCubeContents().get(c) != null) 
                left = this.getCubeContents().get(c);
            if (cl2.getCubeContents().get(c) != null)
                right = cl2.getCubeContents().get(c);
            
            int comp = Math.abs(left - right);
            if (comp != 0) result.put(c,comp);
        }
        
        if (result.size() == 0) return null;
        return result;
    }
}
