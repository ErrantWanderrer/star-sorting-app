# Star Sorting App

Консольное Java-приложение для сортировки кастомных объектов класса **Star (Звезда)**.
Командный итоговый проект.

---

## О проекте

Программа работает в цикле: пользователь выбирает способ заполнения коллекции, длину, поле сортировки, алгоритм и дополнительные операции. Выход — только по явному выбору пользователя.

**Модель:** `Star` — иммутабельный класс с паттерном **Builder** и валидацией.
**Поля:** `name` (String), `apparentMagnitude` (double), `distanceLightYears` (long).

---

## Возможности

### Базовый функционал
-  Кастомный класс `Star` с ручным **Builder** и валидацией в `build()`
-  Класс **иммутабельный** (`final`, `private final` поля)
-  Заполнение коллекции: **из файла / рандом / вручную** + выбор длины
-  **Валидация** вводимых данных (включая файл) с ранней проверкой каждого поля
-  **Паттерн Стратегия** для сортировки (`SortStrategy` + `SortContext`)
-  Свои алгоритмы: **BubbleSort**, **QuickSort**, **MergeSort** (без `Collections.sort` / `Arrays.sort`)
-  Сортировка по **всем 3 полям** (отдельные компараторы)
-  Цикл + выход по выбору пользователя
-  Тесты (ручные, без JUnit)

### Дополнительные задания
-  **Доп.1** — `ParitySort`: чётные значения числового поля (distance) сортируются, нечётные остаются на местах
-  **Доп.2** — `FileAppenderService`: дозапись отсортированных коллекций и найденных значений в файл (`StandardOpenOption.APPEND`)
-  **Доп.3** — заполнение через **Stream API**: `RandomFiller` (`IntStream`), `FileFiller` (`Files.lines`), `ManualFiller` (`IntStream.range`)
-  **Доп.3\*** — кастомная коллекция `CustomArrayList<T> extends Iterable<T>` (`add`, `get`, `set`, `size`, `iterator`)
-  **Доп.4** — `CounterService`: многопоточный подсчёт вхождений (`ExecutorService` + `AtomicInteger`)

---

## Архитектура

**Пакеты и классы:**

- `com.team.starsort.Main` — точка входа
- `model/` — `Star` (модель + Builder + валидация), `StarComparators` (3 компаратора)
- `sorting/` — `SortStrategy` (интерфейс), `SortContext` (контекст Strategy), `BubbleSort`, `QuickSort`, `MergeSort`, `ParitySort` (доп.1)
- `collection/` — `CustomList` (интерфейс), `CustomArrayList` (доп.3*), `CounterService` (доп.4)
- `filling/` — `DataFiller` (интерфейс), `RandomFiller`, `ManualFiller`, `FileFiller`
- `io/` — `FileAppenderService` (доп.2)
- `ui/` — `ConsoleUI` (меню + интеграция)

**Применённые паттерны:**

| Паттерн | Где | Зачем |
|---|---|---|
| Builder | `Star.Builder` | Пошаговое создание иммутабельного объекта + валидация в `build()` |
| Strategy | `SortStrategy` / `SortContext` | Смена алгоритма сортировки на лету |
| Strategy | `DataFiller` | Смена способа заполнения коллекции |

> Готовые реализации паттернов и сортировок **не используются** — всё написано вручную.

---

##  Технологии

- **Java 17**
- **Maven** (сборка)
- Только стандартная библиотека Java — сторонних зависимостей нет
- Тесты — вручную в отдельных классах (JUnit не используется)

---

## Сборка и запуск

### Требования
- JDK 17+
- Maven (или встроенный в IntelliJ IDEA)

### Запуск

```bash
# клонировать
git clone https://github.com/ErrantWanderrer/star-sorting-app.git
cd star-sorting-app

# собрать
mvn clean package

# запустить
java -cp target/classes com.team.starsort.Main
Через IntelliJ IDEA: открыть `Main.java` → правый клик → **Run 'Main.main()'**.
```
---
## Формат входного файла

Для заполнения из файла используется формат:

- name;apparentMagnitude;distanceLightYears
- Сириус;-1.46;9
- Вега;0.03;25
- Полярная;1.98;433
- Star-42;2.5;100


Правила:

- Разделитель — `;`
- `name`: буквы/цифры, разделённые **одиночным** дефисом, до 50 символов
- `apparentMagnitude`: `[-26.74, 15.0]`, не NaN
- `distanceLightYears`: целое `> 0`

Невалидные строки пропускаются с сообщением в консоль.

---
## Тесты

Тесты реализованы **вручную** (без JUnit), в `src/test/java/com/team/starsort/`.

**Запуск в IntelliJ IDEA:**

- Правый клик по `StarTest` / `StarComparatorsTest` / `ConsoleUITest` → **Run**

**Что покрыто:**

| Класс | Что тестируется |
|---|---|
| `StarTest` | Builder + валидация (границы, ошибки), equals/hashCode, toString |
| `StarComparatorsTest` | 3 компаратора сортируют по своему полю, `.reversed()` |
| `ConsoleUITest` | Smoke (happy-path), неверный ввод в меню — не падает |
| `BubbleSortTest` / `QuickSortTest` / `MergeSortTest` | Алгоритмы на разных наборах |
| `ParitySortTest` | Доп.1: чётные сортируются, нечётные на местах |
| `CustomArrayListTest` | add/get/set/size/iterator/resize/границы |
| `CounterServiceTest` | Доп.4: подсчёт в многопоточке |
| `RandomFillerTest` / `FileFillerTest` / `FileAppenderServiceTest` | Заполнение + append |

---

## Команда

| Участник | Роль | Модуль |
|---|---|---|
| Кузьмин Кирилл (лид) | Тим-лид, контракты, модель, UI, координация | `model`, `ui` |
| Шатохин Александр | Разработчик | `sorting` (+ доп.1) |
| Макарова Анастасия | Разработчик | `collection` (+ доп.4) |
| Воробьев Никита | Разработчик | `filling`, `io` (+ доп.2, доп.3) |

## Git Workflow

Проект ведётся по **GitHub Flow**. Подробные правила — в [CONTRIBUTING.md](./CONTRIBUTING.md).

- **5 веток**: у каждого разработчика своя ветка, у тим-лида две (`feature/model`, `feature/sorting`, `feature/collection` `feature/filling`, `feature/ui`)
- Все ветки **смерджены в `main`** через Pull Request
- Прямой push в `main` запрещён
- Коммиты — Conventional Commits: `тип: что сделано`





