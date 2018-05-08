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

public class OperacionesEliminar {

	WebDriver driver;

	
	By deleteOperaciontBtn = By.name("operacionesForm:tbl:1:deleteButtonn");
	
	By tableOperaciones = By.id("operacionesForm:tbl");
	By closeClick = By.xpath("//*[@id=\"operacionesForm:msgs_container\"]/div/div/div[1]");
	By closeMessage = By.className("ui-growl-icon-close ui-icon ui-icon-closethick");
	
	By messageDelete = By.xpath("//*[@id=\"operacionesForm:msgs_container\"]/div/div/div[2]/span");
	
	String messageCrearOperacion = "";
	String messageDeleteComplete = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public OperacionesEliminar(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> deleteOperacion() {

		this.DeleteOperacionData();
		ArrayList<String> elementos = this.swithToAlert();

		return elementos;

	}

	

	private void DeleteOperacionData() {
		WebElement table_element = driver.findElement(tableOperaciones);
		List<WebElement> tr_collection = table_element.findElements(By.xpath("//*[@id=\"operacionesForm:tbl_data\"]/tr"));

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		
		
		String deleteButton = "//*[@id=\"operacionesForm:tbl:" + (tr_collection.size() - 1) + ":deleteButton\"]";
		System.out.println(deleteButton);

		driver.findElement(By.xpath(deleteButton)).click();

		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private ArrayList<String> swithToAlert() {

		// Capturing alert message.
		String alertMessage = driver.findElement(messageDelete).getText();
		ArrayList<String> elementos = new ArrayList<>();

		elementos.add(alertMessage);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elementos;

	}

}
