package com.example.smarttask.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smarttask.models.Task;
import com.example.smarttask.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Créer une tâche
    public Task createTask(Task task) {
        task.setStatus("TODO"); // statut par défaut
        return taskRepository.save(task);
    }

    // Lister toutes les tâches
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Trouver une tâche par ID
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable !"));
    }

    // Modifier une tâche
    public Task updateTask(String id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    // Supprimer une tâche
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    // Changer le statut
    public Task updateStatus(String id, String status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    // Assigner à un utilisateur
    public Task assignTask(String taskId, String userId) {
        Task task = getTaskById(taskId);
        task.setAssignedTo(userId);
        return taskRepository.save(task);
    }
}