package embi.taskordion.dataaccess;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WorkLogRepository {
    private static List<WorkLogEntry> entries = new ArrayList<WorkLogEntry>();

    public void create(String userId, String text) {
        var entry = new WorkLogEntry(userId, UUID.randomUUID(), text, LocalDateTime.now());
        entries.add(entry);
    }

    public List<WorkLogEntry> getByUserId(String userId) {
        return entries.stream().filter(e -> e.userId.equals(userId)).collect(Collectors.toList());
    }
}
