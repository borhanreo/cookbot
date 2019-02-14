package w3engineers.com.cookerbot.sdcard;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import w3engineers.com.cookerbot.constant.Constant;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class SdCard {
    private String TAG = "borhan SdCard";
    String checkfileName="droid";
    boolean isActiveInternal, isActiveExternal;
    private File sdPathFile;
    private File sdPathFolder;
    public boolean isHasSdcard()
    {
        boolean rtn=false;
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isSDPresent)
        {
            rtn=true;
        }
        return rtn;
    }

    public boolean isFolderHas()
    {
        boolean rtn=false;
        File folder = new File(Environment.getExternalStorageDirectory() +File.separator + Constant.FOLDER_NAME);
        //File folder = new File(Environment.getRootDirectory() +File.separator + folderName);
        boolean success = true;
        if (!folder.exists()) {
            try
            {
                success = folder.mkdir();

            }catch (Exception e)
            {
                Log.d(TAG ," "+e.toString());
            }

            sdPathFolder = folder;
        }
        if (success) {
            rtn=true;
        } else {
            rtn=false;
        }
        return rtn;
    }

    public boolean isFileThere(String did)
    {
        File myFile;
        boolean rtn=false;
        myFile =  new File(Environment.getExternalStorageDirectory() +File.separator + Constant.FOLDER_NAME+File.separator+did+".csv");
        if(myFile.exists()) {
            rtn=true;
            sdPathFile = myFile;
        }else
        {
            rtn=false;
        }
        return  rtn;

    }
    public boolean createFile(String did)
    {
        File myFile;
        boolean rtn=false;
        myFile =  new File(Environment.getExternalStorageDirectory() +File.separator + Constant.FOLDER_NAME+File.separator+did+".csv");

        try {
            myFile.createNewFile();
            rtn=true;
        } catch (IOException e) {
            e.printStackTrace();
            rtn=false;
        }
        return rtn;
    }
    public File getSdPathFile()
    {
        return this.sdPathFile;
    }
    public File getSdPathFolder()
    {
        return this.sdPathFolder;
    }
    public String getFileString()
    {
        System.out.println("i am File "+ String.valueOf(this.sdPathFile));
        return String.valueOf(this.sdPathFile);
    }
}
