package control;

import entities.JsonEntity;
import jdk.internal.util.xml.impl.Input;

public class InputModel {
    private double[] supply;
    private double[] demand;
    double[] profitArr;
    double[] sells;
    double[] buys;
    double[] routeArr;

    public InputModel(JsonEntity json) {
        double[] suppliers = new double[]{json.getSupplier1(), json.getSupplier2(), json.getSupplier3(), json.getSupplier4()};
        double[] demands = new double[]{json.getRecipient1(), json.getRecipient2()};
        double[] routeArrs = new double[]{json.getSup1rec1cost(), json.getSup1rec2cost(), json.getSup2rec1cost(),
                json.getSup2rec2cost(), json.getSup3rec1cost()
        , json.getSup3rec2cost(), json.getSup4rec1cost(), json.getSup4rec2cost()};
        double[] sellsArr = new double[]{json.getSupplier1fee(), json.getSupplier2fee(), json.getSupplier3fee(),
                json.getSupplier4fee()};
        double[] buysArr = new double[]{json.getRecipient1fee(), json.getRecipient2fee()};

        this.supply = suppliers;
        this.demand = demands;
        this.sells = sellsArr;
        this.buys = buysArr;
        this.routeArr = routeArrs;

        this.profitArr = new double[]{
                sells[0] - routeArr[0] - buys[0],
                sells[1] - routeArr[1] - buys[0],
                sells[2] - routeArr[2] - buys[0],
                sells[3] - routeArr[3] - buys[0],
                sells[0] - routeArr[4] - buys[1],
                sells[1] - routeArr[5] - buys[1],
                sells[2] - routeArr[6] - buys[1],
                sells[3] - routeArr[7] - buys[1],
        };
    }

    public InputModel(double[] supply, double[] demand, double[] sells, double[] buys,double[] routeArr) {
        this.supply = supply;
        this.demand = demand;
        this.sells = sells;
        this.buys = buys;
        this.routeArr = routeArr;

        this.profitArr = new double[]{
                sells[0] - routeArr[0] - buys[0],
                sells[1] - routeArr[1] - buys[0],
                sells[2] - routeArr[2] - buys[0],
                sells[0] - routeArr[3] - buys[1],
                sells[1] - routeArr[4] - buys[1],
                sells[2] - routeArr[5] - buys[1],
                sells[0] - routeArr[6] - buys[2],
                sells[1] - routeArr[7] - buys[2],
                sells[2] - routeArr[8] - buys[2]
        };
    }

    public double[] getSupply() {
        return supply;
    }

    public double[] getDemand() {
        return demand;
    }

    public double[] getProfitArr() {
        return profitArr;
    }

}