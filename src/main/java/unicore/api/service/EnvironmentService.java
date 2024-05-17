package unicore.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unicore.api.dto.EmailDto;
import unicore.api.entities.Environment;
import unicore.api.entities.User;
import unicore.api.repository.EnvironmentRepository;
import unicore.api.repository.UserRepository;
import unicore.api.senders.MailSender;
import unicore.api.utils.CodeGenerator;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
    private final EnvironmentRepository environmentRepository;
    private final UserRepository userRepository;
    private final UserService userService;

//    public List<Environment> findByName(String name) {
//        return environmentRepository.findByEmail(name);
//    }
    public ResponseEntity<User> createNewEnvironment(String u_email, String env_name) {
        if (userRepository.findByEmail(u_email).get().getEnvironment() != null || environmentRepository.find(env_name, u_email).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Environment env = new Environment(
                env_name,
                u_email
            );
        return ResponseEntity.ok(userService.linkTo(u_email, environmentRepository.save(env)));
    }

    public ResponseEntity<EmailDto> addToEnvironmentRequest(String u_email, String env_name, String env_creatorEmail) {
        if (environmentRepository.findLinkedEnvironment(env_name, u_email, env_creatorEmail).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Environment env = environmentRepository.find(env_name, env_creatorEmail).orElse(null);
        if (env == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String code = CodeGenerator.generateCode();
        env.setAccess_code(code);
        environmentRepository.save(env);
        MailSender.sendNotification(env_creatorEmail, MailSender.NotifyType.USER_ENTRY_TO_ENVIRONMENT);
        MailSender.sendCode(env_creatorEmail, code);
        return ResponseEntity.ok(new EmailDto(env_creatorEmail));
    }

    public ResponseEntity<User> addToEnvironmentConfirm(String u_email, String env_creatorEmail, String env_name, String env_accessCode) {
        User user = userRepository.findByEmail(u_email).get();
        Environment env = environmentRepository.find(env_name, env_creatorEmail).orElse(null);
        if (environmentRepository.findLinkedEnvironment(env_name, u_email, env_creatorEmail).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (env == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!env.getAccess_code().equals(env_accessCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setEnvironment(env);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> exitFromEnvironmentRequest(String u_email, String env_name, String env_creatorEmail) {
        if (environmentRepository.findLinkedEnvironment(env_name, u_email, env_creatorEmail).isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(u_email).get();
        user.setEnvironment(null);
        MailSender.sendNotification(env_creatorEmail, MailSender.NotifyType.USER_EXIT_FROM_ENVIRONMENT);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
