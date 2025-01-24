package InterviewScheduler.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "panel_members")
public class PanelMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(name = "tech_team", nullable = false)
    private String techTeam;

    // Constructors
    public PanelMember() {}

    public PanelMember(String email, String name, String phone, String techTeam) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.techTeam = techTeam;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTechTeam() {
        return techTeam;
    }

    public void setTechTeam(String techTeam) {
        this.techTeam = techTeam;
    }
}
