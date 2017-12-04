package stars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StarMap {

    private static int WIDTH;
    private static int HEIGHT;
    private File dataFile;
    private File outputFile;
    private BufferedImage starMap;

    public StarMap(String file, int width, int height){
        WIDTH = width;
        HEIGHT = height;
        dataFile = new File(file+".txt");
        outputFile = new File(file+".png");
        starMap = util.BigBufferedImage.create(WIDTH, HEIGHT, util.BigBufferedImage.TYPE_INT_RGB);
        parseInput();
        saveStarMap();
    }

    private void parseInput() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            String line;
            while((line = br.readLine()) != null){
                mapStar(line.split(","));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void mapStar(String[] starData) {
        int x = Integer.parseInt(starData[8]);
        int y = Integer.parseInt(starData[9]);
        starMap.setRGB(x,y,getStarColor(starData[0]));
    }

    private void saveStarMap() {
        try {
            ImageIO.write(starMap, "png", outputFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private int getStarColor(String starType) {
        for(StarSheet.StarType type: StarSheet.StarType.values()){
            if(type.toString().equals(starType)){
                return type.rgbValue;
            }
        }
        return Color.black.getRGB();
    }
}
