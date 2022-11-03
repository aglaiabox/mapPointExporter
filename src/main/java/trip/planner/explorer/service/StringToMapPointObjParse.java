package trip.planner.explorer.service;

import org.springframework.stereotype.Component;
import trip.planner.explorer.model.MapPoint;

@Component
public class StringToMapPointObjParse implements StringToMapPointObjParsable {
    private MapPoint mapPoint = new MapPoint();
    @Override
    public MapPoint doParse(String s) {
        String [] strings = s.split("\n");

        // find if there is a link to google map. Save index od
        int lineNumberContaintsUrl = findLineNumberContainsLink(strings);
        if (lineNumberContaintsUrl<0) {
            System.out.println("no link to google map");
            return null;
        }

        // check lines before link and assign them to the fills of obj mapPoint
        if (lineNumberContaintsUrl>0) {
            assignLinesBeforeLink(strings, lineNumberContaintsUrl);
        }

        // check lines after link and assign them to the fills of obj mapPoint
        assignLinesAfterLink(strings, lineNumberContaintsUrl+1);


        return mapPoint;
    }

    private void assignLinesAfterLink(String[] strings, int cursor) {
        if (cursor<strings.length && isContainDigitsAndCLetters(strings[cursor])){
            mapPoint.setAddress(strings[cursor]);
            System.out.println("set address: "+strings[cursor]);
            cursor++;
        }
        for (int i = cursor; i < strings.length; i++) {
            System.out.println("add comment i = "+i);
            mapPoint.setAddToComment(strings[i]+"\n");
            System.out.println("add comment. Now commetns is: "+mapPoint.getComment());
            cursor++;
        }

    }

    private void assignLinesBeforeLink(String [] strings, int lineNumberContaintsUrl){

        int cursor = lineNumberContaintsUrl-1;
        // find phone number
        if (strings[cursor].startsWith("+")&&isContain10Digits(strings[cursor])){
            mapPoint.setTelephone(strings[cursor]);
            System.out.println("set telephone: "+strings[cursor]);
            cursor--;
        }


        // assign another lines:
        // if just one - it's name of the place
        if (cursor>0){
            mapPoint.setName(strings[cursor]);
            System.out.println("set name: "+strings[cursor]);
            cursor--;

            if (cursor>=0) {
                mapPoint.setGroupName(strings[0]);
                System.out.println("set group name: "+strings[cursor]);

                if (cursor>0){
                    for (int i = 0; i < cursor; i++) {
                        mapPoint.setAddToComment(strings[i]+"\n");
                    }
                    System.out.println("add comment. Now commetns is: "+mapPoint.getComment());
                }

            }
        }

        //if more
        if (cursor>0)
            for (int i = lineNumberContaintsUrl-1; i >= 0 ; i--) {
                mapPoint.setName(strings[cursor]);
                System.out.println("set name: "+strings[cursor]);
            }
    }
    private int findLineNumberContainsLink (String [] strings){
        for (int i = 0; i < strings.length; i++) {
            String sInWork = strings [i];
            if (sInWork.contains("https://goo.gl/maps")){
                mapPoint.setLink(sInWork);
                System.out.println("set link "+strings[i]);
                return i;
            }
        }
        return -1;
    }



    private boolean isContain10Digits (String s){
        char [] chars = s.toCharArray();
        int howMuch = 0;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]))
                howMuch++;
            if (howMuch>9)
                return true;
        }
        return false;
    }

    private boolean isContainDigitsAndCLetters (String s){
        char [] chars = s.toCharArray();
        int howMuchDig = 0;
        int howMuchLetters = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch))
                howMuchDig++;
            if (Character.isAlphabetic(ch))
                howMuchLetters++;
            if (howMuchDig>4 && howMuchLetters>10){
                System.out.println("this is address: dig: "+howMuchDig+", let: "+howMuchLetters);
                return true;
            }

        }
        return false;
    }


}
