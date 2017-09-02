package test;

import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;

import java.util.List;

public class testclass {
    public static void main(String[] args)throws Exception {
        String s="1";
        Userseverce userdao=new Userseverce();
//        SystemUser user=userdao.findUserByID(s);
        userdao.DeleteUser("3");
        List<SystemUser> list=null;
        list=userdao.searchUserByName("cong");
        for(SystemUser it : list)
        {
            System.out.println(it.getUserName());
        }
//        System.out.println(user.getUserName());

    }

}
