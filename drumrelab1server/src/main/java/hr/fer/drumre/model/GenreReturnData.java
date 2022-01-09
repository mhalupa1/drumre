package hr.fer.drumre.model;

public class GenreReturnData {

	private double number;
	private String name;

	public GenreReturnData() {
		super();
	}

	public GenreReturnData(double number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GenreReturnData [number=" + number + ", name=" + name + "]";
	}
}
