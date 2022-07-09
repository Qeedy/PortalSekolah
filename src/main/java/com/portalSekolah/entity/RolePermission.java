package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class RolePermission implements GrantedAuthority {

	private static final long serialVersionUID = -7108776038869849264L;

	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;
	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@Override
	public String getAuthority() {
		return roleCode;
	}

}
