// Anh Tran CMSC 132 
package calendar;

import java.util.*;

/**
 * This class represents a simple implementation of a sorted singly-linked list.
 * Elements added to the list are inserted in sorted order based on an specified
 * Comparator object. <br>
 * <br>
 * 
 * This class relies on two classes: MyIterator and MyListNode. We have
 * implemented the MyListNode class for you, but you are responsible for the
 * implementation of the MyIterator class. Notice these are inner classes
 * defined within the MySortedLinkedList class. As a result they may access each
 * other's fields even if they are private. You are required to use the
 * MyListNode class to implement the nodes of your linked list. <br>
 * <br>
 * 
 * Feel free to add any methods to the MySortedLinkedList class you consider are
 * necessary to implement your project. <b>However, keep in mind that there are
 * two methods you are required to implement: an add method returning void (adds
 * an element to the list keeping the list sorted) and a remove method returning
 * void (removes any element(s) from the list that are equal to the
 * parameter).</b>
 * 
 * The JUnit public tests provide an example of using the MySortedLinkedList
 * class. <br>
 * <br>
 * 
 * You may not use the Java API LinkedList or ArrayList classes during the
 * implementation of the MySortedLinkedList class.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class MySortedLinkedList<T> implements Iterable<T> {
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * 
	 * @param comparator
	 */
	Comparator<T> comparator;
	MyListNode<T> head;

	public MySortedLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
		head = null;
	}

	/**
	 * This class represents a linked list node. You should not modify this
	 * class.
	 * 
	 * Because MyListNode is an inner class of MySortedLinkedList, methods in
	 * MySortedLinkedList may access fields of MyListNode directly even though
	 * they are private.
	 * 
	 * You may use the default constructor for MyListNode, which initializes
	 * both val and next to null.
	 * 
	 * @param <V>
	 */
	private class MyListNode<V> {
		private V val;
		private MyListNode<V> next;
	}

	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 */
	public void add(T element) {
		MyListNode<T> n = new MyListNode<T>();
		MyListNode<T> curr = head;
		MyListNode<T> prev = null;
		n.val = element;
		if (head == null) {
			n.next = head;
			head = n;
		} else {
			while (curr != null) {
				if (comparator.compare(n.val, curr.val) <= 0) {
					if (curr == head) {
						n.next = head;
						head = n;
						return;
					} else {
						prev.next = n;
						n.next = curr;
						return;
					}
				}
				prev = curr;
				curr = curr.next;
			}// if compare
			prev.next = n;
		}// while

	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public T get(int index) {
		int size = 0;
		MyListNode<T> curr = head;
		T element = null;
		while (curr != null) {
			if (size == index) {
				element = curr.val;
				break;
			}
			size++;
			curr = curr.next;
		}
		return element;
	}

	/**
	 * Removes any elements matching given element
	 */
	public void remove(T v) {
		MyListNode<T> curr = head;
		MyListNode<T> prev = null;
		while (curr != null) {
			int equal = comparator.compare(curr.val, v);
			if (equal == 0) {
				if (curr == head)
					head = head.next;
				else
					prev.next = curr.next;
			}/* else{
			prev = curr;
			}*/
			prev = curr;
			curr = curr.next;
		}
	}

	public int size() {
		int size = 0;
		MyListNode<T> curr = head;
		while (curr != null) {
			size++;
			curr = curr.next;
		}
		return size;
	}

	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns an iterator over the elements in this list (in proper sequence).
	 * 
	 * @return iterator over the list
	 */
	public Iterator<T> iterator() {
		return new MyIterator<T>(head);
	}

	/**
	 * This class implements an iterator over the list. You must implement this
	 * class.
	 * 
	 * @param <E>
	 */
	private class MyIterator<E> implements Iterator<E> {
		/**
		 * Defines an iterator based on the start node provided.
		 * 
		 * @param start
		 */
		MyListNode<E> curr;

		private MyIterator(MyListNode<E> start) {
			curr = start;
		}

		/**
		 * Returns true if there is another element available
		 * 
		 * @return true if there is another element and false otherwise
		 */
		public boolean hasNext() {
			if (curr == null) {
				return false;
			}
			return true;
		}

		/**
		 * Returns the next element
		 * 
		 * @return next element
		 */
		public E next() {
			E data = curr.val;
			curr = curr.next;
			return data;
		}

		/**
		 * Removes an element from the list. You do not need to implement this
		 * method.
		 */
		public void remove() {
			throw new UnsupportedOperationException(
					"You do NOT need to implement this method");
		}
	}
}