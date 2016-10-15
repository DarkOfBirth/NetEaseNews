package com.example.dllo.neteasenews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class HeadtiaoFragment extends android.support.v4.app.Fragment {

    private MyHeadAdapter adapter;
    private PullToRefreshListView lv;
    private ArrayList<HeadBean> marrlist;
    int a = 1;
    private DBTools tools;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_headtiao, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lv = (PullToRefreshListView) view.findViewById(R.id.lv_head);
        adapter = new MyHeadAdapter(getActivity());
        lv.setAdapter(adapter);
        marrlist = new ArrayList<>();
        HeadAsynctask asynctask = new HeadAsynctask();
        String url = "http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html";
        asynctask.execute(url);
        super.onViewCreated(view, savedInstanceState);

        //判断是否断网
        boolean result = isNetWorkAvailable(getActivity());

        if (result == false){
            DBTools tools1 = new DBTools(getActivity());
            Toast.makeText(getActivity(), "亲, 没有网络哦", Toast.LENGTH_SHORT).show();
            MyHeadAdapter headAdapter = new MyHeadAdapter(getActivity());
            lv.setAdapter(headAdapter);
            headAdapter.setMbean(tools1.queryHeadTable());
        }


        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                marrlist.clear();
                HeadAsynctask asynctask1 = new HeadAsynctask();
                asynctask1.execute("http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html");

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                HeadAsynctask asynctask1 = new HeadAsynctask();
                asynctask1.execute("http://c.3g.163.com/nc/article/headline/T1348647909107/" + 20 * a +"" + "-" + (20 * a + 20)+ "" + ".html");

                a++;
            }
        });

    }
    class HeadAsynctask extends AsyncTask<String, Integer, ArrayList<HeadBean>>{

        private HeadBean bean;

        @Override
        protected ArrayList<HeadBean> doInBackground(String... params) {
            //解析
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream is = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(reader);

                    String result = new String();
                    String line ="";

                    while((line = bufferedReader.readLine())!= null){
                        result += line;
                    }
                    bufferedReader.close();
                    reader.close();
                    is.close();
                    connection.disconnect();
                    JSONObject object = new JSONObject(result);
                    if (object.has("T1348647909107")){
                        JSONArray array = object.getJSONArray("T1348647909107");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = (JSONObject) array.get(i);
                            bean = new HeadBean();
                            if (object1.has("title")){
                                bean.setTitle(object1.getString("title"));
                            }
                            if (object1.has("imgsrc")){
                                bean.setImgUrl(object1.getString("imgsrc"));
                            }





                            marrlist.add(bean);
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return marrlist;
        }

        @Override
        protected void onPostExecute(ArrayList<HeadBean> headBeen) {
            adapter.setMbean(headBeen);
            adapter.notifyDataSetChanged();
            lv.onRefreshComplete();

        }

    }

    public boolean isNetWorkAvailable (Context context) {

        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;



    }

    @Override
    public void onPause() {

        super.onPause();
        tools = new DBTools(getActivity());

        for (int i = 0; i < marrlist.size(); i++) {
        tools.insertHeadtiaoTable(marrlist.get(i));
        }
    }
}
