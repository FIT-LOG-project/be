package lesw.fit_log.api.domain.muscle;

import lesw.fit_log.api.domain.muscle.repository.MuscleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MuscleController {

    private final MuscleMapper muscleMapper;

    @GetMapping("/muscle")
    public List<MuscleDTO> getMuscle() {
        return muscleMapper.findAll();
    }

}