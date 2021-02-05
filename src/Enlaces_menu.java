import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class Enlaces_menu {
    public static void main(String[] args) throws Exception {
        	// CARGAR DRIVER FIREFOX
    	System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
      
        	// MENUS Y URLS
        // String[] menu = {"'Inicio'", "'Cat�logo'", "'�Qui�nes somos?'"};  
        String [] url = {"https://www.rakayoclothing.com/", "https://www.rakayoclothing.com/collections/all", "https://www.rakayoclothing.com/pages/quienes-somos"};

    	// SAVEDATA
        File outFile = new File("resultados.txt");

        // PRUEBAS
        FileWriter outResultado = new FileWriter("resultados.txt");
        for(int i=0; i<3; i++) { 
        	// Para cada p�gina en el men�, comprobar que todas tienen las rutas correctas
        		// Men� Inicio
        	driver.get(url[i]);
        	outResultado.write("Prueba " + (i+1) + " : Iniciar en " + driver.getTitle()+ "\n");
        	Thread.sleep(1000); 
        	driver.findElement(By.xpath("//ul[@id='SiteNav']//li//a[contains(text(), 'Inicio')]")).click();  // Selecciona el bot�n
        	outResultado.write("\t Bot�n inicio: Resultado " + driver.getTitle() + "\n");  // Resultado
        	Thread.sleep(1000); 
        		// Men� Cat�logo
        	driver.get(url[i]);
        	driver.findElement(By.xpath("//ul[@id='SiteNav']//li//a[contains(text(), 'Cat�logo')]")).click();  // Selecciona el bot�n
        	outResultado.write("\t Bot�n Cat�logo: Resultado " + driver.getTitle() + "\n");  // Resultado
        	Thread.sleep(1000);
        	
        		//Men� Quienes Somos
        	driver.get(url[i]);
        	driver.findElement(By.xpath("//ul[@id='SiteNav']//li//a[contains(text(), '�Qui�nes somos?')]")).click();  // Selecciona el bot�n
        	outResultado.write("\t Bot�n Quienes Somos: Resultado " + driver.getTitle() + "\n");  // Resultado
        	Thread.sleep(1000);
        }
        // Cerrar instancias
        outResultado.close();
        driver.quit();
    }
}