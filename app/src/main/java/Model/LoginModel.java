package Model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    // LOGIN FORM
    private String member_number;
    private String password;

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // RESPONSE FROM DATABASE
    private Boolean status;
    private String message;
    private int parker_id;
    private String parker_name;
    private String parker_member_number;
    private String token;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getParker_id() {
        return parker_id;
    }

    public void setParker_id(int parker_id) {
        this.parker_id = parker_id;
    }

    public String getParker_name() {
        return parker_name;
    }

    public void setParker_name(String parker_name) {
        this.parker_name = parker_name;
    }

    public String getParker_member_number() {
        return parker_member_number;
    }

    public void setParker_member_number(String parker_member_number) {
        this.parker_member_number = parker_member_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
