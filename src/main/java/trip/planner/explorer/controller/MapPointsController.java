package trip.planner.explorer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import trip.planner.explorer.model.MapPoint;
import trip.planner.explorer.service.StringToMapPointObjParsable;

import java.util.List;

@RestController
public class MapPointsController {
    @Autowired
    private StringToMapPointObjParsable stmpop;
    public static String incommingFromPostman;

    public static String incommingString1 = "Colruyt\n" +
            "https://goo.gl/maps/8wcsfGZaxy4bwxRYA\n" +
            "Jerusalemstraat 8/16, 1030 Schaarbeek, Belgium\n";

    public static String incommingString2 = "Комиксы \n" +
            "Multi BD\n" +
            "+32 2 513 72 35\n" +
            "https://goo.gl/maps/MYkbCyMTwva2SwjPA\n" +
            "Bd Anspach 122, 1000 Bruxelles, Belgium\n";

    public static String incommingString3 = "Комиксы \n" +
            "Multi BD\n" +
            "+32 2 513 72 35\n" +
            "Bd Anspach 122, 1000 Bruxelles, Belgium\n";


//    @RequestMapping(path = "/api")
//    public List<String> getStringReturnObj(){
//        System.out.println("just do it");
//        return List.of("just do it");
//    }

    @PostMapping(path = "/a")
    public MapPoint parseStringToObjMapPoint(@RequestBody String incommingString){
        MapPoint mp = stmpop.doParse(incommingString);
        System.out.println("parseStringToObjMapPoint: "+mp.toString());
        return mp;
    }


    // три способа как передавать данные в свой сервис - или тело,
    // или параметры url path (?имя параметра = значению)
    // или часть url  - его имя указало в path
    // вот наш пример: http://localhost:4777/getIncommingFromPostman/12?username_bla=tolik
//    @PostMapping(path = "/getIncommingFromPostman/{topicId}", consumes = MediaType.TEXT_PLAIN_VALUE)
//    public void getIncommingFromPostman(@RequestBody String strings,
//                                        @RequestParam("username_bla") String username,
//                                        @PathVariable int topicId
//                                ){
//
//        System.out.println("parseStringToObjMapPoint: " + strings + " username_bla: " + username);
//    }
}
