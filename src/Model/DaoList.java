package Model;

import java.util.ArrayList;

public interface DaoList <T> {

		public ArrayList<T> findAll();
		public T findOne(Integer key);
		public boolean insertOne(T t);
		public boolean deleteOne(Integer key);
		public boolean updateOne(Integer key,T t);
		
	
	} // interface DaoList