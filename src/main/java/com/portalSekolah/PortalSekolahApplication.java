package com.portalSekolah;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.portalSekolah.entity.RolePermission;
import com.portalSekolah.repository.RolePermissionRepository;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({ "com.portalSekolah" })
public class PortalSekolahApplication {

	@Autowired
	RolePermissionRepository rolePermissionRepository;

	public static void main(String[] args) {
		SpringApplication.run(PortalSekolahApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		List<RolePermission> roles = Arrays.asList(createRole("ADMIN", "Admin Role"), createRole("USER", "User Role"),
				createRole("SISWA", "Siswa Role"), createRole("GURU", "Guru Role"));
		rolePermissionRepository.saveAll(roles);
	}

	RolePermission createRole(String roleCode, String desc) {
		RolePermission role = new RolePermission();
		role.setRoleCode(roleCode);
		role.setRoleDescription(desc);
		return role;
	}

}
