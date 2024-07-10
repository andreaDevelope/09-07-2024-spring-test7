package org.java.spring_test7;

import java.util.List;
import java.util.Optional;

import org.java.spring_test7.db.pojo.Post;
import org.java.spring_test7.db.pojo.Tag;
import org.java.spring_test7.db.pojo.User;
import org.java.spring_test7.db.service.PostService;
import org.java.spring_test7.db.service.TagService;
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

	@Autowired
	TagService ts;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringTest7Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testCRUDUser();
		testCRUDPost();
		testPostsTagsRelationals();
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
	
	public void testPostsTagsRelationals(){
		// CREO IL MIO POST CON RELATIVO USER
		User u1 = new User("Mio vero Nome", "giuri che è il mio nome", "querty");
		Post p1 = new Post("Il mio post", " ", 1, u1);
		
		// SALVO IL MIO POST CON RELATIVO USER
		us.save(u1);
		pos.save(p1);
		System.out.println(us.getUserById(7));
		System.out.println(pos.getPostById(5));

		// CREO E SALVO IL MIOP TAG
		Tag t1 = new Tag("#code");
		Tag t2 = new Tag("#ManyToMany");

		ts.save(t1);
		ts.save(t2);


		List<User> users = us.getAll();
		List<Post> posts = pos.getAll();
		List<Tag> tags = ts.getAll();

		users.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		posts.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		tags.forEach(System.out::println);


		//RELAZIONO LA TABELLA PADRE POST A TAG
		Optional<Post> optP1 = pos.getByIdWithTags(3);

		if (optP1.isEmpty()) {
			System.out.println("Post with id 1 not found");
			return;
		}

		System.out.println("-------------------------------------------------------");

		p1 = optP1.get();
		p1.addTag(t1);
		p1.addTag(t2);
		pos.save(p1);

		//RIMUOVO LA RELAZIONE TRA POST E TAG 
		p1.removeTag(t1);
		pos.save(p1);


		//FINALMENTE POSSO CANCELLARE IL TAG
		ts.delete(t1);

		System.out.println(t1 + "tag cancellato");
	}
}
