package com.polify.test.selenium.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.polify.test.selenium.pages.OperacionesCrear;
import com.polify.test.selenium.pages.OperacionesModificar;

public class operacionesModificarTest {

	private static final String URL_WEB = "http://localhost:8080/eclipse-workspace_POLIFY_bootstrap/faces/crearOperacion.xhtml";
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String DRIVER_PATH = "C:\\lib.selenium\\chromedriver.exe";
	private static final String FECHA_INICIO = "1/05/2018";
	private static final String FECHA_FIN = "15/05/2018";
	private static final String N_OPERACIONES = "100";
	private WebDriver driver;
	private OperacionesCrear operacionesC;
	private OperacionesModificar operacionesM;
	
	
	private ArrayList<String> result = new ArrayList<>();

	// Setup Driver
	@BeforeClass
	public static void setUpClass() {
		System.setProperty(WEBDRIVER_CHROME_DRIVER, DRIVER_PATH);
	}

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL_WEB);

	}

	
	public void testCreateOperacion() {

		operacionesC = new OperacionesCrear(driver);

		result = operacionesC.crearOperacion(FECHA_INICIO, FECHA_FIN, N_OPERACIONES);
		
	
	}
	
	@Test
	public void testUpdateOperacion() {
		this.testCreateOperacion();

		operacionesM = new OperacionesModificar(driver);
		ArrayList<String> message = operacionesM.updateOperacion();
		System.out.println(message.get(0));
		assertTrue("Operacion modificada", message.get(0).equals("Operacion Editada exitosamente"));
	}

	// Close Driver
	@After
	public void quitDriver() {
		driver.quit();
	}

}
