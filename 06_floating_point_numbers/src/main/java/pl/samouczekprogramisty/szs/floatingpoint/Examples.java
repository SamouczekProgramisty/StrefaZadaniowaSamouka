package pl.samouczekprogramisty.szs.floatingpoint;

import java.math.BigDecimal;

public class Examples {

    public static void main(String[] args) {
        System.out.println(String.format("%.17f" , 0.1F));
        System.out.println(String.format("%.17f" , 0.2F));
        System.out.println(String.format("%.17f" , 0.3F));

        System.out.println(new BigDecimal("0.1"));
        System.out.println(new BigDecimal(0.1F));

        int money = 12568;

        System.out.println("zlotych: " + money / 100);
        System.out.println("groszy: " + money % 100);
    }

}
