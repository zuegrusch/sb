public class Buyer implements Runnable {
    Storage storage;

    public Buyer(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        //Каждый покупатель берет только одну единицу товара.
        storage.buyProduct(1);
    }
}
