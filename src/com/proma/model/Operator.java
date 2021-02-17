package model;

public class Operator {

	private String name;
	private String surname;
	private int rights;
	
	public Operator(String name, String surname, int rights) {
		super();
		this.name = name;
		this.surname = surname;
		this.rights = rights;
	}

	public static Operator create(String selectedControleur) {
		return new Operator(selectedControleur, "Surname", 0);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}

	// TODO this method must be adapted to read operator names and
	// TODO set the corresponding surname and rights to the selected one
	public void assignOperatorByName(String selectedControleur) {
		this.setName(selectedControleur);
		this.setSurname("modifiedSurName");
		this.setRights(1);
	}
	public String operatorToString(){
		return getName() + " " + getSurname() + " " + getRights();
	}
}
