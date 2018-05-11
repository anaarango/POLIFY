package com.polify.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page_empresa {

	WebDriver webDriver;
	By name = By.name("empresaForm:j_idt11");
	By email = By.name("empresaForm:j_idt15");
	By valueOp = By.name("empresaForm:j_idt19");
	By submit = By.name("empresaForm:crearEmpresa");
	By title = By.xpath("//*[@id=\"empresaForm:msgs_container\"]/div/div/div[2]/p");
	

	By editBtn, editConfirmBtn, editName, editEmail, editValueOp,deleteBtn;

	public page_empresa(WebDriver webDriver, String strName, String strEmail, String strValueOp) {
		setWebDriver(webDriver);
		setName(strName);
		setEmail(strEmail);
		setValueOp(strValueOp);
		setSubmit();
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void setName(String strName) {
		webDriver.findElement(name).clear();
		webDriver.findElement(name).sendKeys(strName);
	}

	public void setEmail(String strEmail) {
		webDriver.findElement(email).clear();
		webDriver.findElement(email).sendKeys(strEmail);
	}

	public void setValueOp(String strValueOp) {
		webDriver.findElement(valueOp).clear();
		webDriver.findElement(valueOp).sendKeys(strValueOp);
	}

	public void setSubmit() {
		webDriver.findElement(submit).click();
	}

	public String getTitle() {
		return webDriver.findElement(title).getText();
	}

	public void setEditBtn(String strLabel) {

		editBtn = By.xpath("//*[@id=\"empresaForm:tbl:" + strLabel + ":j_idt35\"]/a[1]/span");
		webDriver.findElement(editBtn).click();
	}

	public void setEditConfirmBtn(String strLabel) {

		editBtn = By.xpath("//*[@id=\"empresaForm:tbl:" + strLabel + ":j_idt35\"]/a[2]/span");
		webDriver.findElement(editBtn).click();
	}

	public void setEditName(String strLabel, String strEditName) {

		editName = By.name("empresaForm:tbl:"+strLabel+":modelInputEmpresa");
		webDriver.findElement(editName).clear();
		webDriver.findElement(editName).sendKeys(strEditName);
	}

	public void 
	setEditEmail(String strLabel, String strEditEmail) {

		editEmail = By.name("empresaForm:tbl:"+strLabel+":modelInput");
		webDriver.findElement(editEmail).clear();
		webDriver.findElement(editEmail).sendKeys(strEditEmail);
	}
	public void setEditValueOp(String strLabel, String strEditValueOp) {

		editValueOp = By.name("empresaForm:tbl:"+strLabel+":modelInput2");
		webDriver.findElement(editValueOp).clear();
		webDriver.findElement(editValueOp).sendKeys(strEditValueOp);
	}
	
	public void setDeleteBtn(String strLabel) {

		deleteBtn = By.xpath("empresaForm:tbl:"+strLabel+":deleteButton");
		webDriver.findElement(deleteBtn).click();
	}

}
