package relationaldbs.test;

import relationaldbs.dao.UserDao;
import relationaldbs.dao.UserDaoImpl;
import relationaldbs.model.User;

public class UserDaoTest {
 public static void main(String [] args) {
	//insert test
	 UserDao userDao = new UserDaoImpl();
	 userDao.insert(new User(0,"sasha",null,0));
}
}
