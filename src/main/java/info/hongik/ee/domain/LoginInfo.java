package info.hongik.ee.domain;

public class LoginInfo {
    public String Id;
    public String Pw;

    public String getId() {
        return Id;
    }

    public String getPw() {
        return Pw;
    }

    public LoginInfo(String id, String pw) {
        Id = id;
        Pw = pw;
    }

}
