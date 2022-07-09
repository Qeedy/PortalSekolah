package com.portalSekolah.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.portalSekolah.enums.UserRole;
import com.portalSekolah.model.ModelUserRegistration;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value = "all")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = -2897532802294601009L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	@Column
	private String namaDepan;
	@Column
	private String namaBelakang;
	@Column
	private String gender;
	@Column
	private String emailAddress;
	@Column
	private String alamat;
	@Column
	private String noTelp;
	
	private Boolean enabled = false;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "AUTH_USER_AUTHORITY")
	private List<RolePermission> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public User(ModelUserRegistration model, String encryptedPassword) {
		super();
		this.username = model.username();
		this.password = encryptedPassword;
		this.namaDepan = model.firstName();
		this.namaBelakang = model.lastName();
		this.gender = model.gender();
		this.emailAddress = model.emailAddress();
		this.alamat = model.address();
		this.noTelp = model.phoneNumber();
	}

}
