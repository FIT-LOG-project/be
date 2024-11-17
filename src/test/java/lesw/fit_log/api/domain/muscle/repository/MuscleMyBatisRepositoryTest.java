package lesw.fit_log.api.domain.muscle.repository;

import lesw.fit_log.api.domain.muscle.MuscleDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest
class MuscleMyBatisRepositoryTest {

    @Autowired
    private MuscleMapper muscleMapper;

    @Test
    void findById() {
        MuscleDTO findMuscle = muscleMapper.findById(1);

        assertEquals(1, findMuscle.getId());
    }

    @Test
    void findAll() {
        // given
        // when
        List<MuscleDTO> findMuscles = muscleMapper.findAll();

        // then
        assertEquals(5, findMuscles.size());
    }
}