package com.team.starsort.filling;

import java.util.Scanner;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class ManualFiller implements DataFiller<Star> {

	private final Scanner sc = new Scanner(System.in);

	@Override
	public CustomList<Star> fill(int size) {
		CustomList<Star> list = new CustomArrayList<>();

		for (int i = 0; i < size; i++) {
			System.out.println("\n--- Ввод звезды " + (i + 1) + " из " + size + " ---");
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

					Star star = new Star.Builder().name(name).apparentMagnitude(magnitude).distanceLightYears(distance)
							.build();

					list.add(star);
					break;

				} catch (NumberFormatException e) {
					System.err.println("Ошибка: нужно ввести число! Попробуйте снова.");
				} catch (IllegalArgumentException e) {
					System.err.println("Ошибка валидации: " + e.getMessage() + ". Попробуйте снова.");

				}

			}

		}
		return list;
	}
}
