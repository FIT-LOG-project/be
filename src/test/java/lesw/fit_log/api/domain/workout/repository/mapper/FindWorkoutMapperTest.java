package lesw.fit_log.api.domain.workout.repository.mapper;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import lesw.fit_log.api.domain.workout.repository.WorkoutMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@MybatisTest
public class FindWorkoutMapperTest {

    @Autowired
    WorkoutMapper workoutMapper;

    @Test
    @DisplayName("운동 전체 조회")
    void findAll() {
        // given
        // when
        List<WorkoutDTO> workouts = workoutMapper.findAll();

        // then
        assertThat(workouts).isNotEmpty();
    }

    @Test
    @DisplayName("근육 부위 별 운동 조회")
    void findByMuscleId() {
        // given
        // when
        List<WorkoutDTO> findWorkoutsByMuscleId = workoutMapper.findByMuscleId(1L);

        assertThat(findWorkoutsByMuscleId).isNotEmpty();
    }

    @Test
    @DisplayName("근육 부위 id를 사용한 조회 실패")
    void failedFindByMuscleId() {
        // given
        // when
        List<WorkoutDTO> findWorkoutsByMuscleId = workoutMapper.findByMuscleId(-1L);

        // then
        assertThat(findWorkoutsByMuscleId).isEmpty();
    }

    @Test
    @DisplayName("단건 운동 조회")
    void findById() {
        // given
        // when
        WorkoutDTO findWorkout = workoutMapper.findById(1L);

        // then
        assertNotNull(findWorkout);
    }

    @Test
    @DisplayName("단건 조회 실패")
    void failedFindById() {
        WorkoutDTO findWorkout = workoutMapper.findById(-1L);

        assertNull(findWorkout);
    }
}
