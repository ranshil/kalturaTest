package kalturaott;

import restutil.RandomString;

public class KalturaUser{

    private String  password;
    private int partnerId;
    private String token;
    private User user;

    public KalturaUser generateKalturaUser(){
        KalturaUser ku = new KalturaUser();
        ku.partnerId = 888;
        ku.password = "password";
        ku.user = new User().generateUser();
        return ku;
    }

    public void setPartnerId(int partnerId){
        this.partnerId = partnerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return user.username;
    }

    public void setUsername(String username) {
        this.user.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getPartnerId() {
        return partnerId;
    }


    class User{
        private String objectType;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String city;
        private String countryId;
        private String externalId;

        private User generateUser(){
            User u = new User();
            u.objectType = "KalturaOTTUser";
            u.username= RandomString.generateString(10);
            u.firstName= RandomString.generateString(10);
            u.lastName= RandomString.generateString(10);
            u.email= RandomString.generateString(10) + "@gamil.com";
            u.address = RandomString.generateString(10);
            u.city = RandomString.generateString(10);
            u.countryId= "7" ;
            u.externalId = RandomString.generateString(10);
            return u;
        }
    }

}
