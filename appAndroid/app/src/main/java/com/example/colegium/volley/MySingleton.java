package com.example.colegium.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {


    public static MySingleton instance;
    public RequestQueue requestQueue;
    public static Context myContext;

    public MySingleton(Context context){

        myContext = context;
        requestQueue = getRequestQueue();
    }

    /**
     * metodo que obtiene la solicitud hecha al volley
     * @return requestQueue
     */
    public RequestQueue getRequestQueue(){


        if(requestQueue ==null){

            requestQueue = Volley.newRequestQueue(myContext.getApplicationContext());

        }return requestQueue;
    }

    /**
     * metodo que retorna la instancia obtenida desde el context
     * @param context
     * @return
     */
    public static synchronized MySingleton getInsant(Context context){

        if(instance == null){

            instance = new MySingleton(context);

        }
        return instance;
    }

    /**
     * Metodo que agrega la solicitud obtenida, a una cola
     * @param request
     * @param <T>
     */
    public<T> void addTorequestQueue(Request<T> request){

        requestQueue.add(request);

    }




}
