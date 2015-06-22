package tests;

import calendar.*;
import java.util.*;
import junit.framework.TestCase;

/**
 * Please put your own test cases into this file, so they can be tested on the
 * server.
 */

public class StudentTests extends TestCase {

	public void testLinkedListIsEmpty() {
		MySortedLinkedList<Activity> test = new MySortedLinkedList<Activity>(new ActivityComparator());
		assertTrue(test.isEmpty());
	}

	public void testLinkedListGetIndex() {
		Activity[] activities = new Activity[] {
				new Activity("Project", new Time(4, 00, "pm"), 45),
				new Activity("Coffee", new Time(9, 00, "am"), 15),
				new Activity("Brunch", new Time(12, 45, "pm"), 30),
				new Activity("Breakfast", new Time(6, 30, "am"), 30),
				new Activity("TV", new Time(5, 30, "pm"), 30) };
		MySortedLinkedList<Activity> test = new MySortedLinkedList<Activity>(
				new ActivityComparator());
		for (Activity activity : activities) {
			test.add(activity);
		}

	}

	public void testLinkedListRemove() {
		Activity[] activities = new Activity[] {
				new Activity("Project", new Time(4, 00, "pm"), 45),
				new Activity("Coffee", new Time(9, 00, "am"), 15),
				 };
		MySortedLinkedList<Activity> test = new MySortedLinkedList<Activity>(
				new ActivityComparator());
		for (Activity activity : activities) {
			test.add(activity);
		}
		System.out.println(test.size());
		assertEquals(test.size(), 2);
		test.remove(activities[1]);
		assertEquals(test.size(), 1);
		String last = "";
		for(int i = 0; i < test.size(); i++){
			System.out.println(test.get(i).toString());
			last += test.get(i).toString();
		}
		System.out.println(test.get(0).toString() + "v");
		System.out.println(last);
		assertTrue(last.equals(test.get(0).toString()));

	}

	public void testLinkedListSize() {
		Activity[] activities = new Activity[] {
				new Activity("Project", new Time(4, 00, "pm"), 45),
				new Activity("Coffee", new Time(9, 00, "am"), 15),
				new Activity("Brunch", new Time(12, 45, "pm"), 30),
				new Activity("Breakfast", new Time(6, 30, "am"), 30),
				new Activity("TV", new Time(5, 30, "pm"), 30) };
		MySortedLinkedList<Activity> test = new MySortedLinkedList<Activity>(
				new ActivityComparator());
		for (Activity activity : activities) {
			test.add(activity);
		}
		System.out.println(test.size());
		assertEquals(test.size(), 5);
	}

	public void testGetActivity() {
		WeekActivities week = instantiateWeek();
		week.addActivity("Basketball", Day.Wednesday, new Time(12, 00, "pm"),
				30);
		Activity basketball = new Activity("Basketball",
				new Time(12, 00, "pm"), 30);
		assertTrue(basketball.toString().equals(
				week.getActivity(Day.Wednesday, new Time(12, 00, "pm"))
						.toString()));
	}

	public void testRemoveActivity() {
		WeekActivities week = instantiateWeek();
		System.out.println(week.toString());
		assertTrue(week.removeActivity(Day.Monday, new Time(12, 00, "pm")));
		System.out.println(week.toString());
		assertTrue(week.removeActivity(Day.Monday, new Time(10, 00, "am")));
		System.out.println(week.toString());
		assertTrue(week.removeActivity(Day.Monday, new Time(5, 00, "pm")));
		System.out.println(week.toString());
		assertFalse(week.anyActivitiesOnDay(Day.Monday));
	}

	public WeekActivities instantiateWeek() {
		WeekActivities week = new WeekActivities();

		week.addActivity("Coffee", Day.Monday, new Time(10, 00, "am"), 10);
		week.addActivity("Tennis", Day.Monday, new Time(12, 00, "pm"), 180);
		week.addActivity("Gaming", Day.Monday, new Time(5, 00, "pm"), 120);

		return week;
	}
}