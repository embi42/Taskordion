package embi.taskordion.dataaccess;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class WorkLogEntry {
    public final String userId;
    public final UUID id;
    public final String text;
    public final LocalDateTime timestamp;
}
