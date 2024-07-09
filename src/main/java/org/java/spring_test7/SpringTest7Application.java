package org.java.spring_test7;

import java.util.Optional;

import org.java.spring_test7.db.pojo.Post;
import org.java.spring_test7.db.pojo.User;
import org.java.spring_test7.db.service.PostService;
import org.java.spring_test7.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTest7Application implements CommandLineRunner {
	@Autowired
	UserService us;

	@Autowired
	PostService pos;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringTest7Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testCRUDUser();
		testCRUDPost();
		System.out.println("END");
	}

	public void testCRUDUser() {
		// CREATE
		User u1 = new User("Giuggio", "giuggioNumberOne", "querty");
		User u2 = new User("Peppe", "peppone", "querty");
		User u3 = new User("Giggi", "giggi-er-bullo", "querty");
		User u4 = new User("Guapa", "guapa-loca", "querty");

		us.save(u1);
		us.save(u2);
		us.save(u3);
		us.save(u4);

		// READ	LIST
		us.getAll().forEach(System.out::println);

		// READ BY ID
		System.out.println(us.getUserById(4));

		// UPDATE
		Optional<User> oldUOpt = us.getUserById(u4.getId());

		if (oldUOpt.isEmpty()) {
			System.out.println("non esiste questo user");
			return;
		}

		User oldU = oldUOpt.get();
		oldU.setRealName("Nuovo Nome");
		us.save(oldU);
		System.out.println(us.getUserById(u4.getId()));

		// DELETE
		us.delete(u2);

		//DELETE USER WHIT POST JOINED

	}

	public void testCRUDPost() {
		try {
			// CREATE
			User u1 = new User("Pierpaolo", "per l'amici Paolo", "qwerty");
			User u2 = new User("Giovanna", "per l'amici tutta panna", "qwerty");
			us.save(u1);
			us.save(u2);
	
			Post p1 = new Post("Buongiornissimo", " ", 10000, u1);
			Post p2 = new Post("BuonNatale", " ", 55000, u1);
			Post p3 = new Post("BuonaPasqua", " ", 5000, u2);
			Post p4 = new Post("Palestra", "anche oggi ci alleniamo", 10000, u1);
			pos.save(p1);
			pos.save(p2);
			pos.save(p3);
			pos.save(p4);
			us.deleteUserAndPosts(5);
	
			// READ LIST
			pos.getAll().forEach(System.out::println);
	
			// READ BY ID
			System.out.println(pos.getPostById(4));
	
			// UPDATE
			Optional<Post> oldPOpt = pos.getPostById(p2.getId());
	
			if (oldPOpt.isEmpty()) {
				System.out.println("non esiste questo post");
				return;
			}
	
			Post oldP = oldPOpt.get();
			oldP.setTitle("Nuovo Titolo");
			pos.save(oldP);
			System.out.println(pos.getPostById(p2.getId()));
	
			// DELETE
			pos.delete(p3);
		} catch (Exception e) {
			System.err.println("Errore durante l'esecuzione di testCRUDPost: " + e.getMessage());
			e.printStackTrace();
		}

		
	}
	
}
