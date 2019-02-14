package w3engineers.com.cookerbot.aysctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import w3engineers.com.cookerbot.constant.Constant;
import w3engineers.com.cookerbot.controller.CsvWriteCompletedCallBackListener;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;
import w3engineers.com.cookerbot.sdcard.SdCard;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class InitCSV extends AsyncTask<String,Void,String> {
    private SdCard sdCard ;
    private String url=null,device_id= Constant.FOLDER_NAME,log_time=null, noise=null,TAG="borhan CsvWrite";
    private CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener;
    private Context context;
    DBHandler db;

    public InitCSV( Activity activity)
    {

        this.context = activity;
        this.db = new DBHandler(context);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(,"Data Inserted",Toast.LENGTH_LONG).show();
        //System.out.println("i am err "+s.toString());
        //csvWriteCompletedCallBackListener.csvCompleted(true);
    }

    public String getRemoveNewLine(String str)
    {
        String myString = str, rtn;
        myString = str.replaceAll("\n"," ");
        rtn = myString.replaceAll(","," ");
        return myString;
    }

    @Override
    protected String doInBackground(String... params) {


        sdCard = new SdCard();
        if(sdCard.isHasSdcard())
        {
            if(sdCard.isFolderHas())
            {
                    //file not so create new
                    if(sdCard.createFile(device_id))
                    {
                        if(sdCard.isFileThere(device_id))
                        {

                        }else
                        {
                            CSVWriter writer = null;
                            try
                            {
                                String header = Constant.CSV_TITLE_ID+"#"+Constant.CSV_TITLE_RECIPE_NAME+"#"+Constant.CSV_TITLE_RECIPE_API+"#"+Constant.CSV_TITLE_RECIPE_GRADIENTS;
                                String[] headerEntity = header.split("#");
                                writer = new CSVWriter(new FileWriter("/sdcard/CookBOT/"+device_id+".csv"), ',');
                                writer.writeNext(headerEntity);
                                writer.close();
                            }
                            catch (IOException e)
                            {
                                //error
                            }
                        }

                    }


            }
        }
        return null;
    }
}
