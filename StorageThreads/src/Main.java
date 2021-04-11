public class Main {
    public static void main(String[] args) {
        //Изначальное число товаров поставим от 10 до 20
        int initialProductCount = (int) (Math.random() * 11 + 10);
        Storage storage = new Storage(initialProductCount);
        Buyer buyer = new Buyer(storage);

        //Генерация покупателей идет каждую секунду в течении 30
        int count = 30;
        while(count != 0){
            try {
                //Количество потоков покупателей от 3 до 9
                int buyerCount = (int) (Math.random() * 7 + 3);
                System.out.println();
                System.out.println("Пришло "+ buyerCount + " покупателя/ей");
                for(int i=0; i < buyerCount; i++){
                    new Thread(buyer).start();
                }
                count--;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
