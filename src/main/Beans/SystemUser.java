package main.Beans;


/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:07
 */
public class SystemUser {
    private String UserName;
    private String Password;
    private int power;
    private String email;
    private String ID;

    public boolean isLogged(){
        // TODO: finish the logic
        return true;
    }

    public boolean isPasswordMatch(String password){
        // TODO: judge if the password in the data base is match
        return true;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        // TODO: MD5 encode
        Password = password;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
