package lesw.fit_log.api.domain.workout.repository;

import lesw.fit_log.api.domain.workout.WorkoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@Import(MyBatisWorkoutRepository.class)
class MyBatisWorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    @DisplayName("운동 추가")
    void save() {
        // given
        WorkoutDTO workout = WorkoutDTO.builder()
                .name("하체 밀어버리기")
                .muscleId(3)
                .build();

        // when
        Long savedKey = workoutRepository.save(workout);

        // then
        assertEquals(1, savedKey);
    }

    @Test
    @DisplayName("운동 삭제")
    void delete() {
        // given
        Long deleteId = 1L;

        // when
        workoutRepository.delete(deleteId);

        // then
        assertThatThrownBy(() -> workoutRepository.findById(deleteId)).isInstanceOf(IllegalArgumentException.class);
    }
}