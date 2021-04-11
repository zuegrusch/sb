public class Provider implements Runnable {
    Storage storage;

    Provider(Storage storage) {
        this.storage=storage;
    }

    @Override
    public void run() {
        //Поставщик за раз может поставить 1 или 2 единицы товара.
        int count = (int) ( Math.random() * 2 + 1);
        storage.addProduct(count);
    }
}
