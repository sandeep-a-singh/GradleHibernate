import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sunny.test.entities.Languages;


public class Test {

	public static void main(String[] args) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();

			/*
			Suzuki suzuki = new Suzuki();
			suzuki.setName("Swift");
			suzuki.setAccomodation(5);
			Lamborgini lamborgini = new Lamborgini();
			lamborgini.setPistons(600);
			suzuki.setLamborgini(lamborgini);
			Parts parts = new Parts();
			parts.setHoods("MRF-Hoods1");
			parts.setDecals("MRF-Decals1");

			Parts parts2 = new Parts();
			parts2.setHoods("MRF-Hoods2");
			parts2.setDecals("MRF-Decals2");
			
			lamborgini.getParts().add(parts);
			lamborgini.getParts().add(parts2);
			
			
			entitymanager.persist(suzuki);
			entitymanager.persist(lamborgini);
			

			entitymanager.getTransaction().commit();
	*/
			Languages languages = new Languages();
			languages.setId(1);
			languages.setLanguage("Marathi");
			languages.setLocalLanguage("मराठी");
			entitymanager.persist(languages);
			entitymanager.getTransaction().commit();
			
			System.out.println(crietriaQueries(entitymanager));
			entitymanager.close();
			emfactory.close();
		}	
	public static List<Languages> crietriaQueries(EntityManager em){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<Languages> q = cb.createQuery(Languages.class);
		  Root<Languages> c = q.from(Languages.class);
		/*  q.select(c).where(cb.equal(c.get("eid"), 1201));
		  q.select(c).where(cb.equal(c.get("eid"), 1202));
		 */ 
		  List<Predicate> predList = new LinkedList<Predicate>();
		  /*Predicate p= cb.equal(c.get("eid"), 1201);
		  Predicate p2= cb.equal(c.get("eid"), 1202); 
		  Predicate p3= cb.or(p, p2);
		  predList.add(p3);*/
		  
		  Predicate[] predArray = new Predicate[predList.size()];
		  predList.toArray(predArray);
		  q.select(c).where(predArray);
		  TypedQuery<Languages> typedQuery = em.createQuery(q);
		  List<Languages> employeeList = typedQuery.getResultList();
return employeeList;
		  
	}
}
