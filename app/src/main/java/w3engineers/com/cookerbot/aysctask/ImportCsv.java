package w3engineers.com.cookerbot.aysctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import w3engineers.com.cookerbot.constant.Constant;
import w3engineers.com.cookerbot.controller.CsvWriteCompletedCallBackListener;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;
import w3engineers.com.cookerbot.sdcard.SdCard;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class ImportCsv extends AsyncTask<String,Void,String> {
    private CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener;
    private Context context;
    DBHandler db;
    private String TAG = "borhan ImportCsv";
    public ImportCsv(Activity activity, CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener) {

        this.context = activity;
        this.db = new DBHandler(context);
        this.csvWriteCompletedCallBackListener = csvWriteCompletedCallBackListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        csvWriteCompletedCallBackListener.csvCompleted(true);
    }
    @Override
    protected String doInBackground(String... params) {
        CSVReader reader = null;
        File myFile;
        boolean rtn=false;
        myFile =  new File(Environment.getExternalStorageDirectory() +File.separator + Constant.FOLDER_NAME+File.separator+Constant.FOLDER_NAME+".csv");
        try {
            reader = new CSVReader(new FileReader(myFile.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String [] nextLine;
        try {
            long i=0;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if(i>0)
                {
                    db.addRecipe(new RecipeModel(1, nextLine[1] , nextLine[2],nextLine[3]));
                    Log.d(TAG,nextLine[0] + nextLine[1] + "etc...");

                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
