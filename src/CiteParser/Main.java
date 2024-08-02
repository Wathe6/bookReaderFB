package CiteParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Main {
    //https://www.royalroad.com/fiction/21220/mother-of-learning
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.royalroad.com/fiction/21220/mother-of-learning/chapter/301778/1-good-morning-brother");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("authority", "www.royalroad.com");
        httpConn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        httpConn.setRequestProperty("accept-language", "ru-RU,ru;q=0.9");
        httpConn.setRequestProperty("cookie", "ARRAffinity=1704833835.891.57465.91212|a9250f6d914789b42bfce9ece39a25bb; .AspNetCore.Antiforgery.ADJdPfk2gAA=CfDJ8ILykB8WCtVDg0UNDRDucYhKFc9wVe-gzY0TDw5wnvMDDRHTpLyQWjm-piQkP3Pe0ZRsp0MtaBRQINwIga-icJo5R1AXbkc7wVlP6iQzY8gzFFYqr4NMPB8poyWSV5sM-EXU6y2_ZKIrybR-nDt82PA; _ga=GA1.1.1970604508.1704833837; ncmp.domain=royalroad.com; cf_clearance=2hKWgtauRMr1EBjOb4RVb4WDKN4ENeO.kOhmOu5PbkI-1704833837-0-2-de7268c.ff0de726.b909f43b-0.2.1704833837; _pbjs_userid_consent_data=3524755945110770; _sharedid=5f7805a0-e01d-4f4f-8379-59c3fb607347; na-unifiedid=%7B%22TDID%22%3A%22d46a6c2c-bea9-49c9-a98b-32f53af83f18%22%2C%22TDID_LOOKUP%22%3A%22FALSE%22%2C%22TDID_CREATED_AT%22%3A%222024-01-09T20%3A57%3A19%22%7D; na-unifiedid_cst=TyylLI8srA%3D%3D; _ga_DZHTJVDKRZ=GS1.1.1704833837.1.1.1704833954.59.0.0; cto_bundle=sz3CyV9IcEVia2s2b3hLcCUyQkN1YkN0bWxzdFFBdEdHMmhLdEdlJTJGc25HZWpqeE9ldXRxQXU3ekdpV2ZpSWxCVmtKUW5ueGtNQ3JLZnNNMFRsZFVNcG9CNWFLbTRpZlVZWm5XSU5PTTZpbjNINCUyQnc0MmJvMiUyRkRGb1U3STF0TnlWT0poQ3Vy; cto_bidid=WyihiF9jcW14M0p2JTJGM3NreTZIV0sxejM1SWc2N0RRWmJvciUyRmNtUmRTeHh6dk5yJTJCc0J5MGFYNklzMEdPOThmWWhsdzBrSW54RTM4JTJCTEpFMjFrQnZNaXo2Z2NRJTNEJTNE");
        httpConn.setRequestProperty("referer", "https://www.royalroad.com/fiction/21220/mother-of-learning");
        httpConn.setRequestProperty("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpConn.setRequestProperty("sec-fetch-dest", "document");
        httpConn.setRequestProperty("sec-fetch-mode", "navigate");
        httpConn.setRequestProperty("sec-fetch-site", "same-origin");
        httpConn.setRequestProperty("sec-fetch-user", "?1");
        httpConn.setRequestProperty("upgrade-insecure-requests", "1");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}