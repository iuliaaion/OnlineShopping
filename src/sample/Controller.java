package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    ProductRepository productRepository;
    private ArrayList<ProductRepository> repositoryArrayList = new ArrayList<>();

    public Controller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @FXML
    private ListView<String> productListView;

    @FXML
    private ListView<Product> shoppingCartListView;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField lowerBoundTextfield;

    @FXML
    private TextField upperBoundTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    public void initialize()
    {
        ArrayList<String> products = new ArrayList<>();
        for(Product p : this.productRepository.getProducts())
        {
            String prod = p.getId() + " " + p.getBrand() + " " + p.getName() + " " + p.getPrice();
            if(p.getQuantity() == 0)
                prod = prod + "out of stock";
            products.add(prod);
        }
        ObservableList<String> observableList = FXCollections.observableList(products);
        productListView.setItems(observableList);
    }

    @FXML
    void addToCartButtonHandler(ActionEvent event)
    {
        int quantity = Integer.parseInt(quantityTextField.getText());
        String ps = productListView.getSelectionModel().getSelectedItem();
        String[] splitProd = ps.split("[ ]");
        int id = Integer.parseInt(splitProd[0].strip());

        Product prod = new Product();
        for(Product p : this.productRepository.getProducts())
        {
            if(p.getId() == id)
                prod = p;
        }
        boolean ok = prod.buyProduct(quantity);
        initialize();

        BoughtProduct bp = new BoughtProduct(prod, quantity);
        if(ok)
            this.productRepository.addBoughtProducts(bp);
        ArrayList<Product> products = new ArrayList<>();

        for (BoughtProduct b : this.productRepository.getBoughtProducts())
        {
            products.add(b.getP());
        }

        ObservableList<Product> observableList = FXCollections.observableArrayList(products);
        shoppingCartListView.setItems(observableList);

        float totalAmount = 0;
        for(BoughtProduct bbp : this.productRepository.getBoughtProducts())
        {
            totalAmount = totalAmount + bbp.getP().getPrice()*bbp.getAmount();
        }
        totalPriceLabel.setText(String.valueOf(totalAmount));
    }

    @FXML
    void filterButtonHandler(ActionEvent event)
    {
        String substring = productNameTextField.getText();
        int lowerBound = Integer.parseInt(lowerBoundTextfield.getText());
        int upperBound = Integer.parseInt(upperBoundTextField.getText());

        ArrayList<String> filteredProducts = new ArrayList<>();
        this.productRepository.products.stream()
                .filter(product -> product.getName().contains(substring) &&
                        product.getPrice() >lowerBound && product.getPrice() <upperBound)
                .forEach(p->{
                    String prod = p.getId() + " " + p.getBrand() + " " + p.getName() + " " + p.getPrice();
                    if(p.getQuantity() == 0)
                        prod = prod + "Out of stock";
                    filteredProducts.add(prod);
                });
        ObservableList<String> observableList = FXCollections.observableArrayList(filteredProducts);
        productListView.setItems(observableList);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
