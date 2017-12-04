package planet;

import java.io.*;
import java.util.Random;

public class PlanetSheet {

    private File dataFile;
    private File outputFile;
    private Random rand = new Random();
    private BufferedWriter bw;

    public PlanetSheet(String file){
        dataFile = new File(file+".txt");
        outputFile = new File(file+".pla");
        parseInput();
    }

    private void parseInput() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            bw =  new BufferedWriter(new FileWriter(outputFile));
            for(String line; (line = br.readLine()) != null;)
                generatePlanets(line.split(","));
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void generatePlanets(String[] split) throws IOException{
        if(rand.nextInt(10)>4 && (split[0].equals("G") || split[0].equals("K") || split[0].equals("M"))) {
            bw.write(String.format("%s,%s%n",split[8],split[9]));
            for(int i = 0; i <= rand.nextInt(10)+1;i++) {
                double mass = 1000*rand.nextDouble();
                double radius = 145.6*rand.nextDouble();
                double gravity = mass/Math.pow(radius,2);
                double distance = 40*rand.nextDouble();
                bw.write(String.format("%s,%s,%f,%f,%f,%f%n", split[8], split[9], mass, radius, gravity, distance));
            }
        }
    }
}
