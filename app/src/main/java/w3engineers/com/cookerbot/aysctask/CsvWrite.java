package w3engineers.com.cookerbot.aysctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import w3engineers.com.cookerbot.constant.Constant;
import w3engineers.com.cookerbot.controller.CsvWriteCompletedCallBackListener;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;
import w3engineers.com.cookerbot.sdcard.SdCard;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class CsvWrite extends AsyncTask<String, Void, String> {
    private SdCard sdCard;
    private String url = null, device_id = Constant.FOLDER_NAME, log_time = null, noise = null, TAG = "borhan CsvWrite";
    private CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener;
    private Context context;
    DBHandler db;

    public CsvWrite(Context context, CsvWriteCompletedCallBackListener csvWriteCompletedCallBackListener) {

        this.context = context;
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
        //Toast.makeText(,"Data Inserted",Toast.LENGTH_LONG).show();
        //System.out.println("i am err "+s.toString());
        csvWriteCompletedCallBackListener.csvCompleted(true);
    }

    public String getRemoveNewLine(String str) {
        String myString = "";
        myString = str.replaceAll("\n", " ");
        return getRemoveComma(myString);
    }
    public String getRemoveComma(String str) {
        String myString = "";
        myString = str.replaceAll(",", " ");
        return myString;
    }
    @Override
    protected String doInBackground(String... params) {


        sdCard = new SdCard();
        if (sdCard.isHasSdcard()) {
            if (sdCard.isFolderHas()) {
                if (sdCard.isFileThere(device_id)) {

                    try {
                        List<RecipeModel> recipeModels = db.getAllRecipe();
                        for (RecipeModel recipeModel : recipeModels) {
                            FileWriter writer = new FileWriter(sdCard.getSdPathFile(), true);
                            writer.append(recipeModel.getId() + "");
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


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
        return null;
    }
}
