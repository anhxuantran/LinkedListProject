package calendar;

/**
 * This class represents a date (day and number).
 * 
 * We have implemented this class for you and you should not modify it.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class Date {
	private Day day;
	private int number;
	
	/**
	 * Initializes a date object.
	 * @param day
	 * @param dayNumber
	 */
	public Date(Day day, int dayNumber) {
		this.day = day;
		this.number = dayNumber;
	}
	
	/**
	 * Returns a string representation for a date, with the day followed
	 * by the number.
	 * 
	 * @return string representation for a date
	 */
	public String toString() {
		return day + " " + number;
	}
	
	/**
	 * Returns the day value.
	 * @return day
	 */
	public Day getDay() { return day; }
	
	/**
	 * Returns the number associated with the date.
	 * @return number
	 */
	public int getNumber() { return number; }
	
	/**
	 * Compares two date objects.  Two date objects are considered equal if
	 * they have the same day and number, respectively.
	 * 
	 * @return true if the objects are considered equal and false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			Date date = (Date)obj;
			return (day.equals(date.day) && number == date.number ? true : false);
		}
	}
	
	/**
	 * Returns a hash code for the object based on the string representation
	 * for the object.
	 * 
	 * @return hash code value
	 */
	public int hashCode() {
		return toString().hashCode();
	}
}