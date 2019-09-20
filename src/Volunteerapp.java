
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import models.Volunteer;

public class Volunteerapp {

	public static void main(String[] args) throws SQLException {
	Volunteer volunteer = new Volunteer("Baby", "Flinstone", "077455567",LocalDate.of(2000, Month.MARCH, 10));	
	System.out.println(volunteer);
	
	volunteer.insertIntoDB(); //stores the new volunteer object int the sql database
	
	}
}
