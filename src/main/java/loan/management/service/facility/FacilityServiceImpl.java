package loan.management.service.facility;

import loan.management.constant.LoanConstants;
import loan.management.model.entity.Applicant;
import loan.management.model.entity.Facility;
import loan.management.repository.ApplicantRepository;
import loan.management.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import loan.management.model.dto.request.facility.FacilityRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService{

    private final FacilityRepository facilityRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    @Override
    public void registerFacility(FacilityRequest facilityRequest) {
        Applicant applicant = applicantRepository.findByIdentityCode(facilityRequest.getIdentityCode());

        if (Objects.isNull(applicant)){
            applicant = Applicant.builder()
                    .firstName(facilityRequest.getFirstName())
                    .lastName(facilityRequest.getLastName())
                    .identityCode(facilityRequest.getIdCode())
                    .build();
            applicant = applicantRepository.save(applicant);
        }

        Facility facility = Facility.builder()
                .applicant(applicant)
                .currency(facilityRequest.getCurrency())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(LoanConstants.EXPIRED_TIME))
                .number("201928384")
                .totalLimit(BigDecimal.valueOf(100000000))
                .build();
        facilityRepository.save(facility);


    }
}
