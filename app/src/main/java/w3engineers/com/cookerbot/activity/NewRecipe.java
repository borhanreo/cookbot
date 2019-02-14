package w3engineers.com.cookerbot.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;

public class NewRecipe extends AppCompatActivity {

    final Context context = this;
    private Button Oilbutton, reset, WaterButton, spud_grinding, create, onion, chili, salt, mixed_spice, ch_po_ot, oven_heat,ch_po_ot_2;
    private String hardwareApiString = "", oilStr = "", waterStr = "", TAG = "borhan", spudGrindingStr = "", globStr = "",heatStr="", showText="";
    EditText amount_oil, recipe_name,apiString;
    Spinner amount_heat;
    TextView selected_option;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        this.setTitle("New Recipe");
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RecipeModel> recipeModels = db.getAllRecipe();

                for (RecipeModel recipeModel : recipeModels) {
                    String log = "Id: " + recipeModel.getId() + " ,Name: " + recipeModel.getRecipe_name() + " ,Address: " + recipeModel.getRecipe_api();
                    Log.d(TAG, log);
                }
                resetRecipe();
            }
        });
        selected_option = (TextView) findViewById(R.id.selected_option);

        Oilbutton = (Button) findViewById(R.id.oil);
        spud_grinding = (Button) findViewById(R.id.spud_grinding);
        create = (Button) findViewById(R.id.create);
        recipe_name = (EditText) findViewById(R.id.recipe_name);
        apiString = (EditText) findViewById(R.id.apiString);


        onion = (Button) findViewById(R.id.onion);
        chili = (Button) findViewById(R.id.chili);
        salt = (Button) findViewById(R.id.salt);
        mixed_spice = (Button) findViewById(R.id.mixed_spice);
        ch_po_ot = (Button) findViewById(R.id.ch_pot_other);
        ch_po_ot_2 = (Button) findViewById(R.id.ch_pot_other_2);
        oven_heat = (Button) findViewById(R.id.oven_heat);

        ch_po_ot_2.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v) {
                ch_po_ot_2.setEnabled(false);
                globStr = globStr + ":s#8";

                showText = showText+", CH/PO/OT 2";
                selected_option.setText(showText);

            }
        });

        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onion.setEnabled(false);
                globStr = globStr + ":s#1";

                showText = showText+", Onion";
                selected_option.setText(showText);

            }
        });

        chili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chili.setEnabled(false);
                globStr = globStr + ":s#2";
                showText = showText+", Chili";
                selected_option.setText(showText);
            }
        });

        salt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salt.setEnabled(false);
                globStr = globStr + ":s#3";
                showText = showText+", Salt";
                selected_option.setText(showText);
            }
        });
        mixed_spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mixed_spice.setEnabled(false);
                globStr = globStr + ":s#9";
                showText = showText+", Mixed Spice";
                selected_option.setText(showText);
            }
        });
        ch_po_ot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch_po_ot.setEnabled(false);
                globStr = globStr + ":s#7";

                showText = showText+", CH/PO/OT 1";
                selected_option.setText(showText);
            }
        });
        /*onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onion.setEnabled(false);
                globStr = globStr + ":s#1";
            }
        });*/
        oven_heat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oven_heat);
                dialog.setTitle("Heat Status");
                amount_oil = (EditText) dialog.findViewById(R.id.amount_oil);
                amount_heat = (Spinner) dialog.findViewById(R.id.amount_heat);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String heatText = amount_heat.getSelectedItem().toString();
                        Log.d(TAG, " borhan " + heatText);
                        int heatType = 0;
                        if (heatText.equals("LOW")) {
                            heatType = 1;
                        } else if (heatText.equals("MEDIUM")) {
                            heatType = 2;
                        } else if (heatText.equals("HIGH")) {
                            heatType = 3;
                        }

                        heatStr = ":h%" + heatType;
                        globStr = globStr + heatStr;
                        showText = showText+", Oven Heat ("+heatText+")";
                        selected_option.setText(showText);
                        dialog.dismiss();


                    }
                });

                dialog.show();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gradientsStr = apiString.getText().toString();
                if (!isEmpty(recipe_name)) {

                    String recipeNameStr = recipe_name.getText().toString();
                    globStr = recipeNameStr + globStr;
                    db.addRecipe(new RecipeModel(1, recipeNameStr, globStr,gradientsStr));
                    Toast.makeText(context, "Recipe successfully created ", Toast.LENGTH_LONG).show();
                    resetRecipe();
                    finish();
                } else {
                    Toast.makeText(context, "Please enter your recipe name", Toast.LENGTH_LONG).show();
                }

            }
        });
        Oilbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oiltimer);
                dialog.setTitle("Oil in gm");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                amount_oil = (EditText) dialog.findViewById(R.id.amount_oil);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String strValue = amount_oil.getText().toString();
                        if (strValue == " " && strValue == null) {
                            Toast.makeText(context, "Can not be empty or null or string", Toast.LENGTH_LONG).show();
                        } else {
                            int getAmount = Integer.parseInt(strValue);
                            oilStr = ":o+" + getTimeInSecondOil(getAmount);
                            globStr = globStr + oilStr;
                            Oilbutton.setEnabled(false);
                            showText = showText+", Oil ("+strValue+" gm)";
                            selected_option.setText(showText);
                            dialog.dismiss();
                        }

                    }
                });

                dialog.show();
            }
        });
        WaterButton = (Button) findViewById(R.id.water);
        WaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oiltimer);
                dialog.setTitle("Water in ml");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                amount_oil = (EditText) dialog.findViewById(R.id.amount_oil);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String strValue = amount_oil.getText().toString();
                        if (strValue.equals("") || strValue.equals(" ")) {
                            Toast.makeText(context, "Can not be empty or null or string", Toast.LENGTH_LONG).show();
                        } else {
                            int getAmount = Integer.parseInt(strValue);
                            //waterStr="w^"+ getTimeInSecondWater(getAmount);
                            waterStr = ":w^" + getTimeInSecondWater(getAmount);
                            globStr = globStr + waterStr;
                            //WaterButton.setEnabled(false);
                            showText = showText+", Water ("+strValue+" ml)";
                            selected_option.setText(showText);
                            dialog.dismiss();
                        }

                    }
                });

                dialog.show();
            }
        });

        spud_grinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.oiltimer);
                dialog.setTitle("Spud Grinding in second");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                amount_oil = (EditText) dialog.findViewById(R.id.amount_oil);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String strValue = amount_oil.getText().toString();
                        if (strValue == " " && strValue == null) {
                            Toast.makeText(context, "Can not be empty or null or string", Toast.LENGTH_LONG).show();
                        } else {
                            int getAmount = Integer.parseInt(strValue);
                            spudGrindingStr = ":t*" + getAmount;
                            globStr = globStr + spudGrindingStr;
                            //spud_grinding.setEnabled(false);
                            showText = showText+", Spud ("+strValue+" sec)";
                            selected_option.setText(showText);
                            dialog.dismiss();
                        }

                    }
                });

                dialog.show();
            }
        });
    }

    public int getTimeInSecondWater(int gm) {
        int rtn = 0;
        int second = gm / 100;
        return second;
    }

    public int getTimeInSecondOil(int gm) {
        int rtn = 0;
        int second = gm / 10;
        return second;
    }

    public void resetRecipe() {
        Oilbutton.setEnabled(true);
        oilStr = "";
        WaterButton.setEnabled(true);
        waterStr = "";
        spud_grinding.setEnabled(true);
        spudGrindingStr = "";

        onion.setEnabled(true);
        chili.setEnabled(true);
        salt.setEnabled(true);
        mixed_spice.setEnabled(true);
        ch_po_ot.setEnabled(true);
        oven_heat.setEnabled(true);
        showText = "";
        selected_option.setText(showText);
        heatStr="";
        globStr = "";

    }

    public void showToast(String str) {
        Toast.makeText(context, "Already add this " + str, Toast.LENGTH_LONG).show();
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
