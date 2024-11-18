package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@Import(MyBatisWorkoutRepository.class)
public class MyBatisFindWorkoutRepositoryTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        // given
        // when
        List<WorkoutDTO> findWorkouts = workoutRepository.findAll();

        // then
        assertThat(findWorkouts).isNotEmpty();
    }

    @Test
    @DisplayName("운동 id를 사용한 조회")
    void findById() {
        // given
        Long findWorkoutId = 1L;

        // when
        WorkoutDTO findWorkout = workoutRepository.findById(findWorkoutId);

        // then
        assertEquals(findWorkoutId, findWorkout.getId());
    }

    @Test
    @DisplayName("단건 운동 조회 실패")
    void failedFindById() {
        // given
        Long findWorkoutId = -1L;

        // when
        // then
        assertThatThrownBy(() ->
                workoutRepository.findById(findWorkoutId)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("근육 부위 별 운동 조회")
    void findByMuscleId() {
        // given
        Long findMuscleId = 1L;

        // when
        List<WorkoutDTO> findWorkoutsByMuscleId = workoutRepository.findByMuscleId(findMuscleId);

        // then
        assertThat(findWorkoutsByMuscleId).isNotEmpty();
    }

    @Test
    @DisplayName("근육 부위 별 운동 조회 실패")
    void failedFindByMuscleId() {
        // given

        // when
        // then
        assertThatThrownBy(() ->
                workoutRepository.findByMuscleId(-1L)).isInstanceOf(IllegalArgumentException.class);
    }
}
