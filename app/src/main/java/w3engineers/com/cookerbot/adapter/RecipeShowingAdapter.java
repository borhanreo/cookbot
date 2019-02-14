package w3engineers.com.cookerbot.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.controller.OnItemSelectCallBackListener;
import w3engineers.com.cookerbot.controller.OnItemValueChangeListener;
import w3engineers.com.cookerbot.model.RecipeDetailsModel;
import w3engineers.com.cookerbot.model.RecipeModel;

import static android.content.ContentValues.TAG;

/**
 * Created by Borhan Uddin on 3/24/2017.
 */

public class RecipeShowingAdapter extends RecyclerView.Adapter<RecipeShowingAdapter.MyViewHolder>  {
    private List<RecipeDetailsModel> recipeList;
    private OnItemSelectCallBackListener onItemSelectCallBackListener =null;
    private OnItemValueChangeListener onItemValueChangeListener =null;
    private  long timeValue;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView action_name,action_unit, action_type;
        public EditText action_value;
        public Button action_set_value;
        public MyViewHolder(View view) {
            super(view);
            action_name = (TextView) view.findViewById(R.id.action_name);
            action_type = (TextView) view.findViewById(R.id.action_type);
            action_unit = (TextView) view.findViewById(R.id.action_unit);
            action_value = (EditText) view.findViewById(R.id.action_value);
            action_set_value = (Button) view.findViewById(R.id.set_value);
        }
    }
    public RecipeShowingAdapter(List<RecipeDetailsModel> moviesList, OnItemSelectCallBackListener onItemSelectCallBackListener,OnItemValueChangeListener onItemValueChangeListener) {
        this.recipeList = moviesList;
        this.onItemSelectCallBackListener = onItemSelectCallBackListener;
        this.onItemValueChangeListener = onItemValueChangeListener;
    }
    public void delete(int position){
        recipeList.remove(position);
        notifyItemRemoved(position);
    }

    public int getSize(){
        return recipeList.size();
    }

    public RecipeDetailsModel getItem(int v)
    {
        return recipeList.get(v);
    }

    public void updateItem(int position, RecipeDetailsModel recipeDetailsModel)
    {
        recipeList.remove(position);
        recipeList.add(position,recipeDetailsModel);

    }
    /*@Override
    public RecipeShowingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_recycler_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TextView recipeId = (TextView) view.findViewById(R.id.key_id);
                TextView recipeName = (TextView) view.findViewById(R.id.recipeName);
                TextView recipeApi = (TextView) view.findViewById(R.id.recipeApi);
                TextView recipeGradientsList = (TextView) view.findViewById(R.id.recipeGradientsList);
                onItemSelectCallBackListener.back(Integer.parseInt(recipeId.getText().toString()),recipeName.getText().toString(),recipeApi.getText().toString(),recipeGradientsList.getText().toString());*/
            }
        });
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final RecipeShowingAdapter.MyViewHolder holder, final int position) {
        final RecipeDetailsModel recipeDetailsModel = recipeList.get(position);
        holder.action_name.setText(recipeDetailsModel.getAction_name());
        holder.action_value.setText(recipeDetailsModel.getAction_value()+"");
        holder.action_unit.setText(recipeDetailsModel.getAction_unit());
        holder.action_type.setText(recipeDetailsModel.getAction_type()+"");
        //Log.d("borhan-1", recipeDetailsModel.getAction_type()+"");
        //timeValue= recipeDetailsModel.getAction_value();
        holder.action_set_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_text = holder.action_value.getText().toString().trim();
                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
                {}
                else
                {
                    //EditText is not empty
                    timeValue = Long.parseLong(holder.action_value.getText().toString());
                    //Log.d(TAG," "+tValue);
                }
                onItemValueChangeListener.back(position,timeValue);
            }
        });
       /* holder.action_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long tValue=-1;
                String ed_text = holder.action_value.getText().toString().trim();

                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
                {
                    //EditText is empty
                }
                else
                {
                    //EditText is not empty
                    tValue = Long.parseLong(holder.action_value.getText().toString());
                    Log.d(TAG," "+tValue);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }

    @Override
    public int getItemCount() {
      return   recipeList.size();
    }
}
