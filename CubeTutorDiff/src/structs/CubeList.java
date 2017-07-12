package structs;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

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

    private void addCardToContents(Card c) {
        if (this.cubeContents.containsKey(c)) {
            this.cubeContents.put(c,this.cubeContents.get(c)+1);
        }
        else {
            this.cubeContents.put(c, 1);
        }
        
    }
    
}
