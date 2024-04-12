package backend.challenge.modules.task.models;

import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Task {

    private Long id;
    private String title;
    private String description;
    private int progress;
    private TaskStatus status;
    private Date createdAt;
}
