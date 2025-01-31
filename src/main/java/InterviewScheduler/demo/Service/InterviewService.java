package InterviewScheduler.demo.Service;

import InterviewScheduler.demo.Entity.Interview;
import InterviewScheduler.demo.Entity.Logs;
import InterviewScheduler.demo.Repository.InterviewRepository;
import InterviewScheduler.demo.Repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private LogsRepository logsRepository;



    // Save a new interview

    public Interview saveInterview(Interview interview) {
        Interview savedInterview = interviewRepository.save(interview);

        // Create a log entry
        Logs log = new Logs();
        log.setCandidateName(interview.getCandidateName());
        log.setRoundNumber(interview.getRoundNumber());
        log.setAction("CREATE");
        log.setCreatedBy(interview.getEmpName());
        log.setTimestamp(LocalDateTime.now());
        logsRepository.save(log);

        // Send emails concurrently
        sendEmails(savedInterview);

        return savedInterview;
    }

    @Async
    public CompletableFuture<Void> sendEmails(Interview interview) {
        CompletableFuture<Void> candidateEmailFuture = CompletableFuture.runAsync(() -> sendCandidateEmail(interview));
        CompletableFuture<Void> recruiterEmailFuture = CompletableFuture.runAsync(() -> sendRecruiterEmail(interview));
//        CompletableFuture<Void> panelEmailsFuture = CompletableFuture.runAsync(() -> sendPanelEmails(interview));


        return CompletableFuture.allOf(candidateEmailFuture, recruiterEmailFuture); //, panelEmailsFuture

    }


    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }




    private void sendCandidateEmail(Interview interview) {
        SimpleMailMessage candidateMessage = new SimpleMailMessage();
        candidateMessage.setTo(interview.getCandidateEmail());
        candidateMessage.setSubject("Interview Scheduled for " + interview.getCandidateName());
        candidateMessage.setText(createEmailBody(interview, true));

        mailSender.send(candidateMessage);
    }

    private void sendRecruiterEmail(Interview interview) {
        SimpleMailMessage recruiterMessage = new SimpleMailMessage();
        recruiterMessage.setTo(interview.getRecMail());
        recruiterMessage.setSubject("Interview Scheduled for " + interview.getCandidateName());
        recruiterMessage.setText(createEmailBody(interview, false));

        mailSender.send(recruiterMessage);
    }

    private String createEmailBody(Interview interview, boolean isCandidate) {
        StringBuilder emailBody = new StringBuilder();

        if (isCandidate) {
            emailBody.append("Dear ").append(interview.getCandidateName()).append(",\n\n")
                    .append("Your interview is scheduled as follows:").append("\n\n");

            emailBody.append("Round: ").append(interview.getRoundNumber()).append("\n")
                    .append("Time: ").append(interview.getScheduledDate().toLocalTime()).append("\n");

            if (interview.getRole() != null && !interview.getRole().isEmpty()) {
                emailBody.append("Role: ").append(interview.getRole()).append("\n");
            }
            if (interview.getLocation() != null && !interview.getLocation().isEmpty()) {
                emailBody.append("Location: ").append(interview.getLocation()).append("\n");
            }
            if (interview.getPhone() != null && !interview.getPhone().isEmpty()) {
                emailBody.append("Phone Number: ").append(interview.getPhone()).append("\n");
            }
            if (interview.getLink() != null && !interview.getLink().isEmpty()) {
                emailBody.append("Link: ").append(interview.getLink()).append("\n");
            }

            if (interview.getCandidateInstructions() != null && !interview.getCandidateInstructions().isEmpty()) {
                emailBody.append("Instructions for Candidate: ").append(interview.getCandidateInstructions()).append("\n");
            }

        } else {
            emailBody.append("Interview for ").append(interview.getCandidateName()).append(", has been scheduled as follows:\n\n");
            emailBody.append("Round: ").append(interview.getRoundNumber()).append("\n")
                    .append("Time: ").append(interview.getScheduledDate().toLocalTime()).append("\n");

            if (interview.getInterviewPanel() != null && !interview.getInterviewPanel().isEmpty()){
                emailBody.append("Interview Panel: ").append(interview.getInterviewPanel()).append("\n");
            }

            if (interview.getRole() != null && !interview.getRole().isEmpty()) {
                emailBody.append("Role: ").append(interview.getRole()).append("\n");
            }
            if (interview.getLocation() != null && !interview.getLocation().isEmpty()) {
                emailBody.append("Location: ").append(interview.getLocation()).append("\n");
            }
            if (interview.getPhone() != null && !interview.getPhone().isEmpty()) {
                emailBody.append("Phone Number: ").append(interview.getPhone()).append("\n");
            }
            if (interview.getLink() != null && !interview.getLink().isEmpty()) {
                emailBody.append("Link: ").append(interview.getLink()).append("\n");
            }


        }


        emailBody.append("\n\nBest regards,\nRippleHire Team");
        return emailBody.toString();
    }


}
