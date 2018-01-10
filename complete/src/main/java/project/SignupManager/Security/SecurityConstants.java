package project.SignupManager.Security;

public class SecurityConstants {
     public static final String SECRET = "SecretKeyToGenJWTs";
     public static final long EXPIRATION_TIME = 864_000_000; // 10 days
     public static final String TOKEN_PREFIX = "Bearer ";
     public static final String HEADER_STRING = "Authorization";
     public static final String SIGN_UP_URL = "/sign-up/user";
     public static final String CONFIRM = "/sign-up/token";
     public static final String CUSTOMER_URL = "/customer";
     public static final String GREETING = "/greeting";
}
