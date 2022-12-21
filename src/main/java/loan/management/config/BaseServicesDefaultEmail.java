package loan.management.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("base-services.mail")
public class BaseServicesDefaultEmail {
    @Value("${base.service.api.mail.testing.mode.enable}")
    private boolean isEnableTestingMode;

    private String from;

    private String fromAlias;

    private List<InternalServicesEmail> emailTo = new ArrayList<>();

    private List<InternalServicesEmail> emailCCList = new ArrayList<>();

    @Getter
    @Setter
    public static class InternalServicesEmail {
        private String address;
        private String name;
    }
}

