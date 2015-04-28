package main;

import java.util.Collection;

import javax.validation.Valid;

import main.model.Record;
import main.repository.RecordRepository;
import main.model.User;
import main.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // http://localhost:8080/rplatonov/notes
@RequestMapping("{username}/records")
public class RecordsController {


    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping
    Collection<Record> readNotes(@PathVariable String username) {
        return recordRepository.findByUserUsername(username);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void addNote(@PathVariable String username, @Valid @RequestBody Record record) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        record.setUser(user);
        recordRepository.save(record);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNote(@RequestParam Long id) {
        recordRepository.delete(id);
    }


}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("could not find user '" + username + "'; Please, call to support");
    }
}
