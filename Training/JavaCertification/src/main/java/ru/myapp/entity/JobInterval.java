package ru.myapp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Временной интервал
 */
public class JobInterval {

	// dd.MM.yyyy
	private Date startInterval;
	// dd.MM.yyyy
	private Date endInterval;
	// загрузка в %
	private int loadPercent;

	public JobInterval(String startInterval, String endInterval, int loadPercent) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.startInterval = sdf.parse(startInterval);
		this.endInterval = sdf.parse(endInterval);
		this.loadPercent = loadPercent;
	}

	public Date getStartInterval() {
		return startInterval;
	}

	public void setStartInterval(Date startInterval) {
		this.startInterval = startInterval;
	}

	public Date getEndInterval() {
		return endInterval;
	}

	public void setEndInterval(Date endInterval) {
		this.endInterval = endInterval;
	}

	public int getLoadPercent() {
		return loadPercent;
	}

	public void setLoadPercent(int loadPercent) {
		this.loadPercent = loadPercent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JobInterval)) return false;

		JobInterval that = (JobInterval) o;

		if (getLoadPercent() != that.getLoadPercent()) return false;
		if (!getStartInterval().equals(that.getStartInterval())) return false;
		return getEndInterval().equals(that.getEndInterval());
	}

	@Override
	public int hashCode() {
		int result = getStartInterval().hashCode();
		result = 31 * result + getEndInterval().hashCode();
		result = 31 * result + getLoadPercent();
		return result;
	}

	@Override
	public String toString() {
		return "JobInterval{" +
				"startInterval=" + startInterval +
				", endInterval=" + endInterval +
				", loadPercent=" + loadPercent +
				'}';
	}
}
