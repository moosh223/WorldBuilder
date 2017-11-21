import stars.StarMap;
import stars.StarSheet;

/**
 *
 * @author Josh
 */
public class WorldBuilder {
	private static final int NUMBER_OF_STARS = 500000;
	private static final int WIDTH = NUMBER_OF_STARS/10;
	private static final int HEIGHT = NUMBER_OF_STARS/30;
	private static final String OUTPUT_FILE = "MyFile";
	public static void main(String[] args){
		System.out.println("Making Star Sheet...");
		new StarSheet(NUMBER_OF_STARS,WIDTH,HEIGHT,OUTPUT_FILE);
		System.out.println("Making Star Map...");
		new StarMap(OUTPUT_FILE,WIDTH,HEIGHT);
	}
}
