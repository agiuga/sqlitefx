package models;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

public class Volunteer {
	private String firstName, lastName, phoneNumber;
	private LocalDate birthday;
	private File imageFile;  //cleaner to store location of image

	public Volunteer(String firstName, String lastName, String phoneNumber, LocalDate birthday){
		//will force validation when instantiating
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setBirthday(birthday);
		setImageFile(new File("./src/images/defaultPerson.png"));
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

	public String getPhoneNumberString() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber =phoneNumber;

	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		int age =Period.between(birthday, LocalDate.now()).getYears();
		if (age >=14 && age <100)
			this.birthday = birthday;
		else
			throw new IllegalArgumentException("age is wrong");
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	//This method will return a formatted String with the names and age
	//"Fred Flinstone is 19 years old" for example

	public String toString() {
		return String.format("%s %s is %d years old", firstName,lastName, Period.between(birthday, LocalDate.now()).getYears());
	}
	public void insertIntoDB() throws SQLException {
		Connection conn= null;
		PreparedStatement preparedStatement =null; //to avoid sql injection
		try {
			//Class.forName("org.sqlite.JDBC");
			//1. Connect to the database
			conn =DriverManager.getConnection("jdbc:sqlite:src\\models\\volunteer.db");
			//2 Give a time limit to the query
		    Statement statement = conn.createStatement();
	        statement.setQueryTimeout(30);  // set timeout to 30 sec.
			//3. Create a string the holds the query with ? as place holders
			String sql = "INSERT INTO volunteers(firstName,lastName,phoneNumber,birthday,imageFile)"
					+ "VALUES (?,?,?,?,?)";
			//4. prepare the query
			preparedStatement = conn.prepareStatement(sql);
			//5.Convert birthday to sql date
			Date db =Date.valueOf(birthday);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setDate(4, db);
			preparedStatement.setString(5, imageFile.getName());
			//5. Execute the statement. In volunteer app
			preparedStatement.executeUpdate();


		} catch (Exception e) {
			System.err.println(e.getMessage());
				}
	    finally {
	    	if (preparedStatement !=null)
	    		preparedStatement.close();
	    	if (conn !=null)
				conn.close();

	    }

	}

}
