package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.dto.ConsultaLocalizacaoRequest;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class IntegracaoService {

    public JSONObject getDadosApiSiglon(ConsultaLocalizacaoRequest request) throws IOException, ParseException {
        var in = getReader(request);
        var json = getParseredJson(in);
        in.close();
        return json;
    }

    private JSONObject getParseredJson(BufferedReader in) throws IOException, ParseException {
        String inputLine;
        var content = new StringBuffer();
        while (!isEmpty(inputLine = in.readLine())) {
            content.append(inputLine);
        }
        var parser = new JSONParser();
        return (JSONObject) parser.parse(content.toString());
    }

    private BufferedReader getReader(ConsultaLocalizacaoRequest request) throws IOException {
        return new BufferedReader(
            new InputStreamReader(getRequestConnection(getZoneamentoQuery(request)).getInputStream()));
    }

    public String getZoneamentoQuery(ConsultaLocalizacaoRequest request) {
        return "https://siglon.londrina.pr.gov.br/arcgis/rest/services/Zoneamento/MapServer/0/query?f=json&"
            + "returnGeometry=true"
            + "&spatialRel=esriSpatialRelIntersects"
            + "&maxAllowableOffset=0"
            + "&geometry=%7B%22xmin%22%3A" + request.getXmin() + "%2C%22ymin%22%3A" + request.getYmin()
            + "%2C%22xmax%22%3A" + request.getXmax() + "%2C%22ymax%22%3A" + request.getYmax()
            + "%2C%22spatialReference%22%3A%7B%22wkid%22%3A" + request.getWkid() + "%7D%7D"
            + "&geometryType=esriGeometryEnvelope"
            + "&outFields=zoneamento_2019%2Cdatas%2Cquadras%2Cn_loteamen%2Cgleba%2Cparametros%2"
            + "Clei_12_236%2Cobjectid%2Czonea_2015%2Cobjectid_1%2Calteracao_%2Clei_de_alteracao"
            + "&outSR=102100";
    }

    private HttpURLConnection getRequestConnection(String query) throws IOException {
        System.setProperty("jsse.enableSNIExtension", "false");
        System.out.println(query);
        URL url = new URL(query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        return con;
    }
}
