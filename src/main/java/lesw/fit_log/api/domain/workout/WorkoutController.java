package lesw.fit_log.api.domain.workout;

import lesw.fit_log.api.response.RestResponse;
import lesw.fit_log.api.domain.workout.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    @GetMapping("/workout")
    public ResponseEntity<RestResponse<?>> getWorkout(@RequestParam(required = false) Long id,
                                                      @RequestParam(required = false) Long muscle_id) {
        /* 특정 운동 종목 조회 */
        if (id != null) {
            WorkoutDTO findWorkout = workoutRepository.findById(id);
            RestResponse<WorkoutDTO> restResponse = RestResponse.of(HttpStatus.OK, "운동 조회 성공", findWorkout);
            return new ResponseEntity<>(restResponse, restResponse.getStatus());
        }

        List<WorkoutDTO> findWorkouts;
        if (muscle_id != null) {
            findWorkouts = workoutRepository.findByMuscleId(muscle_id);
        } else {
            findWorkouts = workoutRepository.findAll();
        }

        RestResponse<List<WorkoutDTO>> restResponse = RestResponse.of(HttpStatus.OK, "운동 조회 성공", findWorkouts);
        return new ResponseEntity<>(restResponse, restResponse.getStatus());
    }

    @PostMapping("/admin/workout")
    public ResponseEntity<RestResponse<?>> postWorkout(@RequestBody WorkoutDTO workout) {
        Long savedKey = workoutRepository.save(workout);
        WorkoutDTO findWorkout = workoutRepository.findById(savedKey);

        RestResponse<WorkoutDTO> restResponse = RestResponse.of(HttpStatus.OK, "운동 종류 추가 성공", findWorkout);
        return new ResponseEntity<>(restResponse, restResponse.getStatus());
    }

    @PutMapping("/admin/workout")
    public ResponseEntity<RestResponse<?>> putWorkout(@RequestBody WorkoutDTO workout) {
        workoutRepository.update(workout);

        RestResponse<Object> restResponse = RestResponse.of(HttpStatus.OK, "운동 수정 성공", null);
        return new ResponseEntity<>(restResponse, restResponse.getStatus());
    }

    @DeleteMapping("/admin/workout")
    public ResponseEntity<RestResponse<?>> deleteWorkout(@RequestParam Long id) {
        workoutRepository.delete(id);

        RestResponse<Object> restResponse = RestResponse.of(HttpStatus.OK, "운동 삭제 성공", null);
        return new ResponseEntity<>(restResponse, restResponse.getStatus());
    }
}