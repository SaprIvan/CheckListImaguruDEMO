package com.example.checklistimaguru;

import android.content.res.Resources;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CheckList implements Serializable {
    private String logo;
    private String room = "Не установлено";
    private Map<Integer, String> roomName = new HashMap<>();
    private Map<String, String> roomColor = new HashMap<String, String>();
    private Map<Integer, String> seatingMap = new HashMap<>();
    private Map<Integer, String> projector = new HashMap<>();
    private Map<Integer, String> computer = new HashMap<>();
    private Map<Integer, String> presenter = new HashMap<>();
    private Map<Integer, String> speakers = new HashMap<>();
    private Map<Integer, String> coffeePause = new HashMap<>();
    private Map<Integer, String> food = new HashMap<>();
    private Map<Integer, String> flipChart = new HashMap<>();
    private Map<Integer, String> registrationToEvent = new HashMap<>();
    private Map<Integer, String> registrationOnline = new HashMap<>();
    private Map<Integer, String> noticeOfChange = new HashMap<>();
    int ID;
    int IBLOCK_ID;
    String NAME;
    String IBLOCK_SECTION_ID;
    int CREATED_BY; //ID пользователя создавшего чек-лист
    String BP_PUBLISHED; //опубликовано
    String PREVIEW_TEXT;//Комментарии
    String PREVIEW_TEXT_TYPE;
    Map<Integer, String> PROPERTY_1491; //Дата и время начала мероприятия
    Map<Integer, String> PROPERTY_1493; //Дата и время окончания мероприятия
    Map<Integer, Integer> PROPERTY_1375; //Площадка
    Map<Integer, String> PROPERTY_1379; //Стулья
    Map<Integer, String> PROPERTY_1377; //Столы
    Map<Integer,Integer> PROPERTY_1381; //Рассадка в аудитории
    Map<Integer, String> PROPERTY_1383; //РАссадка на сцене
    Map<Integer, Integer> PROPERTY_1385; // проектор
    Map<Integer, Integer> PROPERTY_1387; //Компьютер
    Map<Integer, Integer> PROPERTY_1389; //Презентер
    Map<Integer, String> PROPERTY_1495; //Микрофон
    Map<Integer, Integer> PROPERTY_1497; //Колонки
    Map<Integer, Integer> PROPERTY_1397; //Кофе-пауза
    Map<Integer, Integer> PROPERTY_1399; //Питание
    Map<Integer, String> PROPERTY_1499; //Время и место кофе-паузы
    Map<Integer, Integer> PROPERTY_1395; //Флипчарт, маркеры, стерка
    Map<Integer, String> PROPERTY_1405;//Видеосъемка
    Map<Integer, String> PROPERTY_1501;//Online-трансляция
    Map<Integer, Integer> PROPERTY_1409; //Регистрация на ивент
    Map<Integer, String> PROPERTY_1503;//Наша помощь при регистрации
    Map<Integer, String> PROPERTY_1413;//Анонс: сайт, рассылка
    Map<Integer, Integer> PROPERTY_1417; //Регистрация онлайн
    Map<Integer, Integer> PROPERTY_1419; //Уведомлять об изменении

    private String getValue (Map<Integer, String> tmp){
        String data;
        if (tmp!=null){
        data = tmp.values().toString();
        data = data.substring(1, data.length()-1);
        }else
            data = "Не установлено";
        return data;
    }
    private String getValue (Map<Integer,Integer> tmp, Map<Integer,String> value){ //Map PROPERTY_NNN, Map RealValue
        Integer id;
        String valueById = "Не установлено";
        if (tmp!=null) {
            for (Map.Entry<Integer, Integer> entry : tmp.entrySet()) {
                id = entry.getValue();
                valueById = value.get(id);
            }
        } else valueById = "Не установлено";
       return valueById;
    }
    String getColor(){
        int c = 0;

        String[] roomsColors = {"#95a5a6","#f39c12","#2D7D9A","#3498db","#c0392b","#16a085","#34495e","#d35400","#f1c40f","#d35400","#3498db","#E53935","#7f8c8d"};
        for (int i = 1211; i < 1236; i+=2) {
            roomColor.put(roomName.get(i), roomsColors[c]);
            c++;
        }
        return roomColor.get(room);
    }
    String getNAME(){
        return this.NAME;
    }

    String getRoom(){
        roomName.put(1211, "Global");
        roomName.put(1213, "California");
        roomName.put(1215, "Nordic");
        roomName.put(1217, "Forum");
        roomName.put(1219, "London");
        roomName.put(1221, "Library");
        roomName.put(1223, "Baltic");
        roomName.put(1225, "New York");
        roomName.put(1227, "Design room");
        roomName.put(1229, "Coding room");
        roomName.put(1231, "Forum");
        roomName.put(1233, "Madrid");
        roomName.put(1235, "См. комментарии");

        room = getValue(PROPERTY_1375, roomName);
        return room;
    }
    String getDate(){
        String date;
        date = PROPERTY_1491.values().toString();
        date = date.substring(1,11);
        return date;
    }
    String getBeginTime(){
        String beginTime;
        beginTime = PROPERTY_1491.values().toString();
        beginTime = beginTime.substring(12,17);
        return beginTime;
    }
    String getEndTime(){
        String endTime;
        endTime = PROPERTY_1493.values().toString();
        endTime = endTime.substring(12,17);
        return endTime;
    }
    String getLogo(){
        logo = room.substring(0,1);
        return logo;
    }
    String getChairs(){
        String value = "";
        value = getValue(PROPERTY_1379);
        return value;
    }
    String getTables(){
        return getValue(PROPERTY_1377);
    }
    String getSeating(){
        seatingMap.put(1237, "Амфитеатр");
        seatingMap.put(1239, "Полукругом");
        seatingMap.put(1241, "Полукругом со столами");
        seatingMap.put(1243, "Учебный класс");
        seatingMap.put(1245, "См. комментарии");

        return getValue(PROPERTY_1381, seatingMap);
    }
    String getScene(){
        return getValue(PROPERTY_1383);
    }
    String getProjector(){
        String tmp;
        projector.put(1247, "да");
        projector.put(1249, "нет");
        projector.put(1251, "телевизор");
        projector.put(1253, "См. комментарии");
        tmp = getValue(PROPERTY_1385,projector);
        return tmp;
    }
    String getComputer(){
        computer.put(1255,"наш");
        computer.put(1257,"свой, с переходником");
        computer.put(1259,"свой, наш переходник");
        computer.put(1261,"не нужен");
        computer.put(1263,"См. комментарии");
        return getValue(PROPERTY_1387, computer);
    }
    String getPresenter(){
        presenter.put(1265,"да");
        presenter.put(1267,"нет");
        presenter.put(1269,"свой");
        presenter.put(1271,"См. комментарии");
        return getValue(PROPERTY_1389, presenter);
    }
    String getMicrophone(){
        return getValue(PROPERTY_1495);
    }
    String getSpeakers(){
        speakers.put(1427, "да");
        speakers.put(1429, "нет");
        speakers.put(1431, "См. комментарии");
        return getValue(PROPERTY_1497, speakers);
    }
    String getCoffeePause(){
        coffeePause.put(1285, "См. комментарии");
        coffeePause.put(1287, "1,5$");
        coffeePause.put(1289, "3$");
        coffeePause.put(1291, "6$");
        coffeePause.put(1293, "8$");
        return getValue(PROPERTY_1397, coffeePause);
    }
    String getFood(){
        food.put(1295, "Обед");
        food.put(1297, "Ужин");
        food.put(1299, "Пицца");
        food.put(1301, "См. комментарии");
        return getValue(PROPERTY_1399, food);
    }
    String getTimeAndPlaceCoffee(){
        return getValue(PROPERTY_1499);
    }
    String getFlipChart(){
        flipChart.put(1279, "да");
        flipChart.put(1281, "Нет");
        flipChart.put(1283, "См. комментарии");
        return getValue(PROPERTY_1395, flipChart);
    }
    String getVideoRecording(){
        return getValue(PROPERTY_1405);
    }
    String getVideoTranslation(){
        return getValue(PROPERTY_1501);
    }
    String getRegistrationToEvent(){
        registrationToEvent.put(1303, "на первом этаже");
        registrationToEvent.put(1305, "на втором этаже");
        registrationToEvent.put(1307, "в помещении");
        return getValue(PROPERTY_1409, registrationToEvent);
    }
    String getHelpRegistration(){
        return getValue(PROPERTY_1503);
    }
    String getAnnouncement(){
        return getValue(PROPERTY_1413);
    }
    String getRegistrationOnline(){
        registrationOnline.put(1309, "у заказчика");
        registrationOnline.put(1311, "сайт IMAGURU");
        registrationOnline.put(1313, "См. комментарии");
        return getValue(PROPERTY_1417, registrationOnline);
    }
    String getNoticeOfChange(){
        noticeOfChange.put(1315, "Да");
        noticeOfChange.put(1317, "Нет");
        return getValue(PROPERTY_1419, noticeOfChange);
    }
    String getComments(){
        return PREVIEW_TEXT;
    }
}
