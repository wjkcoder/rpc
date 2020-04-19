package cn.phorcys.rpc.register.loadbanace;

import java.util.List;

public interface LoadBalance {
     String pick(List<String> children);
}
