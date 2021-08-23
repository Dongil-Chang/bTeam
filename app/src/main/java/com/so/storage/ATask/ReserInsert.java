package com.so.storage.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.so.storage.DTO.ReservationDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.so.storage.MainActivity.reservationDTO;
import static com.so.storage.common.CommonMethod.ipconfig;
import static com.so.storage.common.CommonMethod.project_path;

public class ReserInsert extends AsyncTask<Void, Void, Void> {
    ReservationDTO dto;


    //Spring 연결시 Http 통신할 때 필요한 것들
    HttpClient httpClient; //클라이언트 설정부분
    HttpPost httpPost; //Url 맵핑이 들어가는 곳
    HttpResponse httpResponse; //실제 요청하는 곳
    HttpEntity httpEntity; //파라미터나 기타 설정들이 들어가는 곳

    /*public JoinSelect(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }*/

    public ReserInsert(ReservationDTO dto) {
        this.dto = dto;
    }


    //실제 Spring과 연결하여 어떤 데이터 작업이 일어나는 부분
    @Override
    protected Void doInBackground(Void... voids) {
        //Multipart 빌더를 생성
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //문자열 및 데이터 (조회 조건이나 insert시 필요한 데이터들을 넘길 때)
            //Android -> Spring 으로 가는 데이터
            builder.addTextBody("reser_product_id", dto.getProduct_id(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("reser_product_type", dto.getProduct_type(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("reser_booking_member", dto.getBooking_member(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("reser_booking_start", dto.getBooking_start(), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("reser_booking_end", dto.getBooking_end(), ContentType.create("Multipart/related","UTF-8"));
            // builder.addTextBody("reser_product_used", dto.getProduct_using(), ContentType.create("Multipart/related","UTF-8"));


            //Url 만들기
            String postURL = ipconfig + project_path + "/and_reser_insert";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            //접속할 Url 초기화
            httpPost = new HttpPost(postURL);
            //조회 조건이나 데이터 설정들 넘겨줌
            httpPost.setEntity(builder.build());
            //httpPost.setEntity(dto);

            //실제 Spring Url을 요청하는 부분
            httpResponse = httpClient.execute(httpPost);

            //값을 Spring에서 받아오는 부분
            httpEntity = httpResponse.getEntity();
            //entity 이용해서 값을 받아오고 그리고 html로 리턴된 부분을 getContent이용해서받아옴
            inputStream = httpEntity.getContent();

            String result = readMessage(inputStream);

            inputStream.close();

        } catch (IOException e) {
            reservationDTO = null;
            e.printStackTrace();
        } finally {
            //통신을 완료하고 값들을 전부 비움움
            if(httpEntity != null) {
                httpEntity = null;
            }
            if(httpResponse != null) {
                httpResponse = null;
            }
            if(httpPost != null) {
                httpPost = null;
            }
            if(httpClient != null) {
                httpClient = null;
            }
        }

        return null;
    }

    private String readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        String result = "";
        //json 구조에서 다음 정보가 있는지 체크하면서 while 문을 통해 무한반복
        //어레이리스트일 경우 json {{beginArray
        //reader.beginArray();
        //dto 나 map 구조 그대로를 json으로 만든 경우는 beginObject
        reader.beginObject(); //json 리더 열어줌
        while (reader.hasNext()) {
            String tempStr = reader.nextName();
            if(tempStr.equals("result")){
                result = reader.nextString();
            } else {
                reader.skipValue();
                //reader 에 들어있는 값을 skip
            }
        }

        //reader.endArray();
        reader.endObject(); //json 리더 닫아줌
        return result;
        //리턴할 때 초기값 줘야 함. null 처리
    }

}
