package com.example.apielephants.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConectarApiElefantes {

    //Declaramos la url base, que no cambia.
    private static String URL_BASE = "https://elephant-api.herokuapp.com/elephants";

    //Definimos el método para peticiones GET el cual se usará para la consulta de
    //información
    public static String getRequest()
    {
        HttpURLConnection http = null;
        String content = null;
        try {
            //Se forma la url más el endpoint. Así como la cabecera, que permitira decidir
            //la codificación de los datos que se están trasmitiendo.
            URL url = new URL(URL_BASE);
            http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");

            //Si el servidor devuelve un codigo 200 (HTTP_OK == 200)
            // quiere decir que ha devuelto correctamente la información solicitada.
            if( http.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                //Se codifica el texto de la respuesta como String.
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader( http.getInputStream() ));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
                reader.close();
            }
        }
        catch(Exception e) {
            System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            e.printStackTrace();
        }
        finally {

            //1.5 Se desconecta la conexión.
            if( http != null ) http.disconnect();
        }
        return content;
    }

    //Esta función define el tipo de petición POST que se usa para la modificación de
    // la información en base de datos externas. En este caso los parametros van encapsulados en
    // la petición
    public static int postRequest( String strUrl, String data )
    {
        HttpURLConnection http = null;
        int responseCode = -1;
        try {
            URL url = new URL( strUrl );
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setDoOutput(true);


            /*
              Otra forma de formar los datos sería la siguiente:

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("firstParam", paramValue1)
                    .appendQueryParameter("secondParam", paramValue2)
                    .appendQueryParameter("thirdParam", paramValue3);
            String query = builder.build().getEncodedQuery();
            */

            PrintWriter writer = new PrintWriter(http.getOutputStream());
            writer.print(data); //Aquí se le pasaría la variable creada query
            writer.flush();
            responseCode = http.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (http != null) http.disconnect();
        }
        return responseCode;
    }

}


