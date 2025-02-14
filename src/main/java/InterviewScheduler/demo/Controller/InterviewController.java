package InterviewScheduler.demo.Controller;

import InterviewScheduler.demo.Repository.InterviewRepository;
import InterviewScheduler.demo.Service.InterviewService;
import InterviewScheduler.demo.Entity.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "http://localhost:8082")

public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private InterviewRepository interviewRepository;

    // POST /api/interviews
    @PostMapping("/sch")
    public Interview saveInterview(@RequestBody Interview interview) {
        return interviewService.saveInterview(interview);
    }


    // GET /api/interviews
    @GetMapping
    public List<Interview> getAllInterviews() {
        return interviewService.getAllInterviews();
    }
}
