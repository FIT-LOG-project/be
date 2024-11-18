package lesw.fit_log.api.domain.workout.repository.mapper;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import lesw.fit_log.api.domain.workout.repository.WorkoutMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class UpdateWorkoutMapperTest {

    @Autowired
    WorkoutMapper workoutMapper;

    @Test
    @DisplayName("운동 이름 변경")
    void updateName() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("가슴밀기")
                .build();

        workoutMapper.update(updatedWorkout);

        // when
        WorkoutDTO findWorkout = workoutMapper.findById(1L);

        // then
        assertEquals(updatedWorkout.getName(), findWorkout.getName());
        assertEquals(1, findWorkout.getMuscleId());
    }

    @Test
    @DisplayName("해당 운동의 근육 부위 변경")
    void updateMuscleId() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .muscleId(2)
                .build();

        workoutMapper.update(updatedWorkout);

        // when
        WorkoutDTO findWorkout = workoutMapper.findById(1L);

        // then
        assertEquals("벤치프레스", findWorkout.getName());
        assertEquals(updatedWorkout.getMuscleId(), findWorkout.getMuscleId());
    }

    @Test
    @DisplayName("운동 이름, 부위 변경")
    void updateAll() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("가슴밀기")
                .muscleId(2)
                .build();

        workoutMapper.update(updatedWorkout);

        // when
        WorkoutDTO findWorkout = workoutMapper.findById(1L);

        // then
        assertEquals(updatedWorkout.getName(), findWorkout.getName());
        assertEquals(updatedWorkout.getMuscleId(), findWorkout.getMuscleId());
    }

    @Test
    @DisplayName("중복되는 운동 이름")
    void updateFailed() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .name("인클라인 벤치프레스")
                .build();

        // when
        // then
        assertThatThrownBy(() -> workoutMapper.update(updatedWorkout)).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("존재하지 않는 muscle_id 접근")
    void FailedUpdateMuscleId() {
        // given
        WorkoutDTO updatedWorkout = WorkoutDTO.builder()
                .id(1L)
                .muscleId(10)
                .build();

        // when
        // then
        assertThatThrownBy(() ->
                workoutMapper.update(updatedWorkout)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
