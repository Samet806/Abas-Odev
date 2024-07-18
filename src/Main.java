import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Object[][] orders = {
                {1000, 2000, 12, 100.51},
                {1000, 2001, 31, 200.00},
                {1000, 2002, 22, 150.86},
                {1000, 2003, 41, 250.00},
                {1000, 2004, 55, 244.00},
                {1001, 2001, 88, 44.531},
                {1001, 2002, 121, 88.11},
                {1001, 2004, 74, 211.00},
                {1001, 2002, 14, 88.11},
                {1002, 2003, 2, 12.10},
                {1002, 2004, 3, 22.30},
                {1002, 2003, 8, 12.10},
                {1002, 2002, 16, 94.00},
                {1002, 2005, 9, 44.10},
                {1002, 2006, 19, 90.00}
        };
        // a- Toplam tutarı hesaplama
        double total_price = 0;
        for (Object[] order : orders) {

            int quantity = (int) order[2];
            double unitPrice = (double) order[3];
            total_price += quantity * unitPrice;
        }
        System.out.println("Toplam Tutar: " + total_price +"\n");


        // b-ortalama fiyat
        double totalPrice = 0;
        int itemCount = 0;
        for (Object[] order : orders) {
            double unitPrice = (double) order[3];
            totalPrice += unitPrice;
            itemCount++;
        }
        double averagePrice = totalPrice / itemCount;
        System.out.println("Ortalama Fiyat: " + averagePrice + " TL\n");


        // c- Mal bazlı toplam fiyat ve miktarı hesaplama
        Map<Integer, Double> totalPrices = new HashMap<>();
        Map<Integer, Integer> itemCounts = new HashMap<>();

        for (Object[] order : orders) {
            int itemId = (int) order[1];
            double unitPrice = (double) order[3];

            totalPrices.put(itemId, totalPrices.getOrDefault(itemId, 0.0) + unitPrice);
            itemCounts.put(itemId, itemCounts.getOrDefault(itemId, 0) + 1);
        }


        for (Map.Entry<Integer, Double> entry : totalPrices.entrySet()) {
            int itemId = entry.getKey();
            double avrgPrice = entry.getValue() / itemCounts.get(itemId);
            System.out.println("Mal Numarası: " + itemId + " Ortalama Fiyat: " + avrgPrice + " TL");
        }

       System.out.println("\n");




        // d- Mal bazlı siparişlerdeki miktarları hesaplama
        Map<Integer, Map<Integer, Integer>> itemOrders = new HashMap<>();

        for (Object[] order : orders) {
            int orderId = (int) order[0];
            int itemId = (int) order[1];
            int quantity = (int) order[2];

            itemOrders
                    .computeIfAbsent(itemId, k -> new HashMap<>())
                    .merge(orderId, quantity, Integer::sum);
        }


        for (Map.Entry<Integer, Map<Integer, Integer>> entry : itemOrders.entrySet()) {
            int itemId = entry.getKey();
            System.out.println("Mal Numarası: " + itemId);
            for (Map.Entry<Integer, Integer> orderEntry : entry.getValue().entrySet()) {
                System.out.println("Sipariş : " + orderEntry.getKey() + " Miktar: " + orderEntry.getValue());
            }
        }
    }
}
