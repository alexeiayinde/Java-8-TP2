package bo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ObjetTime {

	private ArrayList<LocalTime> times;

	public ObjetTime() {
		times = new ArrayList<LocalTime>();
	}

	public ArrayList<LocalTime> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<LocalTime> times) {
		this.times = times;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Liste des temps : \n");
		for (LocalTime time : times) {
			builder.append("- " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		}
		return builder.toString();
	}

	public void addTime(String str) {
		times.add(LocalTime.parse(str, DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

}
