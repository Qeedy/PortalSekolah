package com.portalSekolah.repository;

import org.springframework.data.repository.CrudRepository;

import com.portalSekolah.entity.RolePermission;


public interface RolePermissionRepository extends CrudRepository<RolePermission, String> {
	RolePermission findByRoleCode(String roleCode);
}
