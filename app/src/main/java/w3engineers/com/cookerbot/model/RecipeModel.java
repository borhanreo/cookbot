package w3engineers.com.cookerbot.model;

/**
 * Created by borhan on 2/22/2017.
 */

public class RecipeModel {
    private int id;
    private String recipe_name;
    private String recipe_api;
    private String recipe_gradients_list;

    public RecipeModel()
    {

    }
    public RecipeModel(int id,String recipe_name,String recipe_api, String recipe_gradients_list)
    {
        this.id = id;
        this.recipe_name = recipe_name;
        this.recipe_api = recipe_api;
        this.recipe_gradients_list  = recipe_gradients_list;
    }

    public void setRecipeName(String recipe_name)
    {
        this.recipe_name = recipe_name;
    }
    public void setRecipeApi(String recipe_api)
    {
        this.recipe_api = recipe_api;
    }
    public void setRecipegradientsList(String recipe_gradients_list)
    {
        this.recipe_gradients_list = recipe_gradients_list;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public int getId()
    {
        return this.id;
    }
    public String getRecipe_name()
    {
        return this.recipe_name;
    }
    public String getRecipe_api()
    {
        return this.recipe_api;
    }
    public String getRecipe_gradients_list()
    {
        return this.recipe_gradients_list;
    }
}
