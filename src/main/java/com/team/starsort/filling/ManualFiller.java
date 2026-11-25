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

		String name;
		while (true) {
			System.out.print("Введите имя: ");
			name = sc.nextLine().trim();
			if (!Star.isValidName(name)) {
				System.out.println(
						"Ошибка: имя — буквы/цифры, разделённые одиночным дефисом, до 50 символов. Попробуйте снова.");
				continue;
			}
			break;
		}

		double magnitude;
		while (true) {
			System.out.print("Введите величину (дробное число): ");
			try {
				magnitude = Double.parseDouble(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: нужно ввести число!");
				continue;
			}
			if (!Star.isValidMagnitude(magnitude)) {
				System.out.println("Ошибка: величина должна быть в диапазоне [-26.74, 15.0]. Попробуйте снова.");
				continue;
			}
			break;
		}

		long distance;
		while (true) {
			System.out.print("Введите расстояние (целое число): ");
			try {
				distance = Long.parseLong(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: нужно ввести целое число!");
				continue;
			}
			if (!Star.isValidDistance(distance)) {
				System.out.println("Ошибка: расстояние должно быть положительным (> 0). Попробуйте снова.");
				continue;
			}
			break;
		}

		return new Star.Builder().name(name).apparentMagnitude(magnitude).distanceLightYears(distance).build();
	}
}