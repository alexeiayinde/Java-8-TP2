package bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ObjetDate {

	private ArrayList<LocalDate> dates;

	public ObjetDate() {
		dates = new ArrayList<LocalDate>();
	}

	public ArrayList<LocalDate> getDates() {
		return dates;
	}

	public void setDates(ArrayList<LocalDate> date) {
		this.dates = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Liste des dates : \n");
		for (LocalDate date : dates) {
			builder.append("- " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
		}
		return builder.toString();
	}

	public void addDate(String str) {
		dates.add(LocalDate.parse(str, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

}
