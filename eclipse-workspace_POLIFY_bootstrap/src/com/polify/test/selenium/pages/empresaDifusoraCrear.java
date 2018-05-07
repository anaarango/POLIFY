package com.polify.test.selenium.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Ana Arango
 * 
 *         Clase encargada de los elementos propios para la creacion de Artistas
 *
 */

public class empresaDifusoraCrear {

	WebDriver driver;

	By crearEmpresaBtn = By.name("empresaForm:crearEmpresa");
	By nombreEmpresa = By.name("empresaForm:j_idt11");
	By email = By.name("empresaForm:j_idt15");
	By valorOperacion = By.name("empresaForm:j_idt19");
	By closeMessage = By.className("ui-growl-icon-close ui-icon ui-icon-closethick");
	By tableEmpresa = By.id("empresaForm:tbl"); 
	
	String messageCrearEmpresa = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public empresaDifusoraCrear(WebDriver driver) {

		this.driver = driver;

	}

	public String crearEmpresa(String nombreEmpresa, String email, String valorOperacion) {

		this.AddEmpresaData(nombreEmpresa,email, valorOperacion );
		this.submit();
		messageCrearEmpresa = this.swithToAlert();
		
		this.closeWindowMessage();
		
		return messageCrearEmpresa;

	}

	private void closeWindowMessage() {
		driver.findElement(closeMessage).click();
		
	}

	private void submit() {
		driver.findElement(crearEmpresaBtn).click();
		
	}

	private void AddEmpresaData(String nombreEmpresa2, String email2, String  valorOperacion2) {

		driver.findElement(nombreEmpresa).clear();
		driver.findElement(nombreEmpresa).sendKeys(nombreEmpresa2);

		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(email2);
		
		driver.findElement(valorOperacion).clear();
		driver.findElement(valorOperacion).sendKeys(valorOperacion2);

		

	}
	
	private ArrayList<String> getEmpresaId(){
		
		WebElement table_element = driver.findElement(tableEmpresa);
		List<WebElement> td_collection = table_element.findElements(By.xpath("//*[@id=\"artistaForm:tbl:j_idt24\"]/span"));
		
		
		int size = td_collection.size();
		
		for (int x = 0; x<td_collection.size();x++) {
			table_element = td_collection.get(x);
		}
		 

		return null;
	}
	
	private String swithToAlert() {
		// Switching to Alert
//		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.findElement(closeMessage).getText();
		// Displaying alert message
		System.out.println(alertMessage);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		// Accepting alert
//		alert.accept();
//
//		// Switching to Alert 2
//		Alert alert2 = driver.switchTo().alert();
//
//		// Capturing alert message.
//		String alertMessage2 = driver.switchTo().alert().getText();
//
//		// Displaying alert message
//		System.out.println(alertMessage2);
//
//		// Accepting alert 2
//		alert2.accept();

		return alertMessage;

	}

}
