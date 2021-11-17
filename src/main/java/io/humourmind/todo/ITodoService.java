package io.humourmind.todo;

import java.util.List;
import java.util.UUID;

public interface ITodoService {

	List<Todo> findAll();

	Todo findById(UUID id);

	Todo save(Todo resource);

	Todo update(Todo resource);

	void deleteById(UUID id);

}
