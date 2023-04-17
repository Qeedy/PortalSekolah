package com.portalSekolah.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portalSekolah.entity.ConfirmationToken;
import com.portalSekolah.entity.RolePermission;
import com.portalSekolah.entity.User;
import com.portalSekolah.model.ModelChangePassword;
import com.portalSekolah.model.ModelUserRegistration;
import com.portalSekolah.repository.ConfirmationTokenRepository;
import com.portalSekolah.repository.RolePermissionRepository;
import com.portalSekolah.repository.UserRepository;
import com.portalSekolah.service.email.EmailSender;
import com.portalSekolah.util.EmailValidator;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory
            .getLogger(UserService.class);

    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolePermissionRepository authorizationRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private Environment env;
    @Autowired
    private SiswaService siswaService;
    @Autowired
    private GuruService guruService;

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username)));
    }

    public void changePassword(ModelChangePassword model) throws AuthenticationException {
        User user = getUserByUuid(model.userUuid());
        if (passwordEncoder.matches(model.lastPassword(), user.getPassword())) {
            String newPasswordEncrypted = passwordEncoder
                    .encode(model.newPassword());
            user.setPassword(newPasswordEncrypted);
            userRepository.save(user);
        } else {
            throw new AuthenticationException("Wrong password");
        }
    }

    public String register(ModelUserRegistration modelUserRegistration) {
        boolean isValidEmail = emailValidator
                .test(modelUserRegistration.emailAddress());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        String encryptedPassword = passwordEncoder
                .encode(modelUserRegistration.password());
        String token = UUID.randomUUID().toString();
        LocalDateTime current = LocalDateTime.now();
        User user = new User(modelUserRegistration, encryptedPassword);
        List<RolePermission> roles = new ArrayList<>();
        modelUserRegistration.roles().forEach(r -> {
            RolePermission role = authorizationRepository.findByRoleCode(r);
            if (null != role) {
                roles.add(role);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
        ConfirmationToken confirmToken = new ConfirmationToken(token, current,
                current.plusMinutes(15), user);
        confirmationTokenRepository.save(confirmToken);
        String linkValidation = env.getProperty("validationLink");
        String link = linkValidation + token;
        emailSender.send(modelUserRegistration.emailAddress(),
                buildEmail(modelUserRegistration.firstName(), link));
        log.info("User Created {}", user.getUsername());
        return token;
    }

    public User getUserByUuid(String uuid) {
        return Optional.ofNullable(userRepository.findById(uuid))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot load stock"))
                .get();
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmToken = confirmationTokenRepository
                .findByToken(token)
                .orElseThrow(
                        () -> new IllegalStateException("token not found"));
        User user = confirmToken.getUser();
        if (confirmToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenRepository.updateConfirmedAt(token,
                LocalDateTime.now());
        userRepository.enableUser(user.getEmailAddress());
        user.getRoles().forEach(r -> {
            if ("SISWA".equals(r.getRoleCode())) {
                siswaService.createSiswa(user);
            }
            if ("GURU".equals(r.getRoleCode())) {
                guruService.createGuru(user);
            }
        });

        return null;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n"
                + "\n"
                + "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n"
                + "\n"
                + "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "    <tbody><tr>\n"
                + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n"
                + "        \n"
                + "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                + "          <tbody><tr>\n"
                + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
                + "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                + "                  <tbody><tr>\n"
                + "                    <td style=\"padding-left:10px\">\n"
                + "                  \n" + "                    </td>\n"
                + "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
                + "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
                + "                    </td>\n" + "                  </tr>\n"
                + "                </tbody></table>\n"
                + "              </a>\n" + "            </td>\n"
                + "          </tr>\n" + "        </tbody></table>\n"
                + "        \n" + "      </td>\n" + "    </tr>\n"
                + "  </tbody></table>\n"
                + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                + "    <tbody><tr>\n"
                + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
                + "      <td>\n" + "        \n"
                + "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                + "                  <tbody><tr>\n"
                + "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
                + "                  </tr>\n"
                + "                </tbody></table>\n" + "        \n"
                + "      </td>\n"
                + "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n"
                + "    </tr>\n"
                + "  </tbody></table>\n" + "\n" + "\n" + "\n"
                + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                + "    <tbody><tr>\n" + "      <td height=\"30\"><br></td>\n"
                + "    </tr>\n" + "    <tr>\n"
                + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
                + "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
                + "        \n"
                + "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi "
                + name
                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
                + link
                + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>"
                + "        \n" + "      </td>\n"
                + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
                + "    </tr>\n" + "    <tr>\n"
                + "      <td height=\"30\"><br></td>\n" + "    </tr>\n"
                + "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n"
                + "\n" + "</div></div>";
    }

}
