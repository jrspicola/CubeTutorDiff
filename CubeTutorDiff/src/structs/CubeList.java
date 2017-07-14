package structs;

import java.util.Scanner;
import java.util.Set;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class CubeList {
    
    private String name;
    private String owner;
    private URL url;
    private int ID;
    private HashMap<Card, Integer> cubeContents;
    
    
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
                String[] values = line.split(",");
                Card c = new Card(values[0],values[1],values[2],values[3]);
                addCardToContents(c);
            }
            
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
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
}
