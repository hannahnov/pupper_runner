package p_r.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomCacheServiceSimpleInMemory <T> implements CustomCacheService<T> {
	
	private ArrayList<T> cache = new ArrayList<T>();
	
	public CustomCacheServiceSimpleInMemory(ArrayList<T> cache) {
		super();
		this.cache = cache;
	}

	public CustomCacheServiceSimpleInMemory() {
		super();
	}

	public void setCache(ArrayList<T> cache) {
		this.cache = cache;
	}

	@Override
	public void addToCache(T obj) {
		System.out.println("Adding object " + obj + " to cache");
		cache.add(obj);
	}

	@Override
	public void removeFromCache(T obj) {
		System.out.println("Removing object " + obj + " from cache");
		cache.remove(obj);
		
	}

	@Override
	public T retrieveItem(T obj) {
		return obj;
	}

	@Override
	public void emptyCache() {
		cache.clear();
		
	}

	@Override
	public boolean contains(T obj) {
		return cache.contains(obj);
	}

	@Override
	public void updateFromCache(T org, T upd) {
		int index = cache.indexOf(org);
		cache.set(index, upd);
		
	}

	@Override
	public List<T> retrieveAllItems() {
					return cache;
	}

	@Override
	public List<T> retrieveMatching(Predicate<T> p) {
		return cache.stream().filter(p).collect(Collectors.toList());
	}
	
	@Override
	public int size() {
		return cache.size();
	}
	
	@Override
	public T at(int i) {
		return cache.get(i);
	}
	
}

