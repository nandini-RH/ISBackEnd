package InterviewScheduler.demo.config;

import InterviewScheduler.demo.Entity.PanelMember;
import InterviewScheduler.demo.Repository.PanelMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Autowired
    private PanelMemberRepository panelMemberRepository;

    @Bean
    public CommandLineRunner seedPanelMembers(PanelMemberRepository repository) {
        return args -> {
            if (repository.count() == 0) { // Check if the table is empty
                repository.save(new PanelMember("vaibhav@example.com", "Vaibhav", "1234567890", "Java Team"));
                repository.save(new PanelMember("shivangi@example.com", "Shivangi", "9876543210", "Python Team"));
                repository.save(new PanelMember("jatanianandini@gmail.com", "Nandini", "1122334455", "Frontend Team"));
                repository.save(new PanelMember("aniket@example.com", "Aniket", "2233445566", "JavaScript Team"));
                repository.save(new PanelMember("dravid@example.com", "Dravid", "3344556677", "DevOps Team"));
                repository.save(new PanelMember("roshan@example.com", "Roshan", "4455667788", "AI Team"));
            }
        };
    }
}
