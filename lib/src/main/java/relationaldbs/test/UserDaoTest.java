package relationaldbs.test;

import relationaldbs.dao.UserDaoImpl;
import relationaldbs.model.User;

public class UserDaoTest {
    public static void main(String[] args) {

        UserDaoImpl userDao = new UserDaoImpl();

        // ─── CREAR TABLA ──────────────────────────────────────────────────
        userDao.createTable();

        // ─── INSERT ───────────────────────────────────────────────────────
        System.out.println("=== INSERT USERS ===");
        userDao.insert(new User("Sasha",     "12345", 3000, 1, "sasha@gmail.com",  "644923321", "Calle Ramadan 23",  "user",  "12/05/2021"));
        userDao.insert(new User("Alejandro", "abcde", 1500, 2, "alex@gmail.com",   "611222333", "Calle Mayor 5",     "admin", "01/01/2022"));
        userDao.insert(new User("Lucas",     "pass1", 500,  3, "lucas@gmail.com",  "699888777", "Avenida del Sol 8", "user",  "15/03/2023"));

        // ─── FIND BY ID ───────────────────────────────────────────────────
        System.out.println("=== FIND USER BY ID ===");
        User userFound = userDao.find(1L);
        if (userFound != null)
            System.out.println("Usuario encontrado: " + userFound.getName() + " - " + userFound.getEmail());

        // ─── FIND BY EMAIL ────────────────────────────────────────────────
        System.out.println("=== FIND USER BY EMAIL ===");
        User userByEmail = userDao.find("sasha@gmail.com");
        if (userByEmail != null)
            System.out.println("Usuario por email: " + userByEmail.getName());

        // ─── FIND ALL ─────────────────────────────────────────────────────
        System.out.println("=== FIND ALL USERS ===");
        userDao.findAll().forEach(u ->
            System.out.println("- " + u.getName() + " | " + u.getEmail() + " | " + u.getRole()));

        // ─── UPDATE ───────────────────────────────────────────────────────
        System.out.println("=== UPDATE USER ===");
        userFound.setBalance(9999);
        userFound.setRole("admin");
        userDao.update(userFound);
        System.out.println("Usuario actualizado: " + userDao.find(1L).getRole());

        // ─── DELETE ───────────────────────────────────────────────────────
        System.out.println("=== DELETE USER ===");
        boolean userDeleted = userDao.delete(3L);
        System.out.println("Usuario eliminado: " + userDeleted);
    }
}