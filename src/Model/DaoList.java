package Model;

import java.util.ArrayList;

public interface DaoList <T> {

		public ArrayList<T> findAll();
		public T findOne(String key);
		public boolean insertOne(T t);
		public boolean deleteOne(String key);
		public boolean updateOne(String key,T t);
		
	
	} // interface DaoList