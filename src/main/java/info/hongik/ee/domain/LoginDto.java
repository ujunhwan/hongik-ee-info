package info.hongik.ee.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {
    @JsonProperty(value = "USER_ID")
    private final String id;

    @JsonProperty(value = "PASSWD")
    private final String passwd;

}
