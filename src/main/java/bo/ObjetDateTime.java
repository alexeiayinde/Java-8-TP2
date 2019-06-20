package bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ObjetDateTime {

	private ArrayList<LocalDateTime> dateTimes;

	public ObjetDateTime() {
		dateTimes = new ArrayList<LocalDateTime>();
	}

	public ArrayList<LocalDateTime> getDatetime() {
		return dateTimes;
	}

	public void setDatetime(ArrayList<LocalDateTime> datetime) {
		this.dateTimes = datetime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Liste des DateTime : \n");
		for (LocalDateTime dateTime : dateTimes) {
			builder.append("- " + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
		}
		return builder.toString();
	}

	public void addDateTime(String str) {
		this.dateTimes.add(LocalDateTime.parse(str, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
	}

}
