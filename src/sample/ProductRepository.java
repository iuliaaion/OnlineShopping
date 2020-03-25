package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class ProductRepository extends Observable {

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();

    public ArrayList<BoughtProduct> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ArrayList<BoughtProduct> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    void addBoughtProducts(BoughtProduct bp)
    {
        this.boughtProducts.add(bp);
        setChanged();
        notifyObservers();
    }

    public ProductRepository() { }

    public void addProduct(Product p)
    {
        this.products.add(p);
    }

    public void addFromFile(String filename)
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) !=null )
            {
                String[] elems = line.split("[,]");
                if(elems.length < 5)
                    continue;
                Product p = new Product(Integer.parseInt(elems[0].strip()),
                        elems[1].strip(), elems[2].strip(), Float.parseFloat(elems[3].strip()),
                        Integer.parseInt(elems[4].strip()));
                addProduct(p);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(br != null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the file" + e);
                }
            }
        }
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

    public void setProducts(ArrayList<Product> products)
    {
        this.products = products;
    }
}
