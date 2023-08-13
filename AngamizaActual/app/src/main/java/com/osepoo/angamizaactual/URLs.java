package com.osepoo.angamizaactual;

public class URLs {

    private static final String ROOT_URL = "http://192.168.137.1/angamiza/angamiza_api.php?apicall=";
    public static final String URL_FEED = "http://192.168.137.1/angamiza/angamiza_api2.php?page=";
    public static final String URL_LOGIN = ROOT_URL + "login";

    //TASKS URL
    public static final String URL_TASKS = "https://192.168.137.1/angamiza/tasks_api.php?page=";

    //TASKS LEADERS URL
    public static final String URL_TASKS_LEADERS = "https://192.168.137.1/angamiza/tasks_leader_api.php?apicall=";
    public static final String URL_TASKS_HEADERS = ROOT_URL + "tasks_header";
    public static final String URL_TASKS_TASKS = ROOT_URL + "taskss";
    public static final String URL_CARLOGTEXTS = ROOT_URL + "carlogtexts";
    public static final String URL_PERSONLOGTEXTS = ROOT_URL + "personlogtexts";

    public static final String URL_SEARCHCAR = ROOT_URL + "searchbarcar";
    public static final String URL_SEARCHPERSON = ROOT_URL + "searchbarperson";
    //JSON TAGS FEED
    public static final String TAG_PHOTO = "photo";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_POLICE_P = "police_precinct";

    //JSON TAGS TASKS
    public static final String TAG_SIGNINNAME = "signinname";
    public static final String TAG_EMPID = "empid";
    public static final String TAG_TASK = "task";
    public static final String TAG_PRIORITY = "priority";
    public static final String TAG_DATE = "date_created";

    //JSON TAGS TASKS_LEADERS
    public static final String TAG_LEADERNAME = "name";
    public static final String TAG_LEADERPHOTO = "precinct";
    public static final String TAG_LEADERPRECINCT = "photo";


}
