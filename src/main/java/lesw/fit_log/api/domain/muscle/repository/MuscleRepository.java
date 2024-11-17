package lesw.fit_log.api.domain.muscle.repository;

import lesw.fit_log.api.domain.muscle.MuscleDTO;

import java.util.List;

public interface MuscleRepository {

    MuscleDTO findById(int id);

    List<MuscleDTO> findAll();

}