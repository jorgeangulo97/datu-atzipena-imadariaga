package exekutagarriak;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Mendia;

public class OinarrizkoEragiketak {

	public static SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public static void main(String[] args) {

		datuaGorde(new Mendia(0, "Kilimanjaro", 5000, "AfrikaEast"));
		datuakIkusi();
	}

	public static void datuaGorde(Mendia m) {

		Session saioa = sf.openSession();
		saioa.beginTransaction();
		saioa.save(m);
		saioa.getTransaction().commit();
		saioa.close();

	}

	public static void datuakIkusi() {

		Session saioa = sf.openSession();
		saioa.beginTransaction();
		List result = saioa.createQuery("from Mendia").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
		for (Mendia m : (List<Mendia>) result) {
			System.out.println(m);
		}
		saioa.getTransaction().commit();
		saioa.close();
	}
}