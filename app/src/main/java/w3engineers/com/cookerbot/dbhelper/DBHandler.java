package w3engineers.com.cookerbot.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import w3engineers.com.cookerbot.model.RecipeModel;

/**
 * Created by borhan on 2/22/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cookbot";
    private static final String TABLE_RECIPE = "recipe";
    private static final String KEY_ID = "id";
    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_API = "recipe_api";
    private static final String RECIPE_GRADIENTS_LIST = "recipe_gradients_list";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + RECIPE_NAME + " TEXT,"
                + RECIPE_API + " TEXT,"
                + RECIPE_GRADIENTS_LIST + " TEXT)";

        db.execSQL(CREATE_RECIPE_TABLE);

        //String chickenApi="o+18:t*160:s#1:t*1:s#2:t*1:s#3:t*1:s#9:t*120:w^1:t*300:s#7:t*1:w^1:t*600:w^4:t*900";
        String chickenApi=":o+10:t*120:s#1:s#2:s#3:s#9:w^1:t*90:s#7:t*1:w^4:t*300:w^8:t*1020";
        ContentValues initialValuesChicken = new ContentValues();
        initialValuesChicken.put(RECIPE_NAME, "System Chicken");
        initialValuesChicken.put(RECIPE_API, chickenApi);
        initialValuesChicken.put(RECIPE_GRADIENTS_LIST, "A");

        String PotatoFryApi=":o+10:t*30:s#1:t*1:s#2:t*1:s#3:t*1:s#7:t*900";
        ContentValues initialValuesPotatoFry = new ContentValues();
        initialValuesPotatoFry.put(RECIPE_NAME, "System Potato Fry");
        initialValuesPotatoFry.put(RECIPE_API, PotatoFryApi); //Vegetable cooking
        initialValuesPotatoFry.put(RECIPE_GRADIENTS_LIST, "A");

        String VagetableApi=":o+10:t*120:s#1:s#2:s#9:s#7:w^8:t*2400";
        ContentValues initialValuesVagetableFry = new ContentValues();
        initialValuesVagetableFry.put(RECIPE_NAME, "System Vegetable Papaya cooking");
        initialValuesVagetableFry.put(RECIPE_API, VagetableApi);
        initialValuesVagetableFry.put(RECIPE_GRADIENTS_LIST, "A");

        String resetApi=":o+1:W^1:s#1:s#2:s#3:s#9:s#7:t*1";
        ContentValues initialValuesResetFry = new ContentValues();
        initialValuesResetFry.put(RECIPE_NAME, "System Reset");
        initialValuesResetFry.put(RECIPE_API, resetApi);
        initialValuesResetFry.put(RECIPE_GRADIENTS_LIST, "A");


        String potatoFryApi=":b=1:d`29:o+5:d`19:s#1:d`3:s#2:d`2:s#3:d`2:s#9:d`3:s#7:d`0:s#7:d`1:s#7:d`1:s#7:d`1:s#7:d`1:s#7:d`1:s#7:d`0:s#7:d`1:s#7:d`1:s#7:d`2:t*1:d`5:t*1:d`1:" +
                "t*1:d`-1:t*1:d`0:t*1:d`2:t*1:d`5:t*1:d`-1:w^1:d`2:t*1:d`2:t*1:d`2:t*1:d`1:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`1:t*1:d`10:t*1:d`3:t*1:d`3:t*1:d`2:t*1:d`3:t*1:d`7:" +
                "t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`-6:o+5:d`14:t*1:d`2:t*1:d`2:t*1:d`3:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`-11:t*1:d`13:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`5:t*1:d`2:" +
                "t*1:d`1:t*1:d`9:t*1:d`4:t*1:d`2:t*1:d`4:t*1:d`2:t*1:d`4:t*1:d`-2:t*1:d`12:b-1:d`-8:t*1:d`2:t*1:d`-6:t*1:d`1:t*1:d`4:t*1:d`3:t*1:d`2:t*1:d`-1:t*1:d`-1:t*1:d`4:" +
                "t*1:d`0:t*1:d`2:t*1:d`-1:t*1:d`3:t*1";
        ContentValues initialValuesPotatofryFry = new ContentValues();
        initialValuesPotatofryFry.put(RECIPE_NAME, "Potato Fry ");
        initialValuesPotatofryFry.put(RECIPE_API, potatoFryApi);
        initialValuesPotatofryFry.put(RECIPE_GRADIENTS_LIST, "A");


        String gourdApi=":b=1:d`29:o+5:d`24:t*1:d`12:s#1:d`4:s#2:d`2:s#3:d`2:s#9:d`3:t*1:d`2:w^1:d`3:w^1:d`5:w^1:d`3:t*1:d`9:t*1:d`3:t*1:d`1:s#7:d`-1:s#7:d`1:s#7:d`0:s#7:d`1:s#7" +
                ":d`13:s#7:d`0:s#7:d`1:s#7:d`3:s#7:d`1:t*1:d`2:w^1:d`2:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`2:t*1:d`-2:t*1:d`2:t*1:d`0:t*1:d`0:t*1:d`4:" +
                "t*1:d`3:t*1:d`45:t*1:d`43:t*1:d`34:t*1:d`0:t*1:d`38:t*1:d`2:t*1:d`52:t*1:d`3:t*1:d`37:t*1:d`57:t*1:d`26:t*1:d`34:t*1:d`54:t*1:d`41:t*1:d`37:t*1:d`22:t*1:d`8:" +
                "t*1:d`13:t*1:d`23:t*1:d`2:t*1:d`27:t*1:d`29:t*1:d`12:t*1:d`13:t*1:d`22:t*1:d`9:t*1:d`3:t*1:d`10:t*1:d`15:t*1:d`3:t*1:d`4:t*1:d`2:t*1:d`2:t*1:d`3:t*1:d`12:b-1:" +
                "d`-10:t*1:d`2:t*1:d`1:t*1:d`3:t*1";
        ContentValues initialValuesGourdFry = new ContentValues();
        initialValuesGourdFry.put(RECIPE_NAME, "Gourd Cook");
        initialValuesGourdFry.put(RECIPE_API, gourdApi);
        initialValuesGourdFry.put(RECIPE_GRADIENTS_LIST, "A");


        String DherhasaApi=":b=1:d`8:o+5:d`17:t*1:d`2:s#1:d`3:s#2:d`2:s#3:d`0:s#9:d`5:s#2:d`4:s#2:d`1:t*1:d`3:t*1:d`-1:o+5:d`10:t*1:d`-1:w^1:d`4:w^1:d`4:t*1:d`8:s#7:d`0:s#7:" +
                "d`1:s#7:d`0:s#7:d`1:s#7:d`1:s#7:d`1:s#7:d`0:t*1:d`2:t*1:d`-1:t*1:d`3:t*1:d`0:t*1:d`1:t*1:d`2:t*1:d`-1:w^1:d`3:w^1:d`2:w^1:d`1:w^1:d`2:w^1:d`1:w^1:d`2:w^1:" +
                "d`1:w^1:d`2:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`1:w^1:d`3:t*1:d`48:t*1:d`51:t*1:d`24:t*1:d`24:t*1:d`12:t*1:d`30:t*1:d`34:t*1:d`77:t*1:d`10:t*1:d`42:" +
                "t*1:d`41:t*1:d`9:o+5:d`18:t*1:d`27:t*1:d`38:t*1:d`27:t*1:d`18:t*1:d`19:t*1:d`3:t*1:d`8:t*1:d`6:t*1:d`6:t*1:d`3:t*1:d`2:t*1:d`3:t*1:d`4:b-1:d`4:b-1:d`-5:t*1:" +
                "d`2:t*1:d`7:t*1:d`7:t*1";
        ContentValues initialValuesDherhasaFry = new ContentValues();
        initialValuesDherhasaFry.put(RECIPE_NAME, "Dherhasa Cook");
        initialValuesDherhasaFry.put(RECIPE_API, DherhasaApi);
        initialValuesDherhasaFry.put(RECIPE_GRADIENTS_LIST, "A");


        String potatoSoupApi=":b=1:d`35:o+5:d`3:o+5:d`19:t*1:d`5:t*1:d`3:s#1:d`5:s#2:d`5:s#3:d`1:s#9:d`0:s#9:d`0:s#9:d`3:s#9:d`23:t*1:d`1:w^1:d`3:w^1:d`9:w^1:d`2:w^1:" +
                "d`30:t*1:d`34:t*1:d`22:t*1:d`7:s#7:d`0:s#7:d`1:s#7:d`0:s#7:d`2:s#7:d`1:t*1:d`11:t*1:d`8:t*1:d`19:t*1:d`3:t*1:d`32:t*1:d`24:t*1:d`28:t*1:d`12:t*1:d`2:" +
                "t*1:d`3:t*1:d`4:w^1:d`2:w^1:d`2:w^1:d`1:w^1:d`2:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`4:w^1:d`5:t*1:d`5:w^1:d`2:w^1:d`2:w^1:d`2:" +
                "w^1:d`2:w^1:d`2:w^1:d`2:w^1:d`3:w^1:d`3:t*1:d`85:t*1:d`53:t*1:d`74:t*1:d`58:t*1:d`31:t*1:d`32:t*1:d`163:t*1:d`105:t*1:d`115:t*1:d`85:t*1:d`27:t*1:d`48:" +
                "t*1:d`13:w^1:d`2:w^1:d`2:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:t*1:d`66:t*1:d`3:t*1:d`231:t*1:d`10:t*1:d`50:t*1:d`44:t*1:d`13:" +
                "t*1:d`18:t*1:d`3:t*1:d`3:t*1:d`13:t*1:d`17:t*1:d`49:t*1:d`35:t*1:d`10:t*1:d`37:t*1:d`-7:t*1:d`60:t*1:d`22:t*1:d`-8:t*1:d`38:t*1";
        ContentValues initialValuesPotatoSoupFry = new ContentValues();
        initialValuesPotatoSoupFry.put(RECIPE_NAME, "Potato Soup");
        initialValuesPotatoSoupFry.put(RECIPE_API, potatoSoupApi);
        initialValuesPotatoSoupFry.put(RECIPE_GRADIENTS_LIST, "A");

        /*String chickenApi22="o+18:t*1:s#1:t*1:s#2:t*1:s#3:t*1:s#9:t*1:w^1:t*3:s#7:t*1:w^1:t*6:w^4:t*9";
        ContentValues initialValuesChicken22 = new ContentValues();
        initialValuesChicken22.put(RECIPE_NAME, "System Chicken RR");
        initialValuesChicken22.put(RECIPE_API, chickenApi22);*/

        String ChickenApi=":b=1:d`63:o+5:d`2:o+5:d`9:t*1:d`14:t*1:d`5:s#1:d`3:s#2:d`1:s#3:d`1:s#9:d`1:t*1:d`2:w^1:d`3:t*1:d`12:t*1:d`3:t*1:" +
                "d`3:t*1:d`2:t*1:d`3:t*1:d`1:t*1:d`1:t*1:d`2:t*1:d`1:s#7:d`0:s#7:d`0:s#7:d`1:s#7:d`0:s#7:d`1:s#7:d`2:s#7:d`0:s#7:d`1:s#7:" +
                "d`0:t*1:d`-1:t*1:d`14:t*1:d`1:t*1:d`2:t*1:d`0:t*1:d`2:t*1:d`-2:w^1:d`2:w^1:d`2:t*1:d`1:t*1:d`1:t*1:d`1:t*1:d`5:t*1:d`2:t*1" +
                ":d`3:t*1:d`3:t*1:d`0:t*1:d`2:t*1:d`1:t*1:d`3:t*1:d`-1:t*1:d`3:t*1:d`-3:t*1:d`4:t*1:d`1:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`0:t*1" +
                ":d`1:t*1:d`-2:t*1:d`4:t*1:d`1:t*1:d`2:w^1:d`1:w^1:d`2:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:" +
                "w^1:d`2:t*1:d`4:w^1:d`1:w^1:d`1:w^1:d`2:t*1:d`308:t*1:d`226:t*1:d`5:t*1:d`167:t*1:d`172:t*1:d`4:t*1:d`121:t*1:d`32:t*1:d`2:t*1:" +
                "d`15:t*1:d`98:t*1:d`3:t*1:d`74:t*1:d`0:t*1:d`6:w^1:d`1:w^1:d`1:w^1:d`1:t*1:d`8:t*1:d`17:t*1:d`96:t*1:d`63:t*1:d`5:t*1:d`16:d`17:" +
                "t*1:d`-1:t*1:d`7:t*1:d`1:t*1:d`3:t*1:d`3:t*1:d`-3:t*1:d`5:t*1:d`2:t*1:d`-1:t*1:d`1:t*1:d`1:t*1:d`-2:t*1:d`12:b-1:d`-8:t*1:d`-1:" +
                "t*1:d`0:t*1:d`4:t*1:d`1:t*1:d`2:t*1:d`1:t*1";
        ContentValues initialValuesChickenFry = new ContentValues();
        initialValuesChickenFry.put(RECIPE_NAME, "Chicken");
        initialValuesChickenFry.put(RECIPE_API, ChickenApi);
        initialValuesChickenFry.put(RECIPE_GRADIENTS_LIST, "A");

        String papaya22Api=":b=1:d`36:o+5:d`2:o+5:d`15:t*1:d`11:t*1:d`36:s#1:d`4:s#2:d`2:s#3:d`0:s#9:d`1:t*1:d`12:s#1:d`-10:t*1:d`-1:t*1:d`-10:t*1:d`12:t*1:" +
                "d`4:t*1:d`1:t*1:d`4:w^1:d`2:w^1:d`1:t*1:d`7:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`1:s#7:d`1:s#7:d`0:s#7:d`-1:s#7:d`1:s#7:d`6:t*1:d`1:s#7:d`1:s#" +
                "7:d`0:s#7:d`4:s#7:d`0:w^1:d`2:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`3:t*1:d`2:t*1:d`0:t*1:d`1:t*1:d`0:t*1:d`-1:t*1:d`4:t*1:d`4:t*1:d`1:" +
                "t*1:d`-1:t*1:d`-1:t*1:d`3:t*1:d`5:t*1:d`2:t*1:d`-2:t*1:d`9:t*1:d`4:t*1:d`-2:t*1:d`7:t*1:d`0:t*1:d`0:t*1:d`1:w^1:d`5:w^1:d`1:w^1:d`0:w^1:d`1:" +
                "w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1" +
                ":d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1" +
                ":w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`0:o+5:d`-5:o+5:d`-5:o+5:d`1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`2:w^1:d`2:w^1:d`1:w^1:" +
                "d`-1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`3:w^1:d`-1" +
                ":w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`21:t*1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`2:w^1:d`2:w^1:d`2:" +
                "w^1:d`2:w^1:d`2:w^1:d`4:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`2:w^1:d`1:w^1:d`2:w^1:d`6:t*1:d`18:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`-1:w^1:d`-1:w^1" +
                ":d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:" +
                "d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:" +
                "w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`22:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`1:w^1:d`0:w^1:d`1:" +
                "w^1:d`1:w^1:d`5:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`6:w^1:d`1:w^1:d`0:w^1:d`0:" +
                "w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`0:t*1:d`-2:w^1:d`3:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`9:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1" +
                ":d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:" +
                "d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`-1:w^1:d`-1:" +
                "w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`12:t*1:d`23:w^1:d`0:w^1:d`2:w^1:d`1:w^1:d`2:w^1:d`1:" +
                "w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`15:w^1:d`1:w^1:d`2:w^1:d`1:" +
                "w^1:d`1:w^1:d`2:w^1:d`1:w^1:d`4:t*1:d`649:t*1:d`405:t*1:d`333:t*1:d`143:t*1:d`21:t*1:d`189:t*1:d`154:s#9:d`0:t*1:d`20:t*1:d`25:t*1:d`87:t*1:d`31:t*1:" +
                "d`70:t*1:d`96:t*1:d`41:t*1:d`46:t*1:d`55:t*1:d`39:t*1:d`26:t*1:d`42:t*1:d`6:t*1:d`3:t*1:d`4:t*1:d`1:t*1:d`4:t*1:d`2:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:" +
                "w^1:d`1:w^1:d`3:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`3:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`2:w^1:" +
                "d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`4:t*1:d`1:t*1:d`55:t*1:d`92:t*1:d`59:" +
                "t*1:d`-2:t*1:d`2:t*1:d`0:t*1:d`75:t*1:d`4:t*1:d`12:t*1:d`2:t*1:d`52:t*1:d`7:t*1:d`5:t*1:d`18:t*1:d`12:t*1:d`3:t*1:d`-2:t*1:d`27:t*1:d`1:t*1:d`-2:t*1:d`2:" +
                "t*1:d`5:b-1:d`-1:t*1:d`-2:t*1:d`5:t*1:d`0:t*1:d`1:t*1:d`0:t*1:d`-2:t*1";
        ContentValues initialValuespapaya22 = new ContentValues();
        initialValuespapaya22.put(RECIPE_NAME, "22-03_papaya");
        initialValuespapaya22.put(RECIPE_API, papaya22Api);
        initialValuespapaya22.put(RECIPE_GRADIENTS_LIST, "A");

        String potatoDalApi=":b=1:d`37:o+5:d`38:s#1:d`2:s#2:d`1:s#3:d`0:s#9:d`0:t*1:d`7:t*1:d`5:t*1:d`6:t*1:d`17:t*1:d`5:t*1:d`2:s#7:d`1:s#7:d`0:s#7:d`4:s#7:" +
                "d`5:t*1:d`-1:s#7:d`4:s#7:d`0:s#7:d`2:s#7:d`1:s#7:d`0:s#7:d`1:s#7:d`2:t*1:d`-5:w^1:d`2:w^1:d`4:t*1:d`-1:t*1:d`0:t*1:d`2:t*1:d`0:t*1:d`5:t*1:" +
                "d`4:t*1:d`1:t*1:d`1:t*1:d`0:t*1:d`1:t*1:d`-2:t*1:d`2:t*1:d`2:t*1:d`2:t*1:d`1:t*1:d`0:t*1:d`-2:t*1:d`-1:t*1:d`6:t*1:d`-1:w^1:d`2:w^1:d`0:w^1:" +
                "d`1:w^1:d`1:w^1:d`0:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`0:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`-1:w^1:d`2:w^1:d`0:w^1:d`1:w^1:d`0:" +
                "w^1:d`6:w^1:d`4:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`0:w^1:d`1:w^1:" +
                "d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`5:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`2:w^1:d`1:w^1:d`0:w^1:d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:" +
                "d`0:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`0:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`1:w^1:d`41:w^1:d`1:w^1:d`38:w^1:d`1:w^1:d`2:w^1:d`3:w^1:" +
                "d`1:w^1:d`3:w^1:d`4:w^1:d`1:w^1:d`1:w^1:d`2:w^1:d`2:w^1:d`17:t*1:d`369:s#2:d`0:t*1:d`168:t*1:d`190:t*1:d`205:t*1:d`121:t*1:d`20:t*1:d`50:t*1:d`141:" +
                "t*1:d`112:t*1:d`53:t*1:d`2:t*1:d`35:t*1:d`3:t*1:d`-11:t*1:d`-11:t*1:d`-11:t*1:d`-12:t*1:d`-11:t*1:d`-12:t*1:d`-12:t*1:d`-11:t*1:d`-12:t*1:d`-12:t*1:" +
                "d`-12:t*1:d`-12:t*1:d`-11:t*1:d`-12:t*1:d`-12:t*1:d`-12:t*1:d`-11:t*1:d`273:t*1:d`77:t*1:d`123:t*1:d`91:t*1:d`125:t*1:d`71:t*1:d`120:t*1:d`7:" +
                "t*1:d`15:b-1:d`-7:t*1";
        ContentValues initialValuespotatoDal = new ContentValues();
        initialValuespotatoDal.put(RECIPE_NAME, "Dal Potato");
        initialValuespotatoDal.put(RECIPE_API, potatoDalApi);
        initialValuespotatoDal.put(RECIPE_GRADIENTS_LIST, "A");


        db.insert(TABLE_RECIPE, null, initialValuesChicken);
        db.insert(TABLE_RECIPE, null, initialValuesPotatoFry);
        db.insert(TABLE_RECIPE, null, initialValuesVagetableFry);
        db.insert(TABLE_RECIPE, null, initialValuesResetFry);
        db.insert(TABLE_RECIPE, null, initialValuesPotatofryFry);
        db.insert(TABLE_RECIPE, null, initialValuesGourdFry);
        db.insert(TABLE_RECIPE, null, initialValuesDherhasaFry);
        db.insert(TABLE_RECIPE, null, initialValuesPotatoSoupFry);
        db.insert(TABLE_RECIPE, null, initialValuesChickenFry);
        db.insert(TABLE_RECIPE, null, initialValuespapaya22);
        db.insert(TABLE_RECIPE, null, initialValuespotatoDal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(db);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Adding new shop
    public void addRecipe(RecipeModel recipeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECIPE_NAME, recipeModel.getRecipe_name());
        values.put(RECIPE_API, recipeModel.getRecipe_api());
        values.put(RECIPE_GRADIENTS_LIST, recipeModel.getRecipe_gradients_list());
        db.insert(TABLE_RECIPE, null, values);
        db.close();
    }
    // Getting one recipe
    public RecipeModel getOneRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECIPE, new String[] { KEY_ID,
                        RECIPE_NAME, RECIPE_API }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        RecipeModel contact = new RecipeModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
// return shop
        return contact;
    }

    public List<RecipeModel> getAllRecipe() {
        List<RecipeModel> shopList = new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                RecipeModel shop = new RecipeModel();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setRecipeName(cursor.getString(1));
                shop.setRecipeApi(cursor.getString(2));
                shop.setRecipegradientsList(cursor.getString(3));
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
        return shopList;
    }
    public List<RecipeModel> getAllRecipeByID(long id) {
        List<RecipeModel> shopList = new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE+ " WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                RecipeModel shop = new RecipeModel();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setRecipeName(cursor.getString(1));
                shop.setRecipeApi(cursor.getString(2));
                shop.setRecipegradientsList(cursor.getString(3));
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
        return shopList;
    }
    public boolean DeleteData(int id) {
        String selectQuery = "DELETE * FROM " + TABLE_RECIPE +" WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return true;
    }

    public void update(int id, String api,String lst) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECIPE_API, api);
        values.put(RECIPE_GRADIENTS_LIST, lst);
        db.update(TABLE_RECIPE,values,KEY_ID +" = '"+ id + "'",null);
        db.close();
    }
    public void deleteSingleContact(String title){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPE,   "id=?", new String[]{title});
    }


}
