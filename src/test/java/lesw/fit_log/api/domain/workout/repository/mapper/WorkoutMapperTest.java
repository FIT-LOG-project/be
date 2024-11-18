package lesw.fit_log.api.domain.workout.repository.mapper;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import lesw.fit_log.api.domain.workout.repository.WorkoutMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@MybatisTest
class WorkoutMapperTest {

    @Autowired
    private WorkoutMapper workoutMapper;

    @Test
    @DisplayName("운동 추가 성공")
    void save() {
        // given
        WorkoutDTO workout = WorkoutDTO.builder()
                .name("가슴 밀어버리기")
                .muscleId(1)
                .build();

        // when
        Long savedKey = workoutMapper.save(workout);

        // then
        assertEquals(1, savedKey);
    }

    @Test
    @DisplayName("운동 이름 중복으로 추가 실패")
    void FailedSaveDuplicate() {
        // given
        WorkoutDTO workout = WorkoutDTO.builder()
                .name("벤치프레스")
                .muscleId(1)
                .build();

        // when
        // then
        assertThatThrownBy(() -> workoutMapper.save(workout)).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("존재하지 않는 muscle_id와 연관된 운동 추가")
    void FailedSaveIncorrectMuscleId() {
        // given
        WorkoutDTO workout = WorkoutDTO.builder()
                .name("test")
                .muscleId(10)
                .build();

        // when
        // then
        assertThatThrownBy(() -> workoutMapper.save(workout)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("운동 삭제 성공")
    void delete() {
        // given
        Long deleteId = 1L;
        workoutMapper.delete(deleteId);

        // when
        WorkoutDTO findWorkout = workoutMapper.findById(deleteId);

        // then
        assertNull(findWorkout);
    }
}