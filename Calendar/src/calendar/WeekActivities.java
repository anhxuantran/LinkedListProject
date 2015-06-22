package calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents activities we have in a week. The class uses a map
 * <code>(Map&lt;Day, MySortedLinkedList&lt;Activity&gt;&gt;)</code> to
 * associate a day with a list of activities for that particular day. <b>If a
 * day has no activities then no entry for that day will exist in the map.</b> <br>
 * <br>
 * Notice that for this project you must use a MySortedLinkedList object to keep
 * track of the activities associated with a particular day.
 * 
 * @author Dept of Computer Science, UMCPs
 */

public class WeekActivities {
	private Map<Day, MySortedLinkedList<Activity>> activitiesMap;

	/**
	 * Creates a HashMap that maps days to list of activities.
	 */
	public WeekActivities() {
		activitiesMap = new HashMap<Day, MySortedLinkedList<Activity>>();
	}

	/**
	 * Returns true if it is possible to schedule an activity in the time period
	 * starting at activityStartTime and lasting for the specified duration
	 * (minutes). Keep in mind that you can schedule an activity that starts
	 * right after another ends, or right before another begins. For example, if
	 * you have an activity scheduled from 3:00 pm to 4:00 pm, you can schedule
	 * an activity starting at 4:00 pm. You could also schedule an activity that
	 * starts before 3:00 pm and ends exactly at 3:00 pm.
	 * 
	 * You can assume an activity will start and end on the same day. For
	 * example, you do not have to handle the case where the start time is 11:59
	 * pm and the duration of the activity is 2 minutes.
	 * 
	 * Any activity will have a duration of at least one minute.
	 * 
	 * @param day
	 *            day activity will take place
	 * @param activityStartTime
	 *            start time
	 * @param duration
	 *            duration of activity in minutes
	 * @return true if the time period is available and false otherwise
	 */
	public boolean isTimePeriodAvailable(Day day, Time activityStartTime,
			int duration) {
		Time finaltime = Time.increaseByMinutes(activityStartTime, duration);

		if (!activitiesMap.isEmpty()) {
			Iterator<Activity> iterator = activitiesMap.get(day).iterator();
			Activity activity;
			while (iterator.hasNext()) {
				activity = iterator.next();
				if ((finaltime.compareTo(activity.getStartTime()) > 0 && 
						finaltime.compareTo(activity.getEndTime()) <= 0)
						|| (activityStartTime.compareTo(activity.getStartTime()) >= 0 && 
								activityStartTime.compareTo(activity.getEndTime()) < 0)
						|| (activityStartTime.compareTo(activity.getStartTime()) < 0 && 
								finaltime.compareTo(activity.getEndTime()) > 0))
					return false;
			}
		}
		return true;

	}

	/**
	 * Verifies whether the specified time period is available and if so, adds
	 * the activity to the list of activities for the day. The list must be kept
	 * sorted in ascending order according to the start time of the activities.
	 * 
	 * You can assume an activity will start and end on the same day. For
	 * example, you do not have to handle the case where the start time is 11:59
	 * pm and the duration of the activity is 2 minutes.
	 * 
	 * Any activity will have a duration of at least one minute.
	 * 
	 * @param name
	 *            activity name
	 * @param day
	 *            day activity will take place
	 * @param activityStartTime
	 *            start time
	 * @param duration
	 *            duration of activity in minutes
	 * @return true if the activity was added and false otherwise
	 */
	public boolean addActivity(String name, Day day, Time activityStartTime,
			int duration) {
		if (!activitiesMap.containsKey(day)) {
			activitiesMap.put(day, new MySortedLinkedList<Activity>(
					new ActivityComparator()));
		}
		if (!isTimePeriodAvailable(day, activityStartTime, duration)) {
			return false;
		} else {
			Activity activity = new Activity(name, activityStartTime, duration);
			activitiesMap.get(day).add(activity);
		}
		return true;

	}

	/**
	 * Returns a reference to the Activity that takes place at the specified day
	 * and that starts at the specified time.
	 * 
	 * @param day
	 *            day activity will take place
	 * @param startTime
	 *            start time
	 * @return Reference to the activity or null otherwise (e.g., if activity
	 *         not found, or if no activities are scheduled for the specified
	 *         day)
	 */
	public Activity getActivity(Day day, Time startTime) {
		if (!activitiesMap.containsKey(day)) {
			return null;
		}
		Activity activity;
		Iterator<Activity> iterator = activitiesMap.get(day).iterator();
		if (anyActivitiesOnDay(day)) {
			while (iterator.hasNext()) {
				activity = iterator.next();
				if (activity.getStartTime().compareTo(startTime) == 0) {
					return activity;
				}
			}
		}
		return null;
	}

	/**
	 * Removes the activity taking place at the specified day and start time. If
	 * after removing the activity there are no more activities left in the day,
	 * then the map entry associated with that day should be removed.
	 * 
	 * @param day
	 *            day activity will take place
	 * @param startTime
	 *            start time
	 * @return true if the activity is removed and false otherwise (e.g.,
	 *         activity not found, or no activities scheduled for the specified
	 *         day).
	 */
	public boolean removeActivity(Day day, Time startTime) {
		if (!activitiesMap.containsKey(day)) {
			return false;
		} else if (anyActivitiesOnDay(day)) {
			Activity activity;
			Iterator<Activity> iterator = activitiesMap.get(day).iterator();
			while (iterator.hasNext()) {
				activity = iterator.next();
				if (activity.getStartTime().compareTo(startTime) == 0) {
					activitiesMap.get(day).remove(activity);
					if (activitiesMap.get(day).isEmpty()) {
						activitiesMap.remove(day);
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns an Activity iterator for the activities in the specified day
	 * 
	 * @param day
	 *            target day
	 * @return Activities' iterator
	 * @throws IllegalArgumentException
	 *             Thrown if the specified day has no activities associated with
	 *             it. The thrown exception must use the message "Invalid day"
	 */
	public Iterator<Activity> dayActivitiesIterator(Day day) {
		if (activitiesMap.get(day).isEmpty() || !activitiesMap.containsKey(day)) {
			throw new IllegalArgumentException("Invalid day");
		}
		return activitiesMap.get(day).iterator();
	}

	/**
	 * Returns true if there are any activities scheduled during the specified
	 * day and false otherwise.
	 * 
	 * @param day
	 *            target day
	 * @return true if any activities are scheduled and false otherwise.
	 */
	public boolean anyActivitiesOnDay(Day day) {
		return activitiesMap.containsKey(day);
	}

	/**
	 * Returns a set of days with activities.
	 * 
	 * @return days set
	 */
	public Set<Day> daysWithActivities() {
		return activitiesMap.keySet();
	}

	/**
	 * This methods returns a map that allow us to tell what activity takes
	 * place at a particular day and at a particular start time.
	 * 
	 * @return a map (which could be empty)
	 */
	public Map<Day, Map<Time, Activity>> getDayTimeActivityMap() {
		HashMap<Day, Map<Time, Activity>> dayMap = new HashMap<Day, Map<Time, Activity>>();

		HashMap<Time, Activity> timeMap;
		MySortedLinkedList<Activity> list;
		Iterator<Activity> iterator;

		for (Day day : activitiesMap.keySet()) {
			timeMap = new HashMap<Time, Activity>();
			list = activitiesMap.get(day);
			iterator = list.iterator();
			while (iterator.hasNext()) {
				Activity currentActivity = iterator.next();
				timeMap.put(currentActivity.getStartTime(), currentActivity);
			}
			dayMap.put(day, timeMap);
		}
		return dayMap;
	}

	/**
	 * Returns a string representation for the week. We have implemented this
	 * method for you and you should not modify it.
	 * 
	 * @return string representation for the week activities
	 */
	public String toString() {
		String result = "";
		for (Day day : Day.values()) {
			MySortedLinkedList<Activity> activityList = activitiesMap.get(day);
			if (activityList != null) {
				result += "  Day: " + day + "\n";
				for (Activity activity : activityList)
					result += activity + "\n";
			}
		}
		return result;
	}
}