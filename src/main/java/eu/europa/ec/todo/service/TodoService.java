package eu.europa.ec.todo.service;

import eu.europa.ec.todo.model.State;
import eu.europa.ec.todo.model.Todo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class TodoService {

    @PersistenceContext(name="todo_PU")
    private EntityManager em;

    public List<Todo> all() {
        return findAll().getResultList();
    }

    public void create(Todo todo) throws ServiceException 
    {
        todo.setCreatedOn(new Date());
        em.persist(todo);
    }

    public Todo get(Long id) {        
        return findById(id).getSingleResult();
    }

    public void update(Todo todo) {
        em.merge(todo);
    }

    public List<Todo> top(int max) {
        return findTop().setMaxResults(max).getResultList();
    }

    // FINDERS
    
    private TypedQuery<Todo> findAll() {
        CriteriaQuery<Todo> cq = em.getCriteriaBuilder().createQuery(Todo.class);
        cq.select(cq.from(Todo.class));        
        return em.createQuery(cq);
    }

    private TypedQuery<Todo> findById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);
        Root<Todo> from = cq.from(Todo.class);
        cq.select(from);
        Predicate p = cb.equal(from.get("id"), id);
        cq.where(p);
        return em.createQuery(cq);
    }

    private TypedQuery<Todo> findTop() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);

        // SELECT
        Root<Todo> from = cq.from(Todo.class);
        cq.select(from);

        // WHERE
        Predicate p = cb.equal(from.get("status"), State.created);
        cq.where(p);

        // ORDER BY
        Order order = cb.asc(from.get("id"));
        cq.orderBy(order);

        return em.createQuery(cq);
    }

}
