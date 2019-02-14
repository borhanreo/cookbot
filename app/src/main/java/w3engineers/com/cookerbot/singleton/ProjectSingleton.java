package w3engineers.com.cookerbot.singleton;

import android.content.Context;

/**
 * Created by Borhan Uddin on 4/5/2017.
 */

public class ProjectSingleton {
    private static ProjectSingleton projectSingleton = null;
    private Context context;
    private String address;


    private ProjectSingleton(Context context)
    {
        this.context = context;
        address =null;
    }
    public static ProjectSingleton getInstance(Context context)
    {
        if(projectSingleton == null)
        {
            projectSingleton = new ProjectSingleton(context);
        }
        return projectSingleton;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String value){
        address = value;
    }

}
