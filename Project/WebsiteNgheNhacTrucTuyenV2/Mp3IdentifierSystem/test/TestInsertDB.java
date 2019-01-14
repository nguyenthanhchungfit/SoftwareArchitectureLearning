
import mp3.identification.models.TUserModel;
import mp3.utils.thrift.models.TUser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chungnt
 */
public class TestInsertDB {
    public static void main(String[] args) {
        TUser user = new TUser();
        user.username = "chungnt";
        user.password = "123";
        user.typeUser = 0;
        System.out.println(TUserModel.Instance.signup(user));
    }
}
