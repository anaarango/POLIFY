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

import com.polify.test.selenium.pages.empresaDifusoraCrear;

public class EmpresaDifusoraCrearTest {

	private static final String URL_WEB = "http://localhost:8080/eclipse-workspace_POLIFY_bootstrap/faces/crearEmpresa.xhtml";
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String DRIVER_PATH = "C:\\lib.selenium\\chromedriver.exe";
	private static final String NOMBRE = "Discos fuentes";
	private static final String EMAIL = "discosfuentes@discosfuentes.com";
	private static final String VALOR_OPERACION = "10";

	private WebDriver driver;
	private empresaDifusoraCrear empresaC;
	
	

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
	@Test
	public void testCreateEmpresa() {

		empresaC = new empresaDifusoraCrear(driver);

		String messageCrear = empresaC.crearEmpresa(NOMBRE, EMAIL, VALOR_OPERACION);
//		assertTrue("Empresa creada: ", messageCrear.equals("La empresa:" + NOMBRE + "y con ID:" + 97+ "ha sido creado de forma exitosa"));

	}
	
	// Close Driver
		@After
		public void quitDriver() {
			driver.quit();
		}

}
