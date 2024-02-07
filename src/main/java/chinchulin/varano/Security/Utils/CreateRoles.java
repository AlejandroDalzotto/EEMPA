//package chinchulin.varano.Security.Utils;
//import chinchulin.varano.Security.Models.Rol;
//import chinchulin.varano.Security.Service.RolService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CreateRoles implements CommandLineRunner {
//
//    @Autowired
//    RolService rolService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Rol rolUser = new Rol(RolName.ROLE_USER);
//        Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
//        rolService.save(rolUser);
//        rolService.save(rolAdmin);
//
//    }
//
//}