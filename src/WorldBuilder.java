import planet.PlanetMap;
import planet.PlanetSheet;
import stars.StarMap;
import stars.StarSheet;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;


public class WorldBuilder {
	private static final int NUMBER_OF_STARS = 10;
	private static final int WIDTH = 75000;
	private static final int HEIGHT = 25000;
	private static final String OUTPUT_FILE = "StarMap";
	public static void main(String[] args) throws IOException{
		try {
			Files.createDirectory(new File("planetSheets").toPath());
			Files.createDirectory(new File("planetMaps").toPath());
		}catch (FileAlreadyExistsException e){
			System.err.println("Directories Good");
		}
		System.out.println("Making Star Sheet...");
		new StarSheet(NUMBER_OF_STARS, WIDTH, HEIGHT, OUTPUT_FILE);
		System.out.println("Making Star Map...");
		new StarMap(OUTPUT_FILE, WIDTH, HEIGHT);
		System.out.println("Making Planet Sheets...");
		new PlanetSheet(OUTPUT_FILE);
		System.out.println("Making Planet Maps...");
		new PlanetMap("planetSheets");
	}
}
