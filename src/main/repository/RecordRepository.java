package main.repository;

import java.util.Collection;

import main.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("recordRepository")
public interface RecordRepository extends JpaRepository<Record, Long> {
    Collection<Record> findByUserUsername(String username);

}
