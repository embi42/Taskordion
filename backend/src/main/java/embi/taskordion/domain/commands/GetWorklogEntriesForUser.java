package embi.taskordion.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetWorklogEntriesForUser {
    @Getter
    public final String userId;
}
