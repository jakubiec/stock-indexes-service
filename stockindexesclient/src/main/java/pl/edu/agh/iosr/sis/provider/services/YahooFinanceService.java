package pl.edu.agh.iosr.sis.provider.services;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import pl.edu.agh.iosr.sis.provider.exceptions.DownloadException;

import com.csvreader.CsvReader;

/**
 * Provides stock indexes values downloaded from YahooFinance. <br>
 * @see <a href="http://finance.yahoo.com/">http://finance.yahoo.com/</a>
 *  
 * @author Konrad Jakubiec
 *
 */
public class YahooFinanceService {

	private static final String URI = "http://finance.yahoo.com/d/quotes.csv?s=";
	private static final String OPTIONS = "&f=sl1";
	private static final String PLUS_SIGN = "+";
	
	/**
	 * Downloads stock index value from YahooFinance.
	 * @param indexSymbols - an array of index symbols
	 * @return Map<String,BigDecimal> - map of stock index symbol and its value
	 * @throws DownloadException - when passed wrong arguments or some problems occur during http communication
	 * 	
	 */
	public static Map<String,BigDecimal> getIndexValue(String ...indexSymbols)throws DownloadException {
		if( indexSymbols == null || indexSymbols.length == 0 )
			throw new DownloadException("Empty stock index symbols arguments");

		String uri = buildURI(indexSymbols);
		String csvContent = download(uri);
		CsvReader csvReader = new CsvReader(new StringReader(csvContent));
		Map<String,BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		try {
			while (csvReader.readRecord()) {
				resultMap.put(csvReader.get(0), new BigDecimal(csvReader.get(1)));
			}
		} catch (IOException e) {
	
			throw new DownloadException(e.getMessage());
		}
		
		return resultMap;
	}

	private static String buildURI(String[] indexSymbols) {
		StringBuilder stringBuilder = new StringBuilder(URI);
		for(String symbol :indexSymbols){
			stringBuilder.append(symbol);
			stringBuilder.append(PLUS_SIGN);
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append(OPTIONS);
		return stringBuilder.toString();
	}
	
	private static  String download(String uri) throws DownloadException {
		HttpClient httpClient = new HttpClient();
		HttpMethod getMethod = new GetMethod(uri);
 
		try {
			int response = httpClient.executeMethod(getMethod);
 
			if (response != 200) {
				throw new DownloadException("HTTP problem, httpcode: "
						+ response);
			}
 
			InputStream stream = getMethod.getResponseBodyAsStream();
			String responseText = responseToString(stream);
 
			return responseText;
		} catch (Exception e) {
			throw new DownloadException(e.getMessage());
		}
	}
 
	private static String responseToString(InputStream stream) throws IOException {
		BufferedInputStream bi = new BufferedInputStream(stream);
 
		StringBuilder sb = new StringBuilder();
 
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = bi.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, bytesRead));
		}
		return sb.toString();
	}
}
