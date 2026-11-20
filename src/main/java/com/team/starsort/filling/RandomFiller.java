package com.team.starsort.filling;

import java.util.Random;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class RandomFiller implements DataFiller<Star> {
	
	private final Random random = new Random();
	@Override
	public CustomList<Star> fill(int size){
		
		CustomList<Star> list = new CustomArrayList<>();
		return list;
		
	}
	private Star createRandomStar() {
        String name = "Star-" + random.nextInt(1000);
        double magnitude = random.nextDouble() * 15.0;
        long distance = (long) (random.nextDouble() * 10000000);

        return new Star.Builder()
                .name(name)
                .apparentMagnitude(magnitude)
                .distanceLightYears(distance)
                .build();
    }

}
