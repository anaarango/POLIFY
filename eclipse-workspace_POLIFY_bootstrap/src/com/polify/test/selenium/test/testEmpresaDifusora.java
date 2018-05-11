package com.polify.test.selenium.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.polify.dao.DaoEmpresa_difusora;
import com.polify.entity.Empresa_difusora;
import com.polify.test.selenium.pages.page_empresa;

public class testEmpresaDifusora {

	String name = "emp";
	String nameEdit = "empresa";
	String email = "emp@emp.com";
	String valueOp = "5";
	String baseUrl = "http://localhost:8090/Polify/faces/";
	WebDriver webDriver;

	page_empresa page_empresa;
	List<Empresa_difusora> empresasList;
	DaoEmpresa_difusora daoEmpresa;

	public testEmpresaDifusora() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setUpClass() {
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@After
	public void tearDown() {
		//webDriver.quit();
	}

	@Test
	public void testCrearEmpresa() throws InterruptedException {
		webDriver.get(baseUrl + "crearEmpresa.xhtml");

		page_empresa = new page_empresa(webDriver, name, email, valueOp);
		Thread.sleep(2000);
		String mnsj = page_empresa.getTitle();
		daoEmpresa = new DaoEmpresa_difusora();
		empresasList = daoEmpresa.getAllEmpresas();
		int idEmp = empresasList.get(empresasList.size() - 1).getId_empresa_difusora();
		String nomEmp = empresasList.get(empresasList.size() - 1).getNombre_empresa();
		assertEquals(mnsj, "La empresa: " + nomEmp + " y con ID: " + idEmp + " ha sido creado de forma exitosa");
		daoEmpresa.delete(idEmp);
	}

	@Test
	public void testEditarEmpresa() throws InterruptedException {
		webDriver.get(baseUrl + "crearEmpresa.xhtml");

		page_empresa = new page_empresa(webDriver, name, email, valueOp);
		Thread.sleep(2000);
		
		daoEmpresa = new DaoEmpresa_difusora();
		empresasList = daoEmpresa.getAllEmpresas();
		String strLabel = Integer.toString(empresasList.size()-1);
		int idEmp = empresasList.get(empresasList.size() - 1).getId_empresa_difusora();
		
		page_empresa.setEditBtn(strLabel);
		page_empresa.setEditName(strLabel, nameEdit);
		page_empresa.setEditConfirmBtn(strLabel);
		Thread.sleep(2000);
		
		String mnsj = page_empresa.getTitle();
		assertEquals(mnsj, "La empresa: "+nameEdit+" y con ID: "+idEmp+" ha sido actualizado de forma exitosa");
		daoEmpresa.delete(idEmp);
	}
	
	@Test
	public void testEliminarEmpresa() throws InterruptedException {
		webDriver.get(baseUrl + "crearEmpresa.xhtml");

		page_empresa = new page_empresa(webDriver, name, email, valueOp);
		Thread.sleep(2000);
		
		daoEmpresa = new DaoEmpresa_difusora();
		empresasList = daoEmpresa.getAllEmpresas();
		String strLabel = Integer.toString(empresasList.size()-1);
		int idEmp = empresasList.get(empresasList.size() - 1).getId_empresa_difusora();
		
		page_empresa.setDeleteBtn(strLabel);
		Thread.sleep(2000);
		
		String mnsj = page_empresa.getTitle();
		assertEquals(mnsj, "La empresa: "+nameEdit+" y con ID: "+idEmp+" ha sido eliminada de forma exitosa");
		
		daoEmpresa.delete(idEmp);
	}

}
