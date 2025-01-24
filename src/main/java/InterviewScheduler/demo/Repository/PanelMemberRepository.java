package InterviewScheduler.demo.Repository;

import InterviewScheduler.demo.Entity.PanelMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanelMemberRepository extends JpaRepository<PanelMember, Long> {
    List<PanelMember> findByNameIn(List<String> names);
}

