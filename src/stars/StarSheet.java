package stars;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StarSheet {
    private static int NUMBER_OF_STARS;
    private static int WIDTH;
    private static int HEIGHT;
    private File outputFile;
    private BufferedWriter bw;
    private StarMath starMath = new StarMath();
    public enum StarType {
        O ((int)((NUMBER_OF_STARS*.9) * .0003f+1), 16, 50, Color.white.getRGB()),
        B ((int)((NUMBER_OF_STARS*.9) * .0013f), 2.1, 16, Color.white.getRGB()),
        A ((int)((NUMBER_OF_STARS*.9) * .006f), 1.4, 2.1, Color.cyan.getRGB()),
        F ((int)((NUMBER_OF_STARS*.9) * .03f), 1.04, 1.4, Color.blue.getRGB()),
        G ((int)((NUMBER_OF_STARS*.9) * .076f), 0.8, 1.04, Color.yellow.getRGB()),
        K ((int)((NUMBER_OF_STARS*.9) * .121), 0.45 , 0.8, Color.orange.getRGB()),
        M ((int)((NUMBER_OF_STARS*.9) * .7645f), .08, 0.45, Color.red.getRGB());
        private final int starCount;
        private final double lowerMassLimit;
        private final double upperMassLimit;
        public final int rgbValue;
        StarType(int starCount, double smallestMass, double biggestMass, int rgbValue){
            this.starCount = starCount;
            this.lowerMassLimit = smallestMass;
            this.upperMassLimit = biggestMass;
            this.rgbValue = rgbValue;
        }
    }

    public StarSheet(int starCount, int width, int height, String filename){
            NUMBER_OF_STARS = starCount;
            WIDTH = width;
            HEIGHT = height;
            outputFile = new File(filename+".txt");
            generateStars();
    }

    private String makeAStar(double mass, StarType type){
        double radius = starMath.getRadius(mass);
        double luminosity = starMath.getLuminosity(mass);
        double lifetime = starMath.getLifetime(mass);
        double temperature = starMath.getTemperature(mass);
        double innerHZ = starMath.getInnerHZ(luminosity);
        double outerHZ = starMath.getOuterHZ(luminosity);
        double phi = starMath.getPhi();
        double rho = starMath.getRho();
        int locationX = (int)(Math.sqrt(rho) * Math.cos(phi) * WIDTH/2)+WIDTH/2;
        int locationY = (int)(Math.sqrt(rho) * Math.sin(phi) * HEIGHT/2)+HEIGHT/2;
        return String.format("%s,%f,%f,%f,%f,%f,%f,%f,%d,%d%n",
                type.toString(),mass,luminosity,radius,innerHZ,outerHZ,temperature,lifetime,locationX,locationY);
    }

    private void generateStars() {
        try {
            bw = new BufferedWriter(new FileWriter(outputFile));
            printStarData();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void printStarData() throws IOException{
        for (StarType type : StarType.values()) {
            for (int i = 0; i < type.starCount; i++) {
                bw.write(makeAStar(starMath.getMass(type.lowerMassLimit, type.upperMassLimit), type));
            }
        }
    }

}