package com.hnq.practice.strategypattern.bean;

import com.hnq.practice.common.Constants;
import com.hnq.practice.strategypattern.service.IPrintService;
import com.hnq.practice.strategypattern.service.impl.CircleImpl;
import com.hnq.practice.strategypattern.service.impl.RectangleImpl;
import com.hnq.practice.strategypattern.service.impl.SquareImpl;

/**
 * @author henengqiang
 * @date 2018/11/20
 */
public class Printer {

    /**
     * 持有的具体策略对象
     */
    private IPrintService printService;

    public void printShape(String shape) {
        switch (shape) {
            case Constants.CIRCLE:
                printService = new CircleImpl();
                printService.printShape();
                break;
            case Constants.RECTANGLE:
                printService = new RectangleImpl();
                printService.printShape();
                break;
            case Constants.SQUARE:
                printService = new SquareImpl();
                printService.printShape();
                break;
            default: break;
        }
    }

}
