package br.com.lowlevel.revisaospringsecurity.domain.user;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
