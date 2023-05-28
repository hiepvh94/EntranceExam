package model.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserFilter extends BaseFilter{
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fromRequestDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date toRequestDate;
    RoleEnums role;
}
