package model;

public class Impiegato {
	
	private int id;
	private String firstName;
	private String lastName;
	private String fiscalCode;
	private String email;
	
	
	public Impiegato(String firstName, String lastName, String fiscalCode, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.email = email;
	}
	
	
	public Impiegato(int id, String firstName, String lastName, String fiscalCode, String email) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.email = email;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fiscalCode="
				+ fiscalCode + ", email=" + email + "]";
	}
	
	
}
