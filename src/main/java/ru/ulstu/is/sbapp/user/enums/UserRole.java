package ru.ulstu.is.sbapp.user.enums;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    DOCTOR,
    PATIENT;

    private static final String PREFIX = "ROLE_";

    @Override
    public String getAuthority() {
        return PREFIX + this.name();
    }

    public static final class AsString {
        public static final String ADMIN = PREFIX + "ADMIN";
        public static final String DOCTOR = PREFIX + "DOCTOR";
        public static final String PATIENT = PREFIX + "PATIENT";
    }
}