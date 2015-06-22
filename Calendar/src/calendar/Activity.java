//Anh Tran
package calendar;

/**
 * This class represents an activity.  An activity is associated with a name,
 * start time, end time and duration.  
 * 
 * We have implemented this class for you and you should not modify it.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class Activity {
	private String name;
	private Time startTime, endTime;
	private int duration;

	/**
	 * Initializes an Activity object based on the parameters.  The endTime 
	 * is initialized based on the start time and the duration.
	 * 
	 * @param name  name of the activity
	 * @param startTime  time when activity begins
	 * @param duration duration of the activity in minutes
	 */
	public Activity(String name, Time startTime, int duration) {
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
		this.endTime = Time.increaseByMinutes(startTime, duration);
	}
	
	/**
	 * Returns the name of the activity.
	 * 
	 * @return name of the activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the activity's name.
	 * 
	 * @param name activity name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the activity's start time.
	 * 
	 * @return start time
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * Updates the activity's start time.  Updating the start time of an
	 * activity updates the end time.
	 * 
	 * @param startTime new start time
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
		this.endTime = Time.increaseByMinutes(startTime, duration);
	}

	/**
	 * Returns the activity's duration (in minutes).
	 * 
	 * @return duration in minutes
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Updates the activity's duration. The end time is updated based
	 * on the new duration.
	 * 
	 * @param duration new duration (in minutes).
	 */
	public void setDuration(int duration) {
		this.duration = duration;
		this.endTime = Time.increaseByMinutes(startTime, duration);
	}
	
	/**
	 * Returns the activity's end time.
	 * 
	 * @return activities end time
	 */
	public Time getEndTime() {
		return endTime;
	}

	/**
	 * Returns a string representation for an Activity.  The format is:
	 * <pre>
	 * Activity Name: <b>name</b>, StartTime: <b>startTime</b>, EndTime: <b>endTime</b>, Duration: <b>duration</b>
	 * </pre>
	 * @return string representation for an activity
	 */
	public String toString() {
		String result = "       Activity Name: " + name + ", StartTime: " + startTime;
		result += ", EndTime: " + endTime + ", Duration: " + duration;
		return result;
	}
}