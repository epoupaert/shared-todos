package eu.europa.ec.todo.service;

import eu.europa.ec.todo.model.Todo;
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
public class TodoService {

    @PersistenceContext(name="todo_PU")
    private EntityManager em;

    public List<Todo> all() {
        return findAll().getResultList();
    }

    public void create(Todo todo) throws ServiceException 
    {
        em.persist(todo);
    }

    public Todo get(Long id) {        
        return findById(id).getSingleResult();
    }

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

    public void update(Todo todo) {
        em.merge(todo);
    }

}
