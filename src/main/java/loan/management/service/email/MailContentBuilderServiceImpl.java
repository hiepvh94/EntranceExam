package loan.management.service.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@AllArgsConstructor
public class MailContentBuilderServiceImpl implements IMailContentBuilderService{

    private final TemplateEngine templateEngine;

    @Override
    public String buildMailContent(String templateName, Map<String, Object> properties) {
        Context context = new Context();
        for (String key : properties.keySet()) {
            context.setVariable(key, properties.get(key));
        }
        return templateEngine.process(templateName, context);
    }
}