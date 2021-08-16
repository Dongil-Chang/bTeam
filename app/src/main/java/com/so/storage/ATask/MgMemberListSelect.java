package com.so.storage.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.so.storage.Adapter.MemberListAdapter;
import com.so.storage.DTO.MemberUserDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.so.storage.common.CommonMethod.ipconfig;
import static com.so.storage.common.CommonMethod.project_path;

// doInBackground 파라미터 타입, onProgressUpdate파라미터 타입, onPostExecute 파라미터 타입 순서
// AsyncTask <Params, Progress, Result> 순서임
public class MgMemberListSelect extends AsyncTask<Void, Void, Void> {
    // 생성자
    ArrayList<MemberUserDTO> myItemArrayList;
    MemberListAdapter adapter;

    public MgMemberListSelect(ArrayList<MemberUserDTO> myItemArrayList, MemberListAdapter adapter) {
        this.myItemArrayList = myItemArrayList;
        this.adapter = adapter;
    }


    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        myItemArrayList.clear();
        String result = "";
        String postURL = ipconfig + project_path + "/and_memberList";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            String jsonStr = stringBuilder.toString();

            inputStream.close();*/

        } catch (Exception e) {
            Log.d("Sub1", e.getMessage());
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //Log.d("Sub1", "List Select Complete!!!");

        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                myItemArrayList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public MemberUserDTO readMessage(JsonReader reader) throws IOException {
        String member_code = "";
        String id = "";
        String pw = "";
        String name = "";
        String email = "";
        String addr = "";
        String tel = "";
        String birth = "";
        String naver_login = "";
        String kakao_login = "";
        String commcode = "";
        String subcode = "";

        reader.beginObject(); //json 리더 열어줌
        while (reader.hasNext()) {
            String tempStr = reader.nextName();

            if(tempStr.equals("member_code")){
                member_code = reader.nextString();
            } else if(tempStr.equals("id")) {
                id = reader.nextString();
            } else if(tempStr.equals("pw")) {
                pw = reader.nextString();
            } else if(tempStr.equals("name")) {
                name = reader.nextString();
            } else if(tempStr.equals("email")) {
                email = reader.nextString();
            } else if(tempStr.equals("addr")) {
                addr = reader.nextString();
            } else if(tempStr.equals("tel")) {
                tel = reader.nextString();
            } else if(tempStr.equals("birth")) {
                birth = reader.nextString();
            } else if(tempStr.equals("naver_login")) {
                naver_login = reader.nextString();
            } else if(tempStr.equals("kakao_login")) {
                kakao_login = reader.nextString();
            } else if(tempStr.equals("commcode")) {
                commcode = reader.nextString();
            } else if(tempStr.equals("subcode")) {
                subcode = reader.nextString();
            } else {
                reader.skipValue();
                //reader 에 들어있는 값을 skip
            }
        }
        reader.endObject();
        return new MemberUserDTO(member_code, id, pw, name, email, addr, tel, birth, naver_login, kakao_login, commcode, subcode);

    }

    /*public List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<Double>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    public User readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                username = reader.nextString();
            } else if (name.equals("followers_count")) {
                followersCount = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new User(username, followersCount);
    }*/

}
