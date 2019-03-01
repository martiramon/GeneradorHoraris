package utils;

public class Pair<T> {

	private T first;
	private T second;
	
	public Pair() {
	}
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T first() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T second() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
	
	
}
