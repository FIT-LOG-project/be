package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;

import java.util.List;

public interface WorkoutRepository {

    Long save(WorkoutDTO workout);

    List<WorkoutDTO> findAll();

    WorkoutDTO findById(Long id);

    List<WorkoutDTO> findByMuscleId(Long id);

    void update(WorkoutDTO workout);

    void delete(Long id);
}
