package planet;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Arrays;
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
            try{
                Files.createDirectory(new File("planetSheets").toPath());
            }catch(FileAlreadyExistsException e){}
            for(String line; (line = br.readLine()) != null;)
                generatePlanets(line.split(","));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void generatePlanets(String[] split) throws IOException{
        if(rand.nextInt(10)>4 && (split[0].equals("G") || split[0].equals("K") || split[0].equals("M"))) {
            outputFile = new File(String.format("planetSheets/%s_%s.pla",split[8],split[9]));
            try {
                Files.createFile(outputFile.toPath());
            }catch(FileAlreadyExistsException e){}
            bw =  new BufferedWriter(new FileWriter(outputFile));
            for(int i = 0; i <= rand.nextInt(10)+1;i++) {
                double mass = 1000*rand.nextDouble();
                double radius = 145.6*rand.nextDouble();
                double gravity = mass/Math.pow(radius,2);
                double distance = 40*rand.nextDouble();
                bw.write(String.format("%s,%s,%f,%f,%f,%f%n", split[8], split[9], mass, radius, gravity, distance));
            }
            bw.close();
        }
    }
}
