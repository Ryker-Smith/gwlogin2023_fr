package net.fachtnaroe.gwlogin2023_fr;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class bits {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
