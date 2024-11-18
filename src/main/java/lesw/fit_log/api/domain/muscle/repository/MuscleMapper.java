package lesw.fit_log.api.domain.muscle.repository;

import lesw.fit_log.api.domain.muscle.MuscleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MuscleMapper {

    MuscleDTO findById(int id);

    List<MuscleDTO> findAll();

}