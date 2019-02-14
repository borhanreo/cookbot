package w3engineers.com.cookerbot.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;


public class ShowRecipeActivity extends AppCompatActivity {
    private TextView gradients;
    private String gradients_list = null, api = null, name = null, id = null;
    private Button details;
    private Context context;
    private DBHandler dbHandler;
    private EditText recipe_gradients_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        dbHandler=new DBHandler(this);
        gradients = (TextView) findViewById(R.id.gradients);
        details = (Button) findViewById(R.id.details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            id = extras.getString("id");
            gradients_list = extras.getString("gradients_list");
            api = extras.getString("api");
            name = extras.getString("name");

            Log.d("borhan","idd"+id+"  "+name);
            gradients.setText(gradients_list);
            this.setTitle(name);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        /*List<RecipeModel> recipeModels = dbHandler.getAllRecipeByID(Long.parseLong(id));
        for (RecipeModel recipeModel : recipeModels) {
            gradients_list = recipeModel.getRecipe_gradients_list();
            api = recipeModel.getRecipe_api();
            name = recipeModel.getRecipe_name();
            gradients.setText(gradients_list);
            this.setTitle(name);
        }*/

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("api", api);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                intent.putExtra("gradients_list", gradients_list);
                startActivity(intent);
            }
        });
    }
    public void loadApi()
    {
        List<RecipeModel> recipeModels = dbHandler.getAllRecipeByID(Integer.parseInt(id));

        for (RecipeModel recipeModel : recipeModels) {
            api = recipeModel.getRecipe_api();
            gradients_list = recipeModel.getRecipe_gradients_list();
            gradients.setText(gradients_list);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        loadApi();
    }

}
