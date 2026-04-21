package relationaldbs.test;

import relationaldbs.dao.UserDao;
import relationaldbs.dao.UserDaoImpl;
import relationaldbs.model.User;

public class UserDaoTest {
 public static void main(String [] args) {
	//insert test
	 UserDao userDao = new UserDaoImpl();
	 userDao.insert(new User("Sasha","12345",3000, 1 , "sasha@gmail.com","644923321","calle ramadan 23","user","12/05/2021"));
	 
	 
}
}
