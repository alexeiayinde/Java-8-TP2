package App;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

	public static void main(String[] args) {
		date = new ObjetDate();
		time = new ObjetTime();
		dateTime = new ObjetDateTime();
		sc = new Scanner(System.in);
		initMenu();
	}

	public static void initMenu() {
		while (choix != 5) {
			System.out.println("\nMENU DATE-TIME" + "\n-----------\n");
			System.out.println("1) Saisir une date " + "\n2) Saisir un temps " + "\n3) Saisir un DateTime "
					+ "\n4) Afficher un des éléments" + "\n5) Quitter");
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
				break;

			case 5:
				System.out.println("\nMerci et à bientôt !");
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

}
