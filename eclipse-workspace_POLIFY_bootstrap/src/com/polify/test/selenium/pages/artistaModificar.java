package com.polify.test.selenium.pages;

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
	By email = By.className("ui-cell-editor-output");
	By acceptBtn = By.className("ui-row-editor-check");
	By closeMessage = By.className("ui-growl-message");
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

	public String updateArtista(String email) {

		this.BuscarElementosTabla();
		// this.changeBtn();
		// this.ChangeArtistaData(email);
		// this.submit();
		// messageModifyArtista = this.swithToAlert();
		// this.closeWindowMessage();
		//
		// return messageModifyArtista;

		return null;

	}

	private void BuscarElementosTabla() {
		WebElement table_element = driver.findElement(tableArtist);
		List<WebElement> tr_collection = table_element
				.findElements(By.xpath("//*[@id=\"artistaForm:tbl_data\"]/tr"));;

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		int row_num, col_num;
		row_num = 1;

		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());

				col_num++;
			}
			row_num++;
		}
		
		
		By elem = By.xpath("//*[@id=\"artistaForm:tbl:"+(tr_collection.size()-1)+":j_idt38\"]/a[1]/span");;
		//By elem = By.xpath("//*[@id=\"artistaForm:tbl:"+40+":j_idt38\"]/a[1]/span");;
		driver.findElement(elem).click();
		System.out.println("Ya dio click");;
		driver.findElement(By.xpath("//*[@id=\"artistaForm:tbl:"+(tr_collection.size()-1)+":modelInputArtista\"]")).sendKeys("pedro");
		driver.findElement(By.xpath("//*[@id=\"artistaForm:tbl:"+(tr_collection.size()-1)+":j_idt38\"]/a[2]/span")).click();
	}

	private void closeWindowMessage() {
		driver.findElement(closeMessage).click();

	}

	private void changeBtn() {
		driver.findElement(updateArtistBtn).click();

	}

	private void submit() {
		driver.findElement(acceptBtn).click();

	}

	private void ChangeArtistaData(String email2) {

		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(email2);

	}

	private String swithToAlert() {

		// Capturing alert message.
		String alertMessage = driver.findElement(closeMessage).getText();

		// Displaying alert message
		System.out.println(alertMessage);
		String lines[] = alertMessage.split("\\r?\\n");
		String resultado = lines[1];
		String mensajes[] = resultado.split(":");
		String mensajeExito = mensajes[0];
		String id = mensajes[1];

		System.out.println("el mensaje de resultado operacion es: " + mensajeExito);
		System.out.println("El ID de modificacion es: " + id);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajeExito;

	}

}
