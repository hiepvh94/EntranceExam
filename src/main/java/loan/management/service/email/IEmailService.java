package loan.management.service.email;

import java.util.Map;

public interface IEmailService {
    boolean sendEmail(String[] recipients, String[] mailCCList, String subject, String templateUrl, Map<String, Object> inputs);
}