package com.polify.test.selenium.pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class artistaModificarTest {

	private static final String URL_WEB = "http://localhost:8080/eclipse-workspace_POLIFY_bootstrap/faces/crearArtista.xhtml";
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String DRIVER_PATH = "C:\\lib.selenium\\chromedriver.exe";
	private static final String NOMBRE = "Bon Jovi";
	private static final String EMAIL = "bonjovi@bonjovi.com";

	private WebDriver driver;
	private artistaCrear artistaC;
	private artistaModificar artistaM;
	private ArrayList<String> resultArtistaChanges;
	private ArrayList<String> resultIdArtista = new ArrayList<>();

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

	
	public void testCreateArtista() {

		artistaC = new artistaCrear(driver);

		resultIdArtista = artistaC.crearArtista(NOMBRE, EMAIL);
		assertTrue("Artista creado: ", resultIdArtista.get(0).equals("La creación del artista ha sido exitosa y con ID"));

	}
	@Test
	public void testUpdateArtista() {
		this.testCreateArtista();

		artistaM = new artistaModificar(driver);
		String messgeModify = artistaM.updateArtista(EMAIL);
		assertTrue("Artista modificado", messgeModify.equals("Artista Editado exitosamente"));
	}

	// Close Driver
	@After
	public void quitDriver() {
		driver.quit();
	}

}
