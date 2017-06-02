package com.sqamexico.ejemplo;

//import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class RegistroSQA {
  /**
  * Clase ejemplo básico de como se implementa Selenium con Firefox
  */
	public static void main(String[] args) throws InterruptedException {
//		Logger log = Logger.getLogger("sqalog");
		/*
		 * #0 Configuraciones para poder usar mi WebDriver
		 * 		Cada Browser tiene distinta configuración y diferente driver (.exe)
		 * 		Firefox:
		 * 			-> https://github.com/mozilla/geckodriver#selenium
		 * 			-> https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
		 * 		Chrome
		 * 			-> https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
		 * 		IE 
		 * 			-> https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
		 * 		Safari
		 * 			-> https://github.com/SeleniumHQ/selenium/wiki/SafariDriver
		 * 		Otros:
		 * 			-> https://github.com/SeleniumHQ/selenium/wiki	
		 */
		
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true); 
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		
		// #1 Creo el WebDriver (el objeto que contiene lo necesario para controlar el browser)
		WebDriver driver = new FirefoxDriver(capabilities);

		driver.manage().window().maximize();  //maximizo mi browser
        // #2 Le indico al webdriver que visite la URL
        driver.get("http://demoregistro.sqamexico.com/");
        
        // #3 Manipulo mis objetos
        //  A) Forma de manipular un objeto, usando WebElement
        WebElement nombre = driver.findElement(By.id("txt_name"));
        nombre.sendKeys("Mr Robot");
        //  B) Forma de manipular un objeto, usando Webdriver directo
        driver.findElement(By.name("txt_user")).sendKeys("root");
        
        //  C) Manipular un Combobox 
        Select combo = new Select (driver.findElement(By.id("cmb_pais"))  );
        combo.selectByValue("Mexico");
        
        combo = new Select (driver.findElement(By.id("cmb_ciudad"))  );
        combo.selectByValue("Jalisco");
        
        // Manipulando un checkbox
        WebElement checkbox_correr = driver.findElement(By.id("chk_hobbie-3"));
        if (!checkbox_correr.isSelected()) //valido que NO este seleccionado para seleccionarlo
        	checkbox_correr.click();
        
        
        // Doy click en el boton submit
        WebElement boton = driver.findElement(By.id("btnsubmit"));
        boton.click();
        
        
        
        
        // #4 Agrego un punto de verificación, para validar que el registro fue dado de alta
        WebElement confirmacion = driver.findElement(By.id("registrationok"));
        
        if (!confirmacion.getText().contains("Perfecto! el registro fue dado de alta correctamente")){
        	System.out.println("Hubo un error");
        	//log.severe("Hubo un error en el registro");
        	return;
        }
        
        System.out.println("Registro completado!");
       //log.info("Registro completado con éxito!!!");
        
        
        //Duermo el proceso para ver las modificaciones
        Thread.sleep(10000);
        
        // #5 Cierro el browser
        driver.close();

	}

}
