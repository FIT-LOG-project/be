package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisWorkoutRepository implements WorkoutRepository{

    private final WorkoutMapper workoutMapper;

    @Override
    public Long save(WorkoutDTO workout) {
        return workoutMapper.save(workout);
    }

    @Override
    public List<WorkoutDTO> findAll() {
        return workoutMapper.findAll();
    }

    @Override
    public WorkoutDTO findById(Long id) {
        WorkoutDTO findWorkout = workoutMapper.findById(id);

        if (findWorkout == null) {
            throw new IllegalArgumentException("Not Found id = " + id);
        }

        return findWorkout;
    }

    @Override
    public List<WorkoutDTO> findByMuscleId(Long id) {

        List<WorkoutDTO> findWorkoutsByMuscle = workoutMapper.findByMuscleId(id);

        if (findWorkoutsByMuscle.isEmpty()) {
            throw new IllegalArgumentException("Incorrect muscle id = " + id);
        }

        return findWorkoutsByMuscle;
    }

    @Override
    public void update(WorkoutDTO workout) {
        try {
            workoutMapper.update(workout);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Duplicate Name = " + workout.getName());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("out of range muscle_id = " + workout.getMuscleId());
        }
    }

    @Override
    public void delete(Long id) {
        workoutMapper.delete(id);
    }
}
