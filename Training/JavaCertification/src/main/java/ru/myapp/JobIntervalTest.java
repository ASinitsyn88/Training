package ru.myapp;

import ru.myapp.entity.JobInterval;
import ru.myapp.entity.OverlapJobInterval;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JobIntervalTest {

	public static void main(String[] args) throws ParseException {
		// Интервалы пересекаются и загрузка превышает 100% (итого 130%)
		JobInterval interval1 = new JobInterval("06.01.2018", "07.01.2018", 50);
		JobInterval interval2 = new JobInterval("01.01.2018", "09.01.2018", 80);

		// Интервалы пересекаются и загрузка превышает 100% (итого 170%)
		JobInterval interval3 = new JobInterval("10.01.2018", "15.01.2018", 90);
		JobInterval interval4 = new JobInterval("11.01.2018", "13.01.2018", 80);

		// Интервалы пересекаются, но загрузка не превышает 100% (итого 90%)
		JobInterval interval5 = new JobInterval("26.01.2018", "28.01.2018", 40);
		JobInterval interval6 = new JobInterval("27.01.2018", "28.01.2018", 50);

		// Интервал ни с кем не пересекаются, загрузка 100%
		JobInterval interval7 = new JobInterval("29.01.2018", "30.01.2018", 25);

		List<JobInterval> intervalList = new ArrayList<>();
		intervalList.add(interval1);
		intervalList.add(interval2);
		intervalList.add(interval3);
		intervalList.add(interval4);
		intervalList.add(interval5);
		intervalList.add(interval6);
		intervalList.add(interval7);

		System.out.println(getOverlapJobIntervals(intervalList));
	}

	private static List<OverlapJobInterval> getOverlapJobIntervals(List<JobInterval> intervalList) {
		// Заполнить Map, которая будет содержать интервал и список всех интервалов с которыми данный интервал пересекается, включая его самого
		Map<JobInterval, List<JobInterval>> intervalToOverlapIntervals = new HashMap<>();
		for (JobInterval sourceJobInterval : intervalList) {
			List<JobInterval> overlapIntervalList = new ArrayList<>();
			for (JobInterval comparedJobInterval : intervalList) {
				if (hasOverlap(sourceJobInterval, comparedJobInterval)) {
					overlapIntervalList.add(comparedJobInterval);
				}
			}
			intervalToOverlapIntervals.put(sourceJobInterval, overlapIntervalList);
		}

		// Заполнить набор списков уникальных интервалов
		Set<List<JobInterval>> setOfOverlapIntervals = new HashSet<>();
		for (Map.Entry<JobInterval, List<JobInterval>> entry : intervalToOverlapIntervals.entrySet()) {
			setOfOverlapIntervals.add(entry.getValue());
		}

		// Вернуть список пересекающихся интервалов
		List<OverlapJobInterval> overlapJobIntervalList = new ArrayList<>();
		for (List<JobInterval> overlaps : setOfOverlapIntervals) {
			overlapJobIntervalList.add(new OverlapJobInterval(overlaps));
		}
		return overlapJobIntervalList;
	}

	/**
	 * Метод проверяет пересечение между 2 временными интервалами
	 * @param interval1 - первый временной интервал
	 * @param interval2 - второй временной интервал
	 * @return пересекаются - true; не пересекаются - false
	 */
	private static boolean hasOverlap(JobInterval interval1, JobInterval interval2) {
		Date start1 = interval1.getStartInterval();
		Date end1 = interval1.getEndInterval();

		Date start2 = interval2.getStartInterval();
		Date end2 = interval2.getEndInterval();

		return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
	}
}
