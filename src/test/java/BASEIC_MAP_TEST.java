import com.write.bmap.BrianHashMap;

public class BASEIC_MAP_TEST {

    public static void main(String[] args) {

        BrianHashMap<String, String> map = new BrianHashMap<>();
        map.put("1","100");
        map.put("2","200");
        map.put("3","300");
        map.put("4","400");
        map.put("5","500");

        map.put("11","1100");
        map.put("12","1200");
        map.put("13","1300");
        map.put("14","1400");
        map.put("15","1500");
        map.put("16","1600");
        map.put("16","16AA");

        map.put("22","2200");
        map.put("22","22AA");
        map.put("23","2300");
        map.put("24","2400");

        map.put("11","11AA");
        map.put("25","11AA");
        map.put("26","11AA");
        map.put("27","11AA");
        map.put("28","11AA");
        map.put("29","11AA");
        map.put("30","11AA");
        map.put("31","11AA");
        map.put("32","11AA");
       /* map.put("33","11AA");
        map.put("34","11AA");
        map.put("35","11AA");
        map.put("36","11AA");
        map.put("37","11AA");
        map.put("38","11AA");*/

        map.print();
        System.out.println(map.get("11"));


        int a =10086;
        System.out.println(~a);

    }


}
