package lviv.lgs.ua;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class Cinema implements Serializable {

	private static final long serialVersionUID = -2991843421434092287L;
	private String name;
	private TimeTable cinemaTimeTable;
	private TreeSet<Hall> cinemaHalls;

	public Cinema(String name) {
		this.name = name;
		this.cinemaTimeTable = new TimeTable();
		this.cinemaHalls = new TreeSet<Hall>();
	}

	public String getName() {
		return name;
	}

	public static Cinema inputCinema() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print(" Enter the name of the cinema: ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.err.println("The name of the cinema was entered incorrectly!");
			name = " Some nameless cinema ";
		}
		System.out.println("Cinema \"" + name.toString() + "\" successfully created!\n");
		return new Cinema(name);
	}

	public void addHallToCinema() {
		Hall cinemaHall = Hall.inputHall();
		cinemaHalls.add(cinemaHall);
		System.out.println("Cinema \"" + cinemaHall.getName() + "\" successfully added to the cinema \"" + name + "\"!\n");
	}

	public Optional<Hall> getHallFromSet(Hall cinemaHall) {
		Optional<Hall> hallFound = cinemaHalls.stream().filter(entry -> entry.getName().equals(cinemaHall.getName()))
				.findFirst();

		return hallFound;
	}

	public boolean removeHallFromCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			cinemaHalls.remove(hallFound.get());
			System.out.println(
					"Cinema \"" + hallFound.get().getName() + "\"successfully deleted from the cinema  \"" + name + "\"!\n");
			return true;
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no  cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addTimeTableToCinema() throws IllegalTimeFormatException {
		boolean isDone = cinemaTimeTable.addTimeTableEntry();
		if (isDone) {
			System.out.println(" Cinema opening hours \"" + name + "\" successfully changed!\n");
			return true;
		} else {
			System.err.println(" Make changes to the opening hours of the cinema \"" + name + "\" failed!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromCinema() {
		boolean isDone = cinemaTimeTable.removeTimeTableEntry();
		if (isDone) {
			System.out.println(" Cinema opening hours \"" + name + "\" successfully changed!\n");
			return true;
		} else {
			System.err.println(" Make changes to the opening hours of the cinema \"" + name + "\" failed!\n");
			return false;
		}
	}

	public boolean addTimeTableToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addTimeTableToHall();

			if (isDone) {
				System.out.println(" Work schedule for \"" + hallFound.get() + "\" successfully added to the cinema \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Make changes to the opening hours of the cinema \"" + name + "\" failed!\n");
				return false;
			}
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no in cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeTimeTableFromHall();

			if (isDone) {
				System.out.println(" Work schedule for\"" + hallFound.get() + "\" successfully deleted from the cinema \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println(" Make changes to the opening hours of the cinema \"" + name + "failed!\" \n");
				return false;
			}
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no in cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addScheduleToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addScheduleToHall();

			if (isDone) {
				System.out.println("Seances schedule for \"" + hallFound.get() + "\"  successfully added to the cinema\""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Make changes to the seances schedule for \"" + hallFound.get() + "\" cinema \""
						+ name + "\" failed!\n");
				return false;
			}
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeScheduleFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeScheduleFromHall();

			if (isDone) {
				System.out.println("Seances schedule for \"" + hallFound.get() + "\" successfully deleted from the cinema \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println(" Make changes to the seances schedule for \"" + hallFound.get() + "\" cinema \""
						+ name + "failed\" !\n");
				return false;
			}
		} else {
			System.err.println("cinema \"" + cinemaHall.getName() + "\"  no cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addSeanceToScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				boolean isDone = hallScheduleEntryFound.get().getValue().addSeance(movie);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " missing in the session schedule for \"" + hallFound.get()
						+ "\" cinema \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no cinema  \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeSeanceFromScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				Seance removingSeance = Seance.inputSeance(movie);
				boolean isDone = hallScheduleEntryFound.get().getValue().removeSeance(removingSeance);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " missing in the session schedule for \"" + hallFound.get()
						+ "\" cinema \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no cinema \"" + name + "\"!\n");
			return false;
		}
	}

	public void addMovieToSeanceInScheduleInHallInCinema() throws IllegalTimeFormatException {
		Movie movie = Movie.inputMovie();

		boolean addOneMoreSeance;

		do {
			Hall cinemaHall = Hall.inputHall();

			Optional<Hall> hallFound = getHallFromSet(cinemaHall);

			if (hallFound.isPresent()) {
				Days day = Days.inputDay();
				if (day == null)
					return;

				Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
						.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

				if (hallScheduleEntryFound.isPresent()) {
					hallScheduleEntryFound.get().getValue().addSeance(movie);
				} else {
					System.err.println(day.toLiteral(true) + "  missing in the seances schedule for\""
							+ hallFound.get() + "\" cinema \"" + name + "\"!\n");
				}
			} else {
				System.err
						.println("Cinema \"" + cinemaHall.getName() + "\" no cinema  \"" + name + "\"!\n");
			}

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			System.out.print("Add another seance ? (true/false) ");
			addOneMoreSeance = scanner.nextBoolean();

			if (!addOneMoreSeance) {
				System.out.println("You refused from adding seances for this movie!\n");
			}

		} while (addOneMoreSeance);
	}

	public void removeMovieFromSeanceInScheduleInAllHallsInCinema() throws IllegalTimeFormatException {
		Movie movie = Movie.inputMovie();
		boolean isDone = false;

		for (Hall hall : cinemaHalls) {
			for (Days day : Days.values()) {
				Optional<Seance> seance = hall.getHallSchedule().entrySet().stream()
						.filter(entry -> entry.getKey().equals(day)).findFirst().get().getValue()
						.getMovieSeanceFromSet(movie);

				if (seance.isPresent()) {
					hall.getHallSchedule().entrySet().stream().filter(entry -> entry.getKey().equals(day)).findFirst()
							.get().getValue().removeSeance(seance.get());
					isDone = true;
				} else
					break;
			}
		}

		if (isDone) {
			System.out.println(movie.toString() + " successfully deleted from the schedule of all cinema halls!\n");
		} else {
			System.err.println("Delete " + movie.toString()
					+ "from the schedule of sessions of all halls of the cinema was not possible, because of there is no such movie in the cinema  schedule \n");
		}
	}

	public void viewCinemaTimeTable() {
		System.out.print("Cinema \"" + name + "\"\n");
		cinemaTimeTable.viewTimeTable();
		System.out.println();
	}

	public void viewCinemaHalls() {
		System.out.println(" List of cinema halls \"" + name + "\":");
		cinemaHalls.forEach(System.out::println);
		System.out.println();
	}

	public boolean viewHallTimeTableInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallTimeTable();
			return true;
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no cinema  \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean viewHallScheduleInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallSchedule();
			return true;
		} else {
			System.err.println("Cinema \"" + cinemaHall.getName() + "\" no cinema \"" + name + "\"!\n");
			return false;
		}
	}

	@Override
	public String toString() {
		if (name == "Some nameless cinema") {
			return (String) name;
		} else
			return "Cinema \"" + name + "\"";
	}
	
	
}
