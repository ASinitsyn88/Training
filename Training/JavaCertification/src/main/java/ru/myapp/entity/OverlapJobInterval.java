package ru.myapp.entity;

import java.util.List;

/**
 * Класс содержит список пересекающихся между собой временных интервалов
 */
public class OverlapJobInterval {

	private List<JobInterval> jobIntervalList;
	private int overloadPercent = 0;

	public OverlapJobInterval(List<JobInterval> jobIntervalList) {
		this.jobIntervalList = jobIntervalList;
		for (JobInterval jobInterval : jobIntervalList) {
			overloadPercent += jobInterval.getLoadPercent();
		}
	}

	public List<JobInterval> getJobIntervalList() {
		return jobIntervalList;
	}

	public void setJobIntervalList(List<JobInterval> jobIntervalList) {
		this.jobIntervalList = jobIntervalList;
	}

	public int getOverloadPercent() {
		return overloadPercent;
	}

	public void setOverloadPercent(int overloadPercent) {
		this.overloadPercent = overloadPercent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OverlapJobInterval)) return false;

		OverlapJobInterval that = (OverlapJobInterval) o;

		if (getOverloadPercent() != that.getOverloadPercent()) return false;
		return getJobIntervalList().equals(that.getJobIntervalList());
	}

	@Override
	public int hashCode() {
		int result = getJobIntervalList().hashCode();
		result = 31 * result + getOverloadPercent();
		return result;
	}

	@Override
	public String toString() {
		return "OverlapJobInterval{" +
				"jobIntervalList=" + jobIntervalList +
				", overloadPercent=" + overloadPercent +
				'}';
	}
}
