import java.io.File;
import java.io.IOException;
import java.util.Set;

import structs.*;

public class MainClass {

    public static void main(String[] args) throws IOException {
        CubeList cl1 = new CubeList("Ravnica", "Joe Spicola");
        CubeList cl2 = new CubeList("Ravnica Full List", "Joe Spicola");

        String fileName = "C:/Users/Joe Spicola/Documents/MTG/Cube Tutor Lists/ravnica.csv";
        File file = new File(fileName);
        cl1.importCubeContents(file);
        
        fileName = "C:/Users/Joe Spicola/Documents/MTG/Cube Tutor Lists/ravnica_list.csv";
        file = new File(fileName);
        cl2.importCubeContents(file);
        
        Set<Card> set = cl1.findDifferentCardsBetweenLists(cl2);
        
        String destFilePath = "C:/Users/Joe Spicola/Documents/MTG/Cube Tutor Lists/missing_ravnica.csv";
        
        CubeList.writeCardSetToNewFile(destFilePath, set);
    }

}
