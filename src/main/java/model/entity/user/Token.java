package model.entity.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.AuditedEntity;
import model.entity.user.User.UserBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "Token")
public class Token extends AuditedEntity{

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private User user;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String refreshToken;
	
	@Column(nullable = false)
	private String expiresIn;
	
}
