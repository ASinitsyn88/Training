package ru.myapp.exercise;

import ru.myapp.entity.Album;
import ru.myapp.entity.Artist;
import ru.myapp.entity.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Exercise1 {

	public static void main(String[] args) {
		System.out.println(addUp(Stream.of(1, 2, 3)));

		Artist artist1 = new Artist("Corey Taylor", "USA");
		Artist artist2 = new Artist("Joey Jordison", "England");
		List<Artist> artistList = new ArrayList<>();
		artistList.add(artist1);
		artistList.add(artist2);
		System.out.println(getArtistInfo(new Artist("Slipknot", artistList, "USA")));

		Track track1 = new Track("Track1", 300);
		Track track2 = new Track("Track2", 400);
		Track track3 = new Track("Track3", 120);
		Track track4 = new Track("Track4", 360);
		List<Track> trackList1 = new ArrayList<>();
		trackList1.add(track1);
		trackList1.add(track2);
		trackList1.add(track3);
		trackList1.add(track4);
		List<Track> trackList2 = new ArrayList<>();
		trackList2.add(track1);
		trackList2.add(track2);
		trackList2.add(track3);
		trackList2.add(track4);
		List<Track> trackList3 = new ArrayList<>();
		trackList3.add(track1);
		trackList3.add(track2);
		trackList3.add(track3);
		Album album1 = new Album("Album1", trackList1, artistList);
		Album album2 = new Album("Album2", trackList2, artistList);
		Album album3 = new Album("Album3", trackList3, artistList);
		List<Album> albumList = new ArrayList<>();
		albumList.add(album1);
		albumList.add(album2);
		albumList.add(album3);
		List<Album> resultAlbumList = getAlbumsContainsNotMoreThreeTrack(albumList);
		System.out.println(resultAlbumList.size() + " " + resultAlbumList.get(0).getName());

		System.out.println("Tracks count v1: " + countTracksV1(albumList));
		System.out.println("Tracks count v2: " + countTracksV2(albumList));
		System.out.println("Tracks count v3: " + countTracksV3(albumList));
		System.out.println("Tracks count v4: " + countTracksV4(albumList));

		System.out.println("Tracks length v1: " + countRunningTimeV1(albumList)); // 3180
		System.out.println("Tracks length v2: " + countRunningTimeV2(albumList));
		System.out.println("Tracks length v3: " + countRunningTimeV3(albumList));
		System.out.println("Tracks length v4: " + countRunningTimeV4(albumList));
	}

	/**
	 * Функция сложения чисел
	 */
	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (acc, element) -> acc + element);
	}

	/**
	 * Функция возвращает список строк: Имя + Место происхождения участника группы
	 */
	public static List<String> getArtistInfo(Artist musicalGroup) {
		return musicalGroup.getMembers()
				.map(artist -> artist.getName() + " " + artist.getNationality())
				.collect(Collectors.toList());
	}

	/**
	 * Функция возвращает список альбомов, содержащих не более 3 произведений
	 */
	public static List<Album> getAlbumsContainsNotMoreThreeTrack(List<Album> albumList) {
		return albumList.stream()
				.filter(album -> album.getTracks().count() <= 3)
				.collect(Collectors.toList());
	}

	/**
	 * Функция подсчитывает общее кол-во треков в списке альбомов
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countTracksV1(List<Album> albumList) {
		long count = 0;
		for (Album album : albumList) {
			count += album.getTracks().count();
		}
		return count;
	}

	/**
	 * Функция подсчитывает общее кол-во треков в списке альбомов
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countTracksV2(List<Album> albumList) {
		return albumList.stream()
				// Преобразовываем каждый альбом в кол-во треков
				.mapToLong(album -> album.getTracks().count())
				// Присвоить аккумулятору начальное значение 0. Затем сложить аккумулятор с каждым элементом
				.reduce(0, (acc, element) -> acc + element);
	}

	/**
	 * Функция подсчитывает общее кол-во треков в списке альбомов
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countTracksV3(List<Album> albumList) {
		return albumList.stream()
				// Преобразовываем каждый альбом в кол-во треков
				.mapToLong(album -> album.getTracks().count())
				// Суммируем кол-во треков по каждому альбому
				.sum();
	}

	/**
	 * Функция подсчитывает общее кол-во треков в списке альбомов
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countTracksV4(List<Album> albumList) {
		return countFeatures(albumList, album -> album.getTracks().count());
	}

	/**
	 * Функция подсчитывает общую продолжительность времени всех треков во всех альбомах
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countRunningTimeV1(List<Album> albumList) {
		long count = 0;
		for (Album album : albumList) {
			for (Track track : album.getTrackList()) {
				count += track.getLength();
			}
		}
		return count;
	}

	/**
	 * Функция подсчитывает общую продолжительность времени всех треков во всех альбомах
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countRunningTimeV2(List<Album> albumList) {
		return albumList.stream()
				// Преобразовываем список альбомов в список треков
				.map(Album::getTrackList)
				// Преобразовываем каждый трек в длину
				.map(tracks -> tracks.stream().mapToLong(Track::getLength))
				// Суммируем длину в каждом треке
				.mapToLong(LongStream::sum)
				// Суммируем длину всех треков
				.sum();
	}

	/**
	 * Функция подсчитывает общую продолжительность времени всех треков во всех альбомах
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countRunningTimeV3(List<Album> albumList) {
		return albumList.stream()
				// Преобразовываем каждый альбом в сумму продолжительности его треков
				.mapToLong(album -> album.getTracks().mapToLong(Track::getLength).sum())
				// Суммируем длину треков по каждому альбому
				.sum();
	}

	/**
	 * Функция подсчитывает общую продолжительность времени всех треков во всех альбомах
	 * @param albumList - список альбомов
	 * @return long
	 */
	public static long countRunningTimeV4(List<Album> albumList) {
		return countFeatures(albumList, album -> album.getTracks().mapToLong(Track::getLength).sum());
	}

	/**
	 * Метод подсчитывает сумму чего-либо из списка альбомов по переданной функции
	 * @param albumList - список альбомов
	 * @param function - функция, которая извлекает необходимую для подсчёта сущность
	 * @return long
	 */
	private static long countFeatures(List<Album> albumList, ToLongFunction<Album> function) {
		return albumList.stream().mapToLong(function).sum();
	}
}
