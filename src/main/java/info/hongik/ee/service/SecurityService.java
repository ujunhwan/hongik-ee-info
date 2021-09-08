package info.hongik.ee.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SecurityService {
    String createToken(Map<String, String> subject);
    String getSubject(String token);
}
