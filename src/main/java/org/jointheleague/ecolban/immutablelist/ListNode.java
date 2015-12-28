package org.jointheleague.ecolban.immutablelist;

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
        ImmutableList<T> lst = this;
        while (!lst.isEmpty()) {
            if (e == null ? e == lst.head() : e.equals(lst.head())) {
                count++;
            }
            lst = lst.tail();
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        ImmutableList<T> lst = this;
        boolean first = true;
        while (!lst.isEmpty()) {
            if (first) {
                first = false;
            } else {
                sb.append(" ");
            }
            sb.append(lst.head() == null ? "null" : lst.head().toString());
            lst = lst.tail();
        }
        sb.append(')');
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		ImmutableList<?> lst = this;
		while(!lst.isEmpty()){
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
        ImmutableList<?> lst = this;
        boolean equalSoFar = true;
        while (!lst.isEmpty() && equalSoFar) {
            if (head == null ? head != that.head : !head.equals(that.head)) {
                equalSoFar = false;
            }
            lst = lst.tail();
        }
        return equalSoFar;
    }

}
