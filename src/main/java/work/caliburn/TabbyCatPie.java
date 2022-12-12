package work.caliburn;

import java.util.ArrayList;

public class TabbyCatPie {
    private ArrayList<String> my_valid_names;

    public TabbyCatPie() {
        my_valid_names = new ArrayList<>();
        my_valid_names.add("派派");
        my_valid_names.add("PiePie");
        my_valid_names.add("派总");
    }
    public boolean CallMe(String msg){
        for(String name : my_valid_names){
            if(msg.contains(name))
                return true;
        }
        return false;
    }
}
