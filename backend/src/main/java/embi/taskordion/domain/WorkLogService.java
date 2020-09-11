package embi.taskordion.domain;

import embi.taskordion.dataaccess.WorkLogEntry;
import embi.taskordion.dataaccess.WorkLogRepository;
import embi.taskordion.domain.commands.CreateNewWorklog;
import embi.taskordion.domain.commands.GetWorklogEntriesForUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Console;
import java.util.List;

@Component
@Slf4j
public class WorkLogService {

    private final WorkLogRepository repository;

    public WorkLogService(WorkLogRepository repository) {
        this.repository = repository;
    }

    public void handle(String userId, CreateNewWorklog command) {
        repository.create(userId, command.getText());
        System.out.println(command.getText());
    }

    public List<WorkLogEntry> handle(GetWorklogEntriesForUser command) {
        return repository.getByUserId(command.getUserId());
    }
}
