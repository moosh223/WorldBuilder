package planet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class PlanetMap {

    private File dataFolder;
    private File outputFile;
    private BufferedImage planetMap;

    public PlanetMap(String file) {
        dataFolder = new File(file);
        try {
            Files.createDirectory(new File("planetMaps").toPath());
        }catch(IOException e){}
        parseInput();
        savePlanetMap();
    }

    private void parseInput() {
        try{
            for(File file : dataFolder.listFiles()){
                if(file.getPath().contains(".pla")) {
                    outputFile = new File("planetMaps/"+file.getName().replace(".pla", ".png"));
                    planetMap = new BufferedImage(1000, 100, BufferedImage.TYPE_INT_RGB);
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    for (String line; (line = br.readLine()) != null; ) {
                        mapPlanets(line.split(","));
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    private void mapPlanets(String[] starData) {
        Graphics g = planetMap.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,25,50,50);
        g.fillRect((int)Float.parseFloat(starData[5])*25,45,10,10);
        savePlanetMap();
    }

    private void savePlanetMap() {
        try {
            ImageIO.write(planetMap, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
