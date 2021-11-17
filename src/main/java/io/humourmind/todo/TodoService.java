package io.humourmind.todo;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TodoService implements ITodoService {

	private final EntityManager entityManager;

	public TodoService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Todo> findAll() {
		return entityManager.createNamedQuery("Todo.findAll", Todo.class).getResultList();
	}

	@Override
	public Todo findById(UUID id) {
		return entityManager.find(Todo.class, id);
	}

	@Override
	public Todo save(Todo resource) {
		entityManager.persist(resource);
		return resource;
	}

	@Override
	public Todo update(Todo resource) {
		return entityManager.merge(resource);
	}

	@Override
	public void deleteById(UUID id) {
		entityManager.remove(id);
	}

}
