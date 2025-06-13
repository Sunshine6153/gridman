package moudle;

import java.io.Serializable;

public class Gridman implements Serializable {
    private String account;
    private String password;

    public Gridman(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}