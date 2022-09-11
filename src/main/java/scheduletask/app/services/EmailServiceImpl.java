package scheduletask.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import scheduletask.app.models.entity.Task;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private static final String NO_REPLY = "noreply@gmail.com";
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendMail(Task task) {
        String funcName = this.getClass().getName() + ".sendMail()";
        log.info("Execute: {}", funcName);

        try {
            log.info("Preparing the mail");
            Context context = new Context();
            context.setVariable("task", task);

            String process = templateEngine.process("mail-template.html", context);
            log.info("Load mail template from resources: /resources/templates/mail-template.html");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("Recordatorio para " + task.getUsername());
            mimeMessageHelper.setText(process, true);
            mimeMessageHelper.setFrom(NO_REPLY);
            mimeMessageHelper.setTo(task.getUsername());
            log.info("Sending email to: " + task.getUsername());
            javaMailSender.send(mimeMessage);
            log.info("Mail sent successfully");
        } catch (MailException e) {
            log.error("ERROR: an error occurred while sending the mail");
            log.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            log.error("ERROR: an error occurred while sending the mail");
            log.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
