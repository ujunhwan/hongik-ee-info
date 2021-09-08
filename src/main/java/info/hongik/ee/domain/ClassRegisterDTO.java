package info.hongik.ee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
public class ClassRegisterDTO {

    private String id;
    private String name;
    private int credit;
    private String detail;
    private String type;
    private int level;
    @Nullable
    private Map<String, Boolean> major;

}
