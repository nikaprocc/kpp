package com.labs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.*;

public class OrderManager {
    private static final String ORDERS_FILE = "orders.dat";
    private static final String JSON_FILE = "orders.json";
    private static final String YAML_FILE = "orders.yaml";

    // IO Streams handling
    public void writeOrderToFile(Order order) throws IOException {
        // Buffered stream for client
        try (BufferedWriter clientWriter = new BufferedWriter(new FileWriter("clients.txt", true))) {
            clientWriter.write(order.getClient().toString());
            clientWriter.newLine();
        }

        // Unbuffered stream for dishes
        try (FileWriter dishWriter = new FileWriter("dishes.txt", true)) {
            for (Dish dish : order.getDishes()) {
                dishWriter.write(dish.toString() + "\n");
            }
        }
    }

    // Native Java Serialization
    public void serializeOrder(Order order) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE))) {
            oos.writeObject(order);
        }
    }

    public Order deserializeOrder() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDERS_FILE))) {
            return (Order) ois.readObject();
        }
    }

    // JSON Serialization using GSON
    public void serializeToJson(Order order) throws IOException {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(order);
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            writer.write(json);
        }
    }

    // JSON Deserialization
    public Order deserializeFromJson() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(JSON_FILE)) {
            return gson.fromJson(reader, Order.class);
        }
    }

    // YAML Serialization
    public void serializeToYaml(Order order) {
        Yaml yaml = new Yaml();
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderNumber", order.getOrderNumber());

        // Skip client info if more than 3 dishes
        if (order.getDishes().size() <= 3) {
            orderMap.put("client", order.getClient());
        }

        try (FileWriter writer = new FileWriter(YAML_FILE)) {
            yaml.dump(orderMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // YAML Deserialization
    public Order deserializeFromYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(YAML_FILE)) {
            Map<String, Object> orderMap = yaml.load(reader);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(orderMap, Order.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

