package InterviewScheduler.demo.Entity;
import InterviewScheduler.demo.Repository.InterviewRepository;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

//commit try 2
@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(generator = "custom-sequence-generator")
    @GenericGenerator(name = "custom-sequence-generator", strategy = "InterviewScheduler.demo.util.CustomIdGenerator")

    private String rh01;




    @Column(name = "emp_name", nullable = false)
    private String empName;

    @Column(name = "recruiter_email", nullable = false)
    private String recMail;

    @Column(name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "candidate_email", nullable = false)
    private String candidateEmail;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "interview_type", nullable = false)
    private String interviewType;

    @Column(name = "interview_panel", nullable = false)
    private String interviewPanel;

    @Column(name = "candidate_instructions")
    private String candidateInstructions;

    @Column(name = "link")
    private String link;

    @Column(name = "round_number", nullable = false)
    private Integer roundNumber;

    @Column(name = "location")
    private String location;



    // Getters and Setters

    public String getRh01() {
        return rh01;
    }

    public void setRh01(String rh01) {
        this.rh01 = rh01;
    }

    public String getRecMail() {
        return recMail;
    }

    public void setRecMail(String recMail){
        this.recMail=recMail;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link=link;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }



    public String getInterviewPanel() {
        return interviewPanel;
    }

    public void setInterviewPanel(String interviewPanel) {
        this.interviewPanel = interviewPanel;
    }

    public String getCandidateInstructions() {
        return candidateInstructions;
    }

    public void setCandidateInstructions(String candidateInstructions) {
        this.candidateInstructions = candidateInstructions;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }


}
