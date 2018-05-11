package com.polify.test.selenium.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ana Arango
 * 
 *         Clase encargada de los elementos propios para la creacion de Artistas
 *
 */

public class artistaEliminar {

	WebDriver driver;

	By deleteArtistBtn = By.name("artistaForm:tbl:0:deleteButton");
	By closeMessage = By.className("ui-growl-icon-close ui-icon ui-icon-closethick");
	By messageDelete = By.xpath("//*[@id=\"artistaForm:msgs_container\"]/div/div/div[2]/p");

	By tableArtist = By.xpath("//*[@id=\"artistaForm:tbl\"]/div[2]/table");
	String messageDeleteComplete = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public artistaEliminar(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> deleteArtista() {

		this.UpdateElementosTabla();
		ArrayList<String> elementos = this.swithToAlert();
		

		return elementos;

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

	private void UpdateElementosTabla() {
		WebElement table_element = driver.findElement(tableArtist);
		List<WebElement> tr_collection = table_element.findElements(By.xpath("//*[@id=\"artistaForm:tbl_data\"]/tr"));

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());

		By elem = By.xpath("//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":j_idt38\"]/a[1]/span");

		driver.findElement(elem).click();

		//String s = "//*[@id=\"artistaForm:tbl:2:deleteButton\"]";
		//System.out.println(s);
		String deleteButton = "//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":deleteButton\"]";
		System.out.println(deleteButton);

		driver.findElement(By.xpath(deleteButton)).click();

		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
