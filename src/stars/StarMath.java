package stars;

import java.util.Random;

public class StarMath {
	private Random rand = new Random();

	public double getRadius(double mass){
		if (mass < 1){
			return Math.pow(mass, 0.8);
		}else if(mass > 1){
			return Math.pow(mass, 0.5);
		}else{
			return 1;
		}
	}
	
	public double getLuminosity(double mass){
		return Math.pow(mass, 3.5);
	}
	
	public double getLifetime(double mass){
		return Math.pow(mass, -2.5);
	}
	
	public double getTemperature(double mass){
		return Math.pow(mass,.505);
	}
	
	public double getInnerHZ(double luminosity){
		return Math.sqrt(luminosity/1.1);
	}
	
	public double getOuterHZ(double luminosity){
		return Math.sqrt(luminosity/.53);
	}

	public double getPhi(){
		return getRandomInRange(0,2*Math.PI);
	}
	public double getRho(){
		return getRandomInRange(0,1);
	}

	private double getRandomInRange(double min, double max){
		double range = max - min;
		double value = rand.nextDouble();
		return (value*range)+min;
	}

	public double getMass(double lowerMassLimit, double upperMassLimit) {
		return getRandomInRange(lowerMassLimit,upperMassLimit);
	}
}
