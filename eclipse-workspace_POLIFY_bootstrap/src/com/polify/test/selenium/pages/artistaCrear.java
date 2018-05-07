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

public class artistaCrear {

	WebDriver driver;

	By crearArtistBtn = By.name("artistaForm:crearArtista");
	By nombreArtista = By.name("artistaForm:nombreArtistaInp");
	By email = By.name("artistaForm:email");
	By empDifusoraSelect = By.name("artistaForm:j_idt19");
	By closeMessage = By.className("ui-growl-message");

	By tableArtista = By.id("artistaForm:tbl");

	String messageCrearArtista = "";

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public artistaCrear(WebDriver driver) {

		this.driver = driver;

	}

	public ArrayList<String> crearArtista(String nombreArtista, String email) {

		this.AddArtistaData(nombreArtista, email);
		this.submit();
		ArrayList<String> elementos = this.swithToAlert();

		this.closeWindowMessage();

		return elementos;

	}

	private void closeWindowMessage() {
		driver.findElement(closeMessage).click();

	}

	private void submit() {
		driver.findElement(crearArtistBtn).click();

	}

	private void AddArtistaData(String nombreArtista2, String email2) {

		driver.findElement(nombreArtista).clear();
		driver.findElement(nombreArtista).sendKeys(nombreArtista2);

		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(email2);

		// Se selecciona una empresa difusora
		Select accountType = new Select(driver.findElement(empDifusoraSelect));
		accountType.selectByVisibleText("Youtube");

	}

//	private ArrayList<String> getArtistaId() {
//
//		WebElement table_element = driver.findElement(tableArtista);
//		List<WebElement> td_collection = table_element
//				.findElements(By.xpath("//*[@id=\"artistaForm:tbl:j_idt24\"]/span"));
//
//		int size = td_collection.size();
//
//		for (int x = 0; x < td_collection.size(); x++) {
//			table_element = td_collection.get(x);
//		}
//
//		return null;
//	}

	private ArrayList<String> swithToAlert() {
		
		// Capturing alert message.
		String alertMessage = driver.findElement(closeMessage).getText();
		ArrayList<String> elementos = new ArrayList<>();
		// Displaying alert message
		System.out.println(alertMessage);
		String lines[] = alertMessage.split("\\r?\\n");
		String resultado = lines[1];
		String mensajes[] = resultado.split(":");
		String mensajeExito = mensajes[0];
		String id = mensajes[1];
		
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
