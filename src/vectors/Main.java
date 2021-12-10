package vectors;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<IVector> vector2DList = new ArrayList<>(){{
            add(new Vector2D(1., 2.));
            add(new Vector2D(3., 4.));
            add(new Vector2D(5., 6.));
        }
        };

        List<Polar2DAdapter> adapters = createAdapters(vector2DList);
        List<Vector3DDecorator> decorators = createDecorators(vector2DList);

        printAll(adapters);

        cdotAll(adapters);

        crossAll(decorators);


//        System.out.print("Iloczyn wektorowy - inheritence\n\n");
//
//        List<Vector3DInheritance> vector3DList = new ArrayList<>();
//        for (IVector vector:vector2DList){
//            double[] comp = vector.getComponents();
//            vector3DList.add(new Vector3DInheritance(comp[0], comp[1], 0));
//        }
//        crossAllInheritence(vector3DList);

    }



    private static void printAll(List<Polar2DAdapter> adapters){
        for(int i = 0; i < adapters.size(); i++){
            System.out.println("| Wektor " + (i+1) + " |");
            System.out.format("Moduł wektora %.2f\n",adapters.get(i).abs());
            double[] comp = adapters.get(i).getComponents();
            System.out.format("Wsp. kartezjańskie: (0.00, 0.00), (%.2f, %.2f)\n", comp[0], comp[1]);
            System.out.format("Wsp. biegunowe: %.2f, %.2f\u00B0\n", adapters.get(i).abs(), adapters.get(i).getAngle());
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }


    private static List<Vector3DDecorator> createDecorators(List<IVector> vectors) {
        List<Vector3DDecorator> decoratorList = new ArrayList<>();
        for(IVector vector:vectors){
            decoratorList.add(new Vector3DDecorator(vector, 0));
        }
        return decoratorList;
    }
    private static List<Polar2DAdapter> createAdapters(List<IVector> vectors){
        List<Polar2DAdapter> adapterList = new ArrayList<>();
        for(IVector vector:vectors){
            adapterList.add(new Polar2DAdapter(vector));
        }
        return adapterList;
    }

    private static void cdotAll(List<Polar2DAdapter> adapters){
        for(Polar2DAdapter adapter: adapters){
            List<Polar2DAdapter> cdotList = adapters.stream().skip(adapters.indexOf(adapter)+1).collect(Collectors.toList());
            for(Polar2DAdapter cdotElem : cdotList){
                System.out.println("Iloczyn skalarny wektorów " + (adapters.indexOf(adapter) + 1) + " i " + (adapters.indexOf(cdotElem) + 1));
                System.out.println(adapter.cdot(cdotElem));
            }
        }
        System.out.print("\n\n");
    }

    private static void crossAll(List<Vector3DDecorator> decorators) {
        for(Vector3DDecorator decorator: decorators){
            List<Vector3DDecorator> crossList = new ArrayList<>(decorators);
            crossList.remove(decorator);
            for(Vector3DDecorator crossElem : crossList){
                System.out.println("Iloczyn wektorowy wektorów " + (decorators.indexOf(decorator) + 1) + " i " + (decorators.indexOf(crossElem) + 1));
                Vector3DDecorator vectorRes = decorator.cross(crossElem);
                double[] comp = vectorRes.getComponents();
                System.out.format("[%.2f, %.2f, %.2f]\n", comp[0], comp[1], comp[2]);
            }
        }
        System.out.print("\n\n");
    }

    private static void crossAllInheritence(List<Vector3DInheritance> vectors){
        for(Vector3DInheritance vector:vectors){
            List<Vector3DInheritance> crossList = new ArrayList<>(vectors);
            crossList.remove(vector);
            for(Vector3DInheritance vector2:crossList){
                System.out.println("Iloczyn wektorowy wektorów " + (vectors.indexOf(vector) + 1) + " i " + (vectors.indexOf(vector2) + 1));
                Vector3DInheritance vectorRes = vector.cross(vector2);
                double[] comp = vectorRes.getComponents();
                System.out.format("[%.2f, %.2f, %.2f]\n", comp[0], comp[1], comp[2]);
            }
        }
        System.out.print("\n\n");

    }
}
