package info.hongik.ee.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CourseCrawlRequest {
    public String p_yy;
    public String p_hakgi;
    public String p_dept;

    public CourseCrawlRequest(String p_yy, String p_hakgi, String p_dept) {
        this.p_yy = p_yy;
        this.p_hakgi = p_hakgi;
        this.p_dept = p_dept;
    }
}
