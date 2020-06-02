import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ItemGateway {

    public List<Integer> getItems(List<Integer> items) {

        // Create a CompletableFuture for each item being requested
        // and start each request asynchronously
        List<CompletableFuture<List<Integer>>> futures = items.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> getItem(item)))
                .collect(Collectors.toList());

        // Wait for ALL CompletableFutures to finish then return
        return futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> getItem(Integer item) {
        try {
            // Simulate a call to some external resource
            // by sleeping for a second
            System.out.println("Start: " + item);
            Thread.sleep(1000);
            System.out.println("End: " + item);
            return Lists.newArrayList(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}