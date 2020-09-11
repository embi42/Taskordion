package embi.taskordion.domain.commands;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreateNewWorklog {
    private final String text;
    private final String dummy;
}
