package info.hongik.ee.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String createToken();
    String getSubject(String token);
}
