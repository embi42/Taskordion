package embi.taskordion;

import embi.taskordion.dataaccess.WorkLogEntry;
import embi.taskordion.domain.WorkLogService;
import embi.taskordion.domain.commands.CreateNewWorklog;
import embi.taskordion.domain.commands.GetWorklogEntriesForUser;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {
    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
    private WorkLogService service;

    public BackendController(WorkLogService service) {
        this.service = service;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/worklog", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void handle(
            @RequestHeader("userId") String userId, @RequestBody CreateNewWorklog command) {
        service.handle(userId, command);
    }

    @RequestMapping(path = "/worklog", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<WorkLogEntry> handle(@RequestHeader("userId") String userId) {
        GetWorklogEntriesForUser command = new GetWorklogEntriesForUser(userId);
        return service.handle(command);
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}
