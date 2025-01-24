package InterviewScheduler.demo.Repository;

import InterviewScheduler.demo.Entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterviewRepository extends JpaRepository<Interview,Long> {

}







