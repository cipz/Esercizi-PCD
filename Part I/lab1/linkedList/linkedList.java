
package lab1.linkedList;

public class linkedList {
 
	private int counter;
	private Node head;
 
	public linkedList() {}
 
	// appends the specified element to the end of this list.
	public void add(Object data) {
		
		if(head == null)
			head = new Node(data);
		
		else {
			
			Node tmp = head;
			
			while(tmp.getNext() != null) 
				tmp = tmp.getNext();
			
			tmp.setNext(new Node(data));
			
		}//if_else
		
		incrementCounter();
		
 		// throw new UnsupportedOperationException("");
		
	}//add
  
	// inserts the specified element at the specified position in this list
	public void add(Object data, int index) {
		
		if(index < 0 || index > getCounter())
			
			throw new UnsupportedOperationException("Index " + index + " exceeds list capacity!");
			
		else {
			
			Node oldNode = null;
			Node newNode = head;
			
			while(newNode != null && index > 0) {
				oldNode = newNode;
				newNode = newNode.getNext();
				index--;
			}//while
						
			if(oldNode == null) {
				oldNode = new Node(data);
				oldNode.setNext(newNode);
				head = oldNode;
			} else {
				oldNode.setNext(new Node(data));
				oldNode.getNext().setNext(newNode);
			}//if_else
			
			incrementCounter();
			
		}
			
		
 		// throw new UnsupportedOperationException("");
		
	}//add
	
	// returns the element at the specified position in this list.
	public Object get(int index){
		
		if(index < 0 || index > getCounter())
			throw new UnsupportedOperationException("Index " + index + " out of bounds!");
		
		Node tmp = head;
		
		while(index > 0) {
			tmp = tmp.getNext();
			index--;
		}//while			
		
		return tmp.getData();
	}
	
	// returns the value of the counter
	public int getCounter(){
		return counter;
	}	
 
	private void incrementCounter() {
		counter++;
	}
 
	private void decrementCounter() {
		counter--;
	}
	
	// removes the element at the specified position in this list.
	public boolean remove(int index) {
		
		if(index < 0 || index > getCounter())
			return false;
		
		Node tmp = head;
		
		while(index > 0) {
			tmp = tmp.getNext();
			index--;
		}//while
		
		tmp.setNext(tmp.getNext().getNext());
	
		return true;
		
	}//remove
 
	// returns the number of elements in this list.
	public int size() {
		return getCounter();
	}
 
	public String toString() {
		
		String output = "";
		Node tmp = head;
		
		while(tmp !=null) {
			output += tmp.getData();
			output += " ";
			tmp = tmp.getNext();
		}//while
			
		return output;
	}//toString
 
	private static class Node {
		// reference to the next node in the chain, or null if there isn't one.
		Node next;
 
		// data carried by this node. could be of any type you need.
		Object data;
 
		// Node constructor
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}
 
		// another Node constructor if we want to specify the node to point to.
		public Node(Object dataValue, Node nextValue) {
		}
 
		// these methods should be self-explanatory
		public Object getData() {
			return data;
		}
 
		@SuppressWarnings("unused")
		public void setData(Object dataValue) {
			data = dataValue;
		}
 
		public Node getNext() {
			return next;
		}
 
		public void setNext(Node nextValue) {
			next = nextValue;
		}
 
	}
	
	public static void main(String[] args) {
		 
		// Default constructor - let's put "0" into head element.
		linkedList list = new linkedList();
 
		// add more elements to LinkedList
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
 
		/*
		 * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
		 * corresponding wrapper class.
		 */
 
		System.out.println("Print: list: \t\t" + list);
		System.out.println(".size(): \t\t\t\t" + list.size());
		System.out.println(".get(3): \t\t\t\t" + list.get(3) + " (get element at index:3 - list starts from 0)");
		System.out.println(".remove(2): \t\t\t\t" + list.remove(2) + " (element removed)");
		System.out.println(".get(3): \t\t\t\t" + list.get(3) + " (get element at index:3 - list starts from 0)");
		System.out.println(".size(): \t\t\t\t" + list.size());
		System.out.println("Print again - list: \t" + list);
	}

}
