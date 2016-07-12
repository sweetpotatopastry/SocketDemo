package yl.bamai.com;

import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Utils {

    public static void showToast(String msg){
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }
}
