//package InterviewScheduler.demo;
//
//import InterviewScheduler.demo.Entity.Interview;
//import jakarta.persistence.PostPersist;
//import jakarta.persistence.PrePersist;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class RHInterviewIdGenerator {
//
//    private static final String PREFIX = "RH";
//    private AtomicInteger counter = new AtomicInteger(0);
//
//    @PrePersist
//    @PostPersist
//    public void generateId(Interview interview) {
//        String id = interview.getId();
//        if (id == null || id.isEmpty()) { // Generate ID only if not already set
//            int incrementedId = counter.incrementAndGet();
//            String formattedId = PREFIX + String.format("%02d", incrementedId);
//            interview.setId(formattedId);
//        }
//    }
//}
