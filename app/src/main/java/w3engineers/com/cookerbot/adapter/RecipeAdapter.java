package w3engineers.com.cookerbot.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.controller.OnItemLongClickCallBackListener;
import w3engineers.com.cookerbot.controller.OnItemSelectCallBackListener;
import w3engineers.com.cookerbot.model.RecipeModel;

import static android.content.ContentValues.TAG;

/**
 * Created by borhan on 2/22/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    private List<RecipeModel> recipeList;
    private OnItemSelectCallBackListener onItemSelectCallBackListener =null;
    private OnItemLongClickCallBackListener  onItemLongClickCallBackListener =null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView keyId, recipeName, recipeApi,recipeGradientsList;

        public MyViewHolder(View view) {
            super(view);
            keyId = (TextView) view.findViewById(R.id.key_id);
            recipeApi = (TextView) view.findViewById(R.id.recipeName);
            recipeName = (TextView) view.findViewById(R.id.recipeApi);
            recipeGradientsList = (TextView) view.findViewById(R.id.recipeGradientsList);
        }
    }


    public RecipeAdapter(List<RecipeModel> moviesList, OnItemSelectCallBackListener onItemSelectCallBackListener, OnItemLongClickCallBackListener onItemLongClickCallBackListener) {
        this.recipeList = moviesList;
        this.onItemSelectCallBackListener = onItemSelectCallBackListener;
        this.onItemLongClickCallBackListener = onItemLongClickCallBackListener;
    }
    public void delete(int position){
        recipeList.remove(position);
        notifyItemRemoved(position);
    }

    public int getSize(){
        return recipeList.size();
    }
    /*@Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list, parent, false);

        return new MyViewHolder(itemView);
    }*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView recipeId = (TextView) view.findViewById(R.id.key_id);
                TextView recipeName = (TextView) view.findViewById(R.id.recipeName);
                TextView recipeApi = (TextView) view.findViewById(R.id.recipeApi);
                TextView recipeGradientsList = (TextView) view.findViewById(R.id.recipeGradientsList);
                onItemSelectCallBackListener.back(Integer.parseInt(recipeId.getText().toString()),recipeName.getText().toString(),recipeApi.getText().toString(),recipeGradientsList.getText().toString());

            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView recipeId = (TextView) v.findViewById(R.id.key_id);
                TextView recipeName = (TextView) v.findViewById(R.id.recipeName);
                TextView recipeApi = (TextView) v.findViewById(R.id.recipeApi);
                TextView recipeGradientsList = (TextView) v.findViewById(R.id.recipeGradientsList);
                onItemLongClickCallBackListener.backLong(Integer.parseInt(recipeId.getText().toString()),recipeName.getText().toString(),recipeApi.getText().toString(),recipeGradientsList.getText().toString());


                return false;
            }
        });

        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecipeModel recipeModel = recipeList.get(position);
        holder.keyId.setText(recipeModel.getId()+"");
        holder.recipeApi.setText(recipeModel.getRecipe_name());
        holder.recipeName.setText(recipeModel.getRecipe_api());
        holder.recipeGradientsList.setText(recipeModel.getRecipe_gradients_list());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
