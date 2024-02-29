/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
 * Purpose: This class is used to seed the database with the HOD user 
 */
package com.ddu.backend.bootstrap;

public class HODSeeder {

}
// @Component
// public class HODSeeder implements ApplicationListener<ContextRefreshedEvent>
// {
// private final RoleRepository roleRepository;
// private final UserRepository userRepository;
//
// private final PasswordEncoder passwordEncoder;
//
//
// public HODSeeder(
// RoleRepository roleRepository,
// UserRepository userRepository,
// PasswordEncoder passwordEncoder
// ) {
// this.roleRepository = roleRepository;
// this.userRepository = userRepository;
// this.passwordEncoder = passwordEncoder;
// }
//
// @Override
// public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
// this.createSuperAdministrator();
// }
//
// private void createSuperAdministrator() {
// RegisterUserDto userDto = new RegisterUserDto();
// userDto.setFullName("HOD").setEmail("super.admin@email.com").setPassword("123456");
//
// Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.HOD);
// Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
//
// if (optionalRole.isEmpty() || optionalUser.isPresent()) {
// return;
// }
//
// var user = new User()
// .setFullName(userDto.getFullName())
// .setEmail(userDto.getEmail())
// .setPassword(passwordEncoder.encode(userDto.getPassword()))
// .setRole(optionalRole.get());
//
// userRepository.save(user);
// }
// }