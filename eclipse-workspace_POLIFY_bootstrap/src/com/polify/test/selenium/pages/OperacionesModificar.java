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

public class OperacionesModificar {

	WebDriver driver;

	By fechaInicio = By.name("operacionesForm:fechaInicio_input");
	By fechaFin = By.name("operacionesForm:fechaFin_input");
	By nombreArtista = By.name("operacionesForm:j_idt15");
	By nombreEmpresa = By.name("operacionesForm:j_idt20");
	By nOperaciones = By.name("operacionesForm:numeroOperaciones");
	By crearBtn = By.name("operacionesForm:j_idt27");
	By tableOperaciones = By.id("operacionesForm:tbl");
	By closeClick = By.xpath("//*[@id=\"operacionesForm:msgs_container\"]/div/div/div[1]");
	By closeMessage = By.className("ui-growl-item");

	String messageCrearOperacion = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public OperacionesModificar(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> updateOperacion() {

		this.UpdateOperacionData();
		ArrayList<String> elementos = this.swithToAlert();
		this.closeWindowMessage();

		return elementos;

	}

	private void closeWindowMessage() {
		
		driver.findElement(closeMessage).click();
	}

	private void UpdateOperacionData() {

		WebElement table_element = driver.findElement(tableOperaciones);
		List<WebElement> tr_collection = table_element.findElements(By.xpath("//*[@id=\"operacionesForm:tbl_data\"]/tr"));
		
		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		
		By elem = By.xpath("//*[@id=\"operacionesForm:tbl:" + (tr_collection.size() - 1) + ":j_idt55\"]/a[1]/span");
		
		driver.findElement(elem).click();
		
		driver.findElement(
				By.xpath("//*[@id=\"operacionesForm:tbl:" + (tr_collection.size() - 1) + ":modelInput\"]")).clear();
		driver.findElement(
				By.xpath("//*[@id=\"operacionesForm:tbl:" + (tr_collection.size() - 1) + ":modelInput\"]"))
				.sendKeys("500");
		driver.findElement(
				By.xpath("//*[@id=\"operacionesForm:tbl:" + (tr_collection.size() - 1) + ":j_idt55\"]/a[2]/span")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private ArrayList<String> swithToAlert() {
		
		// Capturing alert message.
		String alertMessage = driver.findElement(closeMessage).getText();
		ArrayList<String> elementos = new ArrayList<>();
		// Displaying alert message
		System.out.println(alertMessage);
		String lines[] = alertMessage.split("\\r?\\n");
		String resultado = lines[1];
		String mensajes[] = resultado.split(":");
		String mensajeExito = lines[0];
		
		String[] splited = mensajes[1].split("\\s+");
		String id = splited[1];
		
		elementos.add(mensajeExito);
		elementos.add(id);
		
		System.out.println("el mensaje de resultado operacion es: "+mensajeExito);
		System.out.println("El ID de creacion es: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return elementos;

	}

}
