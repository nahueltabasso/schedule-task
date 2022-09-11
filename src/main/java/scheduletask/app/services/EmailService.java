package scheduletask.app.services;

import scheduletask.app.models.entity.Task;

public interface EmailService {

    public void sendMail(Task task);
}
