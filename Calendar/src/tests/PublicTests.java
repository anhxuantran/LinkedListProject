package tests;
import junit.framework.TestCase;

import java.util.Iterator;

import calendar.*;

public class PublicTests extends TestCase {

	public void testListEmpty() {
		MySortedLinkedList<Activity> myList = new MySortedLinkedList<Activity>(new ActivityComparator());
		Iterator<Activity> iterator = myList.iterator();
		assertTrue(iterator.hasNext() == false);
	}
	
	public void testGoodFaithAttempt() {
		String resultsFileName = "pubTestListAdd.txt";	
		Activity[] activities = new Activity[]{new Activity("WorkOnProject", new Time(3,00,"pm"), 45),
										       new Activity("CoffeeBreak", new Time(10,00,"am"), 15),
										       new Activity("Lunch", new Time(12,45,"pm"), 30),
										       new Activity("Breakfast", new Time(6,30,"am"), 30),
										       new Activity("WatchTV", new Time(5,30,"pm"), 30)};
		MySortedLinkedList<Activity> myList = new MySortedLinkedList<Activity>(new ActivityComparator());
		for (Activity activity : activities) 
			myList.add(activity);
		
		Iterator<Activity> iterator = myList.iterator();
		String output = "";
		while (iterator.hasNext()) {
			output += iterator.next() + "\n";
		}
		System.out.println(output);
		assertTrue(TestingSupport.correctResults(resultsFileName, output));
	}

	public void testWeekActivitiesAddGeneral() {
		String resultsFileName = "pubTestWeekActivitiesAddGeneral.txt";		
		WeekActivities weekActivities = initializeActivities();
		assertTrue(TestingSupport.correctResults(resultsFileName, weekActivities.toString()));
	}
		
	public void testWeekActivitiesPeriodAvail() {
		WeekActivities weekActivities = initializeActivities();
		
		assertFalse(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(8,30,"am"), 10));
		System.out.println(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(8,30,"am"), 10));
		System.out.println(weekActivities.toString());
		System.out.println(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(8,30,"am"), 10));
		assertTrue(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(8,20,"am"), 10));
		assertFalse(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(3,14,"pm"), 2));
		assertTrue(weekActivities.isTimePeriodAvailable(Day.Monday, new Time(4,00,"pm"), 30));
	}
	
	public void testWeekActivitiesAddBetween() {
		String resultsFileName = "pubTestWeekActivitiesAddBetween.txt";
		WeekActivities weekActivities = initializeActivities();
		assertTrue(weekActivities.addActivity("Tennis", Day.Monday, new Time(1,00,"pm"), 45));	
		assertTrue(weekActivities.addActivity("Chess", Day.Monday, new Time(1,45,"pm"), 30));
		assertFalse(weekActivities.addActivity("WebBrowsing", Day.Monday, new Time(2,15,"pm"), 46));
		assertFalse(weekActivities.addActivity("CallWife", Day.Monday, new Time(2,14,"pm"), 45));
		assertTrue(TestingSupport.correctResults(resultsFileName, weekActivities.toString()));
	}	
	
	/******* Support method *******/
	private static WeekActivities initializeActivities() {		
		WeekActivities weekActivities = new WeekActivities();		
		weekActivities.addActivity("Breakfast", Day.Monday, new Time(8,30,"am"), 60);	
		weekActivities.addActivity("MeetingChair", Day.Monday, new Time(9,30,"am"), 60);
		weekActivities.addActivity("Lunch", Day.Monday, new Time(12,00,"pm"), 60);
		weekActivities.addActivity("CoffeeBreak", Day.Monday, new Time(3,00,"pm"), 15);
		weekActivities.addActivity("Gym", Day.Wednesday, new Time(11,00,"am"), 75);
//		
		return weekActivities;
	}	
}