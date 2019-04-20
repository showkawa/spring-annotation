import com.write.blist.BrianArrayList;

import java.util.ArrayList;

public class BASEIC_LIST_TEST {

    public static void main(String[] args) {
        BrianArrayList<String> arrayList = new BrianArrayList(1);
        arrayList.add("过桥米线");
        arrayList.add("过桥拉面");
        arrayList.add("火锅");
        arrayList.add("烤鱼");
       // arrayList.remove(1);
       // arrayList.remove("过桥米线");
        arrayList.add(4,"兰州拉面");
        for (int i = 0; i <arrayList.size() ; i++) {
            System.out.println(arrayList.get(i));
        }

       System.out.println(12 << 1);

    }
}
