package org.jointheleague.rhymelyet.immutablelist;

import java.util.NoSuchElementException;

public final class EmptyList<T> implements ImmutableList<T> {

	private static ImmutableList instance = new EmptyList();

	public static ImmutableList getInstance() {
		return instance;
	}

	private EmptyList() {
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public ImmutableList<T> remove(T e) {
		return this;
	}

	@Override
	public ImmutableList<T> push(T e) {
		return new ListNode<T>(e, this);
	}

	@Override
	public ImmutableList<T> append(T e) {
		return new ListNode<T>(e, this);
	}

	@Override
	public T head() {
		throw new NoSuchElementException("EmptyList has no head.");
	}

	@Override
	public ImmutableList<T> tail() {
		throw new NoSuchElementException("EmptyList has no tail.");
	}
	
	@Override
	public String toString() {
		return "()";
	}
}
