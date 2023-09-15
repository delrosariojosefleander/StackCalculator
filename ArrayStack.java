package edu.ics211.h08;

import java.util.EmptyStackException;

public class ArrayStack<E> implements StackInterface<E> {

	/*
	 * fields to store the stack elements and the location of the top of the stack.
	 * the values are in array locations 0..top if top >= 0 for an empty array, top
	 * is -1
	 */
	private int top;
	private Object[] array;
	private int size;

	/* no-arguments default constructor creates an empty stack */
	public ArrayStack() {
		top = -1; // empty stack
		array = new Object[10]; // make room for at most 6 items
		size = 0;
		checkInvariants();
	}

	/* @return whether the stack is empty */
	public boolean empty() {
		checkInvariants();
		boolean isEmpty = (top == -1);
		checkInvariants();
		return isEmpty;
	}

	/* @param value to push onto the stack */
	public E push(E value) throws FullStackException {
		checkInvariants();
		try {
			top++;
			array[top] = value;
			size++;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			top--;
			throw new FullStackException("Stack is already full");
		}
		checkInvariants();
		return value;
	}

	/*
	 * different implementation of pop, does exactly the same.
	 * 
	 * @return the top value on the stack
	 */
	@SuppressWarnings("unchecked")
	public E pop() throws EmptyStackException {
		checkInvariants();
		try {
			E value = (E) array[top];
			array[top] = null;
			top--;
			size--;
			checkInvariants();
			return value;

		} catch (ArrayIndexOutOfBoundsException dylan) {
			top = -1;
			checkInvariants();
			throw new EmptyStackException();
		}
	}

	/* @return the top value on the stack */
	@SuppressWarnings("unchecked")
	public E peek() throws EmptyStackException {
		checkInvariants();
		E value = null;
		try {
			value = (E) array[top];

		} catch (ArrayIndexOutOfBoundsException e) {
			top = -1;
			checkInvariants();
			throw new EmptyStackException();
		}
		checkInvariants();
		return value;
	}

	/*
	 * convert the stack to a printable string
	 * 
	 * @return a string representing the stack
	 */
	public String toString() {
		checkInvariants();
		if (empty()) {
			checkInvariants();
			return "Empty Stack";
		} else {
			checkInvariants();
			return recursiveToString(0);
		}
	}

	/*
	 * recursive method to print a non-empty stack
	 * 
	 * @param the starting index in the array
	 * 
	 * @return a string representing the stack
	 */
	private String recursiveToString(int startPos) {
		checkInvariants();
		if (startPos > top) {
			return "";
		}
		String separator = "";
		if (startPos > 0) {
			separator = " :: ";
		}
		checkInvariants();
		return separator + array[startPos] + recursiveToString(startPos + 1);
	}

	public int size() {
		return size;
	}

	private void verify(boolean mustBeTrue) {
		if (!mustBeTrue) {
			throw new java.lang.AssertionError("assertion error");
		}
	}

	private void checkInvariants() {
		verify((top == -1) == (array[0] == null));
		if (top != -1) {
			verify(array[top] != null);
			verify(size > 0);
		}
		verify(top >= -1);
		verify(top <= array.length - 1);
	}

	// simple test
	public static void main(String[] args) {
		StackInterface<String> s = new ArrayStack<String>();

		System.out.println("before pushing anything, " + s);
		s.push("hello");
		s.push("world");
		System.out.println("after pushing hello and world, " + s);
		System.out.println("pop returns " + s.pop());
		System.out.println("after popping, " + s);
		StackInterface<Integer> si = new ArrayStack<Integer>();
		// push 100 values
		for (int i = 0; i < 6; i++) {
			si.push(i);
		}
		// now pop them and make sure the same values are returned
		// in LIFO order
		for (int i = 5; i >= 0; i--) {
			Integer returned = si.pop();
			if (!returned.equals(i)) {
				System.out.println("error: pop returns " + returned + ", expected " + i);
			}
		}
		s.push("a");
		s.push("beautiful");
		s.push("day");
		System.out.println("after pushing 'a beautiful day', " + s);
		System.out.println("pop returns " + s.pop());
		System.out.println("pop returns " + s.pop());
		System.out.println("pop returns " + s.pop());
		System.out.println("pop returns " + s.pop());
		System.out.println("after popping, " + s);
		/*
		 * expected output: before pushing anything, Empty Stack after pushing hello and
		 * world, hello :: world pop returns world after popping, hello after pushing 'a
		 * beautiful day', hello :: a :: beautiful :: day pop returns day pop returns
		 * beautiful pop returns a pop returns hello after popping, Empty Stack
		 */

	}
}