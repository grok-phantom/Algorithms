package Assignment2;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The type Deque.
 *
 * @param <Item> the type parameter
 */
public class Deque<Item> implements Iterable<Item> {
  private Node head = null;
  private Node tail = null;
  private int size;

  /**
   * Instantiates a new Deque.
   */
  public Deque() {
    size = 0;
    head = new Node(null);
    tail = new Node(null);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<Integer>();
    dq.addFirst(1);
    dq.addFirst(2);
    dq.addFirst(2);
    dq.addLast(2);
    StdOut.println(dq.size());
    StdOut.println(dq.isEmpty());
    for (int a : dq) {
      StdOut.println(a);
    }
    dq.removeFirst();
    dq.removeFirst();
    dq.removeFirst();
    System.out.println(dq.size());
    System.out.println(dq.isEmpty());
  }

  /**
   * Is empty boolean.
   *
   * @return the boolean
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Size int.
   *
   * @return the int
   */
  public int size() {
    return size;
  }

  /**
   * Add head.
   *
   * @param item the item
   */
  public void addFirst(Item item) {
    if (item == null) {
      throw new NoSuchElementException();
    }
    Node newNode = new Node(item);
    newNode.next = head.next;
    newNode.prev = head;
    head.next.prev = newNode;
    head.next = newNode;
    ++size;
  }

  /**
   * Add tail.
   *
   * @param item the item
   */
  public void addLast(Item item) {
    if (item == null) {
      throw new NoSuchElementException();
    }
    Node newNode = new Node(item);
    newNode.next = tail;
    newNode.prev = tail.prev;
    tail.prev.next = newNode;
    tail.prev = newNode;
    ++size;
  }

  /**
   * Remove head item.
   *
   * @return the item
   */
  public Item removeFirst() {
    if (isEmpty()) {
      throw new IllegalArgumentException();
    }
    final Node oldNode = head.next;
    head.next = oldNode.next;
    head.next.prev = head;
    --size;
    return oldNode.item;
  }

  /**
   * Remove tail item.
   *
   * @return the item
   */
  public Item removeLast() {
    if (isEmpty()) {
      throw new IllegalArgumentException();
    }
    final Node oldNode = tail.prev;
    tail.prev = oldNode.prev;
    tail.prev.next = tail;
    --size;
    return oldNode.item;
  }


  @Override
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = head;

    public boolean hasNext() {
      return current.next != tail;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      current = current.next;
      return current.item;
    }
  }

  private class Node {
    Item item;
    Node prev = null;
    Node next = null;

    Node(Item item) {
      this.item = item;
    }
  }
}
