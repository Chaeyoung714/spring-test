package cholog.auth.ui;

import cholog.auth.application.AuthService;
import cholog.auth.application.AuthorizationException;
import cholog.auth.dto.AuthInfo;
import cholog.auth.dto.MemberResponse;
import cholog.auth.infrastructure.AuthorizationExtractor;
import cholog.auth.infrastructure.BasicAuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicLoginController {
    private final AuthService authService;
    private final AuthorizationExtractor<AuthInfo> authorizationExtractor;

    public BasicLoginController(AuthService authService) {
        this.authService = authService;
        this.authorizationExtractor = new BasicAuthorizationExtractor();
    }

    /**
     * ex) request sample
     * <p>
     * GET /members/me/basic HTTP/1.1
     * authorization: Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
     * accept: application/json
     */
    @GetMapping("/members/me/basic")
    public ResponseEntity<MemberResponse> findMyInfo(
            HttpServletRequest request
    ) {
        // TODO: authorization 헤더의 Basic 값에 있는 email과 password 추출 (hint: authorizationExtractor 사용)

        //요청한 이메일/패스워드 추출
        AuthInfo extract = authorizationExtractor.extract(request);
        String email = extract.getEmail();
        String password = extract.getPassword();

        //이메일/패스워드가 맞는지 확인
        if (authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        //이메일/패스워드가 맞으면 이에 맞는 사용자 식별
        MemberResponse member = authService.findMember(email);
        return ResponseEntity.ok().body(member);
    }
}
