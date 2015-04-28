package main;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import main.model.Record;
import main.repository.RecordRepository;
import main.model.User;
import main.repository.UserRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {
    @Bean
    CommandLineRunner init(UserRepository userRepository, RecordRepository recordRepository) {
    	return (evt) -> {
	    	User user = new User();
			generateUser(user);
	    	userRepository.save(user);
	    	Record record = new Record();
	    	record.setUser(user);
	    	record.setText("test note");
	    	recordRepository.save(record);
	    	
	    	user = new User();
	    	user.setUsername("jetbrains");
	    	user.setPassword("222");
	    	userRepository.save(user);
	    	record = new Record();
	    	record.setUser(user);
	    	record.setText("jetbrains note");
	    	recordRepository.save(record);
    	};
    }

	private void generateUser(User user) {
		user.setUsername("rplatonov");
		user.setPassword("111");
	}

	public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }
}
