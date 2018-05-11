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

public class artistaModificar {

	WebDriver driver;

	By updateArtistBtn = By.className("ui-icon ui-icon-pencil");
	By acceptBtn = By.className("ui-row-editor-check");
	By closeMessage = By.className("ui-growl-message");
	By mensajeEdicion = By.xpath("//*[@id=\"artistaForm:msgs_container\"]/div/div/div[2]/span");
	By tableArtist = By.xpath("//*[@id=\"artistaForm:tbl\"]/div[2]/table");
	By clickEditar = By.xpath("//*[@id=\"artistaForm:tbl:31:j_idt38\"]/a[1]/span");

	String messageModifyArtista = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public artistaModificar(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> updateArtista() {

		this.UpdateElementosTabla();
		ArrayList<String> elementos = this.swithToAlert();

		this.closeWindowMessage();

		return elementos;

	}

	private void UpdateElementosTabla() {
		WebElement table_element = driver.findElement(tableArtist);
		List<WebElement> tr_collection = table_element.findElements(By.xpath("//*[@id=\"artistaForm:tbl_data\"]/tr"));
		;

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		

		By elem = By.xpath("//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":j_idt38\"]/a[1]/span");
		
		driver.findElement(elem).click();
		
		driver.findElement(
				By.xpath("//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":modelInputArtista\"]")).clear();
		driver.findElement(
				By.xpath("//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":modelInputArtista\"]"))
				.sendKeys("pedro");
		driver.findElement(
				By.xpath("//*[@id=\"artistaForm:tbl:" + (tr_collection.size() - 1) + ":j_idt38\"]/a[2]/span")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeWindowMessage() {
		driver.findElement(closeMessage).click();

	}


	private ArrayList<String> swithToAlert() {

		// Capturing alert message.
		String alertMessage = driver.findElement(mensajeEdicion).getText();
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
