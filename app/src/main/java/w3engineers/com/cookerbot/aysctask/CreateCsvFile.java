package w3engineers.com.cookerbot.aysctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;
import w3engineers.com.cookerbot.constant.Constant;
import w3engineers.com.cookerbot.controller.CsvWriteCompletedCallBackListener;
import w3engineers.com.cookerbot.csv.SaveCSV;
import w3engineers.com.cookerbot.model.RecipeModel;
import w3engineers.com.cookerbot.sdcard.SdCard;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class CreateCsvFile extends AsyncTask<String,Void,String> {
    private SdCard sdCard ;
    private SaveCSV saveCSV;
    private String url=null,device_id=Constant.FOLDER_NAME,log_time=null, noise=null;
    private Context context;
    private RecipeModel recipeModel;
    private CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener;

    public CreateCsvFile(RecipeModel recipeModel, Activity activity)
    {
        this.recipeModel = recipeModel;
        this.context = activity;
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
                if(sdCard.isFileThere(device_id))
                {

                    try
                    {
                        FileWriter writer = new FileWriter(sdCard.getSdPathFile(),true);
                        writer.append(recipeModel.getId()+"");
                        writer.append(',');
                        writer.append(recipeModel.getRecipe_name());
                        writer.append(',');
                        writer.append(recipeModel.getRecipe_api());
                        writer.append(',');
                        writer.append(getRemoveNewLine(recipeModel.getRecipe_gradients_list()));
                        writer.append('\n');
                        writer.flush();
                        writer.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }



                }else
                {
                    //file not so create new
                    if(sdCard.createFile(device_id))
                    {
                        CSVWriter writer = null;
                        try
                        {
                            String header = Constant.CSV_TITLE_ID+"#"+Constant.CSV_TITLE_RECIPE_NAME+"#"+Constant.CSV_TITLE_RECIPE_API+"#"+Constant.CSV_TITLE_RECIPE_GRADIENTS;
                            String[] headerEntity = header.split("#");
                            writer = new CSVWriter(new FileWriter("/sdcard/CookBOT/"+device_id+".csv"), ',');
                            writer.writeNext(headerEntity);
                            String dd = recipeModel.getId()+"_"+recipeModel.getRecipe_name()+"_"+recipeModel.getRecipe_api()+"_"+recipeModel.getRecipe_gradients_list();
                            String[] entries = dd.split("_");
                            writer.writeNext(entries);
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
