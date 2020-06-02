import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ItemGateway {

    public List<Integer> getItems(List<Integer> items) {

        List<CompletableFuture<List<Integer>>> futures = items.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> getItem(item)))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> getItem(Integer item) {
        try {
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