package org.jointheleague.rhymelyet.immutablelist;

public final class EmptyList<T> implements ImmutableList<T> {

	private static ImmutableList instance = new EmptyList();

	public static ImmutableList getInstance() {
		return instance;
	}

	private EmptyList() {
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImmutableList<T> remove(T e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<T> push(T e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<T> append(T e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T head() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableList<T> tail() {
		// TODO Auto-generated method stub
		return null;
	}
}
