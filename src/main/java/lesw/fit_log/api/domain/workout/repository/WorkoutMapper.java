package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkoutMapper {

    Long save(WorkoutDTO workout);

    List<WorkoutDTO> findByMuscleId(Long id);

    List<WorkoutDTO> findAll();

    WorkoutDTO findById(Long id);

    void update(WorkoutDTO workout);

    void delete(Long id);

}
