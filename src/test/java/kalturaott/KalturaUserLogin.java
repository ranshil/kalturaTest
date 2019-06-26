package kalturaott;

public class KalturaUserLogin {

    int partnerId ;
    String username;
    String password;

    public KalturaUserLogin(KalturaUser kalturaUser){
        partnerId = kalturaUser.getPartnerId();
        username = kalturaUser.getUsername();
        password = kalturaUser.getPassword();

    }

    public void setPassword(String password) {
        this.password = password;
    }
}
