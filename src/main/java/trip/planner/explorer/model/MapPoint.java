package trip.planner.explorer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapPoint {
    String groupName;
    String name;
    String telephone;
    String link;
    String address;
    String comment;

    public void setAddToComment (String newPartComment){
        if (comment != null){
            comment = comment + newPartComment;
        }
        else comment = newPartComment;
    }

}

