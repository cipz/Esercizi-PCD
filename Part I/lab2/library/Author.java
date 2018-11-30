package lab2.library;

/**
 * This class models an author in our library context.
 * 
 */
public class Author implements Cloneable {

	private String name;
	private String surname;
	private String SSN;
	
	/**
	 * Builds an author given its social security number
	 * 
	 * @param SSN: social security number 
	 */
	public Author(String SSN) {
		this("", "", SSN);
	}
	
	/**
	 * Builds an author given its name, surname and SSN
	 * 
	 * @param name: 	optional, the name of the author, might be empty
	 * @param surname:	optional, the surname of the author, might be empty
	 * @param SSN: 		mandatory, social security number, cannot be empty
	 * 
	 * @throws IllegalArgumentException in case parameters are not valid e.g., are null.
	 */
	public Author(String name, String surname, String SSN) {
		boolean validParameters = Util.parameterNotNull(name) &&
				Util.parameterNotNull(surname) &&
				Util.strinParameterNotEmpty(SSN);
		if(!validParameters) throw new IllegalArgumentException("Author parameters should not be empty or null");
		this.name = name;
		this.surname = surname;
		this.SSN = SSN;		
	}

	/**
	 * @return the authors name
	 * */
	public String getAuthorName() {
		return name;
	}
	
	/**
	 * @return the authors surname
	 * */
	public String getAuthorSurname() {
		return surname;
	}
	
	/**
	 * @return the authors SSN
	 * */
	public String getAuthorSSN() {
		return SSN;
	}
		
	@Override
	public String toString() {
		return "Author(" + 
							((name.isEmpty())? "N/A": name) + " " + 
							((surname.isEmpty())? "N/A": surname) + " " 
							+ SSN 
							+ ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Author)) 
			return false;

		if(this == obj) 
			return true;

		Author other = (Author)obj;
		return other.getAuthorName().equals(name) &&
			   other.getAuthorSurname().equals(surname) &&
			   other.getAuthorSSN().equals(SSN);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + surname.hashCode();
		result = 31 * result + SSN.hashCode();
		return result;
		//OSS.: refer also to utility class java.util.Objects
	}


	@Override
	public Author clone() {
		try {
			return (Author)super.clone();
		} catch (CloneNotSupportedException e) {}
		return null;
	}
}