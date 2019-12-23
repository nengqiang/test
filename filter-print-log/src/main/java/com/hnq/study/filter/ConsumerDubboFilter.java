//package com.hnq.study.filter;
//
//import com.alibaba.dubbo.rpc.*;
//import com.alibaba.dubbo.rpc.support.RpcUtils;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ArrayUtils;
//
//import java.util.Collection;
//import java.util.Map;
//
//@Slf4j
//public class ConsumerDubboFilter implements Filter {
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        log.info("Dobbo consumer method {} with arguments {}", RpcUtils.getMethodName(invocation),
//                ArrayUtils.toString(RpcUtils.getArguments(invocation)));
//        Long start = System.currentTimeMillis();
//
//        Result result = invoker.invoke(invocation);
//
//        log.info("Dobbo consumer result of method {} - {}", RpcUtils.getMethodName(invocation),
//                getValue(result.getValue()));
////        log.info("it took {}ms to execute {}", System.currentTimeMillis() - start,
////                RpcUtils.getMethodName(invocation));
//
//        return result;
//    }
//
//    private Object getValue(Object value){
//        if(value instanceof Collection){
//            int size = ((Collection)value).size();
//            return "result size is "+size;
//        }
//        if(value instanceof Map){
//            int size = ((Map)value).size();
//            return "result size is "+size;
//        }
//        return JSON.toJSONString(value);
//
//
//    }
//}
