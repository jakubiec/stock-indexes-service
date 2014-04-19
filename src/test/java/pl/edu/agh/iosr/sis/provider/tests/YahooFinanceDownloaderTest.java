package pl.edu.agh.iosr.sis.provider.tests;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.iosr.sis.provider.exceptions.DownloadException;
import pl.edu.agh.iosr.sis.provider.services.YahooFinanceDownloader;

public class YahooFinanceDownloaderTest {
	
	private static final String FAKE_SYMBOL1 = "NOT_EXIST1";
	private static final String FAKE_SYMBOL2 = "NOT_EXIST2";
	
	
	@Test
	public void getIndexValueTest() throws DownloadException{
		Map<String, BigDecimal> result = YahooFinanceDownloader.getIndexValue(FAKE_SYMBOL1,FAKE_SYMBOL2);
		Assert.assertTrue(result.size() == 2);
		Assert.assertTrue(result.containsKey(FAKE_SYMBOL1));
		Assert.assertTrue(result.containsKey(FAKE_SYMBOL2));
		Assert.assertTrue(BigDecimal.ZERO.compareTo(result.get(FAKE_SYMBOL1)) == 0 );
		Assert.assertTrue(BigDecimal.ZERO.compareTo(result.get(FAKE_SYMBOL2)) == 0 );
		
	}
	
	@Test(expected=DownloadException.class)
	public void getIndexValueNullTest() throws DownloadException{
		YahooFinanceDownloader.getIndexValue((String[])null);
	}
	
	@Test(expected=DownloadException.class)
	public void getIndexValueEmptylTest() throws DownloadException{
		YahooFinanceDownloader.getIndexValue(new String[0]);
	}
}
