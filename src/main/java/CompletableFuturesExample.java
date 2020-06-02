import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class CompletableFuturesExample {

    public static void main(String[] args) {

        List<Integer> items = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ItemGateway itemGateway = new ItemGateway();

        long startTime = System.currentTimeMillis();

        List<Integer> results = itemGateway.getItems(items);

        System.out.println(String.format("Done in %s ms", (System.currentTimeMillis() - startTime)));

        System.out.println("Results: " + results.stream().map(Object::toString).collect(Collectors.joining(",")));

    }

}
