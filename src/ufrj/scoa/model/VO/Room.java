package ufrj.scoa.model.VO;

public class Room {
	
	private int id;
	private int number;
	private int floor;
	private String building;
	
	public Room(int id,String building, int number, int floor) {
		super();
		this.id = id;
		this.building = building;
		this.number = number;
		this.floor = floor;
	}
	
	public Room(String building, int number, int floor) {
		super();
		this.building = building;
		this.number = number;
		this.floor = floor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String toString() {
		
		String retorno = this.building;
		
		if(this.floor > 0) {
			retorno += " " + this.floor + "º andar";
		}
		
		if(this.number > 0) {
			retorno += " " + this.number;
		}
		
		return retorno;
	}
	
}
