package lesw.fit_log.api.domain.workout;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class WorkoutDTO {
    private Long id;
    private String name;
    private Integer muscleId;
}