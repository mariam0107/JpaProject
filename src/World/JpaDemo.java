package World;

import World.domain.City;
import World.domain.Country;
//import World.domain.Country;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDemo {
  static EntityManagerFactory factory = Persistence.createEntityManagerFactory("World");
    
  static Scanner sc = new Scanner(System.in);
    public static void findCity(String name) {
      
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT c from City c where c.name=:name");
        query.setParameter("name", name);

        List<City> cities = (List<City>) query.getResultList();
        System.out.printf("Found %d matches for %s\n", cities.size(), name);
        for (City c : cities) {
            System.out.println(c);
        }
        em.close();
    }

    public static int findCountry(String name) {
   
        EntityManager em = factory.createEntityManager();
        try
        {
        Query query = em.createQuery("SELECT c from Country c where c.name=:name");
        query.setParameter("name", name);

        Country c = (Country) query.getSingleResult();
        
        System.out.println(c);
        System.out.println(c.getCapital());
        }
        catch(NoResultException e)
        {
            System.out.println(e.toString());
            System.out.println("NO SUCH COUNTRY");
        }
        em.close();
        System.out.println("DO YOU WANT TO CONTINUE(1-FOR YES,2-NO)");
        int ch=sc.nextInt();
        return ch;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String country;
        int ch=1;
        findCity("Los Angeles");
        while(ch==1)
        {
        System.out.println("ENTER THE COUNTRY TO BE FOUND");
        country = sc.nextLine();
        ch=findCountry(country);
        }

}
}
