package eu.europa.ec.todo.service;

import eu.europa.ec.todo.model.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class PeopleService {
    
    @PersistenceContext(name="todo_PU")
    private EntityManager em;

    public List<Person> all() {
        return findAll().getResultList();
    }

    public void create(Person person) throws ServiceException 
    {
        em.persist(person);
    }

    public Person get(Long id) {        
        return findById(id).getSingleResult();
    }

    public void update(Person person) {
        em.merge(person);
    }



    // FINDERS
    
    private TypedQuery<Person> findAll() {
        CriteriaQuery<Person> cq = em.getCriteriaBuilder().createQuery(Person.class);
        cq.select(cq.from(Person.class));        
        return em.createQuery(cq);
    }

    private TypedQuery<Person> findById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> from = cq.from(Person.class);
        cq.select(from);
        Predicate p = cb.equal(from.get("id"), id);
        cq.where(p);
        return em.createQuery(cq);
    }


}
