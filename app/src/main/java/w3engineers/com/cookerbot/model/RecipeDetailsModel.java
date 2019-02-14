package w3engineers.com.cookerbot.model;

/**
 * Created by Borhan Uddin on 3/24/2017.
 */

public class RecipeDetailsModel {
    private int action_type;
    private String action_name;
    private long action_value;
    private String action_unit;

    public RecipeDetailsModel()
    {

    }
    public RecipeDetailsModel(String action_name,long action_value, String action_unit,int action_type)
    {
        this.action_name = action_name;
        this.action_value = action_value;
        this.action_unit = action_unit;
        this.action_type  = action_type;
    }

    public void setAction_name(String action_name)
    {
        this.action_name = action_name;
    }
    public void setAction_value(long action_value)
    {
        this.action_value = action_value;
    }
    public void setAction_unit(String action_unit)
    {
        this.action_unit = action_unit;
    }
    public void setAction_type(int action_type)
    {
        this.action_type=action_type;
    }
    public String getAction_name()
    {
        return this.action_name;
    }
    public long getAction_value()
    {
        return this.action_value;
    }
    public String getAction_unit()
    {
        return this.action_unit;
    }
    public int getAction_type()
    {
        return this.action_type;
    }
}
