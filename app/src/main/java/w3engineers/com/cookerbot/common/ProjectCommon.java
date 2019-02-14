package w3engineers.com.cookerbot.common;

import w3engineers.com.cookerbot.constant.Constant;

/**
 * Created by Borhan Uddin on 3/27/2017.
 */

public class ProjectCommon {
     int OVEN_ON = 1;

    public  String getGradients(int value) {
        String rtn = "";
        if(Constant.OVEN_ON==value)
        {
            rtn = "b=";
        }else if(Constant.OVEN_OFF==value)
        {
            rtn="b|";
        }else if(Constant.OIL==value)
        {
            rtn="o+";
        }else if(Constant.WATER==value)
        {
            rtn="w^";
        }else if(Constant.ONION==value)
        {
            rtn="s#";
        }else if(Constant.CHILI==value)
        {
            rtn="s#";
        }else if(Constant.SALT==value)
        {
            rtn="s#";
        }else if(Constant.SPICE==value)
        {
            rtn="s#";
        }else if(Constant.SPUD==value)
        {
            rtn="t*";
        }else if(Constant.TIME_DELAY==value)
        {
            rtn="d`";
        }

        return rtn;
    }

}
