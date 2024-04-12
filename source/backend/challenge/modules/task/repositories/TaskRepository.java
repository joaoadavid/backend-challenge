package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskRepository implements ITaskRepository {

	private final static List<Task> tasks = new ArrayList<>();

	@Override
	public Task index(final Long taskId) {
		return tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst().orElse(null);
	}

	@Override
	public List<Task> show() {
		return tasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task newTask =  new Task();

		newTask.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		newTask.setTitle(taskDTO.getTitle());
		newTask.setDescription(taskDTO.getDescription());
		newTask.setProgress(0);
		newTask.setStatus(TaskStatus.PROGRESS);
		newTask.setCreatedAt(new Date());
		tasks.add(newTask);

		return newTask;
	}

	@Override
	public Task update(final Task task) {
		Task taskUpdate = this.index(task.getId());
		taskUpdate.setTitle(task.getTitle());
		taskUpdate.setDescription(task.getDescription());

		return taskUpdate;
	}

	@Override
	public void delete(final Long taskId) {
		Task taskToDelete = this.index(taskId);
		tasks.remove(taskToDelete);
	}

	@Override
	public Task UpdateProgress(TaskProgressDTO taskProgressDTO) {
		Task task = index(taskProgressDTO.getId());

		if (taskProgressDTO.getProgress() > 0 && taskProgressDTO.getProgress() <= 100) {
			task.setProgress(taskProgressDTO.getProgress());
			if (taskProgressDTO.getProgress() == 100) {
				task.setStatus(TaskStatus.COMPLETE);
			}
		}
		return task;
	}


}
