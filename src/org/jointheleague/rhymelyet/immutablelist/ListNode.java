package org.jointheleague.rhymelyet.immutablelist;

//This class is purposely made package private
final class ListNode<T> implements ImmutableList<T> {

	private final T head;
	private final ImmutableList<T> tail;
	private final int length;

	ListNode(T head, ImmutableList<T> tail) {
		if (tail == null) {
			throw new IllegalArgumentException("Tail cannot be null.");
		}
		this.head = head;
		this.tail = tail;
		this.length = tail.length() + 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public ImmutableList<T> remove(T e) {
		int c = countOccurences(e);
		return remove(e, c);
	}

	private int countOccurences(T e) {
		int count = 0;
		for (ImmutableList<T> list = this; !list.isEmpty(); list = list.tail())
			if (list.head() == null ? list.head() == e : list.head().equals(e))
				count++;
		return count;
	}

	private ImmutableList<T> remove(T e, int c) {
		if (c == 0) {
			return this;
		} else if (head == null ? head == e : head.equals(e)) {
			return tail.isEmpty() ? tail : ((ListNode<T>) tail).remove(e, c - 1);
		} else {
			return new ListNode<T>(head, ((ListNode<T>) tail).remove(e, c));
		}
	}

	@Override
	public ListNode<T> push(T e) {
		return new ListNode<T>(e, this);
	}

	@Override
	public ImmutableList<T> append(T e) {
		return new ListNode<T>(head, tail.append(e));
	}

	@Override
	public T head() {
		return head;
	}

	@Override
	public ImmutableList<T> tail() {
		return tail;
	}

	@Override
	public String toString() {
		String $ = "(";
		for (ImmutableList<T> list = this; !list.isEmpty(); list = list.tail())
			$ += list.head() + " ";
		$ = $.substring(0, $.length() - 1);
		$ += ")";
		return $;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		ImmutableList<?> lst = this;
		while (!lst.isEmpty()) {
			result = prime * result + (lst.head() == null ? 0 : lst.head().hashCode());
			lst = lst.tail();
		}
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ListNode<?>)) {
			return false;
		}
		ListNode<?> that = (ListNode<?>) other;
		if (that.length != length) {
			return false;
		}
		if(head == null ? that.head() != null : !head.equals(that.head()))
			return false;
		return tail.equals(that.tail());
	}
	
}
