package info.hongik.ee.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Key;

@Entity
@Getter @Setter
public class Security {

    @Id @GeneratedValue
    private Long id;

    private Key signingKey;
}
