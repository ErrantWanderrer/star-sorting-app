package com.team.starsort.filling;

import java.util.Scanner;
import java.util.stream.IntStream;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class ManualFiller implements DataFiller<Star> {

	private final Scanner sc;

	public ManualFiller(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public CustomList<Star> fill(int size) {
		CustomList<Star> list = new CustomArrayList<>();

		IntStream.range(0, size).mapToObj(i -> readOneStar(i + 1, size)).forEach(list::add);

		return list;
	}

	private Star readOneStar(int index, int total) {
		System.out.println("\n--- Ввод звезды " + index + " из " + total + " ---");
		while (true) {
			try {
				System.out.print("Введите имя: ");
				String name = sc.nextLine().trim();
				if (name.isEmpty()) {
					System.out.println("Имя не может быть пустым");
					continue;
				}

				System.out.print("Введите величину (дробное число): ");
				double magnitude = Double.parseDouble(sc.nextLine().trim());

				System.out.print("Введите расстояние (целое число): ");
				long distance = Long.parseLong(sc.nextLine().trim());

				return new Star.Builder().name(name).apparentMagnitude(magnitude).distanceLightYears(distance).build();

			} catch (NumberFormatException e) {
				System.err.println("Ошибка: нужно ввести число! Попробуйте снова.");
			} catch (IllegalArgumentException e) {
				System.err.println("Ошибка валидации: " + e.getMessage() + ". Попробуйте снова.");
			}
		}
	}
}