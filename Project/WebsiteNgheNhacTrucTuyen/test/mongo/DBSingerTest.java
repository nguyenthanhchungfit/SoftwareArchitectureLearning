/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongo;

import data_access_object.DBSingerModelMongo;
import server_data.DBSingerModel;
import models.Referencer;
import models.Singer;

/**
 *
 * @author cpu11165-local
 */
public class DBSingerTest {
    public static void main(String[] args) {
        Singer singer = new DBSingerModelMongo().getSingerInformation("IWZ987UI");
        System.out.println(singer);
    }
}
