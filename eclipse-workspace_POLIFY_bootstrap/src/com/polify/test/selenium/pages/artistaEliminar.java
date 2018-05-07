package com.polify.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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
	String messageDeleteComplete = "";	

	/**
	 * @param driver,
	 *            se recibe el driver para la ejecucion de selenium
	 */
	public artistaEliminar(WebDriver driver) {

		this.driver = driver;

	}

	public String deleteArtista(String email) {

		this.deleteBtn();
		messageDeleteComplete = this.swithToAlert();
		this.closeMns();
		
		return messageDeleteComplete;

	}

	private void closeMns() {
		driver.findElement(closeMessage).click();
		
	}

	private String swithToAlert() {
		// Capturing alert message.
				String alertMessage = driver.findElement(messageDelete).getText();
				// Displaying alert message
				System.out.println(alertMessage);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return alertMessage;
	}

	private void deleteBtn() {
		driver.findElement(deleteArtistBtn).click();
		
	}

	

}
