package w3engineers.com.cookerbot.permission;

import android.Manifest;
import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Borhan Uddin on 4/12/2017.
 */

public class CookBotAppPermissions {

    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_BLUETOOTH = Manifest.permission.BLUETOOTH;
    public static final String PERMISSION_BLUETOOTH_ADMIN= Manifest.permission.BLUETOOTH_ADMIN;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static String[] GetPermissionsArray(String... permissions){

        return permissions;

    }

    public static void ShowingToastMessageForPermission(final Activity activity, final String message){

        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
