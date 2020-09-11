package embi.taskordion;

import embi.taskordion.dataaccess.WorkLogRepository;
import embi.taskordion.domain.WorkLogService;
import embi.taskordion.domain.commands.CreateNewWorklog;
import lombok.var;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class WorkLogTests {
    @Test
    void testCreateWorkLog()  {
        String userId = "userId";
        String text = "dummy Text";
        LocalDateTime timestamp = LocalDateTime.now();
        var command = new CreateNewWorklog(text, "");
        var repository = new WorkLogRepository();
        var service = new WorkLogService(repository);

        service.handle(userId, command);

        var entriesForUser = repository.getByUserId(userId);
        Assert.assertEquals(1, entriesForUser.size());
    }
}
