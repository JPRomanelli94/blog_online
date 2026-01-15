package com.todocode.blog_online.security.config;

import com.todocode.blog_online.model.Permission;
import com.todocode.blog_online.model.Role;
import com.todocode.blog_online.model.UserSec;
import com.todocode.blog_online.repository.IPermissionRepository;
import com.todocode.blog_online.repository.IRoleRepository;
import com.todocode.blog_online.repository.IUserSecRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner bootstrapData(
            IPermissionRepository permissionRepo,
            IRoleRepository roleRepo,
            IUserSecRepository userRepo,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // 1️⃣ PERMISOS
            Permission read = permissionRepo.findByPermissionName("READ")
                    .orElseGet(() -> permissionRepo.save(new Permission("READ")));

            Permission create = permissionRepo.findByPermissionName("CREATE")
                    .orElseGet(() -> permissionRepo.save(new Permission("CREATE")));

            Permission update = permissionRepo.findByPermissionName("UPDATE")
                    .orElseGet(() -> permissionRepo.save(new Permission("UPDATE")));

            Permission delete = permissionRepo.findByPermissionName("DELETE")
                    .orElseGet(() -> permissionRepo.save(new Permission("DELETE")));

            // 2️⃣ ROLES
            Role adminRole = roleRepo.findByRole("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role("ADMIN");
                        r.setPermissionList(Set.of(read, create, update, delete));
                        return roleRepo.save(r);
                    });

            Role authorRole = roleRepo.findByRole("AUTHOR")
                    .orElseGet(() -> {
                        Role r = new Role("AUTHOR");
                        r.setPermissionList(Set.of(read, create, update));
                        return roleRepo.save(r);
                    });

            Role userRole = roleRepo.findByRole("USER")
                    .orElseGet(() -> {
                        Role r = new Role("USER");
                        r.setPermissionList(Set.of(read));
                        return roleRepo.save(r);
                    });

            // 3️⃣ USUARIO ADMIN
            if (!userRepo.existsByUsername("blogadmin")) {

                UserSec admin = new UserSec();
                admin.setUsername("blogadmin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setEnabled(true);
                admin.setAccountNotExpired(true);
                admin.setAccountNotLocked(true);
                admin.setCredentialNotExpired(true);
                admin.setRolesList(Set.of(adminRole));

                userRepo.save(admin);
            }
        };
    }
}
