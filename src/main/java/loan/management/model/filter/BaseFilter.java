package loan.management.model.filter;

import loan.management.model.dto.request.user.SortByUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BaseFilter {
    private Integer size;
    private Integer page;
    private Boolean isDescending;
    private Sort.Direction orderBy;
    private String sortBy;
    private SortByUser sortByUser;
}
