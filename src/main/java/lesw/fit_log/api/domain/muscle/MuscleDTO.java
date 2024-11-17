package lesw.fit_log.api.domain.muscle;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MuscleDTO {
    private int id;
    private String name;
}