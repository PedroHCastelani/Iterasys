package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList();

    private By produtos = By.className("product-description");
    private By textoProdutosNoCarrinho = By.className("cart-products-count");
    private By login = By.className("hidden-sm-down");
    private By nomeProdutos = By.cssSelector(".product-description a");
    private By precoProdutos = By.className("price");
    private By botaoSingIn = By.cssSelector(".user-info a");
    private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    public int contarProdutos() {
        carregarListaProdutos();
        return listaProdutos.size();
    }

    private void carregarListaProdutos() {

        listaProdutos = driver.findElements(produtos);
    }

    public int obterQuantidadeProdutosNoCarrinho() {

        String quantidadeProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText();

        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(", "");
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");

        int qtdProdutosNoCarrinho = Integer.parseInt(quantidadeProdutosNoCarrinho);

        return qtdProdutosNoCarrinho;
    }

    public String obterNomeProduto(int indice) {
        return driver.findElements(nomeProdutos).get(indice).getText();
    }

    public String obterPrecoProduto(int indice) {
       return driver.findElements(precoProdutos).get(indice).getText();

    }

    public  ProdutoPage clicarProduto(int indice) {
        driver.findElements(nomeProdutos).get(indice).click();
        return  new ProdutoPage(driver);
    }

    public LoginPage clicarBotaoSignIn() {
        driver.findElement(botaoSingIn).click();
        return new LoginPage(driver);
    }

    public  boolean validarLogin(String texto) {
        return texto.contentEquals(driver.findElement(usuarioLogado).getText());
    }

}
