package w3engineers.com.cookerbot.activity;

import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.adapter.RecipeShowingAdapter;
import w3engineers.com.cookerbot.common.ProjectCommon;
import w3engineers.com.cookerbot.constant.Constant;
import w3engineers.com.cookerbot.controller.OnItemSelectCallBackListener;
import w3engineers.com.cookerbot.controller.OnItemValueChangeListener;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeDetailsModel;
import w3engineers.com.cookerbot.model.RecipeModel;


public class RecipeDetailsActivity extends AppCompatActivity implements OnItemSelectCallBackListener, OnItemValueChangeListener {
    private String gradients_list = null, api = null, name = null, id = null;
    private TextView rid;
    private EditText tv_gradients;

    private List<RecipeDetailsModel> recipeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecipeShowingAdapter mAdapter;
    private RecipeShowingAdapter aditableAdapter;
    private EditText recipe_gradients_list;
    private TextView r_id;
    private Button update;
    private String TAG = "borhan RecipeDetailsActivity";
    private ProjectCommon projectCommon = new ProjectCommon();
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recipe_gradients_list = (EditText) findViewById(R.id.recipe_gradients_list);
        rid = (TextView) findViewById(R.id.r_id);
        update = (Button) findViewById(R.id.update);
        Bundle extras = getIntent().getExtras();
        dbHandler = new DBHandler(this);
        if (extras != null) {
            gradients_list = extras.getString("gradients_list");
            api = extras.getString("api");
            name = extras.getString("name");
            id = extras.getString("id");
            this.setTitle(name);
            recipe_gradients_list.setText(gradients_list);
            rid.setText(id);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecipeShowingAdapter(recipeList, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRecipeModelData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, String.valueOf(mAdapter.getItemId(3)));
                String rDetails = recipe_gradients_list.getText().toString();
                int counter = mAdapter.getSize();
                String hardwareApi="";
                for(int i=0;i<=counter-1;i++)
                {
                    RecipeDetailsModel item = mAdapter.getItem(i);

                    int tp = item.getAction_type();
                    hardwareApi=hardwareApi+":"+projectCommon.getGradients(tp)+item.getAction_value();
                    //Log.d(TAG," "+projectCommon.getGradients(tp)+item.getAction_value());
                }
                Log.d(TAG," "+hardwareApi);
                dbHandler.update(Integer.parseInt(id),hardwareApi,rDetails);
                finish();

                //mAdapter=aditableAdapter;
                //mAdapter.notifyDataSetChanged();


            }
        });


    }

    private void prepareRecipeModelData() {
        /*List<RecipeModel> recipeModels = db.getAllRecipe();
        for (RecipeModel recipeModel : recipeModels) {
            recipeModel = new RecipeModel(recipeModel.getId(), recipeModel.getRecipe_name(), recipeModel.getRecipe_api(),recipeModel.getRecipe_gradients_list());
            recipeList.add(recipeModel);
        }
        mAdapter.notifyDataSetChanged();*/

        /*RecipeDetailsModel recipeDetailsModel = new RecipeDetailsModel();
        for (int i=0;i<=10;i++)
        {
            recipeDetailsModel = new RecipeDetailsModel("Oil", i, "sec",1);
            recipeList.add(recipeDetailsModel);
        }
        mAdapter.notifyDataSetChanged();

        long itemCounter = mAdapter.getSize();
        Log.d("borhan"," total item "+itemCounter);*/
        RecipeDetailsModel recipeDetailsModel = new RecipeDetailsModel();
        Log.d(TAG," api "+api);
        String[] apiArr = api.split(":");
        for (int i = 0; i <= apiArr.length - 1; i++) {
           //

            if (apiArr[i].contains("=")) {
                String parts[] = apiArr[i].split("=");
                recipeDetailsModel = new RecipeDetailsModel("Oven On", Long.parseLong(parts[1]), "Switch", Constant.OVEN_ON);
                recipeList.add(recipeDetailsModel);
            } else if (apiArr[i].contains("|")) {
                String parts[] = apiArr[i].split("|");
                if(!parts[0].contains("|"))
                {
                    /*long actualValue;
                    if(Integer.parseInt(parts[1])<0)
                    {
                        actualValue =0;
                    }else
                    {
                        actualValue = Long.parseLong(parts[1]);
                    }*/
                    recipeDetailsModel = new RecipeDetailsModel("Oven Off",1, "Switch", Constant.OVEN_OFF);
                    recipeList.add(recipeDetailsModel);
                }

            }
            if (apiArr[i].contains("+")) {
                String parts[] = apiArr[i].split("\\+");
                long actualValue;
                if(Integer.parseInt(parts[1])<0)
                {
                    actualValue =0;
                }else
                {
                    actualValue = Long.parseLong(parts[1]);
                }
                recipeDetailsModel = new RecipeDetailsModel("Oil", actualValue, "Sec", Constant.OIL);
                recipeList.add(recipeDetailsModel);
            }
            if(apiArr[i].contains("^"))
            {
                String parts[] = apiArr[i].split("\\^");
                long actualValue;
                if(Integer.parseInt(parts[1])<0)
                {
                    actualValue =0;
                }else
                {
                    actualValue = Long.parseLong(parts[1]);
                }
                recipeDetailsModel = new RecipeDetailsModel("Water", actualValue , "Sec", Constant.WATER);
                recipeList.add(recipeDetailsModel);
            }
            if(apiArr[i].contains("`"))
            {
                String parts[] = apiArr[i].split("`");
                long actualValue;
                if(Integer.parseInt(parts[1])<0)
                {
                    actualValue =0;
                }else
                {
                    actualValue = Long.parseLong(parts[1]);
                }
                recipeDetailsModel = new RecipeDetailsModel("Delay",actualValue , "Sec", Constant.TIME_DELAY);
                recipeList.add(recipeDetailsModel);
            }
            if(apiArr[i].contains("*"))
            {
                String parts[] = apiArr[i].split("\\*");
                long actualValue;
                if(Integer.parseInt(parts[1])<0)
                {
                    actualValue =0;
                }else
                {
                    actualValue = Long.parseLong(parts[1]);
                }
                recipeDetailsModel = new RecipeDetailsModel("Spud",actualValue, "Unit", Constant.SPUD);
                recipeList.add(recipeDetailsModel);
            }
            if(apiArr[i].contains("#"))
            {
                String parts[] = apiArr[i].split("#");
                long actualValue;
                if(Integer.parseInt(parts[1])<0)
                {
                    actualValue =0;
                }else
                {
                    actualValue = Long.parseLong(parts[1]);
                }
                if(Integer.parseInt(parts[1])==1)
                {
                    recipeDetailsModel = new RecipeDetailsModel("Onion", actualValue , "Sec", Constant.SPICE);

                }else if(Integer.parseInt(parts[1])==2)
                {
                    recipeDetailsModel = new RecipeDetailsModel("Chili", actualValue , "Sec", Constant.SPICE);
                }
                else if(Integer.parseInt(parts[1])==3)
                {
                    recipeDetailsModel = new RecipeDetailsModel("Slat", actualValue , "Sec", Constant.SPICE);
                }
                else if(Integer.parseInt(parts[1])==9)
                {
                    recipeDetailsModel = new RecipeDetailsModel("Spice", actualValue , "Sec", Constant.SPICE);
                }
                else if(Integer.parseInt(parts[1])==7)
                {
                    recipeDetailsModel = new RecipeDetailsModel("Chicken", actualValue , "Sec", Constant.SPICE);
                }
                recipeList.add(recipeDetailsModel);
            }
        }
    }

    @Override
    public void back(int id, String name, String api, String gradients_list) {

    }

    @Override
    public void back(int position, long value) {

        RecipeDetailsModel item = mAdapter.getItem(position);
        item.setAction_value(value);
        Log.d(TAG, " value " + position + " " + value + "   " + item.getAction_value());
        mAdapter.updateItem(position, item);
        mAdapter.notifyDataSetChanged();
    }

    public void loadApi()
    {
        List<RecipeModel> recipeModels = dbHandler.getAllRecipeByID(Integer.parseInt(id));

        for (RecipeModel recipeModel : recipeModels) {
            api = recipeModel.getRecipe_api();
            gradients_list= recipeModel.getRecipe_gradients_list();
            recipe_gradients_list.setText(gradients_list);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        loadApi();
    }
}
