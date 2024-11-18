package lesw.fit_log.api.domain.muscle;

import lesw.fit_log.api.domain.muscle.repository.MuscleMapper;
import lesw.fit_log.api.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RestResponse<List<MuscleDTO>>> getMuscle() {
        List<MuscleDTO> findMuscles = muscleMapper.findAll();

        RestResponse<List<MuscleDTO>> restResponse = RestResponse.of(HttpStatus.OK, "전체 조회 성공", findMuscles);
        return new ResponseEntity<>(restResponse, restResponse.getStatus());
    }

}