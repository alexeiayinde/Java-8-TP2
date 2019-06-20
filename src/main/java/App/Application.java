package App;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import bo.ObjetDate;
import bo.ObjetDateTime;
import bo.ObjetTime;

public class Application {

	private static int choix;
	private static int choix2;
	private static Scanner sc;
	private static ObjetDate date;
	private static ObjetTime time;
	private static ObjetDateTime dateTime;
	private static String saisie;
	private static int filtre;
	private static String startGame;

	public static void main(String[] args) {
		date = new ObjetDate();
		time = new ObjetTime();
		dateTime = new ObjetDateTime();
		sc = new Scanner(System.in);
		initMenu();
	}

	public static void initMenu() {
		while (choix != 9) {
			System.out.println("\nMENU DATE-TIME" + "\n-----------\n");
			System.out.println("1) Saisir une date " + "\n2) Saisir un temps " + "\n3) Saisir un DateTime "
					+ "\n4) Afficher un des éléments" + "\n5) Afficher la différence entre deux Date/Time/DateTime"
					+ "\n6) Afficher la DateTime actuelle dans une autre zone géographique"
					+ "\n7) Afficher la DateTime actuelle modifiée" + "\n8) Minijeu : deviner le temps écoulé !"
					+ "\n9) Quitter");
			choix = sc.nextInt();

			switch (choix) {
			case 1:
				sc.nextLine();
				System.out.println("Veuillez saisir une date (dd/MM/YYYY) : ");
				saisie = sc.nextLine();
				date.addDate(saisie);
				break;

			case 2:
				sc.nextLine();
				System.out.println("Veuillez saisir un temp (HH:mm:ss) : ");
				saisie = sc.nextLine();
				time.addTime(saisie);
				break;

			case 3:
				sc.nextLine();
				System.out.println("Veuillez saisir un DateTime (dd/MM/YYYY HH:mm:ss) : ");
				saisie = sc.nextLine();
				dateTime.addDateTime(saisie);
				break;

			case 4:
				initSousMenuAffichage();
				break;

			case 5:
				System.out.println("\nQuelle différence souhaitez-vous calculer et afficher?");
				System.out.println("1) Deux Date" + "\n2) Deux Time" + "\n3) Deux DateTime");
				choix = sc.nextInt();

				switch (choix) {
				case 1:
					if (date.getDates().isEmpty()) {
						System.out.println("Il n'y a aucune date d'enregistrée");
						break;
					}
					date.getDates().stream().forEach(date -> System.out.println(date));
					System.out.println("\nChoisissez deux dates pour la calcul : ");
					System.out.println("Choix 1 (dd/MM/yyyy) : ");
					sc.nextLine();
					saisie = sc.nextLine();
					LocalDate date1 = LocalDate.parse(saisie, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					System.out.println("Choix 2 (dd/MM/yyyy) : ");
					saisie = sc.nextLine();
					LocalDate date2 = LocalDate.parse(saisie, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					System.out.println("La différence entre les deux dates est de : " + Period.between(date1, date2));
					break;
				case 2:
					if (time.getTimes().isEmpty()) {
						System.out.println("Il n'y a aucun temps d'enregistré");
						break;
					}
					time.getTimes().stream().forEach(time -> System.out.println(time));
					System.out.println("\nChoisissez deux times pour le calcul :");
					System.out.println("Choix 1 (HH:mm:ss) : ");
					sc.nextLine();
					saisie = sc.nextLine();
					LocalTime time1 = LocalTime.parse(saisie, DateTimeFormatter.ofPattern("HH:mm:ss"));
					System.out.println("Choix 2 (HH:mm:ss) : ");
					saisie = sc.nextLine();
					LocalTime time2 = LocalTime.parse(saisie, DateTimeFormatter.ofPattern("HH:mm:ss"));
					System.out.println("La différence entre les deux temps est de : " + Duration.between(time1, time2));
					break;
				case 3:
					if (dateTime.getDatetime().isEmpty()) {
						System.out.println("Il n'y a aucun DateTime d'enregistré");
						break;
					}
					dateTime.getDatetime().stream().forEach(dateTime -> System.out.println(dateTime));
					System.out.println("\nChoisissez deux dateTimes pour le calcul : ");
					System.out.println("Choix 2 (dd/MM/yyyy HH:mm:ss) : ");
					sc.nextLine();
					saisie = sc.nextLine();
					LocalDateTime ldt1 = LocalDateTime.parse(saisie,
							DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
					System.out.println("Choix 2 (dd/MM/yyyy HH:mm:ss) : ");
					saisie = sc.nextLine();
					LocalDateTime ldt2 = LocalDateTime.parse(saisie,
							DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
					System.out.println("La différence entre les deux temps est de : " + Duration.between(ldt1, ldt2));
				}
				break;

			case 6:
				System.out.println("Veuillez saisir l'identifiant de la zone dont vous souhaitez obtenir l'heure : ");
				sc.nextLine();
				String zoneId = sc.nextLine();
				ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(zoneId));
				System.out.println(zdt);
				break;

			case 7:
				initSousMenuModification();
				break;

			case 8:
				initJeu();
				break;

			case 9:
				System.out.println("\nMerci et à bientôt !");
				break;

			default:
				System.out.println("\nChoix non reconnu !");
			}
		}
	}

	public static void initSousMenuAffichage() {
		while (choix != 0) {
			System.out.println("\nQuels éléments souhaitez-vous afficher ?\n" + "1) Les dates saisies"
					+ "\n2) Les temps saisis" + "\n3) Les DateTime saisis" + "\n0) Revenir au menu principal");
			choix = sc.nextInt();

			if (choix == 0)
				break;

			switch (choix) {
			case 1:
				System.out.println("\nSouhaitez-vous effectuer un tri/filtre avant affichage des éléments ?"
						+ "\n1) Aucun tri/filtre" + "\n2) Tri sur la date" + "\n3) Filtre sur l'année"
						+ "\n4) Filtre sur le mois" + "\n5) Filtre sur le jour");
				choix2 = sc.nextInt();
				trierDate(date.getDates());
				break;
			case 2:
				System.out.println("\nSouhaitez-vous effectuer un tri/filtre avant affichage des éléments ?"
						+ "\n1) Aucun tri/filtre" + "\n2) Tri sur le temps" + "\n3) Filtre sur l'heure"
						+ "\n4) Filtre sur les minutes" + "\n5) Filtre sur les secondes");
				choix2 = sc.nextInt();
				trierTime(time.getTimes());
				break;
			case 3:
				System.out.println("\nSouhaitez-vous effectuer un tri/filtre avant affichage des éléments ?"
						+ "\n1) Aucun tri/filtre" + "\n2) Tri sur la date" + "\n3) Filtre sur l'année"
						+ "\n4) Filtre sur le mois" + "\n5) Filtre sur le jour" + "\n6) Filtre sur l'heure"
						+ "\n7) Filtre sur les minutes" + "\n8) Filtre sur les secondes");
				choix2 = sc.nextInt();
				trierDateTime(dateTime.getDatetime());
				break;
			default:
				System.out.println("\nChoix non reconnu !");
			}
		}

	}

	public static void trierDate(ArrayList<LocalDate> liste) {
		if (choix2 != 1 && choix2 != 2) {
			System.out.println("Veuillez saisir la valeur du filtre à appliquer : ");
			filtre = sc.nextInt();
		}

		switch (choix2) {
		case 1:
			liste.stream().forEach(date -> System.out.println(date));
			break;
		case 2:
			liste.stream().sorted((date1, date2) -> date1.compareTo(date2)).forEach(date -> System.out.println(date));
			;
			break;
		case 3:
			liste.stream().filter(date -> (filtre == date.getYear())).collect(Collectors.toList())
					.forEach(date -> System.out.println(date));
			break;
		case 4:
			liste.stream().filter(date -> (filtre == date.getMonthValue())).collect(Collectors.toList())
					.forEach(date -> System.out.println(date));
			break;
		case 5:
			liste.stream().filter(date -> (filtre == date.getDayOfMonth())).collect(Collectors.toList())
					.forEach(date -> System.out.println(date));
			break;
		default:
			System.out.println("\nChoix non reconnu!");
		}
	}

	public static void trierTime(ArrayList<LocalTime> liste) {
		if (choix2 != 1 && choix2 != 2) {
			System.out.println("Veuillez saisir la valeur du filtre à appliquer : ");
			filtre = sc.nextInt();
		}

		switch (choix2) {
		case 1:
			liste.stream().forEach(time -> System.out.println(time));
			break;
		case 2:
			liste.stream().sorted((time1, time2) -> time1.compareTo(time2)).forEach(time -> System.out.println(time));
			break;
		case 3:
			liste.stream().filter(time -> (filtre == time.getHour())).collect(Collectors.toList())
					.forEach(time -> System.out.println(time));
			break;
		case 4:
			liste.stream().filter(date -> (filtre == date.getMinute())).collect(Collectors.toList())
					.forEach(time -> System.out.println(time));
			break;
		case 5:
			liste.stream().filter(date -> (filtre == date.getSecond())).collect(Collectors.toList())
					.forEach(time -> System.out.println(time));
			break;
		default:
			System.out.println("\nChoix non reconnu!");
		}
	}

	public static void trierDateTime(ArrayList<LocalDateTime> liste) {
		if (choix2 != 1 && choix2 != 2) {
			System.out.println("Veuillez saisir la valeur du filtre à appliquer : ");
			filtre = sc.nextInt();
		}

		switch (choix2) {
		case 1:
			liste.stream().forEach(dateTime -> System.out.println(dateTime));
			break;
		case 2:
			liste.stream().sorted((dateTime1, dateTime2) -> dateTime1.compareTo(dateTime2))
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 3:
			liste.stream().filter(dateTime -> (filtre == dateTime.getYear())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 4:
			liste.stream().filter(dateTime -> (filtre == dateTime.getMonthValue())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 5:
			liste.stream().filter(dateTime -> (filtre == dateTime.getDayOfMonth())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 6:
			liste.stream().filter(dateTime -> (filtre == dateTime.getHour())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 7:
			liste.stream().filter(dateTime -> (filtre == dateTime.getMinute())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		case 8:
			liste.stream().filter(dateTime -> (filtre == dateTime.getSecond())).collect(Collectors.toList())
					.forEach(dateTime -> System.out.println(dateTime));
			break;
		default:
			System.out.println("\nChoix non reconnu!");
		}
	}

	public static void initJeu() {
		System.out.println(
				"\nLe programme va lancer un chronomètre pendant un temps aléatoire. A la fin de ce temps, ce sera à vous de deviner combien de temps s'est écoulé !");
		System.out.println("\nSi vous êtes prêt à démarrer, saisir 'OK'.");
		sc.nextLine();
		startGame = sc.nextLine();

		if (startGame.equals("OK")) {
			System.out.println("Déclenchement du chrono...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("GO!");
			int random = (int) (Math.random() * 10 + 1);
			try {
				Thread.sleep(random * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\nFINI!" + "\nCombien de secondes pensez-vous s'est écoulé : ");
			choix = sc.nextInt();

			if (choix == random) {
				System.out
						.println("Vous avez trouvé la bonne réponse! Il s'est bien écoulé : " + random + " secondes !");
			} else {
				System.out.println("Mauvaise réponse ! Il s'est écoulé : " + random + " secondes ! ");
			}
		}
	}

	public static void initSousMenuModification() {
		System.out.println("\nQuel paramètre souhaitez-vous modifier ?");
		System.out.println("1) L'année" + "\n2) Le mois" + "\n3) Le jour" + "\n4) L'heure" + "\n5) Les minutes"
				+ "\n6) Les secondes");
		choix = sc.nextInt();
		ChronoUnit cu = null;
		switch (choix) {
		case 1:
			cu = ChronoUnit.YEARS;
			break;
		case 2:
			cu = ChronoUnit.MONTHS;
			break;
		case 3:
			cu = ChronoUnit.DAYS;
			break;
		case 4:
			cu = ChronoUnit.HOURS;
			break;
		case 5:
			cu = ChronoUnit.MINUTES;
			break;
		case 6:
			cu = ChronoUnit.SECONDS;
			break;
		}
		System.out.println("\nCombien souhaitez-vous ajouter/retirer ?");
		choix = sc.nextInt();
		System.out.println("Voici la date actuelle modifiée : " + LocalDateTime.now().plus(choix, cu));
	}
}
