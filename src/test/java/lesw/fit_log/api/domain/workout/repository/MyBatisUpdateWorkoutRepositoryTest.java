package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@Import(MyBatisWorkoutRepository.class)
public class MyBatisUpdateWorkoutRepositoryTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    @DisplayName("운동 이름 수정")
    void updateName() {
        // given
        WorkoutDTO updateWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("벤치프레스1")
                .build();

        // when
        workoutRepository.update(updateWorkout);

        // then
        WorkoutDTO findWorkout = workoutRepository.findById(updateWorkout.getId());

        assertEquals(updateWorkout.getName(), findWorkout.getName());
        assertEquals(1, findWorkout.getMuscleId());
    }

    @Test
    @DisplayName("근육 부위 연관 관계 수정")
    void updateMuscleId() {
        // given
        WorkoutDTO updateWorkout = WorkoutDTO.builder()
                .id(1L)
                .muscleId(2)
                .build();

        // when
        workoutRepository.update(updateWorkout);

        // then
        WorkoutDTO findWorkout = workoutRepository.findById(updateWorkout.getId());

        assertEquals(updateWorkout.getMuscleId(), findWorkout.getMuscleId());
        assertEquals("벤치프레스", findWorkout.getName());
    }

    @Test
    @DisplayName("운동 이름, 근육 부위 모두 수정")
    void updateAll() {
        // given
        WorkoutDTO updateWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("벤치프레스1")
                .muscleId(2)
                .build();

        // when
        workoutRepository.update(updateWorkout);

        // then
        WorkoutDTO findWorkout = workoutRepository.findById(updateWorkout.getId());

        assertEquals(updateWorkout.getName(), findWorkout.getName());
        assertEquals(updateWorkout.getMuscleId(), findWorkout.getMuscleId());
    }

    @Test
    @DisplayName("중복된 운동 이름")
    void failedUpdateDuplicate() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("인클라인 벤치프레스")
                .build();

        // when
        // then
        assertThatThrownBy(() -> workoutRepository.update(updatedWorkout)).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("존재하지 않는 muscle_id 접근")
    void failedUpdateMuscleId() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .muscleId(10)
                .build();

        // when
        // then
        assertThatThrownBy(() ->
                workoutRepository.update(updatedWorkout)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
