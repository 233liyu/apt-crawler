package main.servlet.tool;

import main.Beans.SystemUser;

public class apt_set {
    public static void setob(SystemUser user, String str1, String str2, String str3) {
        user.setUserName(str1);
        user.setPassword(str2);
        user.setEmail(str3);
    }
}
