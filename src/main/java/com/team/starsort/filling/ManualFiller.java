package com.team.starsort.filling;

import java.util.Scanner;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class ManualFiller implements DataFiller<Star> {

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public CustomList<Star> fill(int size) {
		CustomList<Star> list = new CustomArrayList<>();

		for (int i = 0; i < size; i++) {
			System.out.println("\n--- Ввод звезды " + (i + 1) + " из " + size + " ---");

		}

		return list;
	}
}