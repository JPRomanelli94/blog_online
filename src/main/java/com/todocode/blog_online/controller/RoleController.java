package com.todocode.blog_online.controller;

import com.todocode.blog_online.model.Permission;
import com.todocode.blog_online.model.Role;
import com.todocode.blog_online.service.IPermissionService;
import com.todocode.blog_online.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Role> getRolesById(@PathVariable Long id){
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRoles(@RequestBody Role role){

        //Recuperar el/los permisos validos
        Set<Permission> validPermissions = role.getPermissionList().stream()
                .map(permission -> permissionService.findById(permission.getId()).orElse(null))
                .filter(Objects::nonNull)
                .map(Permission.class::cast)
                .collect(Collectors.toSet());

        if (validPermissions.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("error","El rol debe tener al menos un permiso valido"));
        }

        //seteamos los permisos validos
        role.setPermissionList(validPermissions);
        roleService.save(role);

        return ResponseEntity.ok(role);

    }

    //Agregamos end-point de UPDATE, utilizando Patch
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {

        Role rol = roleService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol ID " + role.getRole_id() + " no existe"));

        if (rol!=null) {
            for (Permission p : role.getPermissionList()) {
                Permission existingPermission = permissionService.findById(p.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permiso ID " + p.getId() + " no existe"));
                rol.getPermissionList().add(existingPermission); // agrega sin duplicar si es Set
            }

        }

        roleService.update(rol);
        return ResponseEntity.ok(rol);

    }

}
