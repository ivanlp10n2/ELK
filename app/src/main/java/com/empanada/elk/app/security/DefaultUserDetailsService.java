package com.empanada.elk.app.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultUserDetailsService implements UserDetailsService {
  List<UserDTO> users = new ArrayList<>();
  final String ENCODER_PREFIX = "{noop}";

  DefaultUserDetailsService(){
    users.add(new UserDTO("root", "root", "USER"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserDTO> user = users.stream()
            .filter(userDTO -> userDTO.username.equals(username))
            .findAny();
    if(!user.isPresent())
      throw new UsernameNotFoundException("User not found by name: " + username);

    return toUserDetails(user.get());
  }

  private UserDetails toUserDetails(UserDTO userDTO) {
    return User.withUsername(userDTO.username)
            .password(ENCODER_PREFIX + userDTO.password)
            .roles(userDTO.role).build();
  }

  private static class UserDTO {
    private String username;
    private String password;
    private String role;

    public UserDTO(String username, String password, String role) {
      this.username = username;
      this.password = password;
      this.role = role;
    }
  }
}
