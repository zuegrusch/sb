public class Storage {
    private final int MIN_NUM = 10; //минимальное число товаров
    private int productCount;

    public int getProductCount() {
        return productCount;
    }

    public Storage(int initialCount){
        this.productCount = initialCount;
        System.out.println("Начальное число товаров на складе: " + productCount);
    }

    public synchronized void buyProduct(int productCount) {
        //Если продуктов меньше порогового значения - добавляем поставщика
        if (this.productCount < MIN_NUM) {
            new Thread(new Provider(this)).start();
        }
        this.productCount -= productCount;
        System.out.println(productCount + " товар/а забрали. На складе осталось " + this.productCount);
    }

    public synchronized void addProduct(int productCount) {
        this.productCount += productCount;
        System.out.println("Поставщик добавил " + productCount + " товара/ов. Всего на складе " + this.productCount);
    }
}
