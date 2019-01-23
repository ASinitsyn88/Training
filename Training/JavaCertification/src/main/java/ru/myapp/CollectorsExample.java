package ru.myapp;

import ru.myapp.entity.Track;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsExample {

	public static void main(String[] args) {
		List<Track> trackList = Arrays.asList(new Track("Track1", 5), new Track("Track2", 5), new Track("Track3", 10));
		// Группирует треки по продолжительности и возвращает продолжительность и список соответствующих ей треков
		Map<Integer, List<Track>> grouping = trackList.stream().collect(Collectors.groupingBy(Track::getLength));
		System.out.println(grouping);
	}
}
