package com.labs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення тестових даних
        Client client = new Client("John Doe", "+380503456789");
        List<Dish> dishes = Arrays.asList(
                new Dish("Pizza", 150.0),
                new Dish("Sushi", 80.0),
                new Dish("Dessert", 60.0)
        );
        Order order = new Order(1, client, dishes);

        OrderManager manager = new OrderManager();

        try {
            // Серіалізація замовлення у файл
            manager.writeOrderToFile(order);
            manager.serializeOrder(order);
            manager.serializeToJson(order);
            manager.serializeToYaml(order);

            System.out.println("All operations completed successfully!");

            // Десеріалізація замовлення з файлу
            Order deserializedOrder = manager.deserializeOrder();
            System.out.println("Deserialized Order: " + deserializedOrder.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // Deserialize and display JSON
            Order jsonOrder = manager.deserializeFromJson();
            System.out.println("Deserialized JSON Order: " + jsonOrder);

            // Deserialize and display YAML
            Order yamlOrder = manager.deserializeFromYaml();
            System.out.println("Deserialized YAML Order: " + yamlOrder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
