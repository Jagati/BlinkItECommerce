package com.lldproject.blinkitecommerce.repositories;

import com.lldproject.blinkitecommerce.models.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIdIn(List<Long> taskIds);
}
