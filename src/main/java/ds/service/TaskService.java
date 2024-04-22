package ds.service;

import ds.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

}
