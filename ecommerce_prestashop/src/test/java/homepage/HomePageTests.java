package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import Base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProdutoPage;

import java.util.List;


public class HomePageTests extends BaseTests {

    @Test
    public void testcontarProdutos_oitoProdutosDiferentes() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado_zeroItensNocarrinho() {
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        assertThat(produtosNoCarrinho, is(0));
    }

    ProdutoPage produtoPage;
    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        produtoPage = homePage.clicarProduto(indice);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
        assertThat(precoProduto_HomePage.toUpperCase(), is(precoProduto_ProdutoPage.toUpperCase()));
    }

    LoginPage loginPage;
    @Test
    public void testLoginComSucesso_UsuarioLogado() {

         loginPage = homePage.clicarBotaoSignIn();

         //Preencher usuário e senha
         loginPage.preencherEmail("pedro.castelani@hotmail.com");
         loginPage.preencherPassword("teste123");

         //Clicar no botão Sign In para logar
         loginPage.clicarBotaoSignIn();

         //Validar se o usuário está logado de fato
        assertThat(homePage.validarLogin("Pedro Castelani"), is(true));

        carregarPaginaInicial();
    }

    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {

        //Pré-condição
        //Usuário logado
        if(!homePage.validarLogin("Pedro Castelani")) {
            testLoginComSucesso_UsuarioLogado();
        }

        //Teste
        //Selecionando produto
        testValidarDetalhesDoProduto_DescricaoEValorIguais();

        //Selecionar tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelcionadas();

        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //Selecionar cor

        //Selecionar quantidade
        //int addProduto = incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso();

       // System.out.println(addProduto);
       // return addProduto;

        //Adicionar no carrinho


    }
}
