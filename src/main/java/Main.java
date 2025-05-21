import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.Instant;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//Diseñado por Eduardo Martinez Narvaez - 05/19/2025
public class Main {
    public static void main(String[] args) {
        String browser = "chrome"; //selecciona en que navegador se va a realizar la prueba.
        WebDriver driver = null;


        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            // System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
            // WebDriverManager.geckodriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            // System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\msedgedriver_v114.exe");
            //  WebDriverManager.msedgedriver().setup();
            driver = new EdgeDriver();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Actions a = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.mercadolibre.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@id='MX']")).click();

        //si aparece ventana emergente lo cierra..
        try {
            WebElement skipButton = driver.findElement(By.xpath("//button//span[contains(text(),'Más tarde')]"));
            skipButton.click();
            Thread.sleep(1000); // da tiempo a que desaparezca
        } catch (Exception e) {
            System.out.println("Ventana emergente- no apareció o ya estaba cerrado.");
        }
        //si aparece banner de cookies lo cierra
        try {
            WebElement cookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Aceptar cookies')]")));
            cookiesBtn.click();
            System.out.println("OK:Banner de cookies cerrado");
        } catch (Exception e) {
            System.out.println("OK:No se mostro el banner de cookies.");
        }

        //Busca playstation5
        driver.findElement(By.xpath("//html/body/header/div/div[2]/form/input")).click();
        driver.findElement(By.xpath("//html/body/header/div/div[2]/form/input")).sendKeys("playstation5");
        driver.findElement(By.xpath("//div[@class='nav-icon-search']")).click();

        // Filtra por: Nuevo. (tuve que meter scroll por que botón no estaba visible)
        WebElement filtroNuevo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Nuevo') and @class='ui-search-filter-name']")));
        scrollAndClick(driver, filtroNuevo);
        // Filtrar por ubicación: Ciudad de México
        WebElement filtroCDMX = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Distrito Federal')]")));
        scrollAndClick(driver, filtroCDMX);
        // Orden de "Mayor a Menor precio"
        WebElement ordenarDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'andes-dropdown__display-values') and text()='Más relevantes']")));
        ordenarDropdown.click();
        WebElement mayorPrecio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='andes-list__item-primary' and text()='Mayor precio']")));
        mayorPrecio.click();

        // Espera a que cargue la lista de productos ...
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.poly-component__title")));

        // Obtine los primeros 5 productos
        List<WebElement> productos = driver.findElements(By.cssSelector("a.poly-component__title"));

        System.out.println(" 5 primeros productos de la lista:");
        for (int i = 0; i < Math.min(5, productos.size()); i++) {
            String nombre = productos.get(i).getText();
            System.out.println((i + 1) + " " + nombre);
        }

        driver.quit();

    }

    //Hace scroll para encvontrar los elementos de Nuevo y Ubiacción
    private static void scrollAndClick(WebDriver driver2, WebElement element) {
        ((JavascriptExecutor) driver2).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver2).executeScript("arguments[0].click();", element);

    }
}