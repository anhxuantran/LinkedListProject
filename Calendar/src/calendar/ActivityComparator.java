package calendar;
import java.util.Comparator;

/**
 * This class allow us to compare activities based on start time.  
 * 
 * We have implemented this class for you and you should not modify it.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class ActivityComparator implements Comparator<Activity> {
	 public int compare(Activity a1, Activity a2) {
		return a1.getStartTime().compareTo(a2.getStartTime());
	}
}