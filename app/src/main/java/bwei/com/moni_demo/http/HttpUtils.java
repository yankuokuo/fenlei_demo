package bwei.com.moni_demo.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static HttpUtils mhttpUtils;
    private final OkHttpClient okHttpClient;
    private final Handler myhandler;
    private HttpUtils(){
        myhandler = new Handler(Looper.getMainLooper());
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .build();

    }
    public static HttpUtils getintence(){
        if (mhttpUtils==null){
            synchronized (HttpUtils.class){
                if (mhttpUtils==null){
                    return mhttpUtils=new HttpUtils();
                }
            }
        }
        return mhttpUtils;
    }
//get
    public void DoGet(String url,final HttpCallBack httpCallBack){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
       final Call call = okHttpClient.newCall(request);
       call.enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                  final String json= response.body().string();
                  myhandler.post(new Runnable() {
                      @Override
                      public void run() {
                        httpCallBack.OnRespose(json);
                      }
                  });
                }
           }
       });
    }
    public void DoPost(String url, Map<String,String>map,final HttpCallBack httpCallBack){
      FormBody.Builder builder=  new FormBody.Builder();
        if (map!=null){
            for(String key:map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        FormBody formBody=builder.build();
       Request request= new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
      Call call= okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    final String json=response.body().string();
                    myhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.OnRespose(json);
                        }
                    });
                }
            }
        });
    }
    public interface HttpCallBack{
        void onFailure(String error);
        void OnRespose(String json);
    }
}
