package in.knowledgeportal.myclass.ccpt.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.knowledgeportal.myclass.ccpt.Classes.Announcement;
import in.knowledgeportal.myclass.ccpt.Classes.Marks;
import in.knowledgeportal.myclass.ccpt.Classes.Notes;
import in.knowledgeportal.myclass.ccpt.Classes.Students;
import in.knowledgeportal.myclass.ccpt.Classes.Test;
import in.knowledgeportal.myclass.ccpt.Classes.Toppers;
import in.knowledgeportal.myclass.ccpt.Classes.ToppersStudents;

/**
 * Created by harsh on 18-05-2015.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String OPTIONS_TABLE = "options";
    public static final String MARKS_TABLE = "marks";
    public static final String ANNOUNCEMENT_TABLE = "announcement";
    public static final String NOTES_TABLE = "notes";
    public static final String TEST_TABLE = "test";
    public static final String STUDENT_TABLE = "students";
    public static final String TOPPERS_TABLE = "toppers";
    public static final Integer DATABASE_VERSION = 3;

    //private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table options " +
                        "(id integer primary key, name text,value text)"
        );

        db.execSQL(
                "create table marks " +
                        "(id integer primary key,score integer, test_name text, topic text, date_test text, activeflag integer, highest integer, total integer, average integer, testType integer)"
        );

        db.execSQL(
                "create table announcement " +
                        " (id integer primary key, name text, activeflag integer, datecreated text)"
        );

        db.execSQL(
                "create table notes " +
                        " (id integer primary key, name text,url text, activeflag integer, datecreated text, downloadFlag integer)"
        );

        db.execSQL(
                "create table students " +
                        " (id integer primary key, name text, activeflag integer, batchID integer)"
        );

        db.execSQL(
                "create table test " +
                        "(id integer primary key, name text, topic text, date_test text, activeflag integer, maximum integer, average integer, testType integer)"
        );

        db.execSQL(
                "create table toppers " +
                        "(id integer primary key autoincrement, markID integer, studentID integer, testID integer, test integer, activeflag integer, downloadflag integer)"
        );

        insertDefaultOptionsValues(db);


    }

    private void insertDefaultOptionsValues(SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();

        // app_state = 0 : student not verified
        // app_state = -1 : student sms sent
        // app_state = 1 : student verified
        contentValues.put("id", 1);
        contentValues.put("name", "app_state");
        contentValues.put("value", "0");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 2);
        contentValues.put("name", "student_ID");
        contentValues.put("value", "0");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 3);
        contentValues.put("name", "student_name");
        contentValues.put("value", "");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 4);
        contentValues.put("name", "activation_code");
        contentValues.put("value", "");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 5);
        contentValues.put("name", "classID");
        contentValues.put("value", "12");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 6);
        contentValues.put("name", "batchID");
        contentValues.put("value", "0");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 7);
        contentValues.put("name", "mobile");
        contentValues.put("value", "");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 8);
        contentValues.put("name", "deviceID");
        contentValues.put("value", "");
        db.insert(OPTIONS_TABLE, null, contentValues);

        contentValues.put("id", 9);
        contentValues.put("name", "GCM_ID");
        contentValues.put("value", "");
        db.insert(OPTIONS_TABLE, null, contentValues);




    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS options");
        db.execSQL("DROP TABLE IF EXISTS marks");
        db.execSQL("DROP TABLE IF EXISTS announcement");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS test");
        db.execSQL("DROP TABLE IF EXISTS toppers");
        db.execSQL("DROP TABLE IF EXISTS notes");

        onCreate(db);
    }

    public String getOptionValue(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from options where id="+id+"", null );
        res.moveToFirst();

//        if(res.isAfterLast() != false){
//            return res.getString(res.getColumnIndex("value"));
//        }else
//            return null;

        //return res.getString(res.getColumnIndex("value"));
        return res.getString(2);

        }


    public void insertAnnouncement(Integer id, String name, Integer activeflag, String datecreated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("activeflag", activeflag);
        contentValues.put("datecreated", datecreated);

        db.replace(ANNOUNCEMENT_TABLE, null, contentValues);

    }

    public void insertNotes(Integer id, String name,String url, Integer activeflag, String datecreated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("url", url);
        contentValues.put("activeflag", activeflag);
        contentValues.put("downloadFlag", 0);
        contentValues.put("datecreated", datecreated);

        db.replace(NOTES_TABLE, null, contentValues);

    }

    public void insertStudent(Integer id,String name, Integer batchID, Integer activeflag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("batchID", batchID);
        contentValues.put("activeflag", activeflag);

        db.replace(STUDENT_TABLE, null, contentValues);


       /*
        Boolean flag = ifValueExists("branch", id);

        if (flag) {
            db.update("branch", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        }else{
            db.insert("branch", null, contentValues);

        }*/
    }

    public void insertMarks(Integer id, Integer score, String test_name, String topic,
                            String date_test, Integer activeflag, Integer highest, Integer total,
                            Integer average, Integer testType) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("score", score);
        contentValues.put("test_name", test_name);
        contentValues.put("topic", topic);
        contentValues.put("date_test", date_test);
        contentValues.put("activeflag", activeflag);
        contentValues.put("highest", highest);
        contentValues.put("total", total);
        contentValues.put("average", average);
        contentValues.put("testType", testType);

        contentValues.put("activeflag", activeflag);

        db.replace(MARKS_TABLE, null, contentValues);
    }

    public void insertTest(Integer id, String name, String topic, String date_test, Integer activeflag, Integer maximum, Integer average, Integer testType) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("topic", topic);
        contentValues.put("date_test", date_test);
        contentValues.put("activeflag", activeflag);
        contentValues.put("maximum", maximum);
        contentValues.put("average", average);
        contentValues.put("testType", testType);

        contentValues.put("activeflag", activeflag);

        db.replace(TEST_TABLE, null, contentValues);
    }

    public void insertToppers(Integer markID, Integer studentID, Integer testID, Integer mark, Integer activeflag, Integer downloadFlag) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("markID", markID);
        contentValues.put("studentID", studentID);
        contentValues.put("testID", testID);
        contentValues.put("test", mark);
        contentValues.put("activeflag", activeflag);


        contentValues.put("downloadFlag", downloadFlag);

        db.replace(TOPPERS_TABLE, null, contentValues);
    }


    public void updateOption(Integer id, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put("name", name);
        contentValues.put("id", id);
        contentValues.put("value", value);

        //db.replace("options", null, contentValues);
        db.update("options", contentValues, "id = ? ", new String[]{String.valueOf(id)});



       /*
        Boolean flag = ifValueExists("branch", id);

        if (flag) {
            db.update("branch", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        }else{
            db.insert("branch", null, contentValues);

        }*/
    }



    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MARKS_TABLE);
        return numRows;
    }



    public ArrayList<Marks> getAllMarks(){

        ArrayList<Marks> myMarks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from marks where activeflag = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //


            Marks temp = new Marks(res.getInt(res.getColumnIndex("id")),res.getInt(res.getColumnIndex("score")),
                    res.getString(res.getColumnIndex("test_name")),res.getString(res.getColumnIndex("topic")),
                    res.getString(res.getColumnIndex("date_test")),res.getInt(res.getColumnIndex("activeflag")),
                    res.getInt(res.getColumnIndex("highest")),res.getInt(res.getColumnIndex("total"))
                    ,res.getInt(res.getColumnIndex("average")),res.getInt(res.getColumnIndex("testType")));

            myMarks.add(temp);

            res.moveToNext();
        }

        return myMarks;
    }

    public ArrayList<Announcement> getAllAnnouncements(){

        ArrayList<Announcement> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from announcement where activeflag = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Announcement temp = new Announcement(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("name")),res.getInt(res.getColumnIndex("activeflag"))
                    ,res.getString(res.getColumnIndex("datecreated")));



            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public ArrayList<Notes> getAllNotes(){

        ArrayList<Notes> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes where activeflag = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Notes temp = new Notes(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("name")),res.getString(res.getColumnIndex("url")),
                    res.getInt(res.getColumnIndex("activeflag")),res.getString(res.getColumnIndex("datecreated"))
                    ,res.getInt(res.getColumnIndex("downloadFlag")));


            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public ArrayList<Students> getAllStudents(){

        ArrayList<Students> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students where activeflag = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Students temp = new Students(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("name")),res.getInt(res.getColumnIndex("activeflag")),
                    res.getInt(res.getColumnIndex("batchID")));

            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public Students getStudentByID(Integer id){



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from students where activeflag = 1 and id = " + id, null);
        res.moveToFirst();


            Students temp = new Students(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("name")),res.getInt(res.getColumnIndex("activeflag")),
                    res.getInt(res.getColumnIndex("batchID")));

            return temp;

    }

    public ArrayList<Test> getAllTests(){

        ArrayList<Test> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from test where activeflag = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Test temp = new Test(res.getInt(res.getColumnIndex("id")),res.getString(res.getColumnIndex("name")),
                    res.getString(res.getColumnIndex("topic")),res.getString(res.getColumnIndex("date_test")),
                    res.getInt(res.getColumnIndex("activeflag")),res.getInt(res.getColumnIndex("maximum")),
                    res.getInt(res.getColumnIndex("average")),res.getInt(res.getColumnIndex("testType")));

            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public ArrayList<Toppers> getAllToppersByTest(Integer testID){

        ArrayList<Toppers> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from toppers where activeflag = 1 and testID = "+testID, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Toppers temp = new Toppers(res.getInt(res.getColumnIndex("markID")),res.getInt(res.getColumnIndex("studentID")),
                    res.getInt(res.getColumnIndex("testID")),res.getInt(res.getColumnIndex("test")),
                    res.getInt(res.getColumnIndex("activeflag")),res.getInt(res.getColumnIndex("downloadFlag")));

            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public ArrayList<ToppersStudents> getAllToppersStudentsByTest(Integer testID){

        ArrayList<ToppersStudents> myList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from toppers where activeflag = 1 and testID = "+testID, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            //

            Students std = getStudentByID(res.getInt(res.getColumnIndex("studentID")));

            ToppersStudents temp = new ToppersStudents(res.getInt(res.getColumnIndex("test")),std.getName());

            myList.add(temp);

            res.moveToNext();
        }

        return myList;
    }

    public boolean toppersExistInDatabase(Integer testID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from toppers where activeflag = 1 and testID = "+testID, null );

        if(res.getCount()>0)
            return  true;
        else
            return false;


    }


    public void enterStudentData(String studentID, String batchID, String code, String mobile, String name) {

        updateOption(1,"-1");
        updateOption(2,studentID);
        updateOption(4,code);
        updateOption(6,batchID);
        updateOption(7, mobile);
        updateOption(3,name);




    }

    public void enterMarks(JSONArray marks_array) throws JSONException {

        Integer id;
        Integer score;
        String topic;
        String date_test;
        String test_name;
        Integer activeflag;
        Integer highest;
        Integer total;
        Integer average;
        Integer testType;

        if(marks_array.length()>0) {


            for (int i = 0; i < marks_array.length(); i++) {

                JSONObject json_data = marks_array.getJSONObject(i);

                id = json_data.getInt("id");
                score = json_data.getInt("score");
                test_name = json_data.getString("test_name");
                topic = json_data.getString("topic");
                date_test = json_data.getString("date_test");
                activeflag = json_data.getInt("activeflag");
                highest = json_data.getInt("highest");
                total = json_data.getInt("total");
                average = json_data.getInt("average");
                testType = json_data.getInt("testType");

                insertMarks(id, score, test_name, topic, date_test, activeflag, highest, total, average, testType);


            }
        }

    }

    public void enterAnnouncements(JSONArray data_array) throws JSONException {

        Integer id;
        String name;
        Integer activeflag;
        String datecreated;

        if(data_array.length()>0) {


            for (int i = 0; i < data_array.length(); i++) {

                JSONObject json_data = data_array.getJSONObject(i);

                id = json_data.getInt("id");
                name = json_data.getString("display");
                activeflag = json_data.getInt("activeflag");
                datecreated = json_data.getString("datecreated");

                insertAnnouncement(id, name, activeflag, datecreated);

            }
        }

    }

    public void enterNotes(JSONArray data_array) throws JSONException {

        Integer id;
        String name;
        String url;
        Integer activeflag;
        String datecreated;

        if(data_array.length()>0) {


            for (int i = 0; i < data_array.length(); i++) {

                JSONObject json_data = data_array.getJSONObject(i);

                id = json_data.getInt("id");
                name = json_data.getString("name");
                url = json_data.getString("url");
                activeflag = json_data.getInt("activeflag");
                datecreated = json_data.getString("datecreated");

                insertNotes(id, name, url, activeflag, datecreated);

            }
        }

    }

    public void enterStudents(JSONArray data_array) throws JSONException {

        Integer id;
        String name;
        Integer batchID;
        Integer activeflag;

        if(data_array.length()>0) {


            for (int i = 0; i < data_array.length(); i++) {

                JSONObject json_data = data_array.getJSONObject(i);

                id = json_data.getInt("id");
                name = json_data.getString("name");
                batchID = json_data.getInt("batchID");
                activeflag = json_data.getInt("activeflag");

                insertStudent(id, name, batchID, activeflag);


            }
        }

    }

    public void enterTests(JSONArray marks_array) throws JSONException {

        Integer id;
        String topic;
        String date_test;
        String name;
        Integer activeflag;
        Integer maximum;
        Integer average;
        Integer testType;

        if(marks_array.length()>0) {


            for (int i = 0; i < marks_array.length(); i++) {

                JSONObject json_data = marks_array.getJSONObject(i);

                id = json_data.getInt("id");
                name = json_data.getString("name");
                topic = json_data.getString("topic");
                date_test = json_data.getString("date_test");
                activeflag = json_data.getInt("activeflag");
                maximum = json_data.getInt("maximum");
                average = json_data.getInt("average");
                testType = json_data.getInt("testType");

                insertTest(id, name, topic, date_test, activeflag, maximum, average, testType);

            }
        }

    }

    public void enterToppers(JSONArray data_array) throws JSONException {

        Integer markID;
        Integer activeflag;
        Integer studentID;
        Integer testID;
        Integer mark;

        if(data_array.length()>0) {


            for (int i = 0; i < data_array.length(); i++) {

                JSONObject json_data = data_array.getJSONObject(i);

                markID = json_data.getInt("mark_id");
                activeflag = json_data.getInt("activeflag");
                studentID = json_data.getInt("studentID");
                testID = json_data.getInt("testID");
                mark = json_data.getInt("test");

                insertToppers(markID, studentID, testID, mark, activeflag, 0);

            }
        }

    }




}
