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

public class OperacionesCrear {

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
	public OperacionesCrear(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> crearOperacion(String fechaInicio, String fechaFin, String nOperaciones) {

		this.AddOperacionData(fechaInicio, fechaFin, nOperaciones);
		this.submit();
		ArrayList<String> elementos = this.swithToAlert();

		this.closeWindowMessage();

		return elementos;

	}

	private void closeWindowMessage() {
		//System.out.println(closeMessage);
		driver.findElement(closeMessage).click();

	}

	private void submit() {
		driver.findElement(crearBtn).click();

	}

	private void AddOperacionData(String fechaInicio2, String fechaFin2, String nOperaciones2) {

		driver.findElement(fechaInicio).sendKeys(fechaInicio2);
		driver.findElement(fechaFin).sendKeys(fechaFin2);
		
		// Se selecciona nombre del artista
		Select nombreA = new Select(driver.findElement(nombreArtista));
		nombreA.selectByVisibleText("Bon Jovi");
		
		// Se selecciona una empresa difusora
		Select nombreE = new Select(driver.findElement(nombreEmpresa));
		nombreE.selectByVisibleText("Youtube");
		
		//Diligenciar el numero de operaciones
		//driver.findElement(nOperaciones).clear();
		driver.findElement(nOperaciones).sendKeys(nOperaciones2);

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
