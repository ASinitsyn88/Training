package ru.myapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Пример иллюстрирует, что без переопределения метода hashCode()
 * для двух одинаковых объектов будет вычисляться разный код
 * и объекты, не смотря на свою одинаковость, будут считаться разными.
 * В данном примере у одинаковых объектов hashCode будет равен 100.
 */
public class HashCodeExample {

	static class Book {

		private String isbn;

		public Book(String isbn) {
			this.isbn = isbn;
		}

		@Override
		public boolean equals(Object o) {
			return (o instanceof Book && ((Book)o).isbn.equals(this.isbn));
		}

		@Override
		public int hashCode() {
			return 100;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
	}

	static class BookStore {

		private Map<Book, Integer> map = new HashMap<>();

		public BookStore() {
			map.put(new Book("A111"), 10);
			map.put(new Book("B222"), 5);
		}

		public Integer getNumberOfCopies(Book b){
			return map.get(b);
		}
	}

	public static void main(String[] args){
		BookStore bs = new BookStore();
		System.out.println(bs.getNumberOfCopies(new Book("A111")));
		System.out.println("There are the same books. Book1 hashCode=" + new Book("1111").hashCode() + " and Book2 hashCode=" + new Book("1111").hashCode());
	}
}
