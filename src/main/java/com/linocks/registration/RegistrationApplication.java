package com.linocks.registration;

import com.linocks.registration.model.Users;
import com.linocks.registration.repository.UserRepository;
import com.linocks.registration.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class RegistrationApplication {

    private UserService userService;

    public RegistrationApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("running");
            userService.saveUser(new Users("Sam", "Son", "sam@gmail.com", "157 Flower Street"));
            userService.saveUser(new Users("Jacob", "Son", "Jacob@gmail.com", "157 Flower Street"));
            userService.saveUser(new Users("Isaac", "Son", "Isaac@gmail.com", "157 Flower Street"));
            userService.saveUser(new Users("Nick", "Son", "Nick@gmail.com", "157 Flower Street"));
        };
    }

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }
}
