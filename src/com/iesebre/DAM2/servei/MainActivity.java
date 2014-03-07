package com.iesebre.DAM2.servei;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.Menu;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
 
public class MainActivity extends Service
{
    private static final String TestServicio = null;
	public static Activity ACTIVIDAD;
    private Timer timer = null;
 
    public static void establecerActividadPrincipal(Activity actividad)
    {
        TestServicio.ACTIVIDAD=actividad;
    }
 
    public void onCreate()
    {
        super.onCreate();
 
        // Iniciamos el servicio
        this.iniciarServicio();
 
        Log.i(getClass().getSimpleName(), "Servicio iniciado");
    }
 
    public void onDestroy()
    {
        super.onDestroy();
 
        // Detenemos el servicio
        this.finalizarServicio();
 
        Log.i(getClass().getSimpleName(), "Servicio detenido");
    }
 
    public IBinder onBind(Intent intent)
    {
        // No usado de momento, sólo se usa si se va a utilizar IPC
        // (Inter-Process Communication) para comunicarse entre procesos
        return null;
    }
 
    public void iniciarServicio()
    {
        try
        {
            Log.i(getClass().getSimpleName(), "Iniciando servicio...");
 
            // Creamos el timer
            this.timer=new Timer();
 
            // Configuramos lo que tiene que hacer
            this.timer.scheduleAtFixedRate ( new TimerTask() { public void run() { ejecutarTarea(); } }, 0, 1000 // Cada segundo );
 
            Log.i(getClass().getSimpleName(), "Temporizador iniciado");
        }
        catch(Exception e)
        {
            Log.i(getClass().getSimpleName(), e.getMessage());
        }
    }
 
    public void finalizarServicio()
    {
        try
        {
            Log.i(getClass().getSimpleName(), "Finalizando servicio...");
 
            // Detenemos el timer
            this.timer.cancel();
 
            Log.i(getClass().getSimpleName(), "Temporizador detenido");
        }
        catch(Exception e)
        {
            Log.i(getClass().getSimpleName(), e.getMessage());
        }
    }
 
    private void ejecutarTarea()
    {
        Log.i(getClass().getSimpleName(), "Ejecutando tarea...");
 
        // Reflejamos la tarea en la actividad principal
        TestServicio.ACTIVIDAD.runOnUiThread ( new Runnable()
        {
            public void run()
            {
                TextView ejecuciones=(TextView)TestServicio.ACTIVIDAD.findViewById(R.id.TextView01);
                ejecuciones.append(".");
            }
        } );
    }
}