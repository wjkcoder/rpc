package cn.phorcys.rpc.register.loadbanace;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomBalance implements LoadBalance {
    @Override
    public  String pick(List<String> children) {
        if (Objects.isNull(children)||children.size()==0) {
            throw new RuntimeException("list null");
        }
        int size = children.size();
        Random random = new Random();
        return children.get(random.nextInt(size));
    }
}
