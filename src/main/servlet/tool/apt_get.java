package main.servlet.tool;

import main.Beans.SystemUser;

public class apt_get{
public static void getob(String str1, String str2, String str3, SystemUser user){
    str1=user.getUserName();
    str2=user.getPassword();
    str3=user.getEmail();
}
}
