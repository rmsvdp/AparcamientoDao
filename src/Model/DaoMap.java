package Model;

import java.util.HashMap;

public interface DaoMap<T> {

		public HashMap<Integer,T> findAll();
		public T findOne(Integer key);
		public boolean insertOne(T t);
		public boolean deleteOne(Integer key);
		public boolean updateOne(Integer key,T t);
		
	
	} // interface Dao